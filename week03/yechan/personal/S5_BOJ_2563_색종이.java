package week3.yechan.personal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class S5_BOJ_2563_색종이 {

    static int N;
    static int[][] map = new int[100][100];
 
    public static void main(String[] args) throws Exception, IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());            
            
            for(int j = x; j< x+10; j++){
                for(int k= y; k < y+10; k++){
                    map[j][k] = 1;
                }
            }
        }

        int sum = 0;
        for(int i=0; i<100; i++){
            for(int j=0; j<100; j++){
                if(map[i][j] == 1) sum ++;
            }
        }

        System.out.println(sum);

        

        


    }
    
}

/**
 * 💥 놓친 부분 
 * 1. 삽질
 * 
 * 좌표를 하나만 주고 정사각형 구간을 색칠하라길래
 * bfs로 해야하나 고민했었는데 그냥 반복문 구간을 x+10, y+10으로 해서 
 * map을 1로 채워갔으면 됐다. 엄청 둘러서 생각했어서 시간을 엄청 쓴 문제.
 * 
 * 
 */