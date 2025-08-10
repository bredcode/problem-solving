import java.util.ArrayDeque;
import java.util.List;

public class Solution {
  public static void main(String[] args) {
    String N = "126648";
    int K = 3;

    ArrayDeque<Character> queue = new ArrayDeque<>();
    for (char ch : N.toCharArray())
      queue.offerLast(ch);

    while (K-- > 0) {
      queue.pollFirst();
      Character a = queue.pollFirst();
      if (a != null)
        queue.offerLast(a);
    }

    // front 출력
    System.out.println(queue.peekFirst());
  }
}
