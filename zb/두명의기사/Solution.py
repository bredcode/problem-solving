class Node:
    def __init__(self, x, y):
        self.x = x
        self.y = y

enemy_dx = [-1, 1, 0, 0]
enemy_dy = [0, 0, -1, 1]
knight_dx = [0, -2, -2, -1, -1, 1, 1, 2, 2]
knight_dy = [0, -1, 1, -2, 2, -2, 2, -1, 1]

N = 0
hero = None

def bfs(new_board):
    graph = [[-1] * N for _ in range(N)]  # 최단 거리 맵
    q = []

    # 적군 위치에서부터 BFS 시작
    for i in range(N):
        for j in range(N):
            if new_board[i][j] == "E":  # 적군인 경우
                q.append(Node(i, j))
                graph[i][j] = 0

    while q:  # BFS 수행
        cur = q.pop(0)
        x, y = cur.x, cur.y

        # 주인공에게 도달하면 즉시 종료하고 그때의 최단 거리 반환
        if x == hero.x and y == hero.y:
            return graph[x][y]

        for i in range(4):
            nx, ny = x + enemy_dx[i], y + enemy_dy[i]

            # 범위를 벗어나거나 벽이나 기사인 경우 무시
            if not (0 <= nx < N and 0 <= ny < N) or new_board[nx][ny] in ("X", "K"):
                continue

            # 처음 방문하는 경우만 큐에 추가
            if graph[nx][ny] == -1:
                graph[nx][ny] = graph[x][y] + 1
                q.append(Node(nx, ny))

    # 주인공에게 도달할 수 없으면 -1 반환
    return -1

# 전체 보드의 크기(N)와 각 보드 정보 배열(board)을 입력받기
def solution(input_n, board):
    global N, hero
    N = input_n
    answer = 0
    knights = []

    for i in range(N):
        for j in range(N):
            if board[i][j] == "H":
                hero = Node(i, j)
            elif board[i][j] == "K":
                knights.append(Node(i, j))

    for k1 in range(9):  # 1번 기사의 이동 방향
        for k2 in range(9):  # 2번 기사의 이동 방향
            new_board = copy_board(board)

            # 기사는 항상 2명만 존재
            for i in range(2):
                x, y = knights[i].x, knights[i].y
                # 첫번째 기사의 방향 혹은 두번째 기사의 방향으로 nx, ny 결정
                nx = x + (knight_dx[k1] if i == 0 else knight_dx[k2])
                ny = y + (knight_dy[k1] if i == 0 else knight_dy[k2])

                # 공간을 벗어난 경우 무시
                if not (0 <= nx < N and 0 <= ny < N):
                    continue

                # 적군이 있거나 빈 공간일 때만 이동 가능
                if new_board[nx][ny] in ("E", "B"):
                    new_board[x][y] = "B"
                    new_board[nx][ny] = "K"

            # BFS로 주인공까지의 최단 거리 계산
            time = bfs(new_board)
            if time == -1:
                return 0  # 주인공에게 도달 불가능한 경우
            answer = max(answer, time)

    return answer

def copy_board(board):
    return [row[:] for row in board]
