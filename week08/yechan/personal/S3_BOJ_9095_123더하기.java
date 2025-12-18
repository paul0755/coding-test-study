package week8.yechan.personal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class S3_BOJ_9095_123더하기 {

    static int N, T;
    static int[] d = new int[11];
    public static void main(String[] args) throws Exception, IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        T = Integer.parseInt(br.readLine());
        
        d[1] = 1;
        d[2] = 2;
        d[3] = 4;
        for(int i=0; i<T; i++){

            N = Integer.parseInt(br.readLine());

            for(int j=4; j<=N; j++){
                d[j] = d[j-1] + d[j-2] + d[j-3];
            }

            System.out.println(d[N]);

        }

        
    }
    
}
