package BJ.gold_4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 사탕의 칼로리가 더 큰 사람이 내기에서 짐.
// 사탕 가격과 칼로리
// 사탕을 중복으로 가져가든, 섞어서 가져가든 총 값을 넘지 않고 최고의 칼로리를 반환.
public class B4781_사탕가게 {
    static int n; // 사탕 종류
    static double m; // 돈의 양
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (true){
            String[] input2 = br.readLine().split(" ");
            if (input2[0].equals("0") && input2[1].equals("0.00")) break;
            n = Integer.parseInt(input2[0]);
            m = Double.parseDouble(input2[1]);
            int money = (int)(m*100 +0.5);
            int[] dp = new int[money + 1]; // 돈을 썼을 때 최대 칼로리 기준으로 저장.

            for (int i = 0; i < n; i++) { // 전체 돌리기
                String[] input3 = br.readLine().split(" ");
                int c = Integer.parseInt(input3[0]);
                double pD = Double.parseDouble(input3[1]);

                int p = (int)(pD*100+0.5);

                for (int j = p; j <= money ; j++) { // 내 값의 기준부터 확인해서 끝까지 돌기.
                    dp[j] = Math.max(dp[j], dp[j-p]+c); // 1원씩 증가하면서 각 경우의 최고 칼로리를 저장함.
                }
            }
            System.out.println(dp[money]);
        }
    }
}
