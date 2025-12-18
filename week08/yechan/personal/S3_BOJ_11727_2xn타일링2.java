package week8.yechan.personal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*

생각 흐름 
1. D[i] = n이 i일때 방법의 수
2. D[1]...D[4] 까지 그리며 갯수를 구하는데
규칙이 보이지않음 ㅠ
(gpt 도움)

규칙이 보이지 않을때
1. 전체중 마지막 조건이 무엇일지 생각하기
예) 2xn 타일링 2
마지막에 올수있는 경우의 수
1) 2x1 세로 -> D[i-1] 
2) 1x2 가로 -> D[i-2]
3) 2x2 가로 -> D[i-2]
D[i] = D[i-1] + 2*D[i-2] 도출

DP는 패턴을 찾는 문제가아닌 
경우를 나눠서 문제를 푸는 것임을 잊지말기!


*/

public class S3_BOJ_11727_2xn타일링2 {
    
    static int N;
    static int[] dp;
    public static void main(String[] args) throws Exception, IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        if(N == 1){
            System.out.println(1);
            return;
        }

        dp = new int[N+1];

        dp[1] = 1;
        dp[2] = 3;

        for(int i=3; i<=N; i++){
            dp[i] = (dp[i-1] + 2*dp[i-2] ) % 10007;
        }

        System.out.println(dp[N]);

    }
}
