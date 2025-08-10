let N = 6;
let K = 2;

let queue = []; // 배열을 queue로 사용
for (let i = 1; i <= N; i++) {
  queue.push(i);
}

let ret = []; // 요세푸스 순열 결과

while (queue.length) {
  for (let i = 1; i < K; i++) {
    // 맨 앞의 원소를 빼서 다시 넣음 (회전)
    queue.push(queue.shift());
  }
  // K번째 원소를 결과에 넣고 제거
  ret.push(queue.shift());
}

console.log(ret);
