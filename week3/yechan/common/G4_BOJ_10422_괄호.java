package week3.yechan.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class G4_BOJ_10422_괄호 {

    static int T, L;
    static long[] dp;
    // long 자료형을 쓰는이유는 "오버플로우" 때문
    static final int MOD = 1_000_000_007;
    public static void main(String args[]) throws Exception, IOException{
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        T = Integer.parseInt(br.readLine());

        dp = new long[2501]; // L/2 = 2500이기에 2501크기.
        dp[0] = 1;
        for(int i=1 ; i<= 2500; i++){ // 괄호쌍 1~2500까지
            for(int j=0; j<i ; j++){ // (A)의 쌍 개수 j
                dp[i] = (dp[i]+dp[j] * dp[i-1-j]) % MOD;
                // 맨 바깥 한쌍을 쓰면 i-1
                // 이 i-1쌍을 A와 B가 나눠씀
                // A가 j쌍을 내부에서 썼다면
                // B가 남은 (i-1-j)쌍을 사용해야함.
            }
        }

        for(int i=0; i<T; i++){
            L = Integer.parseInt(br.readLine());
            galho(L);
        }
        
    }
    private static void galho(int l) {

        int i = L/2; // i는 전체 괄호쌍 수

        if(l%2 == 1){
            System.out.println(0);
        }else{
            System.out.println(dp[i]);
        }
    }
    
}

/*
카탈란 수(Catalan Number)는 
괄호 문제, 이진트리 구조, 경로 문제 등 수많은 조합 문제의 기본이야.
 * 💡 카탈란 수(Catalan Number)란?

어떤 구조적 규칙을 가진 조합의 개수를 세는 수열이야.
대표적으로 올바른 괄호 문자열의 개수를 나타내지!

 * 
 */