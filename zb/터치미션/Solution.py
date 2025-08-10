from collections import deque

PLAYER = "P"
GOAL = "O"
WALL = "X"
ROAD = "B"

N = 0
start = None

class Node:
    def __init__(self, y, x, cnt):
        self.y = y
        self.x = x
        self.cnt = cnt

def solution(_N, board):
    # 전역변수화
    global N, start
    N = _N
    answer = int(1e9)

    # 시작점 찾기
    for i in range(N):
        for j in range(N):
            if board[i][j] == PLAYER:
                start = Node(i, j, 0)

    # 아무 방법을 쓰지 않은 경우
    count = bfs(board)
    answer = min(answer, count)

    # 방법 1: 특정 타겟 제거
    for i in range(N):
        for j in range(N):
            if board[i][j] == GOAL:
                board[i][j] = ROAD
                count = bfs(board)
                answer = min(answer, count)
                board[i][j] = GOAL

    # 방법 2: 벽 제거
    for i in range(N):
        for j in range(N):
            if board[i][j] == WALL:
                board[i][j] = ROAD
                count = bfs(board)
                answer = min(answer, count)
                board[i][j] = WALL

    return -1 if answer == int(1e9) else answer

def bfs( board):
    ret = -1
    dy = [-1, 0, 1, 0]
    dx = [0, -1, 0, 1]

    visit = [[0] * N for _ in range(N)]

    q = deque([start])

    while q:
        cur = q.popleft()
        y, x, cnt = cur.y, cur.x, cur.cnt

        if visit[y][x] == 1:
            continue
        visit[y][x] = 1

        if board[y][x] == GOAL:
            ret = max(ret, cnt)

        for i in range(4):
            ny, nx = y + dy[i], x + dx[i]

            if 0 <= ny < N and 0 <= nx < N and board[ny][nx] != WALL:
                q.append(Node(ny, nx, cnt + 1))

    # bfs를 통해 얻은 값이 없으면 1e9, 그렇지 않으면 ret 리턴
    return int(1e9) if ret == -1 else ret
