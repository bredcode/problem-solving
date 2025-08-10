package org.example.ps.zb.터치미션;

import java.util.ArrayDeque;
import java.util.Queue;

class Node {
    public int y;
    public int x;
    public int cnt;

    public Node(int y, int x, int cnt) {
        this.y = y;
        this.x = x;
        this.cnt = cnt;
    }
}

class Solution {
    private static final String PLAYER = "P";
    private static final String GOAL = "O";
    private static final String WALL = "X";
    private static final String ROAD = "B";

    int N;
    Node start;

    public int solution(int _N, String[][] board) {
        // 전역변수 화
        N = _N;

        int answer = (int)1e9;

        // 시작점 찾기
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (board[i][j].equals(PLAYER)) {
                    start = new Node(i, j, 0);
                }
            }
        }

        // 아무 방법을 쓰지 않은 경우
        int count = bfs(board);
        answer = Math.min(answer, count);

        // 방법 1: 특정 타겟 제거
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (board[i][j].equals(GOAL)) {
                    board[i][j] = ROAD;
                    count = bfs(board);
                    answer = Math.min(answer, count);
                    board[i][j] = GOAL;
                }
            }
        }

        // 방법 2: 벽 제거
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (board[i][j].equals(WALL)) {
                    board[i][j] = ROAD;
                    count = bfs(board);

                    answer = Math.min(answer, count);
                    board[i][j] = WALL;
                }
            }
        }

        return answer == 1e9 ? -1 : answer; // 도달 불가능하면 -1
    }

    public int bfs(String[][] board) {
        int ret = -1;
        int[] dy = { -1, 0, 1, 0};
        int[] dx = { 0, -1, 0, 1};

        int[][] visit = new int[N][N];

        Queue<Node> q = new ArrayDeque<>();
        q.add(start);

        while(!q.isEmpty()) {
            Node cur = q.remove();
            int y = cur.y;
            int x = cur.x;
            int cnt = cur.cnt;

            if (visit[y][x] == 1) {
                continue;
            }
            visit[y][x] = 1;

            if (board[y][x].equals(GOAL)) {
                ret = Math.max(ret, cnt);
            }

            for (int i = 0; i < 4; i++) {
                int ny = cur.y + dy[i];
                int nx = cur.x + dx[i];

                if ((0 <= ny && ny < N && 0 <= nx && nx < N) && !board[ny][nx].equals(WALL)) {
                    q.add(new Node(ny, nx, cnt + 1));
                }
            }
        }

        // bfs를 통해 얻은 값이 없으면 1e9 그게아니면 ret 리턴
        return ret == -1 ? (int)1e9 : ret;
    }
}