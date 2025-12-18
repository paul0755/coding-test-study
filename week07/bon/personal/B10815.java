package baekjoon.binarysearch;

import java.util.Arrays;
import java.util.Scanner;

public class BaekJoon_10815 {

    static int N,M;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        int[] cards = new int[N];
        for (int i = 0; i < N; i++) {
            cards[i] = sc.nextInt();
        }

        Arrays.sort(cards);

        M = sc.nextInt();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < M; i++) {
            int target = sc.nextInt();
            sb.append(binarySearch(cards, target) ? "1 " : "0 ");
        }

        System.out.println(sb.toString());
    }

    private static boolean binarySearch(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;

        while (left <= right) {
            int mid = (left + right) / 2;

            if (arr[mid] == target) return true;
            else if (arr[mid] < target) left = mid + 1;
            else right = mid - 1;
        }
        return false;
    }


}
