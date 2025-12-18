package week2.yechan.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 1트 -> 정규식으로 접근하였더니 틀렸다.
// 놓친부분 : PPAP 내부에 PPAP구조가 무한히 생길 수 있다는 점
// 해결 : Stack / StringBuilder or 그리디 축소


public class G4_BOJ_16120 {
    
    static String pt = "^(PP)(APP|PAP)+(AP)?$";
    public static void main(String args[]) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String s = br.readLine();

        // 그리디 축소로 문제 풀이
        // while (s.contains("PPAP")) s = s.replaceAll("PPAP", "P");
        
        // System.out.println(s.equals("P") ? "PPAP" : "NP");

        // Stack / StringBuilder 문제 풀이  
        StringBuilder sb = new StringBuilder();
        for(char c : s.toCharArray()){
            sb.append(c);
            if(sb.length()>=4 && sb.substring(sb.length()-4).equals("PPAP")){
                sb.delete(sb.length()-4, sb.length());
                sb.append('P');
            }
        }
        System.out.println(sb.toString().equals("P")? "PPAP": "NP");

    }
}
