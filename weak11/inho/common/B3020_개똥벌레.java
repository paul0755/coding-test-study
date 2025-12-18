package BJ.gold_5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 각 높이에 따른 누적된 그 이상의 값들을 구해놓고, 2 배열을 더해가면서 최소값 세는 거
public class B3020_개똥벌레 {

    public static void main(String[] args) throws IOException {
        BufferedReader br =  new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");
        int N = Integer.parseInt(input[0]);
        int H = Integer.parseInt(input[1]);

        int[] down = new int[H + 1];
        int[] up = new int[H + 1];

        // 입력을 받으면서 홀짝으로 석순과 좆ㅇ류석의 길이를 받고, 길이의 개수를 셈.
        for (int i = 0; i < N; i++) {
            int h = Integer.parseInt(br.readLine());
            if (i % 2 == 0) {
                down[h]++;
            } else {
                up[h]++;
            }
        }

        // 뒤에서부터 누적합. 해당 길이 이상의 개수 세기.
        for (int i = H - 1; i >= 1; i--) {
            down[i] += down[i + 1];
        }


        for (int i = H - 1; i >= 1; i--) {
            up[i] += up[i + 1];
        }

        int min = Integer.MAX_VALUE;
        int count = 0;

        for (int h = 1; h <= H; h++) {

            int crash = down[h] + up[H - h + 1]; // 지금 높이에서 부딪히는 장애물 수의 합.

            if (crash < min) { // 이 합이 최소면
                min = crash; // 최소값 갱신하고
                count = 1; // 첫 발견이므로 count 1로 해놓기
            } else if (crash == min) {
                count++; // 최소값이 동일하면 count ++;
            }
        }

        System.out.println(min + " " + count);
    }
}
