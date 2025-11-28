package week8.yechan.personal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 생각 흐름
 * d[i][j] = i번째부터 j번째 최대합으로
 * 보고 시작을 하니 점화식이 세워지지가 않았다.
 * 
 * 이 문제는 뒤에서부터 봐야하는 문제였다.
 * 
 * d[i] = i번째 원소로 끝나는 원소합의 최대
 * 경우
 * i) arr[i]            
 * ii) dp[i-1] + arr[i]
 * 이 두가지 밖에없기에
 * dp[i] = Math.max(arr[i], dp[i-1]+arr[i])
 * 
 * 마지막 조각을 찾고 경우를 나누는 작업이
 * 눈에 들어오기가 쉽지않다...
 * 
 * 
 */

public class S2_BOJ_1912_연속합 {
    
    static int N;
    static int[] arr;
    static int[] dp;
    public static void main(String[] args) throws Exception, IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st ;

        N = Integer.parseInt(br.readLine());

        arr = new int[N+1];
        dp = new int[N+1];

        
        st = new StringTokenizer(br.readLine());
        for(int i=1; i<=N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }
        
        dp[1] = arr[1];
        if(N == 1){
            System.out.println(dp[1]);
            return;
        }
        for(int i=2; i<=N; i++){
            dp[i] = Math.max(dp[i-1]+arr[i], arr[i]);
            //System.out.println("dp:" + dp[i]);
        }

        int answer = -1000;
        for(int i=1; i<=N; i++){
            answer = Math.max(answer,dp[i]);
        }

        System.out.println(answer);
        
        
    }
}
