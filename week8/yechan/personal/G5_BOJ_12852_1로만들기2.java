package week8.yechan.personal;

import java.io.BufferedReader;
import java.io.InputStreamReader;


public class G5_BOJ_12852_1로만들기2 {

    static int N;
    static int[] dp;
    static int[] pre; // 복원용 테이블
    static StringBuilder sb = new StringBuilder();
    
    public static void main(String[] args) throws Exception {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        N = Integer.parseInt(br.readLine());

        dp = new int[N+1];
        pre = new int[N+1];

        dp[1] = 0;

        for(int i=2; i<=N; i++){
            dp[i] = dp[i-1] + 1;
            pre[i] = i-1;

            if(i%2 == 0 && dp[i] > dp[i/2] + 1){
                dp[i] = Math.min(dp[i/2] + 1, dp[i]);
                pre[i] = i/2;
            }

            if(i%3 == 0 && dp[i] > dp[i/3] + 1){
                dp[i] = Math.min(dp[i/3] + 1, dp[i]);
                pre[i] = i/3;
            }

        }
        sb.append(dp[N]).append("\n");

        int cur = N;
        while(true){
            sb.append(cur).append(" ");
            if(cur == 1) break;
            cur = pre[cur];
        }

        System.out.println(sb);

    }
}
