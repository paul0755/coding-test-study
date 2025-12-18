package BJ.gold_2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class B16946_벽부수고이동하기4 {
    static int N, M;
    static int[][] field;
    static int[][] rows = {
            {1, 0},
            {-1, 0},
            {0, 1},
            {0, -1}
    };
    static int[][] groups;
    static boolean[][] visited;
    static int[] groupSize;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);
        field = new int[N][M];
        groups = new int[N][M];
        visited = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            String[] arr = br.readLine().split("");
            for (int j = 0; j < M; j++) {
                field[i][j] = Integer.parseInt(arr[j]);
            }
        }

        int id = 1;
        List<Integer> sizes = new ArrayList<>();
        sizes.add(0);
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (field[i][j] == 0 && !visited[i][j]) {
                    int size = bfs(i, j, id);
                    sizes.add(size);
                    id++;
                }
            }
        }
        groupSize = new int[sizes.size()];
        for (int j = 0; j <sizes.size(); j++) {
            groupSize[j] = sizes.get(j);
        }

        // 인접한 groupId의 개수들 더하기 (1일 때마다 반복)
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (field[i][j] == 0) sb.append(0);
                else {
                    Set<Integer> set = new HashSet<>();
                    int sum = 1;
                    for (int[] d : rows) {
                        int nx = i + d[0];
                        int ny = j + d[1];
                        if (nx < 0 || ny < 0 || nx >= N || ny >= M) continue;
                        int gid = groups[nx][ny];
                        if (gid > 0 && set.add(gid)) sum += groupSize[gid];
                    }
                    sb.append(sum % 10);
                }
            }
            sb.append('\n');
        }
        System.out.print(sb);
    }

    private static int bfs(int x, int y, int id) {
        Deque<Node> deque = new ArrayDeque<>();
        deque.add(new Node(x, y));
        visited[x][y] = true;
        groups[x][y] = id;
        int count = 1;

        while (!deque.isEmpty()) {
            Node now = deque.remove();
            for (int[] row : rows) {
                int nx = now.x + row[0];
                int ny = now.y + row[1];
                if (nx < 0 || ny < 0 || nx >= N || ny >= M) {
                    continue;
                }
                if (!visited[nx][ny] && field[nx][ny] == 0) {
                    visited[nx][ny] = true;
                    groups[nx][ny] = id;
                    deque.add(new Node(nx, ny));
                    count++;
                }
            }
        }
        return count;
    }

    static class Node {
        int x;
        int y;

        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
