package baekjoon.binarysearch;

import java.util.Arrays;
import java.util.Scanner;

public class BaekJoon_1920 {

    static int N,M;
    static int[] arr;
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = sc.nextInt();
        }

        Arrays.sort(arr);

        M = sc.nextInt();
        while(M-->0){
            int temp = sc.nextInt();

            int st = 0;
            int end = N-1;

            boolean isTrue = false;
            while(st<=end){
                int mid = (st+end)/2;

                if(arr[mid] < temp){
                    st = mid+1;
                }else if(arr[mid]>temp){
                    end = mid-1;
                }else{
                    isTrue = true;
                    break;
                }
            }
            if(isTrue){
                System.out.println(1);
            }else{
                System.out.println(0    );
            }

        }
    }
}
