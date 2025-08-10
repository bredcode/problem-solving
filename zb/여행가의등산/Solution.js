class MinHeap {
    constructor() {
        this.heap = [null]; // 첫 번째 인덱스를 비워둠
    }

    push(item) {
        this.heap.push(item);
        let index = this.heap.length - 1;
        let parentIndex = Math.floor(index / 2);

        while (parentIndex > 0 && this.heap[parentIndex].value > this.heap[index].value) {
            [this.heap[parentIndex], this.heap[index]] = [this.heap[index], this.heap[parentIndex]];
            index = parentIndex;
            parentIndex = Math.floor(index / 2);
        }
    }

    pop() {
        if (this.heap.length === 2) return this.heap.pop();
        const root = this.heap[1];
        this.heap[1] = this.heap.pop();

        let index = 1;
        let leftChildIndex = 2 * index;
        let rightChildIndex = 2 * index + 1;

        while (
            this.heap[leftChildIndex] &&
            (this.heap[index].value > this.heap[leftChildIndex].value ||
                (this.heap[rightChildIndex] && this.heap[index].value > this.heap[rightChildIndex].value))
            ) {
            let smallerChildIndex =
                rightChildIndex < this.heap.length && this.heap[rightChildIndex].value < this.heap[leftChildIndex].value
                    ? rightChildIndex
                    : leftChildIndex;

            [this.heap[index], this.heap[smallerChildIndex]] = [this.heap[smallerChildIndex], this.heap[index]];

            index = smallerChildIndex;
            leftChildIndex = 2 * index;
            rightChildIndex = 2 * index + 1;
        }

        return root;
    }

    size() {
        return this.heap.length - 1; // 첫 번째 인덱스를 제외한 크기
    }
}

// 상, 하, 좌, 우
const dy = [-1, 1, 0, 0];
const dx = [0, 0, -1, 1];
let dist;

// MinHeap을 사용한 다익스트라 알고리즘
function dijkstra(N, arr) {
    const pq = new MinHeap();
    pq.push({ y: 0, x: 0, value: 0 }); // 시작 지점 (0, 0)에서 체력 소모 0으로 시작

    dist[0][0] = 0;

    while (pq.size() > 0) {
        const { y, x, value: cost } = pq.pop();

        // 목표 지점 (N-1, N-1)에 도달하면 최소 체력 소모량 반환
        if (y === N - 1 && x === N - 1) {
            return cost;
        }

        // 상, 하, 좌, 우로 이동
        for (let i = 0; i < 4; i++) {
            const ny = y + dy[i];
            const nx = x + dx[i];

            // 범위를 벗어나는 경우는 무시
            if (ny < 0 || nx < 0 || ny >= N || nx >= N) continue;

            // 고도 차이에 따른 체력 소모량 계산
            const nextCost = cost + Math.abs(arr[ny][nx] - arr[y][x]);

            // 현재 경로가 더 적은 체력 소모량을 제공하는 경우
            if (nextCost < dist[ny][nx]) {
                dist[ny][nx] = nextCost;
                pq.push({ y: ny, x: nx, value: nextCost });
            }
        }
    }

    return dist[N - 1][N - 1]; // 목표 지점에 도달하는 최소 체력 소모량
}

function solution(N, arr) {
    dist = Array.from({ length: N }, () => Array(N).fill(Infinity));
    return dijkstra(N, arr);
}