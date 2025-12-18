package week8.yechan.personal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
생각 흐름

D[i] = i자리 이친수 개수
D[1] = 1, D[2] = 1
마지막 i번째에 
i) 1이 올 경우 
ii) 0가 올 경우
*but, 맨 마지막에 1이 오면 앞에 0만 올수있기에
사건이 독립적이지 않음
-> 이땐, 경우를 더 나눠야함!

A[i] = i번째에 0
B[i] = i번째에 1

//     (i-1에 0) / (i-1에 1)
A[i] = A[i-1]    +  B[i-1]
B[i] = A[i-1] (0만 가능)

D[i] = A[i] + B[i]
A[i-1]+B[i-1] + A[i-1]
D[i-1] + A[i-1]
A[i-1]이면 또 앞에서 0,1을 둘수있는 전체조건이기에
D[i] = [i-1] + D[i-2]

**** 마지막 조건이 독립적이지 않을 때 *******
마지막 조각이 앞조각에 의존적일때는
경우를 더 나눠야함!! 
위처럼 A,B로 상태를 나눠서 바라볼수있어야함.

!! 자료형
피보나치는 크기가 엄청나게 커지니까 long타입을 써야함!

*/

public class S3_BOJ_2193_이친수 {

    static int N;
    static long[] dp;
    public static void main(String[] args) throws Exception, IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        if(N == 1){
            System.out.println(1);
            return;
        }

        dp = new long[N+1];

        dp[1] = 1;
        dp[2] = 1;
        for(int i=3; i<=N; i++){
            dp[i] = dp[i-1] + dp[i-2];
        }

        System.out.println(dp[N]);

    }
}
