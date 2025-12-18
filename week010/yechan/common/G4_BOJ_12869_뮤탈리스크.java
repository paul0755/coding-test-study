package week10.yechan.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 분류 : 탑다운 DP 
 * 
 * 
 check(12,10,4)
  → check(3,7,3) +1
       → check(0,4,2) +1
            → check(0,1,1) +1
                 → check(0,0,0) = 0
            → dp[0][1][1]=1
       → dp[0][4][2]=2
  → dp[3][7][3]=3
→ 최종적으로 min(여섯 방식) 계산 후 dp[12][10][4] 저장
 * 
 * 
 */

public class G4_BOJ_12869_뮤탈리스크 {

    static int N;
    static int[][][] dp;
    static int[] hp = new int[3];
    
    public static void main(String[] args) throws Exception, IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            hp[i] = Integer.parseInt(st.nextToken());
        }

        dp = new int[hp[0]+1][hp[1]+1][hp[2]+1];

        // 3중 배열에 -1을 채우는 다른 방법
        for(int i=0; i<dp.length; i++){
            for(int j=0; j<dp[i].length; j++){
                Arrays.fill(dp[i][j], -1);
            }
        }

        System.out.println(check(hp[0], hp[1], hp[2]));
    }

    private static int check(int i, int j, int k) {
        
        // 0보다 작아지면 scv 죽는거 처리
        int hp1 = Math.max(i, 0);
        int hp2 = Math.max(j, 0);
        int hp3 = Math.max(k, 0);

        // 모두 0이면 끝내는 기저조건
        if(hp1 == 0 && hp2 == 0 && hp3 == 0) return 0;

        // 값이 계산되었다면 
        if(dp[hp1][hp2][hp3] != -1) return dp[hp1][hp2][hp3];
        
        int result = Integer.MAX_VALUE;

        // 모든 경우의 수를 확인해 최적의 수 확인
        result = Math.min(result, check(hp1-9, hp2-3, hp3-1)+1);
        result = Math.min(result, check(hp1-9, hp2-1, hp3-3)+1);
        result = Math.min(result, check(hp1-3, hp2-9, hp3-1)+1);
        result = Math.min(result, check(hp1-3, hp2-1, hp3-9)+1);
        result = Math.min(result, check(hp1-1, hp2-3, hp3-9)+1);
        result = Math.min(result, check(hp1-1, hp2-9, hp3-3)+1);
        //System.out.println("result : "  +result);

        return dp[hp1][hp2][hp3] = result; // 값저장

    }
}
