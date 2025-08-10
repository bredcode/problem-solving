package org.example.ps.manual;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

public class 요세푸스_문제 {
  public static void main(String[] args) {
    System.out.println(new Solution().solution(6, 2));
  }
}

class Solution {
  public List<Integer> solution(int N, int K) {
    ArrayDeque<Integer> queue = new ArrayDeque<>();
    for (int i = 1; i <= N; i++) {
      queue.add(i);
    }

    List<Integer> ret = new ArrayList<>();

    while (!queue.isEmpty()) {
      for (int i = 1; i < K; i++) {
        // 맨 앞의 원소를 빼서 끝에 넣음
        queue.addLast(queue.removeFirst());
      }
      // K번째 원소를 결과에 넣고 제거
      ret.add(queue.removeFirst());
    }

    return ret;
  }
}
