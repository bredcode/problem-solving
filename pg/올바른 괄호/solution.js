function solution(s) {
  const stack = [];

  for (let char of s) {
    if (char === "(") {
      stack.push(char); // 여는 괄호는 스택에 추가
    } else {
      // 닫는 괄호인 경우
      if (stack.length === 0) {
        // 닫는 괄호가 왔는데 여는 괄호가 없으면 false
        return false;
      }
      stack.pop(); // 한 쌍 완성하여 스택에서 제거
    }
  }

  // 모든 괄호가 짝지어졌으면 스택이 비어있음
  return stack.length === 0;
}
