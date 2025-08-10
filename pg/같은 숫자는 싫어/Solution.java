import java.util.ArrayDeque;
import java.util.Deque;

public class Solution {
    public static int[] solution(int[] arr) {
        Deque<Integer> stack = new ArrayDeque<>();

        for (int value : arr) {
            if (stack.isEmpty()) {
                stack.push(value);
            } else {
                int top = stack.peek();
                if (top != value) {
                    stack.push(value);
                }
                // 같으면 넘어감
            }
        }

        // ArrayDeque는 LIFO(스택) 구조라 역순이므로, 배열 변환 시 뒤집어야 함
        int[] result = new int[stack.size()];
        for (int i = stack.size() - 1; i >= 0; i--) {
            result[i] = stack.pop();
        }

        return result;
    }

    public static void main(String[] args) {
        int[] arr = { 1, 1, 3, 3, 0, 1, 1 };
        int[] result = solution(arr);

        for (int val : result) {
            System.out.print(val + " ");
        }
        // 출력: 1 3 0 1
    }}

    

    

    
    
    
