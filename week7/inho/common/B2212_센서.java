package BJ.gold_5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// K개의 집중국 놓기
// 가장 긴 길이를 뺀다고 접근.
public class B2212_센서 {
    static int N, K;
    static int[] field;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        K = Integer.parseInt(br.readLine());
        field = new int[N];
        String[] input = br.readLine().split(" ");
        for (int i = 0; i < input.length; i++) {
            field[i] = Integer.parseInt(input[i]);
        }

        Arrays.sort(field);
        int[] arr = new int[N - 1];
        for (int i = field.length - 1; i > 0; i--) {
            arr[i - 1] = field[i] - field[i - 1];
        }
        Arrays.sort(arr);
        int result = 0;
        for (int i = 0; i < arr.length - (K - 1); i++) {
            result += arr[i];
        }

        System.out.println(result);
    }
}
