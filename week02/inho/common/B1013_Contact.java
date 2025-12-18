package BJ.gold_5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

// 그냥 정규식 설명함.
// +는 뒤에 여러 개 올 수 있음.
// |는 또는
// ()는 그룹
public class B1013_Contact {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        String pattern = "(100+1+|01)+";

        for (int i = 0; i < T; i++) {
            String input = br.readLine();
            if (input.matches(pattern)) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
        }
    }
}
