function solution(s) {
  const stack = [];

  for (let char of s) {
    if (stack.length > 0 && stack[stack.length - 1] === char) {
      // 스택 top과 현재 문자가 같으면 제거 (pop)
      stack.pop();
    } else {
      // 다르면 스택에 추가 (push)
      stack.push(char);
    }
  }

  // 최종적으로 스택이 비어있으면 모두 제거 가능 -> 1, 아니면 0
  return stack.length === 0 ? 1 : 0;
}
