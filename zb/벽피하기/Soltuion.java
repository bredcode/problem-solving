package org.example.ps.zb.벽피하기;

import java.util.ArrayDeque;
import java.util.Queue;

class Item {
    int y;
    int x;
    int time;

    public Item(int y, int x, int time) {
        this.y = y;
        this.x = x;
        this.time = time;
    }
}

class Solution {
    private static final int WALL = 1;
    private static final int ROAD = 0;
    private static final int USER = 2;

    // 왼쪽, 가만히, 오른쪽 이동
    private int[] dy = {0, 0, 0};
    private int[] dx = {-1, 0, 1};
    private int sy, sx;

    public int solution(int N, int M, int[][] board) {
        int answer = 0;

        for (int i = 0; i < M; i++) {
            if (board[N - 1][i] == USER) {
                sy = N - 1;
                sx = i;
                break;
            }
        }

        boolean[][] visit = new boolean[N][M];
        Queue<Item> q = new ArrayDeque<>();
        q.add(new Item(sy, sx, 0));
        visit[sy][sx] = true;

        while(!q.isEmpty()) {
            Item item = q.remove();
            int y = item.y;
            int x = item.x;
            int time = item.time;

            answer = Math.max(answer, time);

            for (int i = 0; i < 3; i ++) {
                int ny = y + dy[i];
                int nx = x + dx[i];

                // 좌, 가만히, 우 이동이 가능한지 확인
                if (0 <= ny && ny < N && 0 <= nx && nx < M && board[ny][nx] != WALL) {
                    int nny = ny - 1;
                    // 위로 한칸 갔을 때 가능한지 확인
                    if (0 <= nny && board[nny][nx] != WALL && !visit[nny][nx]) {
                        // 위 두 경우를 통과하면 이동 및 1초 추가
                        q.add(new Item(nny, nx, time + 1));
                        visit[nny][nx] = true;
                    }
                }
            }
        }


        return answer;
    }
}
