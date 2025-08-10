package org.example.ps.zb.미용실;

import java.util.*;

class Node {
    public int s;
    public int e;

    public Node(int s, int e) {
        this.s = s;
        this.e = e;
    }
}

class Solution {
    public int solution(int N, int[][] reserved) {
        // 종료가 빠른 순 -> 시작이 빠른 순으로 정렬
        List<Node> arr = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            arr.add(new Node(reserved[i][0], reserved[i][1]));
        }

        arr.sort((o1, o2) -> {
            if (o1.e == o2.e) {
                return o1.s - o2.s;
            }
            return o1.e - o2.e;
        });

        int answer = 1; // 처음 예약자 수는 1명으로 시작
        int curIdx = 0; // 현재 예약자 인덱스

        for (int i = 1; i < N; i++) {
            // 현재 커트가 끝난 후 다음 예약자를 받을 수 있다면 카운트
            if (arr.get(curIdx).e <= arr.get(i).s) {
                curIdx = i;
                answer++;
            }
        }

        return answer;
    }
}
