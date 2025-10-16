package week3.yechan.personal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class G5_BOJ_16926_배열돌리기1 {
    
    static int N,M,R;
    static int[][] map;

    public static void main(String args[]) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        map = new int[N][M];

        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 반시계 R만큼 회전
        for(int i=0 ; i<R; i++){
            rotate(0,0,N-1,M-1);
        }

        StringBuilder sb = new StringBuilder();
        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                sb.append(map[i][j]).append(" ");
            }
            sb.append("\n");
        }

        System.out.println(sb);

    }

    private static void rotate(int ly, int lx, int ry, int rx) {
        
        while(ly<ry && lx<rx){

            // 맨 왼쪽위 값 임시저장
            int temp = map[ly][lx];
            
            // 위 : 오른쪽 -> 왼쪽 (미는방향)
            // 접근 순서(왼쪽부터)
            for(int i=lx; i<rx; i++ ){
                map[ly][i] = map[ly][i+1];
            }

            // 오른쪽 : 아래 -> 위
            for(int i=ly; i<ry; i++){
                map[i][rx] = map[i+1][rx];
            }

            // 아래 : 왼쪽 -> 오른쪽
            for(int i=rx; i>lx; i--){
                map[ry][i] = map[ry][i-1];
            }

            // 왼쪽 : 위 -> 아래
            for(int i=ry; i>ly; i--){
                map[i][lx] = map[i-1][lx];
            }

            map[ly+1][lx] = temp;

            ly ++;
            lx ++;
            ry --;
            rx --;
        }
    }
}

// 반시계방향 : 위 -> 오 -> 아 -> 왼
// 시계방향 : 왼 -> 위 -> 오 -> 아
