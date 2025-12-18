package week8.yechan.personal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class S3_BOJ_1463_1로만들기 {
    /*
    일단 시간제한 : 0.15초 처음봄
    
    생각흐름
    1. 3으로 나눠지는 것과 1로 뺀것중 최솟값 선택
    2. 2로 나눠지는 것과 1로 뺀것중 최솟값 선택
    -> 3과 2로도 안나눠지는 수를 계산 못함
    
    해결: 1로 빼는 연산은 모든 수에 가능하기때문에
    dp[i] = dp[i-1] + 1으로 테이블을 짜고
    3과 2로 나눠지는 수는 나눠서 값을 가져올 때
    더 작으면 갱신해주는 방향으로 로직 수정
    

    */

    static int N;
    static int[] dp;

    public static void main(String[] args) throws Exception, IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        dp = new int[N+1];

        dp[1] = 0;

        for(int i=2; i<=N; i++){

            // 모든 수에 대해 가능한 연산이기에 저장
            dp[i] = dp[i-1] + 1; 


            // 2로 나눠지는 수에 경우 뭐가 더 적은 연산인지에 따라 갱신
            if(i%2 ==0){
                dp[i] = Math.min(dp[i/2] + 1, dp[i]);
            }

            if(i%3 == 0){
                dp[i] = Math.min(dp[i/3]+1, dp[i] );
            }
        }

        System.out.println(dp[N]);

    }
}
