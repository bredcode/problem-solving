package org.example.zb.스피드러너스트리머의궁극의도전;

import java.util.*;

class Solution {
    private int minTime;
    private int N;
    private int[] boss;
    private int[] skill;

    // 보스 처치 순서에 따른 최소 시간을 구하는 함수
    public int solution(int _N, int[] _boss, int[] _skill) {
        N = _N;
        boss = _boss.clone();
        skill = _skill.clone();
        minTime = Integer.MAX_VALUE;

        // 가능한 보스의 모든 순서를 생성하여 계산
        boolean[] visited = new boolean[N];
        List<Integer> order = new ArrayList<>();
        battle(visited, order, 0, 0);

        return minTime;
    }

    // 백트래킹을 이용해 모든 순열을 탐색하는 함수
    private void battle(boolean[] visited, List<Integer> order, int currentSkill, int currentTime) {
        // 이미 누적된 시간이 최소 시간보다 크면 탐색 중지 (프루닝, 휴리스틱)
        if (currentTime > minTime) {
            return;
        }

        // 모든 보스를 선택한 경우 최소 시간 갱신
        if (order.size() == N) {
            minTime = Math.min(minTime, currentTime);
            return;
        }

        for (int i = 0; i < N; i++) {
            if (!visited[i]) {
                visited[i] = true;
                order.add(i);

                // 현재 보스를 물리치는 데 걸리는 시간 계산
                int battleTime = Math.max(0, boss[i] - currentSkill);

                // 재귀 호출로 다음 보스 탐색
                battle(visited, order, skill[i], currentTime + battleTime);

                // 원래 상태로 복구
                order.remove(order.size() - 1);
                visited[i] = false;
            }
        }
    }
}
