const PLAYER = "P";
const GOAL = "O";
const WALL = "X";
const ROAD = "B";

let N = 0;
let start = null;

class Node {
    constructor(y, x, cnt) {
        this.y = y;
        this.x = x;
        this.cnt = cnt;
    }
}

function solution(_N, board) {
    N = _N;
    let answer = Number.MAX_SAFE_INTEGER;

    // 시작점 찾기
    for (let i = 0; i < N; i++) {
        for (let j = 0; j < N; j++) {
            if (board[i][j] === PLAYER) {
                start = new Node(i, j, 0);
            }
        }
    }

    // 아무 방법을 쓰지 않은 경우
    let count = bfs(board);
    answer = Math.min(answer, count);

    // 방법 1: 특정 타겟 제거
    for (let i = 0; i < N; i++) {
        for (let j = 0; j < N; j++) {
            if (board[i][j] === GOAL) {
                board[i][j] = ROAD;
                count = bfs(board);
                answer = Math.min(answer, count);
                board[i][j] = GOAL;
            }
        }
    }

    // 방법 2: 벽 제거
    for (let i = 0; i < N; i++) {
        for (let j = 0; j < N; j++) {
            if (board[i][j] === WALL) {
                board[i][j] = ROAD;
                count = bfs(board);
                answer = Math.min(answer, count);
                board[i][j] = WALL;
            }
        }
    }

    return answer === Number.MAX_SAFE_INTEGER ? -1 : answer;
}

function bfs(board) {
    const dy = [-1, 0, 1, 0];
    const dx = [0, -1, 0, 1];
    let ret = -1;

    let visit = Array.from(Array(N), () => Array(N).fill(0));
    let q = [];
    q.push(start);

    while (q.length > 0) {
        const cur = q.shift();
        const { y, x, cnt } = cur;

        if (visit[y][x] === 1) continue;
        visit[y][x] = 1;

        if (board[y][x] === GOAL) {
            ret = Math.max(ret, cnt);
        }

        for (let i = 0; i < 4; i++) {
            let ny = y + dy[i];
            let nx = x + dx[i];

            if (0 <= ny && ny < N && 0 <= nx && nx < N && board[ny][nx] !== WALL) {
                q.push(new Node(ny, nx, cnt + 1));
            }
        }
    }

    // bfs를 통해 얻은 값이 없으면 Number.MAX_SAFE_INTEGER, 그렇지 않으면 ret 리턴
    return ret === -1 ? Number.MAX_SAFE_INTEGER : ret;
}
