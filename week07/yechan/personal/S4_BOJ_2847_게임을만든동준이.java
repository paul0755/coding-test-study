package week7.yechan.personal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * 첫시도 (성공)
 * -> 뒤에서 접근하니까 신기하게도 모든 상황이 해결되었음
 * N이 100이하라서 완전탐색도 가능할것같아서 앞뒤를 비교하는 상황을
 * 진행하려다가 아무리 생각해도 안될것같아서
 * 뒤에서부터 천천히 빼면서 내려오는 상황으로 바꾸니 해결되었음.
 * 또한 if문에서 while문으로 바꾼 부분이 핵심이었다고 생각함.
 * 
 */

public class S4_BOJ_2847_게임을만든동준이 {

    static int N;
    static int[] level;
    public static void main(String[] args) throws NumberFormatException, IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        level = new int[N];

        for(int i=0; i<N; i++){
            level[i] = Integer.parseInt(br.readLine());
        }

        int cnt = 0;
        for(int i=N-1; i>0; i--){
            while(level[i] <= level[i-1]){
                level[i-1] --;
                cnt ++;
            }
        }

        
        // for(int i=0; i<N; i++){
        //     System.out.print(level[i] + " ");
        // }
        
        //System.out.println();
        System.out.println(cnt);


    }
}
