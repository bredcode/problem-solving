package org.example.ps.zb.자신감점수;

import java.util.ArrayList;
import java.util.List;

class Solution {
    public int solution(int N, int[] _warriors) {
        int answer = 0;

        // i번째 워리어를 제거 예정
        for (int i = 0; i < N; i++) {
            // 일단 모든 워리어 추가
            List<Integer> warriors = new ArrayList<>();
            for (int w : _warriors) {
                warriors.add(w);
            }

            // i번째 워리어 제거
            warriors.remove(i);

            // 자신감 계산
            int count = confidence(N - 1, warriors);
            answer = Math.max(answer, count);
        }

        return answer;
    }

    public int confidence(int M, List<Integer> arr) {
        int cnt = 0; // 자신감 점수
        for (int i = 0; i < M; i++) {
            int left = i - 1; // 왼쪽 전사
            if (left == -1)
                left = M - 1;
            int right = i + 1; // 오른쪽 전사
            if (right == M)
                right = 0;
            // 자신감이 있는 상태인 전사인 경우
            if (arr.get(left) < arr.get(i) && arr.get(i) > arr.get(right))
                cnt += 1; // 카운트

        }
        return cnt;
    }
}
