package BJ.silver_1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class B2156_포도주시식 {
    static int N;
    static int[] arr;
    static int[][] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N+1];
        dp = new int[N+1][3]; // 개수, 1연속 2연속까지만 가능
        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        //System.out.println(Arrays.toString(arr));
        dp[1][1] = arr[1];
        if (N>=2){
            dp[2][0] = arr[1];
            dp[2][1] = arr[2];
            dp[2][2] = arr[1] + arr[2];
        }

        for (int i = 3; i <= N; i++) {
            dp[i][1] = Math.max(dp[i-2][0],Math.max(dp[i-2][1],dp[i-2][2]))+arr[i];
            dp[i][2] = dp[i-1][1] + arr[i];
            dp[i][0] = Math.max(dp[i-1][0],Math.max(dp[i-1][1],dp[i-1][2]));
        }
        //System.out.println(Arrays.deepToString(dp));
        System.out.println(Math.max(Math.max(dp[N][1],dp[N][2]),dp[N][0]));
    }
}
