import java.util.ArrayDeque;
import java.util.List;

class Solution {
  public int solution(int x, int y, int n) {
    boolean[] visit = new boolean[y + 1];
    Queue<int[]> queue = new ArrayDeque();
    queue.offer(new int[] { x, 0 });
    visit[x] = true;

    while (!queue.isEmpty()) {
      int[] curInfo = queue.poll();
      int cur = curInfo[0];
      int cnt = curInfo[1];

      if (cur == y)
        return cnt;

      int[] nextArr = { cur + n, cur * 2, cur * 3 };
      for (int next : nextArr) {
        if (next <= y && !visit[next]) {
          visit[next] = true;
          queue.offer(new int[] { next, cnt + 1 });
        }
      }
    }
    return -1;
  }
}