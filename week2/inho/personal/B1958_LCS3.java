package BJ.gold_4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class B1958_LCS3 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String a = br.readLine();
        String b = br.readLine();
        String c = br.readLine();

        int[][][] dp = new int[a.length()+1][b.length()+1][c.length()+1];

        for (int i = 0; i < a.length(); i++) {
            for (int j = 0; j < b.length(); j++) {
                for (int k = 0; k < c.length(); k++) {
                    if (a.charAt(i) == b.charAt(j) && b.charAt(j) == c.charAt(k)) {
                        dp[i + 1][j + 1][k + 1] = dp[i][j][k] + 1;
                    } else {
                        dp[i + 1][j + 1][k + 1] = Math.max(Math.max(dp[i][j+1][k+1], dp[i+1][j][k+1]), dp[i+1][j+1][k]);
                    }
                }

            }
        }
        System.out.println(dp[a.length()][b.length()][c.length()]);
    }
}
