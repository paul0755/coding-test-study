package baekjoon.dfs;

import java.util.Scanner;

public class BaekJoon_1405 {

    static int N;
    static int[][] grid;
    static boolean[][] visited;
    static double count = 0;


    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static double[] pro = new double[4];

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();

        grid = new int[(2 * N + 1)][(2 * N + 1)];
        visited = new boolean[2 * N + 1][(2 * N + 1)];

        pro[0] = sc.nextInt() / 100.0;
        pro[1] = sc.nextInt() / 100.0;
        pro[2] = sc.nextInt() / 100.0;
        pro[3] = sc.nextInt() / 100.0;


        int position = (2 * N + 1) / 2;

        visited[position][position] = true;
        dfs(0, position, position, 1.0);

        System.out.println(count);


    }

    private static void dfs(int depth, int x, int y, double prob) {
        if (depth == N) {
            count += prob;
            return;
        }

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];


            if (!visited[ny][nx] && pro[i] > 0) {
                visited[ny][nx] = true;
                dfs(depth + 1, nx, ny, prob * pro[i]);
                visited[ny][nx] = false;
            }
        }

    }
}
