package BJ.gold_4;

// 받을 수 있는 자두 개수 구하기.
// 최대 W만 움직이고 싶음.
// 시작은 1번 나무고, 1과2나무중 랜덤하게 떨어짐.

// 최소 이동이니까 최대

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class B2240_자두나무 {
    static int T, W;
    static int[][][] dp;
    static int[] field;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        T = Integer.parseInt(input[0]);
        W = Integer.parseInt(input[1]);
        dp = new int[T + 1][W + 1][3]; // 시간,이동횟수, 현재위치
        field = new int[T + 1];

        for (int i = 1; i <= T; i++) {
            int num = Integer.parseInt(br.readLine());
            field[i] = num;
        }
        if (field[1] == 1) { // 첫번째가 1이면
            dp[1][0][1] = 1;
            dp[1][1][2] = 0;
        } else { // 첫번째가 2라면
            dp[1][0][1] = 0;
            dp[1][1][2] = 1;
        }
        for (int i = 2; i <= T; i++) {
            int now = field[i];
            for (int j = 0; j <= W; j++) {
                // 분기를 생각해보자..
                if (now == 1) { // 다음 게 1일 때
                    dp[i][j][1] = Math.max(dp[i - 1][j][1] + 1, dp[i][j][1]); // 그대로 있는 1
                    dp[i][j][2] = Math.max(dp[i - 1][j][2], dp[i][j][2]); //그대로 있는 2
                    if (j >= 1) {
                        dp[i][j][1] = Math.max(dp[i - 1][j - 1][2] + 1, dp[i][j][1]);
                        dp[i][j][2] = Math.max(dp[i - 1][j - 1][1], dp[i][j][2]);
                    }
                } else { // 다음 게 2일 때
                    dp[i][j][1] = Math.max(dp[i - 1][j][1], dp[i][j][1]); // 그대로 있는 1
                    dp[i][j][2] = Math.max(dp[i - 1][j][2] + 1, dp[i][j][2]); //그대로 있는 2
                    if (j >= 1) {
                        dp[i][j][1] = Math.max(dp[i - 1][j - 1][2], dp[i][j][1]);
                        dp[i][j][2] = Math.max(dp[i - 1][j - 1][1] + 1, dp[i][j][2]);
                    }
                }
            }
        }
        int ans = 0;
        for (int j = 0; j <= W; j++) {
            ans = Math.max(ans, dp[T][j][1]);
            ans = Math.max(ans, dp[T][j][2]);
        }
        System.out.println(ans);
    }
}
