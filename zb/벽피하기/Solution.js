const WALL = 1;
const ROAD = 0;
const USER = 2;

// 왼쪽, 가만히, 오른쪽 이동
const dy = [0, 0, 0];
const dx = [-1, 0, 1];

// 전역 변수로 플레이어 시작 위치
let sy = 0;
let sx = 0;

function solution(N, M, board) {
    let answer = 0;

    // 플레이어 시작 위치 찾기
    for (let i = 0; i < M; i++) {
        if (board[N - 1][i] === USER) {
            sy = N - 1;
            sx = i;
            break;
        }
    }

    let visit = Array.from({ length: N }, () => Array(M).fill(false));
    let queue = [[sy, sx, 0]];  // y, x, time
    visit[sy][sx] = true;

    while (queue.length > 0) {
        let [y, x, time] = queue.shift();

        answer = Math.max(answer, time);

        for (let i = 0; i < 3; i++) {
            let ny = y + dy[i];
            let nx = x + dx[i];

            // 좌, 가만히, 우 이동이 가능한지 확인
            if (0 <= ny && ny < N && 0 <= nx && nx < M && board[ny][nx] !== WALL) {
                let nny = ny - 1;
                // 위로 한 칸 갔을 때 가능한지 확인
                if (nny >= 0 && board[nny][nx] !== WALL && !visit[nny][nx]) {
                    // 위 조건을 만족하면 이동 및 1초 추가
                    queue.push([nny, nx, time + 1]);
                    visit[nny][nx] = true;
                }
            }
        }
    }

    return answer;
}