package baekjoon.bfs;

import java.util.Queue;
import java.util.*;
import java.util.Scanner;

public class BaekJoon_14442 {

    static int N, M, K;
    static int[][] grid;
    static boolean[][][] visited;
    static int answer = 0;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        K = sc.nextInt();
        grid = new int[N][M];
        visited = new boolean[N][M][K+1];

        sc.nextLine();
        for (int i = 0; i < N; i++) {
            String s = sc.nextLine();
            for (int j = 0; j < M; j++) {
                grid[i][j] = s.charAt(j) - '0';
            }
        }


        if (bfs()) {
            System.out.println(answer);
        } else {
            System.out.println(-1);
        }

    }

    private static boolean bfs() {
        Queue<int[]> queue = new LinkedList<>();



        visited[0][0][0] = true;
        queue.add(new int[]{0, 0, 1, 0});

        while (!queue.isEmpty()) {

            int[] poll = queue.poll();
            int curX = poll[0];
            int curY = poll[1];
            //System.out.println("현재 좌표 X : " + curX + " Y : " + curY);
            int curCount = poll[2];
            int curK = poll[3];

            if (curX == M - 1 && curY == N - 1) {
                answer = curCount;
                return true;
            }

            for (int i = 0; i < 4; i++) {
                int nx = curX + dx[i];
                int ny = curY + dy[i];

                if (nx >= 0 && ny >= 0 && nx < M && ny < N ) {
                    if (grid[ny][nx] == 1 && curK<K && !visited[ny][nx][curK+1]) {
                        visited[ny][nx][curK+1] = true;
                        queue.add(new int[]{nx, ny, curCount + 1, curK+1});
                    } else if (grid[ny][nx] == 0 && !visited[ny][nx][curK]) {
                        visited[ny][nx][curK] = true;
                        queue.add(new int[]{nx, ny, curCount + 1, curK});
                    }

                }
            }


        }

        return false;

    }
}
