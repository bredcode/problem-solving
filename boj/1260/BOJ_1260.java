package org.example.ps.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_1260 {
    static ArrayList<Integer>[] arr; // 인접 리스트 배열로 그래프 구현
    static boolean[] visited; // 방문 여부를 저장하는 배열
    static int n; // 정점의 개수

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken()); // 정점의 개수
        int m = Integer.parseInt(st.nextToken()); // 간선의 개수
        int r = Integer.parseInt(st.nextToken()); // 탐색을 시작할 정점

        arr = new ArrayList[n + 1];
        visited = new boolean[n + 1];

        // 인접 리스트 배열 초기화
        for (int i = 0; i <= n; i++) {
            arr[i] = new ArrayList<>();
        }

        // 그래프에 간선 정보 추가
        for (int j = 0; j < m; j++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            // 양방향 간선이므로 양쪽에 추가
            arr[a].add(b);
            arr[b].add(a);
        }

        // 각 인접 리스트를 오름차순으로 정렬
        for (int k = 1; k <= n; k++) {
            Collections.sort(arr[k]);
        }

        // DFS 탐색 및 출력
        dfs(r);
        System.out.println();

        // BFS 탐색 및 출력
        bfs(r);
    }

    // DFS 탐색
    static void dfs(int v) {
        visited[v] = true;
        System.out.print(v + " ");

        for (int neighbor : arr[v]) {
            if (!visited[neighbor]) {
                dfs(neighbor);
            }
        }
    }

    // BFS 탐색
    static void bfs(int start) {
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[n + 1];

        queue.offer(start);
        visited[start] = true;

        while (!queue.isEmpty()) {
            int cur = queue.poll();
            System.out.print(cur + " ");

            for (int next : arr[cur]) {
                if (!visited[next]) {
                    queue.offer(next);
                    visited[next] = true;
                }
            }
        }
    }
}