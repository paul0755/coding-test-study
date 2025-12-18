package week8.yechan.common;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 나중에 다시 보기 -> 너무 어려움
 * 
 * dp[t][w] = t초까지, w번 이동해서 얻을 수 있는 최대 자두 수
 * 
 * 위치는 움직임으로 도출이 가능하여 따로 안넣어도 된다함.
 */

public class G4_BOJ_2240_자두나무 {

    static int T, W;
    static int[][] dp;

    
    public static void main(String[] args) throws Exception {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        T = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());

        dp = new int[T+1][W+1];

        for (int i = 1; i <= T; i++) {
            int jadu = Integer.parseInt(br.readLine());

            for (int j = 0; j <= W; j++) {

                // 1. t=1일 때는 초기값 처리
                if (i == 1) {
                    if (j % 2 + 1 == jadu) dp[i][j] = 1;
                    else dp[i][j] = 0;
                    continue;
                }

                // 2. dp[i][j] 기본값은 dp[i-1][j] (이동 안 함)
                dp[i][j] = dp[i-1][j];

                // 3. 이동 가능하면 이동한 경우 비교
                if (j > 0) dp[i][j] = Math.max(dp[i][j], dp[i-1][j-1]);

                // 4. 현재 위치가 자두 떨어진 나무면 +1
                int pos = (j % 2) + 1;
                if (pos == jadu) dp[i][j]++;
            }
        }


        int answer = 0;      
        for(int i=0; i<=W; i++){
            answer = Math.max(answer, dp[T][i]);    
        }

        System.out.println(answer);


    }

}
