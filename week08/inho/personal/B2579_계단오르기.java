package BJ.silver_3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class B2579_계단오르기 {
    static int N;
    static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N+1];
        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        int[][] dp = new int[N+1][3];
        dp[1][1] = arr[1];
        if (N >= 2) {
            dp[2][1] = arr[2];
            dp[2][2] = arr[1] + arr[2];
        }
        for (int i = 3; i <= N; i++) {
            dp[i][1] = Math.max(dp[i-2][2],dp[i-2][1]) + arr[i];// 첫번째로 한 칸 갔을 경우 -> 2칸 전에서 와야함
            dp[i][2] = dp[i-1][1] + arr[i];// 두번째로 한 칸 갔을 경우 -> 1칸 전에서 와야함.
        }

        System.out.println(Math.max(dp[N][1],dp[N][2]));
    }
}
