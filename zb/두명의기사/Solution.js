class Node {
    constructor(x, y) {
        this.x = x;
        this.y = y;
    }
}

const enemyDx = [-1, 1, 0, 0];
const enemyDy = [0, 0, -1, 1];
const knightDx = [0, -2, -2, -1, -1, 1, 1, 2, 2];
const knightDy = [0, -1, 1, -2, 2, -2, 2, -1, 1];

let N;
let hero;

function bfs(newBoard) {
    const graph = Array.from({ length: N }, () => Array(N).fill(-1)); // 최단 거리 맵
    const q = [];

    // 적군 위치에서부터 BFS 시작
    for (let i = 0; i < N; i++) {
        for (let j = 0; j < N; j++) {
            if (newBoard[i][j] === "E") { // 적군인 경우
                q.push(new Node(i, j));
                graph[i][j] = 0;
            }
        }
    }

    while (q.length > 0) { // BFS 수행
        const cur = q.shift();
        const x = cur.x;
        const y = cur.y;

        // 주인공에게 도달하면 즉시 종료하고 그때의 최단 거리 반환
        if (x === hero.x && y === hero.y) {
            return graph[x][y];
        }

        for (let i = 0; i < 4; i++) {
            const nx = x + enemyDx[i];
            const ny = y + enemyDy[i];

            // 범위를 벗어나거나 벽이나 기사인 경우 무시
            if (nx < 0 || ny < 0 || nx >= N || ny >= N || newBoard[nx][ny] === "X" || newBoard[nx][ny] === "K") {
                continue;
            }

            // 처음 방문하는 경우만 큐에 추가
            if (graph[nx][ny] === -1) {
                graph[nx][ny] = graph[x][y] + 1;
                q.push(new Node(nx, ny));
            }
        }
    }

    // 주인공에게 도달할 수 없으면 -1 반환
    return -1;
}

// 전체 보드의 크기(N)와 각 보드 정보 배열(board)을 입력받기
function solution(inputN, board) {
    N = inputN;
    let answer = 0;
    const knights = [];

    for (let i = 0; i < N; i++) {
        for (let j = 0; j < N; j++) {
            if (board[i][j] === "H") {
                hero = new Node(i, j);
            } else if (board[i][j] === "K") {
                knights.push(new Node(i, j));
            }
        }
    }

    for (let k1 = 0; k1 < 9; k1++) { // 1번 기사의 이동 방향
        for (let k2 = 0; k2 < 9; k2++) { // 2번 기사의 이동 방향
            const newBoard = copyBoard(board);

            // 기사는 항상 2명만 존재
            for (let i = 0; i < 2; i++) {
                const x = knights[i].x;
                const y = knights[i].y;
                // 첫번째 기사의 방향 혹은 두번째 기사의 방향으로 nx, ny 결정
                const nx = i === 0 ? x + knightDx[k1] : x + knightDx[k2];
                const ny = i === 0 ? y + knightDy[k1] : y + knightDy[k2];

                // 공간을 벗어난 경우 무시
                if (!(0 <= nx && nx < N && 0 <= ny && ny < N)) {
                    continue;
                }

                // 적군이 있거나 빈 공간일 때만 이동 가능
                if (newBoard[nx][ny] === "E" || newBoard[nx][ny] === "B") {
                    newBoard[x][y] = "B";
                    newBoard[nx][ny] = "K";
                }
            }

            // BFS로 주인공까지의 최단 거리 계산
            const time = bfs(newBoard);
            if (time === -1) {
                return 0; // 주인공에게 도달 불가능한 경우
            }
            answer = Math.max(answer, time);
        }
    }

    return answer;
}

function copyBoard(board) {
    const newBoard = Array.from({ length: N }, () => Array(N));
    for (let i = 0; i < N; i++) {
        for (let j = 0; j < N; j++) {
            newBoard[i][j] = board[i][j];
        }
    }
    return newBoard;
}
