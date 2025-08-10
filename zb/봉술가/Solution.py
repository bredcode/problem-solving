from collections import deque

dy = [-1, 1, 0, 0]
dx = [0, 0, -1, 1]

class Node:
    def __init__(self, y, x, dist=0):
        self.y = y
        self.x = x
        self.dist = dist

def bfs(N, board):
    # 최단 거리 맵 초기화
    graph = [[-1] * N for _ in range(N)]

    # 큐 초기화 및 그래플러 위치 삽입
    q = deque()
    for i in range(N):
        for j in range(N):
            if board[i][j] == "G":
                q.append(Node(i, j))
                graph[i][j] = 0

    # BFS 수행
    while q:
        cur = q.popleft()
        y, x = cur.y, cur.x

        for i in range(4):
            ny = y + dy[i]
            nx = x + dx[i]

            # 공간을 벗어난 경우 무시
            if not (0 <= ny < N and 0 <= nx < N):
                continue

            # 처음 방문하는 경우에만 거리 갱신
            if graph[ny][nx] == -1 and board[ny][nx] == "B":
                graph[ny][nx] = graph[y][x] + 1
                q.append(Node(ny, nx))

    return graph

def solution(N, board):
    # BFS를 통해 각 빈 칸의 그래플러와의 최단 거리 계산
    graph = bfs(N, board)

    # 가장 가까운 그래플러와의 거리가 최대인 위치 찾기
    max_list = []
    for i in range(N):
        for j in range(N):
            if board[i][j] == "B":
                max_list.append(Node(i, j, graph[i][j]))

    # 거리 기준 내림차순, 행과 열 기준 오름차순 정렬
    max_list.sort(key=lambda node: (-node.dist, node.y, node.x))

    # 정답 도출
    return [max_list[0].y, max_list[0].x]
