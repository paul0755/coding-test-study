package BJ.gold_4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// ppap 만들기.

public class B16120_PPAP {
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split("");

        for (String num : input) {
            sb.append(num);
            if (sb.length() >= 4) {
                if (sb.substring(sb.length()-4).equals("PPAP")) {
                    sb.delete(sb.length() - 4, sb.length());
                    sb.append('P');
                }
            }
            //System.out.println(sb);
        }
        if (sb.length()==1 && sb.toString().equals("P")){
            System.out.println("PPAP");
        }else {
            System.out.println("NP");
        }
    }
}
