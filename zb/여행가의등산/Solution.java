package org.example.ps.zb.여행가의등산;

import java.util.*;

class Node {
    int y;
    int x;
    int cost;

    public Node(int y, int x, int cost) {
        this.y = y;
        this.x = x;
        this.cost = cost;
    }
}

// Q. BFS가 안되는 이유는? -> 일정한 방향이 안되어서 (위로갔다가 밑으로갔다가 좌, 우로갔다가...)
class Solution {
    // 상, 하, 좌, 우
    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};

    public int solution(int N, int[][] arr) {
        // 최소 체력 소모량을 저장하는 배열
        int[][] dist = new int[N][N];
        for (int i = 0; i < N; i++) {
            Arrays.fill(dist[i], Integer.MAX_VALUE);
        }

        // 우선순위 큐 사용 (소모 체력이 작은 순서대로 처리)
        PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> Integer.compare(a.cost, b.cost));
        pq.offer(new Node(0, 0, 0)); // (x좌표, y좌표, 소모 체력)

        dist[0][0] = 0;

        // 다익스트라 알고리즘
        while (!pq.isEmpty()) {
            Node current = pq.poll();
            int y = current.y;
            int x = current.x;
            int cost = current.cost;

            // 현재 위치가 (N-1, N-1)인 경우 최소 소모량 반환
            if (y == N - 1 && x == N - 1) {
                return cost;
            }

            // 상, 하, 좌, 우 이동
            for (int i = 0; i < 4; i++) {
                int ny = y + dy[i];
                int nx = x + dx[i];

                // 범위를 벗어나면 무시
                if (!(0 <= nx && nx < N && 0 <= ny && ny < N)) continue;

                // 이동 시 고도 차이에 따른 체력 소모량 계산
                int nextCost = cost + Math.abs(arr[ny][nx] - arr[y][x]);

                // 현재 경로가 더 적은 체력 소모량을 제공할 경우
                if (nextCost < dist[ny][nx]) {
                    dist[ny][nx] = nextCost;
                    pq.offer(new Node(ny, nx, nextCost));
                }
            }
        }

        return dist[N-1][N-1]; // 도달할 수 없는 경우는 없다고 가정
    }
}