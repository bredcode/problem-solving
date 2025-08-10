package org.example.ps.zb.봉술가;

import java.util.*;


class Node {
    public int y;
    public int x;
    public int dist;

    public Node(int y, int x) {
        this.y = y;
        this.x = x;
        this.dist = 0;
    }

    // 마지막 정답 도출을 위해 만든 생성자
    public Node(int y, int x, int dist) {
        this.y = y;
        this.x = x;
        this.dist = dist;
    }
}

class Solution {
    // 상, 하, 좌, 우
    public int[] dy = { -1, 1, 0, 0 };
    public int[] dx = { 0, 0, -1, 1 };

    public int[] solution(int N, String[][] board) {
        // BFS를 활용한 최단 거리 탐색
        int[][] graph = bfs(N, board);

        // 가장 가까운 그래플러와의 거리가 최대가 되는 위치
        int[] answer = new int[2];
        List<Node> maxList = new ArrayList<>();

        // 빈 칸 중에서 가장 멀리 떨어진 위치 찾기
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (board[i][j].equals("B")) {
                    maxList.add(new Node(i, j, graph[i][j]));
                }
            }
        }

        // 가장 가까운 그래플러와의 거리가 최대이면서 행, 열 오름차순
        maxList.sort((o1, o2) -> {
            if (o1.dist == o2.dist) {
                if (o1.y == o2.y) {
                    return o1.x - o2.x; // 행이 같을 경우 열 기준 정렬
                }
                return o1.y - o2.y; // 거리가 같을 경우 행 기준 오름차순
            }
            return o2.dist - o1.dist; // 거리 기준 내림차순 정렬
        });

        answer[0] = maxList.get(0).y;
        answer[1] = maxList.get(0).x;
        return answer;
    }

    // BFS를 이용해 각 빈 칸의 그래플러와의 최단 거리 계산
    public int[][] bfs(int N, String[][] board) {
        // 최단 거리 맵
        int[][] graph = new int[N][N];
        for (int i = 0; i < N; i++)
            Arrays.fill(graph[i], -1);

        // 각 그래플러의 위치를 모두 담아 BFS 진행
        Queue<Node> q = new ArrayDeque<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                // 각 그래플러 위치를 큐에 삽입
                if (board[i][j].equals("G")) {
                    q.offer(new Node(i, j));
                    // 그래플러가 있는 위치는 거리가 0
                    graph[i][j] = 0;
                }
            }
        }

        // 너비 우선 탐색(BFS) 수행
        while (!q.isEmpty()) {
            Node cur = q.remove();
            int y = cur.y;
            int x = cur.x;

            for (int i = 0; i < 4; i++) {
                int ny = y + dy[i];
                int nx = x + dx[i];

                // 공간을 벗어난 경우 무시
                if (!(0 <= ny && ny < N && 0 <= nx && nx < N))
                    continue;

                // 처음 방문하는 경우에만 거리 갱신
                if (graph[ny][nx] == -1 && board[ny][nx].equals("B")) {
                    // 최단 거리 기록
                    graph[ny][nx] = graph[y][x] + 1;
                    q.add(new Node(ny, nx));
                }
            }
        }
        return graph;
    }
}
