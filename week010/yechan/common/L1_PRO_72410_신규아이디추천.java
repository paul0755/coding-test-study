package week10.yechan.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
/*

str.replaceAll() 은 원본을 바꾸지 않는다.

*/

public class L1_PRO_72410_신규아이디추천 {

    static String new_id;
    public static void main(String[] args) throws Exception {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        new_id = br.readLine();

        
        // 1) 대문자 -> 소문자
        new_id = new_id.toLowerCase();

        // 2) 알파벳 소문자 ,빼기(-), 밑줄(_), 마침표(.)를 제외한 문자 제거
        new_id = new_id.replaceAll("[^a-z0-9-_.]", "");
        
        // 3)  ".." 마침표 두개 이상은 '.' 마침표 하나로 변환
        new_id = new_id.replaceAll("[.]{2,}", ".");
        
        // 4)  맨 앞, 맨 뒤 마침표제거
        new_id = new_id.replaceAll("^[.]|[.]$", "");
        
        // 5)  빈 문자열일 경우 'a'대입
        if(new_id.isEmpty()){
            new_id = "a";
        }

        // 6) 16자 이상이면, 15개의 문자만큼 남게 뒤에부분 제거
        if(new_id.length() > 15){
            new_id = new_id.substring(0, 15);
        }

        // 7)  2자 이하이면, 마지막 문자를 복제(길이가 3일될때까지)
        if(new_id.length() <= 2){
            String tmp = new_id.substring(new_id.length()-1);
            while(new_id.length() < 3){
                new_id = new_id.concat(tmp);

                // concat 대신 new_id += tmp도 가능
            }
        }

        
        

        System.out.println(new_id);
        

    }
}