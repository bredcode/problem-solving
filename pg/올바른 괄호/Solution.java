import java.util.ArrayDeque;

class Solution {
  public boolean solution(String s) {
    ArrayDeque<Character> stack = new ArrayDeque<>();
    for (int i = 0; i < s.length(); i++) {
      char ch = s.charAt(i);
      if (ch == '(') {
        stack.push(ch);
      } else if (ch == ')') {
        if (stack.isEmpty())
          return false;
        stack.pop();
      }
    }
    return stack.isEmpty();
  }
}