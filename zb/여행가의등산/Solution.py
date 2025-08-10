import heapq

# 상, 하, 좌, 우
dy = [-1, 1, 0, 0]
dx = [0, 0, -1, 1]

# 다익스트라 알고리즘을 통해 최소 체력 소모량을 구하는 함수
def dijkstra(N, arr):
    global dist
    pq = []
    heapq.heappush(pq, (0, 0, 0))  # (cost, y좌표, x좌표)

    dist[0][0] = 0

    while pq:
        cost, y, x = heapq.heappop(pq)

        # 현재 위치가 (N-1, N-1)인 경우 최소 소모량 반환
        if y == N - 1 and x == N - 1:
            return cost

        # 상, 하, 좌, 우 이동
        for i in range(4):
            ny = y + dy[i]
            nx = x + dx[i]

            # 범위를 벗어나면 무시
            if not (0 <= nx < N and 0 <= ny < N):
                continue

            # 이동 시 고도 차이에 따른 체력 소모량 계산
            next_cost = cost + abs(arr[ny][nx] - arr[y][x])

            # 현재 경로가 더 적은 체력 소모량을 제공할 경우
            if next_cost < dist[ny][nx]:
                dist[ny][nx] = next_cost
                heapq.heappush(pq, (next_cost, ny, nx))

    return dist[N-1][N-1]  # 도달할 수 없는 경우는 없다고 가정

def solution(N, arr):
    global dist
    dist = [[float('inf')] * N for _ in range(N)]
    return dijkstra(N, arr)
