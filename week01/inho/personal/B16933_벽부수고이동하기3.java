package BJ.gold_1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class B16933_벽부수고이동하기3 {
    static int N, M, K;
    static int[][] field;
    static boolean[][][] visited;
    static int[][] rows = {
            {1, 0},
            {-1, 0},
            {0, 1},
            {0, -1}
    };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);
        K = Integer.parseInt(input[2]);
        field = new int[N][M];
        visited = new boolean[N][M][K + 1];
        for (int i = 0; i < N; i++) {
            String[] arr = br.readLine().split("");
            for (int j = 0; j < M; j++) {
                field[i][j] = Integer.parseInt(arr[j]);
            }
        }

        Deque<Node> deque = new ArrayDeque<>();
        deque.add(new Node(0, 0, 1, 0, 0)); // 0을 낮으로, 1을 밤으로 설정하자
        visited[0][0][0] = true;
        while (!deque.isEmpty()) {
            Node now = deque.remove();
            if (now.x == N - 1 && now.y == M - 1) {
                System.out.println(now.count);
                return;
            }

            for (int[] row : rows) {
                int nx = now.x + row[0];
                int ny = now.y + row[1];
                if (nx < 0 || ny < 0 || nx >= N || ny >= M) {
                    continue;
                }
                // 낮에 0 만났을 때
                if (!visited[nx][ny][now.magic] && field[nx][ny] == 0 && now.day == 0) {
                    deque.add(new Node(nx, ny, now.count + 1, now.magic, 1));
                    visited[nx][ny][now.magic] = true;
                }
                // 밤에 0 만났을 때
                if (!visited[nx][ny][now.magic] && field[nx][ny] == 0 && now.day == 1) {
                    deque.add(new Node(nx, ny, now.count + 1, now.magic, 0));
                    visited[nx][ny][now.magic] = true;
                }
                // 낮에 1 만났을 때
                if (now.magic < K && !visited[nx][ny][now.magic + 1] && field[nx][ny] == 1 && now.day == 0) {
                    deque.add(new Node(nx, ny, now.count + 1, now.magic + 1, 1));
                    visited[nx][ny][now.magic + 1] = true;
                }
                // 밤에 1 만났을 때
                if (now.magic < K && !visited[nx][ny][now.magic + 1] && field[nx][ny] == 1 && now.day == 1) {
                    deque.add(new Node(now.x, now.y, now.count + 1, now.magic, 0));
                }
            }

        }
        System.out.println(-1);
    }

    static class Node {
        int x;
        int y;
        int count;
        int magic;
        int day;

        Node(int x, int y, int count, int magic, int day) {
            this.x = x;
            this.y = y;
            this.count = count;
            this.magic = magic;
            this.day = day;
        }
    }
}
