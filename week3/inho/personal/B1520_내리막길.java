package BJ.gold_3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class B1520_내리막길 {
    static int N,M;
    static int[][] field;
    static int[][] dp;
    static int[][] rows = {
            {1,0},
            {-1,0},
            {0,1},
            {0,-1}
    };
    public static void main(String[] args) throws IOException {
        BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
        String[] ar = br.readLine().split(" ");
        N = Integer.parseInt(ar[0]);
        M = Integer.parseInt(ar[1]);
        field = new int[N][M];
        dp = new int[N][M];

        for (int i = 0; i < N; i++) {
            String[] arr = br.readLine().split(" ");
            for (int j = 0; j < M; j++) {
                field[i][j] = Integer.parseInt(arr[j]);
                dp[i][j] = -1;
            }
        }

        System.out.println(dfs(0,0));
    }

    private static int dfs(int x, int y) {
        if (x == N - 1 && y == M - 1) return 1;
        if (dp[x][y] != -1) return dp[x][y];
        dp[x][y] = 0; // 초기화 후 시작

        for (int[] row : rows) {
            int nx = x + row[0];
            int ny = y + row[1];

            if (nx < 0 || ny < 0 || nx >= N || ny >= M) continue;

            if (field[x][y] > field[nx][ny]) {
                dp[x][y] += dfs(nx, ny);
            }
        }

        return dp[x][y];

    }
}
