let N = "126648";
let K = 3;

const queue = [];
for (const ch of N) queue.push(ch);

while (K--) {
  queue.shift();
  let a = queue.shift();
  queue.push(a);
}
console.log(queue[0]);
