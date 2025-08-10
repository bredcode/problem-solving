const dy = [-1, 1, 0, 0];
const dx = [0, 0, -1, 1];

class Node {
    constructor(y, x, dist = 0) {
        this.y = y;
        this.x = x;
        this.dist = dist;
    }
}

function bfs(N, board) {
    // 최단 거리 맵 초기화
    const graph = Array.from({ length: N }, () => Array(N).fill(-1));
    const queue = [];

    // 그래플러 위치 찾기
    for (let i = 0; i < N; i++) {
        for (let j = 0; j < N; j++) {
            if (board[i][j] === "G") {
                queue.push(new Node(i, j));
                graph[i][j] = 0;
            }
        }
    }

    // BFS 수행
    while (queue.length > 0) {
        const cur = queue.shift();
        const y = cur.y;
        const x = cur.x;

        for (let i = 0; i < 4; i++) {
            const ny = y + dy[i];
            const nx = x + dx[i];

            // 공간을 벗어난 경우 무시
            if (ny < 0 || ny >= N || nx < 0 || nx >= N) continue;

            // 처음 방문하는 경우에만 거리 갱신
            if (graph[ny][nx] === -1 && board[ny][nx] === "B") {
                graph[ny][nx] = graph[y][x] + 1;
                queue.push(new Node(ny, nx));
            }
        }
    }

    return graph;
}

function solution(N, board) {
    // BFS를 통해 각 빈 칸의 그래플러와의 최단 거리 계산
    const graph = bfs(N, board);

    // 가장 가까운 그래플러와의 거리가 최대인 위치 찾기
    const maxList = [];
    for (let i = 0; i < N; i++) {
        for (let j = 0; j < N; j++) {
            if (board[i][j] === "B") {
                maxList.push(new Node(i, j, graph[i][j]));
            }
        }
    }

    // 거리 기준 내림차순, 행과 열 기준 오름차순 정렬
    maxList.sort((a, b) => {
        if (a.dist === b.dist) {
            if (a.y === b.y) return a.x - b.x; // 행이 같으면 열 기준 정렬
            return a.y - b.y; // 거리가 같으면 행 기준 오름차순
        }
        return b.dist - a.dist; // 거리 기준 내림차순 정렬
    });

    // 정답 도출
    return [maxList[0].y, maxList[0].x];
}
