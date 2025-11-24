package week8.yechan.personal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
1차 시도 : 실패

*타일이 어떻게 배치되는지 이해되지않아 무작정 n이 1일때부터
5일때까지 그려보며 결과를 보던 중
n을 1과 2로 만드는 방법의 수와 같음을 느낌
d[i] = d[i-2] + d[i-1]임을 눈으로 확인

문제 : N이 1일때를 생각하지 못함.
1일경우 위에서 처리해주지않으면 dp[2] = 2;부분에서
ArrayIndexBoundary오류 발생

해결 : if 문으로 N ==1일때는 1출력과 함께 return
+
문제 : dp에 10,007을 나눈 나머지를 저장해야함
점화식이 피보나치수와 같기에 지수적으로 증가함
그렇기에 연산과정에서 스택 오버플로우가 발생할수있기에
10,007로 나눈 나머지를 dp배열에 저장해야했음.

해결 : dp[i] = (dp[i-2] + dp[i-1]) % 10,007

*/

public class S3_BOJ_11726_2xn타일링 {

    static int N;
    static long[] dp;
    public static void main(String[] args) throws Exception, IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        dp = new long[N+1];

        if(N == 1){
            System.out.println(1);
            return;
        }

        dp[1] = 1;
        dp[2] = 2;

        for(int i=3; i<=N; i++){
            dp[i] = (dp[i-2] + dp[i-1]) % 10007;
        }

        System.out.println(dp[N]);

        

    }
}
