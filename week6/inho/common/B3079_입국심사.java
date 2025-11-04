package BJ.gold_5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class B3079_입국심사 {
    static int N,M;
    static long[] field;
    static long max = Integer.MAX_VALUE;
    static long answer = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);
        field = new long[N];
        for (int i = 0; i < N; i++) {
            field[i] = Integer.parseInt(br.readLine());
            max = Math.min(field[i], max);
        }
        max *=M;

        long left = 1;
        long right = max;
        while (left <= right){
            long mid = (left + right)/2;
            long total = 0;
            for(long f : field) { // 시간 때 몇 명이 심사 가능한지 판단
                total += mid/f;
                if (total > M) break;
            }
            if (total>=M){ //가능한 경우
                answer = mid;
                right = mid-1;
            }
            if(total <M) left = mid+1;
        }
        System.out.println(answer);
    }
}
