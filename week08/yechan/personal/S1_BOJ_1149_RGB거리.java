package week8.yechan.personal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 1차 시도 : 실패
 * 
 * 문제 : 테이블 정의를 할 때, 1차원 배열로 접근하여서
 * dp[i] = i번째까지 색을 칠했을 때 최소비용 
 * 으로 접근하니 점화식을 세울수가 없었음 -> why? 색상이 같으면 안되었기에
 * 
 * 해결 : 2차원 배열 dp로 변경
 * dp[i][0] -> i번째 까지 색을 칠했을 때 최소비용, 단 i번째는 빨강색
 * dp[i][1] -> i번째 까지 색을 칠했을 때 최소비용, 단 i번째는 초록색
 * dp[i][2] -> i번째 까지 색을 칠했을 때 최소비용, 단 i번째는 파랑색 
 * 
 * 점화식
 * D[k][0] = min(d[k-1][1], d[k-1][2]) + R[k]
 * D[k][1] = min(d[k-1][0], d[k-1][2]) + G[k]
 * D[k][2] = min(d[k-1][0], d[k-1][1]) + B[k]
 * k번째까지 칠했을 때 최소비용을 구하기위해선
 * k-1번째까지 칠했을 때 최소비용을 구해야하는데, 이때 k의 색을 정하고
 * 이후 k-1번째중에서 남은 색중 더 값이 작은거를 구한다.
 * 
 * 초기값
 * d[1][0] = R[1]
 * d[1][1] = G[1]
 * d[1][2] = B[1]
 * 
 * 
 * 
 * 
 */

public class S1_BOJ_1149_RGB거리 {

    static int N;
    static int[] R, G, B;
    static int [][] arr;
    static int [][] dp;
    
    public static void main(String[] args) throws Exception, IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        arr = new int[N][3];
        dp = new int[N+1][3];

        for(int i=0; i<N; i++ ){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<3; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        R = new int[N+1];
        G = new int[N+1];
        B = new int[N+1];
        
        for(int i=1; i<=N; i++ ){
            R[i] = arr[i-1][0];
        }
        for(int i=1; i<=N; i++ ){
            G[i] = arr[i-1][1];
        }
        for(int i=1; i<=N; i++ ){
            B[i] = arr[i-1][2];
        }

        dp[1][0] = R[1];
        dp[1][1] = G[1];
        dp[1][2] = B[1];

        for(int i=2; i<=N; i++){

            dp[i][0] = Math.min(dp[i-1][1], dp[i-1][2]) + R[i];
            dp[i][1] = Math.min(dp[i-1][0], dp[i-1][2]) + G[i];
            dp[i][2] = Math.min(dp[i-1][0], dp[i-1][1]) + B[i]; 
        }

        int result = Math.min(dp[N][0], dp[N][1]);
        result = Math.min(result, dp[N][2]);

        System.out.println(result);

    }
}
