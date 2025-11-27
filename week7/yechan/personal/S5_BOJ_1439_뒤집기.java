package week7.yechan.personal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class S5_BOJ_1439_뒤집기 {

    static String s;
    static int zero_cnt = 0, one_cnt = 0;
    static boolean zero_cont = false, one_cont = false;
    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        s = br.readLine();

        for(char c : s.toCharArray()){

            if(c == '0' && !zero_cont ){
                one_cont = false;
                zero_cont = true;
                zero_cnt++;
            }

            if(c == '1' && !one_cont){
                zero_cont = false;
                one_cont = true;
                one_cnt++;
            }
        }

        int result = Math.min(zero_cnt, one_cnt);

        System.out.println(result);
        

    }
}