package org.example.ps.zb.회전방어;

class Solution {
    // 전역 변수로 N과 enemies 선언
    public int N;
    public int[][] enemies;

    // 현재 중복순열에 포함된 원소의 인덱스(index)
    public int[] selected = new int[9];
    public int answer = (int) 1e9;

    // DFS를 활용하여 중복 순열을 탐색하는 함수
    public void dfs(int idx) {
        // 모든 중복 순열을 확인하는 부분
        if (idx == N) {
            // 3개의 선택지 중에서 N초에 걸쳐서 하나씩 선택한 상황
            int view = 0; // 처음에 상(위쪽) 방향을 보고 있음
            int result = 0;
            for (int i = 0; i < N; i++) { // 1초부터 N초까지 확인
                // 선택지에 따라서 주인공의 방향 변경
                if (selected[i] == 0) {
                    // 가만히 있기
                }
                else if (selected[i] == 1) { // 좌회전
                    view = ((view - 1) + 4) % 4;
                }
                else if (selected[i] == 2) { // 우회전
                    view = (view + 1) % 4;
                }
                // 상(0), 우(1), 하(2), 좌(3)의 위치를 확인하며
                for (int j = 0; j < 4; j++) {
                    int power = enemies[j][i]; // 해당 위치의 적군의 공격력
                    // 앞에 존재하는 적은 공격력의 1배수
                    if (view == j) {
                        result += power;
                    }
                    // 오른쪽에 존재하는 적은 공격력의 2배수
                    else if ((view + 1) % 4 == j) {
                        result += power * 2;
                    }
                    // 뒤쪽에 존재하는 적은 공격력의 3배수
                    else if ((view + 2) % 4 == j) {
                        result += power * 3;
                    }
                    // 왼쪽에 존재하는 적은 공격력의 2배수
                    else {
                        result += power * 2;
                    }
                }
            }
            // 차감되는 점수의 합의 최솟값 계산
            answer = Math.min(answer, result);
            return;
        }

        // 0번, 1번, 2번의 선택지를 하나씩 확인하며
        for (int i = 0; i < 3; i++) {
            // 가만히 있기(0), 좌회전(1), 우회전(2)
            selected[idx] = i; // 현재 원소 선택
            dfs(idx + 1); // 재귀 함수 호출
            selected[idx] = -1; // 현재 원소 선택 취소
        }
    }

    // 적군이 등장하는 횟수(N)와 적군의 공격력 정보 배열(enemies)을 전역 변수로 설정
    public int solution(int N, int[][] enemies) {
        // 전역 변수 설정
        this.N = N;
        this.enemies = enemies;

        // DFS를 활용한 모든 경우의 수 탐색
        dfs(0);
        return answer;
    }
}
