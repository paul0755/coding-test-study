package week2.yechan.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class G5_BOJ_1013 {

    static int T;
    static String pt = "(100+1+|01)+";

    public static void main(String args[]) throws Exception, IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        T = Integer.parseInt(br.readLine());

        for(int i=0; i<T; i++){
            String tmp = br.readLine();
            if(tmp.matches(pt)){
                System.out.println("YES");
            }else{
                System.out.println("NO");
            }
        }
        
    }
}