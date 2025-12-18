package BJ.gold_5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 만들 수 있는 모든 경우의 수 세기
public class B9084_동전 {
    static int T;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            int N = Integer.parseInt(br.readLine()); // 동전의 가지수
            String[] input = br.readLine().split(" "); // 동전의 각 금액
            int M = Integer.parseInt(br.readLine()); // 만들어야할 금액

            // M원을 만들어야하니
            int[] dp = new int[M+1];
            for (String s : input){
                int intS = Integer.parseInt(s);
                dp[0] = 1;
                for (int j = intS; j <= M; j++) {
                    dp[j] = dp[j]+dp[j-intS];
                }
            }

            System.out.println(dp[M]);
        }
    }
}
