function solution(arr) {
  const stack = [];

  for (let i = 0; i < arr.length; i++) {
    if (stack.length === 0) {
      stack.push(arr[i]);
    } else {
      // 스택 top과 현재 값 비교
      let top = stack[stack.length - 1];
      if (top !== arr[i]) {
        stack.push(arr[i]);
      }
      // 같다면 그냥 넘어감 (즉, 중복 제거)
    }
  }

  return stack;
}
