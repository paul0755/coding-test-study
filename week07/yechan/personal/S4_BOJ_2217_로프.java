package week7.yechan.personal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class S4_BOJ_2217_로프 {

    static int N;
    static int[] rope;

    public static void main(String[] args) throws NumberFormatException, IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        rope = new int[N];

        for(int i=0; i<N; i++){
            rope[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(rope);

        int maxWeight = 0;
        for(int i=0; i<N; i++){
            int possible = rope[i] * (N -i);
            if(possible > maxWeight) maxWeight = possible;
            
        }

        System.out.println(maxWeight);

    }
}