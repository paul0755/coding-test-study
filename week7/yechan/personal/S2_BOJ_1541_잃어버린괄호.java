package week7.yechan.personal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 
 * String.split("")에서 에러나는 특수기호들
 * . * + ? | ^ $ ( ) [ ] { } \
 * 위 문자들은 "\\." , "\\*" 으로 이스케이프를 넣어줘야 인식됨
 * 
 */

public class S2_BOJ_1541_잃어버린괄호 {

    static String poly;
    //static boolean isNegative = false;
    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        poly = br.readLine();

        String[] num = poly.split("-");

        // '-'를 기준으로 그룹을 나눠서 더해주면 최솟값을 이루는 것 같다.


        
        int sum = 0;
        sum += addGroup(num[0]);
        for(int i=1; i<num.length; i++){
            sum -= addGroup(num[i]);
        }

        System.out.println(sum);

        

    }
    private static int addGroup(String str) {
        
        int result = 0;
        if(str.contains("+")){
            String[] tmp = str.split("\\+");
            for(String t : tmp){
                result += Integer.parseInt(t);
            }
        }else {result += Integer.parseInt(str);}

        return result;
    }
}


