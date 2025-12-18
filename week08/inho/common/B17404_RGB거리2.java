package BJ.gold_5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// 집이 N개 있고 순서대로 있다.
// 빨,초,파 중 하나의 색의 집이다. 비용도 함께 주어짐.
// 1번 집의 색은 2번, N번 집의 색과 같지 않아야함.
// N번 집의 색은 N-1번, 1번 집의 색과 같지 않아야함.
// i번 집의 색은 i-1, i+1 집의 색과 같지 않아야함.
public class B17404_RGB거리2 {
    static int N;
    static int answer = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        int[][] dp = new int[N + 1][3]; // 순서, (빨,초,파 중 하나)
        int[][] cost = new int[N + 1][3];

        for (int i = 1; i <= N; i++) {
            String[] input = br.readLine().split(" ");
            cost[i][0] = Integer.parseInt(input[0]);
            cost[i][1] = Integer.parseInt(input[1]);
            cost[i][2] = Integer.parseInt(input[2]);
        }
        // 3번 dp 진행
        dp[1][0] = cost[1][0];
        dp[1][1] = 1000000;
        dp[1][2] = 1000000;
        for (int i = 2; i <= N; i++) {
            dp[i][1] = Math.min(dp[i - 1][0], dp[i - 1][2])+cost[i][1];
            dp[i][2] = Math.min(dp[i - 1][0], dp[i - 1][1])+cost[i][2];
            dp[i][0] = Math.min(dp[i - 1][1], dp[i - 1][2])+cost[i][0];
        }
        answer = Math.min(dp[N][1],dp[N][2]);

        dp[1][0] = 1000000;
        dp[1][1] = cost[1][1];
        dp[1][2] = 1000000;
        for (int i = 2; i <= N; i++) {
            dp[i][0] = Math.min(dp[i - 1][1], dp[i - 1][2])+cost[i][0];
            dp[i][2] = Math.min(dp[i - 1][0], dp[i - 1][1])+cost[i][2];
            dp[i][1] = Math.min(dp[i - 1][0], dp[i - 1][2])+cost[i][1];
        }
        answer = Math.min(answer,Math.min(dp[N][0],dp[N][2]));

        dp[1][0] = 1000000;
        dp[1][1] = 1000000;
        dp[1][2] = cost[1][2];
        for (int i = 2; i <= N; i++) {
            dp[i][0] = Math.min(dp[i - 1][1], dp[i - 1][2])+cost[i][0];
            dp[i][1] = Math.min(dp[i - 1][0], dp[i - 1][2])+cost[i][1];
            dp[i][2] = Math.min(dp[i - 1][0], dp[i - 1][1])+cost[i][2];
        }
        answer = Math.min(answer,Math.min(dp[N][0],dp[N][1]));
        System.out.println(answer);
    }
}
