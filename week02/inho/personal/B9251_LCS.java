package BJ.gold_5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 최장 공통 부분 수열 문제
public class B9251_LCS {
    static String  N,M;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = br.readLine();
        M = br.readLine();

        int[][] dp = new int[N.length()+1][M.length()+1];

        for (int i = 1; i <= N.length(); i++) {
            for (int j = 1; j <= M.length(); j++) {
                if (N.charAt(i-1)==M.charAt(j-1)){
                    dp[i][j] = dp[i-1][j-1]+1;
                }else {
                    dp[i][j] = Math.max(dp[i-1][j],dp[i][j-1]);
                }
            }
        }
        System.out.println(dp[N.length()][M.length()]);
    }
}
