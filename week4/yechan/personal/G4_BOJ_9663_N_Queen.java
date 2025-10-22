package week4.yechan.personal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class G4_BOJ_9663_N_Queen {

    static int N, cnt =0;
    static boolean[] issued1; // 열
    static boolean[] issued2; // 좌측하단 - 우측상단 (대각선)
    static boolean[] issued3; // 좌측상단 - 우측하단 (대각선)
    public static void main(String[] args) throws Exception, IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        issued1 = new boolean[N];
        issued2 = new boolean[N+N];
        issued3 = new boolean[N+N];

        dfs(0);
        System.out.println(cnt);
    }
    private static void dfs(int cur) { // cur은 y좌표
        
        if(cur == N){
            cnt ++;
            return;
        }

        for(int i=0; i<N; i++){ // i는 x좌표 
            if(issued1[i]  || issued2[i+cur]  || issued3[i-cur+N-1] )
            continue;

            issued1[i] = true;
            issued2[i+cur] = true;
            issued3[i-cur+N-1] = true;
            dfs(cur+1);
            issued1[i] = false;
            issued2[i+cur] = false;
            issued3[i-cur+N-1] = false;
        }        
    }
}
