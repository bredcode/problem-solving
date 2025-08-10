from collections import deque

WALL = 1
ROAD = 0
USER = 2

# 왼쪽, 가만히, 오른쪽 이동
dy = [0, 0, 0]
dx = [-1, 0, 1]

# 전역 변수로 플레이어 시작 위치
sy = 0
sx = 0

def solution(N, M, board):
    global sy, sx
    answer = 0

    # 플레이어 시작 위치 찾기
    for i in range(M):
        if board[N - 1][i] == USER:
            sy = N - 1
            sx = i
            break

    visit = [[False] * M for _ in range(N)]
    q = deque([(sy, sx, 0)])  # y, x, time
    visit[sy][sx] = True

    while q:
        y, x, time = q.popleft()

        answer = max(answer, time)

        for i in range(3):
            ny = y + dy[i]
            nx = x + dx[i]

            # 좌, 가만히, 우 이동이 가능한지 확인
            if 0 <= ny < N and 0 <= nx < M and board[ny][nx] != WALL:
                nny = ny - 1
                # 위로 한칸 갔을 때 가능한지 확인
                if 0 <= nny and board[nny][nx] != WALL and not visit[nny][nx]:
                    # 위 조건을 만족하면 이동 및 1초 추가
                    q.append((nny, nx, time + 1))
                    visit[nny][nx] = True

    return answer