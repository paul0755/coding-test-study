package week8.yechan.personal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*

생각 흐름
N이 작아서 이중포문으로 구간을 비교하면서 최대합을 구하려했는데
이게 떨어져있어도 연속되는 수여야하는데
연속되는 것에 빠져서 살짝 돌아갔음

(gpt 도움)
dp[i] = i번째를 마지막으로 부분수열의 합
각 구간에서 앞에 자기자신보다 작은 숫자가 없는경우가 최솟값이기에
모든 구간을 for문을 통해 최솟값을 가지게한다.

그러고 i구간에서 0-j-i인 j가 arr[i]보다 작을경우
dp를 갱신해주는 방향으로 로직을 설계하였다.

**POINT**
연속되는 길이 
부분수열의 합
점화식이 다르다!
*/

public class S2_BOJ_11055_가장큰증가하는부분수열 {

    static int N;
    static int[] arr;
    static int[] dp;
    
    public static void main(String[] args) throws Exception, IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        arr = new int[N];
        dp = new int[N+1];

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        // i번째로 끝나는 부분수열의 최대합
        // 최솟값은 각 구간에서 하나만 택한
        // 즉, 자기자신을 초기값으로 가짐
        for(int i=0; i<N; i++){
            dp[i] = arr[i];
        }

        for(int i=0; i<N; i++){
            for(int j=0; j<i; j++){

                if(arr[j] < arr[i]){

                    dp[i] = Math.max(dp[i], dp[j] + arr[i]);

                }
            }
        }

        int answer = 0;
        for(int i=0; i<N; i++){
            answer = Math.max(answer, dp[i]);
        }

        System.out.println(answer);


    }

}
