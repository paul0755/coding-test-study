package week5.yechan.personal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class B1_BOJ_1259_팰린드롬수 {

    static boolean isP;
    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while(true){

            isP = true;
            int N = Integer.parseInt(br.readLine());
            if(N == 0) break;
            String tmp = Integer.toString(N);
            char[] arr = tmp.toCharArray();
            for(int i=0; i<tmp.length(); i++){
                if(arr[i] != arr[tmp.length()-1-i]) isP = false;
            }

            if(isP){
                System.out.println("yes");
            }else{
                System.out.println("no");
            }

            

        }
    }
}
