package baekjoon.greedy;

import java.util.Scanner;

/**
 * 문제를 많이 헤맸고 어떻게 풀지를 몰라 dp를 사용하였다.
 * 이 문제의 핵심은 어린이 숫자가 랜덤으로 주어질때 연속 증가 수열의 길이를 체크할 수 있어야한다.
 * 체크하기 위해 DP를 사용
 * DP[i] = DP[i-1] +1 점화식
 * 나보다 1 작은 숫자가 작은수열을 가지고 있는지 체크하여 더해준다.
 * 최장수혈의 길이에 속한 나머지 숫자들 갯수만큼 움직여주면 쉽게 순서대로 정렬 가능하다.
 */
public class BaekJoon_7570 {

    static int N;
    static int[] arr;
    static int[] dp;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();

        arr = new int[N+1];
        dp = new int[N+1];

        for (int i = 1; i < N+1; i++) {
            arr[i] = sc.nextInt();
        }

        for (int i = 1; i < N+1; i++) {
            dp[arr[i]] = dp[arr[i]-1] +1;
        }

        int max =0;
        for (int i = 1; i < N+1; i++) {

            max = Math.max(max,dp[i]);
        }

        int answer = N - max;

        System.out.println(answer);

    }
}
