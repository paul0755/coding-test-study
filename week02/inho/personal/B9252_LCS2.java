package BJ.gold_4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class B9252_LCS2 {
    static String N, M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = br.readLine();
        M = br.readLine();

        int[][] dp = new int[N.length() + 1][M.length() + 1];

        for (int i = 1; i <= N.length(); i++) {
            for (int j = 1; j <= M.length(); j++) {
                if (N.charAt(i - 1) == M.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        System.out.println(dp[N.length()][M.length()]);

        StringBuilder lcs = new StringBuilder();
        int i = N.length();
        int j = M.length();
        while (i>0 && j >0){
            if (N.charAt(i-1)==M.charAt(j-1)){ // 똑같으면 저장하고 왼쪽 위로
                lcs.append(N.charAt(i-1));
                i--;
                j--;
            }else if(dp[i-1][j] > dp[i][j-1]){ // 다를 때 더 큰 쪽ㅇ로
                i--;
            }else{
                j--;
            }
        }

        System.out.println(lcs.reverse());
    }
}
