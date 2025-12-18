package BJ.gold_5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 최댓값을 구하기 위해서, 무게 기준으로 dp 쌓아가기.
public class B12865_평범한배낭 {
    static int N,K;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        K = Integer.parseInt(input[1]);
        long[] dp = new long[K+1]; // 무게마다 가치를 저장.
        for (int i = 0; i < N; i++) {
            String[] input2 = br.readLine().split(" ");
            int w = Integer.parseInt(input2[0]);
            int v = Integer.parseInt(input2[1]);
            for (int j = K; j >= w; j--) {
                dp[j] = Math.max(dp[j],dp[j-w]+v);
            }
        }
        System.out.println(dp[K]);
    }
}
