package week8.yechan.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
1차 시도 : 실패 ㅠ

생각 흐름
정수형과 실수형 자료형으로 Map자료구조로 저장을 해서
처리해야하나 싶었는데
Map을 사용하면 key가 중복되면 덮어쓰기에 포기.
cal, price를 각자 다른 배열에 저장

D[i] = i원일 때, 칼로리 최대 (여기까진 왔는데..)
제한된 돈이 0원이 될때까지라는 생각과 동시에 칼로리 최대도 해야한다는
생각에 잠겨 진전이 불가능했음 

2차 시도 (gpt) -> gpt를 봐도 이해못함
3차 시도 (블로그) -> https://lympsw12.tistory.com/entry/%EC%82%AC%ED%83%95-%EA%B0%80%EA%B2%8C%EB%B0%B1%EC%A4%804781
위 블로그에서 사탕하나를 받을때 마다
사탕 가격에서 부터 시작해 칼로리를 dp에 저장하는 방식으로 
진행되는 표를 보고 이해했음.

총평 : 너무 어렵다.. 골드 Dp는 정신 못차리겠네

*/

public class G4_BOJ_4781_사탕가게 {

    static int n;
    static double tmp_m;
    static StringBuilder sb  = new StringBuilder();
    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        while(true){

            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            tmp_m = Double.parseDouble(st.nextToken());
            tmp_m *= 100; tmp_m += 0.5;
            int m = (int)tmp_m;
            
            if(n == 0 && m == 0) break;
            
            int dp[] = new int[m + 1];

            for(int i=0; i<n; i++){

                st = new StringTokenizer(br.readLine());
                int cal = Integer.parseInt(st.nextToken());
                double tmp = Double.parseDouble(st.nextToken());
                // 칼로리랑 돈도 따로 저장해 둘 필요없음.
                tmp *= 100; tmp += 0.5;
                int num = (int) tmp;

                if(num > m) continue;

                for(int j = num ; j < m+1 ; ++j){
                    dp[j] = Math.max(dp[j], dp[j-num] + cal);        
                }

            }

            sb.append(dp[m]).append("\n");
            
        }

        System.out.println(sb);

    }
}
