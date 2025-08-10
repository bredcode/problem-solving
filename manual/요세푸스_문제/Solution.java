import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

public class Solution {
  public static void main(String[] args) {
    int N = 7;
    int K = 3;
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

    System.out.println(ret);
  }
}