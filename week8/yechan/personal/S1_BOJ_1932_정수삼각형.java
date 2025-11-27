package week8.yechan.personal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*

1차 시도 : 실패

생각 흐름
1. 매번 아래층에서 큰수만 고르는것 x
2. dp인지도 잘 모르겠음..
3. 모든 경로의 합을 보면서 최대를 찾아야함
4. D[i] = i번째까지 선택했을 때, 최대합
5. D[1] = tree[0][0] , D[2] = max(tree[1][0], tree[1][1]) + D[1]
D[3] = ..? 이렇게하면 틀리게됨
고려안한 것 : 이전 선택에서 왼쪽 대각선, 오른쪽 대각선만 접근가능

2차시도 (Gpt 도움)
2차 DP로 시도해야함
dp[i][j] = i번째 행, j번째 열일때 최대합

1. 맨왼쪽 : 이전 행에서 i,j번째 기준 오른쪽에서 밖에 못옴
dp[i][0] = dp[i-1][0] + tree[i][0]

2. 맨오른쪽 : 이전 행에서 i,j번째 기준 왼쪽에서 밖에 못옴
dp[i][i] = dp[i-1][i-1] + tree[i][i]

3. 중간 
dp[i][j] = max(dp[i-1][j-1] , dp[i-1][j]) + tree[i][j]

4. 맨 마지막에 줄에서 가장 큰거 출력 

5. N == 1 일때는 tree[0][0] 출력하고 return

*/

public class S1_BOJ_1932_정수삼각형 {

    static int N;
    static int[][] tree;
    static int[][] dp;
    public static void main(String[] args) throws Exception, IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        tree = new int[N][N];
        dp = new int[N][N];

        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<=i; j++){
                tree[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        if(N == 1){
            System.out.println(tree[0][0]);
            return;
        }

        dp[0][0] = tree[0][0];

        for(int i=1; i<N; i++){
            for(int j=0; j<=i; j++){

                // 맨 왼쪽
                if(j==0){
                    dp[i][j] = dp[i-1][j] + tree[i][j];
                }
                // 맨 오른쪽
                else if(j==i){
                    dp[i][j] = dp[i-1][j-1] + tree[i][j];
                }
                
                // 중간
                else{
                    dp[i][j] = Math.max(dp[i-1][j-1], dp[i-1][j]) + tree[i][j];
                }

            }
        }

        int answer = 0;
        for(int i=0; i<N; i++){
            answer = Math.max(answer, dp[N-1][i]);
        }

        System.out.println(answer);
        

    }
}
