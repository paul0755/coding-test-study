package week8.yechan.personal;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
1차시도 : 실패

문제 : 입출력을 최대한 줄여도 시간초과
아마도 DP를 사용하지않아서 그런거같은데 왜 DP를 사용해야하는지 몰겠음
N과 M이 10만이기에 O(NM)이면 100억이라 시간초과

해결 : dp를 이용
dp[i] = 1번 인덱스부터 i번 인덱스까지 누적합
dp[b] = arr[1] + arr[2] + ... + arr[a-1] + arr[a] + ... + arr[b]
우리가 필요한 부분 a ~ b 구간 합
dp[b] - dp[a-1] -> 우리가 구하고자 하는 답

중요 : prefix sum(누적합)을 다루는 문제로
입력이 커질 때, 성능을 높이는 방법으로 기억해두면 좋다.
*/

public class S3_BOJ_11659_구간합구하기4 {

    static int[] arr;
    static int[] dp;
    static int N, M;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws Exception {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N+1];
        dp = new int[N+1];
        
        dp[0] = 0;
        st = new StringTokenizer(br.readLine());
        for(int i=1; i<=N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
            dp[i] = dp[i-1] + arr[i];
        }

        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            
            sb.append(dp[b] - dp[a-1]).append("\n");
            
        }

        System.out.println(sb);

    }
}