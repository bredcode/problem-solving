let N = 6;
let K = 2;

let queue = []; // 배열을 queue로 사용
for (let i = 1; i <= N; i++) {
  queue.push(i);
}

let front = 0;
let ret = [];

while (front < queue.length) {
  for (let i = 1; i < K; i++) {
    // 앞의 값을 뒤에 push
    queue.push(queue[front++]);
  }
  // K번째 값을 꺼내 result에 넣기
  ret.push(queue[front++]);
}

console.log(ret); // [2, 4, 6, 3, 1, 5]
