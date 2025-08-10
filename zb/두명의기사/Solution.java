package org.example.ps.zb.두명의기사;

import java.util.*;

class Node {
    public int x;
    public int y;
    public Node(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

class Solution {
    public static int[] enemyDx = { -1, 1, 0, 0 };
    public static int[] enemyDy = { 0, 0, -1, 1 };

    // 가만히 있는 경우 및 이동 가능한 8가지 방향
    public static int[] knightDx = {0, -2, -2, -1, -1, 1, 1, 2, 2};
    public static int[] knightDy = {0, -1, 1, -2, 2, -2, 2, -1, 1};

    public int N;
    public Node hero;

    public int bfs(String[][] newBoard) {
        int[][] graph = new int[N][N]; // 최단 거리 맵
        for (int i = 0; i < N; i++) {
            Arrays.fill(graph[i], -1);
        }

        Queue<Node> q = new LinkedList<>();

        // 적군 위치에서부터 BFS 시작
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (newBoard[i][j].equals("E")) { // 적군인 경우
                    q.offer(new Node(i, j));
                    graph[i][j] = 0;
                }
            }
        }

        while (!q.isEmpty()) { // BFS 수행
            Node cur = q.poll();
            int x = cur.x;
            int y = cur.y;

            // 주인공에게 도달하면 즉시 종료하고 그때의 최단 거리 반환
            if (x == hero.x && y == hero.y) {
                return graph[x][y];
            }

            for (int i = 0; i < 4; i++) {
                int nx = x + enemyDx[i];
                int ny = y + enemyDy[i];

                // 범위를 벗어나거나 벽이나 기사인 경우 무시
                if (nx < 0 || ny < 0 || nx >= N || ny >= N || newBoard[nx][ny].equals("X") || newBoard[nx][ny].equals("K")) {
                    continue;
                }

                // 처음 방문하는 경우만 큐에 추가
                if (graph[nx][ny] == -1) {
                    graph[nx][ny] = graph[x][y] + 1;
                    q.offer(new Node(nx, ny));
                }
            }
        }

        // 주인공에게 도달할 수 없으면 -1 반환
        return -1;
    }

    // 전체 보드의 크기(N)와 각 보드 정보 배열(board)을 입력받기
    public int solution(int N, String[][] board) {
        this.N = N;
        int answer = 0;
        ArrayList<Node> knights = new ArrayList<Node>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (board[i][j].equals("H")) {
                    hero = new Node(i, j);
                }
                else if (board[i][j].equals("K")) {
                    knights.add(new Node(i, j));
                }
            }
        }

        for (int k1 = 0; k1 < 9; k1++) { // 1번 기사의 이동 방향
            for (int k2 = 0; k2 < 9; k2++) { // 2번 기사의 이동 방향
                String[][] newBoard = copyBoard(board);

                // 기사는 항상 2명만 존재
                for (int i = 0; i < 2; i++) {
                    int x = knights.get(i).x;
                    int y = knights.get(i).y;
                    // 첫번째 기사의 방향 혹은 두번째 기사의 방향으로 nx, ny 결정
                    int nx = i == 0 ? x + knightDx[k1] : x + knightDx[k2];
                    int ny = i == 0 ? y + knightDy[k1] : y + knightDy[k2];

                    // 공간을 벗어난 경우 무시
                    if (!(0 <= nx && nx < N && 0 <= ny && ny < N)) {
                        continue;
                    }

                    // 적군이 있거나 빈 공간일 때만 이동 가능
                    if (newBoard[nx][ny].equals("E") || newBoard[nx][ny].equals("B")) {
                        newBoard[x][y] = "B";
                        newBoard[nx][ny] = "K";
                    }
                }

                // BFS로 주인공까지의 최단 거리 계산
                int time = bfs(newBoard);
                if (time == -1) {
                    return 0; // 주인공에게 도달 불가능한 경우
                }
                answer = Math.max(answer, time);
            }
        }

        return answer;
    }

    private String[][] copyBoard(String[][] board) {
        String[][] newBoard = new String[N][N];
        for (int i = 0; i < N; i++) {
            System.arraycopy(board[i], 0, newBoard[i], 0, N);
        }
        return newBoard;
    }
}