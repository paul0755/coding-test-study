package week3.yechan.personal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class S5_BOJ_1475_방번호 {
    
    static String N;
    static int[] count = new int[10];
    public static void main(String args[]) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = br.readLine();

        for(char c : N.toCharArray()){
            count[c-'0']++;
        }

        int count_69 = (count[6] + count[9]+1) / 2;
        count[6] = count[9] = count_69;

        int set_count = 0;
        for(int i =0; i<10; i++){
            set_count = Math.max(set_count, count[i]);
        }

        System.out.println(set_count);
    }
}
