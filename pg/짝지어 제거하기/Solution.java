import java.util.ArrayDeque;

class Solution {
  public int solution(String s) {
    ArrayDeque<Character> stack = new ArrayDeque<>();
    for (int i = 0; i < s.length(); i++) {
      char ch = s.charAt(i);
      if (!stack.isEmpty() && stack.peekLast() == ch) {
        stack.pollLast(); // pop
      } else {
        stack.offerLast(ch); // push
      }
    }
    return stack.isEmpty() ? 1 : 0;
  }
}
