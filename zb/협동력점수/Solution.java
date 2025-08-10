package org.example.ps.zb.협동력점수;

import java.util.*;

class Solution {
    public int answer = (int) 1e9;
    public List<int[]> permutations = new ArrayList<>();

    // N명의 학생을 K개의 그룹에 배치하는 중복 순열을 미리 계산
    public void generatePermutations(int N, int K, int idx, int[] current) {
        // 중복순열이 만들어지면 저장
        if (idx == N) {
            permutations.add(current.clone());
            return;
        }
        for (int i = 0; i < K; i++) {
            current[idx] = i;
            generatePermutations(N, K, idx + 1, current);
        }
    }

    // 최종 풀이 함수
    public int solution(int N, int K, int[] arr) {
        int[] current = new int[N];
        generatePermutations(N, K, 0, current);

        for (int[] perm : permutations) {
            // 각 그룹 생성
            List<List<Integer>> groups = new ArrayList<>();
            for (int i = 0; i < K; i++) {
                groups.add(new ArrayList<>());
            }

            // 각 학생을 그룹에 배정
            for (int i = 0; i < N; i++) {
                groups.get(perm[i]).add(arr[i]);
            }

            // 1명도 배치되지 않은 그룹이 있는 경우 무시
            boolean isValid = true;
            for (int i = 0; i < K; i++) {
                if (groups.get(i).size() == 0) {
                    isValid = false;
                    break;
                }
            }
            if (!isValid) continue;

            // 그룹의 전투력을 계산
            int minScore = (int) 1e9; // 가장 전투력이 낮은 그룹
            int maxScore = (int) -1e9; // 가장 전투력이 높은 그룹
            for (int i = 0; i < K; i++) {
                int score = 0;
                for (int j = 0; j < groups.get(i).size(); j++) {
                    int power = groups.get(i).get(j);
                    score += groups.get(i).size() * power;
                }
                minScore = Math.min(minScore, score);
                maxScore = Math.max(maxScore, score);
            }

            // 전투력 차이의 최솟값 계산
            answer = Math.min(answer, Math.abs(minScore - maxScore));
        }

        return answer;
    }
}