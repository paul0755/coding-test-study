package week7.yechan.personal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class S4_BOJ_11047_동전0 {

    static int N, K, cnt = 0;
    static int[] select;

    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        select = new int[N];

        for(int i=0; i<N; i++){
            select[i] = Integer.parseInt(br.readLine());
        }

        // 동전 개수의 최솟값

        for(int i = N-1 ; i>= 0 ; i--){

            if(select[i] <= K){
                
                cnt += (K/select[i]);
                K = K % select[i];
            }
        }

        System.out.println(cnt);
        

    }
}