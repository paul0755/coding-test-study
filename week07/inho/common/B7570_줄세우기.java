package BJ.gold_2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// 줄을 오름차순으로 세우는데 최소 횟수로 세우기
// 음..
// 못풉니다.
public class B7570_줄세우기 {
    static int N;
    static int[] list;
    static int[] dp;
    static int max = Integer.MIN_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        list = new int[N+1];
        dp = new int[N+1];
        String[] input = br.readLine().split(" ");
        for (int i = 0; i < N; i++) {
            list[Integer.parseInt(input[i])] = i;
        }
        for (int i = 1; i <= N; i++) {
            if (list[i-1] < list[i] ) dp[i] = dp[i-1]+1;
            else dp[i] = 1;
            max = Math.max(dp[i],max);
        }

        System.out.println(list.length-1 - max);
    }
}
