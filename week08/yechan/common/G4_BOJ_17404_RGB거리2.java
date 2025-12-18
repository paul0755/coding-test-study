package week8.yechan.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
실패 실패 실패 

문제 : 구간마다 조건이 다르기에
구간마다 점화식을 세워서 진행해보려했으나
구간을 나눠도 서로서로 영향을 끼치기에 안된다는 것을 앎
gpt 도움으로 원형 형태임을 앎

해결 : 첫번째 집에 색을 정하고
dp를 총 세번 돌리고
마지막 집과 첫번째집의 색이 다를 때
값을 저장해준다.

*RGB거리에서 사용한 점화식을 이용하기위해
첫번째 집에 색을 정하는 과정에서 다른 색을 배제하기위해
최대값을 저장하여 색을 정하는 과정이 신기함.
+
*마지막 집과 첫번째 집을 비교하여 색이 다를경우에
값을 갱신하여 최솟값을 저장하는 과정도 생각못함.



*/

public class G4_BOJ_17404_RGB거리2 {

    static int N;
    static int[][] cost;
    static int[][] dp;
    static int INF = 1000000000;

    
    public static void main(String[] args) throws Exception, IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st; 

        N = Integer.parseInt(br.readLine());

        dp = new int[N+1][3];
        cost = new int[N+1][3]; 

        for(int i=1; i<=N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<3; j++){
                cost[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int answer = INF;

        for(int first = 0; first<3; first++){

            for(int i=0; i<3; i++){
                dp[1][i] = (i==first) ? cost[1][i] : INF; 
            }

            // RGB 거리 1과 동일
            for (int i = 2; i <= N; i++) {
                dp[i][0] = Math.min(dp[i-1][1], dp[i-1][2]) + cost[i][0];
                dp[i][1] = Math.min(dp[i-1][0], dp[i-1][2]) + cost[i][1];
                dp[i][2] = Math.min(dp[i-1][0], dp[i-1][1]) + cost[i][2];
            }

            // 마지막 집은 첫 집과 같은 색 불가능!!
            for (int lastColor = 0; lastColor < 3; lastColor++) {
                if (lastColor != first) {
                    answer = Math.min(answer, dp[N][lastColor]);
                }
            }
        }

        System.out.println(answer);




    }
}
