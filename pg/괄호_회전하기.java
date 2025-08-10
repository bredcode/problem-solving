package org.example.ps.pg;

import java.util.ArrayDeque;
import java.util.List;

public class 괄호_회전하기 {
  public static void main(String[] args) {
    List<String> tcList = List.of("[](){}", "}]()[{", "[)(]", "}}}");

    for (String tc : tcList) {
      System.out.println(new Solution().solution(tc));
    }
  }
}

class Solution {
    public int solution(String s) {
        int answer = 0;
        ArrayDeque<Character> queue = new ArrayDeque<>();
        
        // 문자열을 큐에 하나씩 넣기
        for (char c : s.toCharArray()) {
            queue.offer(c);
        }

        // s의 길이만큼 회전시켜보며, 올바른 괄호 문자열인지 확인
        for (int i = 0; i < s.length(); i++) {
            if (isValid(queue)) {
                answer++;
            }
            
            // 큐에서 첫 번째 요소를 꺼내서 뒤에 넣기
            queue.offer(queue.poll());
        }
        return answer;
    }

    // 큐를 받아서 올바른 괄호 문자열인지 확인하는 메서드
    public boolean isValid(ArrayDeque<Character> queue) {
        ArrayDeque<Character> stack = new ArrayDeque<>();
        
        for (char ch : queue) {
            // 여는 괄호는 스택에 넣음
            if (ch == '(' || ch == '[' || ch == '{') {
                stack.push(ch);
            } 
            // 닫는 괄호는 스택에서 여는 괄호와 짝이 맞는지 확인
            else if (ch == ')' || ch == ']' || ch == '}') {
                if (stack.isEmpty()) {
                    return false; // 짝이 맞지 않으면 false
                }
                char top = stack.peekFirst();
                if (top == '(' && ch == ')') {
                  stack.pop();
                } else if (top == '[' && ch == ']') {
                  stack.pop();
                } else if (top == '{' && ch == '}') {
                  stack.pop();
                } else {
                  return false;
                }
            }
        }
        
        return stack.isEmpty(); // 스택이 비어 있으면 올바른 괄호 문자열
    }
}
