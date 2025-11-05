package week6.yechan.personal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 
 * 1. A,B에대해 정렬을 해주지않아서 틀렸음.
 * 
 */

public class S4_BOJ_1822_차집합 {

    static int N, M;
    static long[] A, B;
    static List<Long> list = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        A = new long[N];
        B = new long[M];

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            A[i] = Long.parseLong(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<M; i++){
            B[i] = Long.parseLong(st.nextToken());
        }

        Arrays.sort(B);
        Arrays.sort(A);

        for(long a : A){
            if(!binarySearch(B, a)) list.add(a);
        }

        System.out.println(list.size());
        for(long n : list){
            System.out.print(n + " ");
        }



    }
    private static boolean binarySearch(long[] B, long a) {
        
        // 집합의 원소가 21억 자연수이기에 int -> long
        int lo = 0;
        int hi = B.length-1;

        while(lo <= hi){
            int mid = (lo+hi) / 2;

            if(B[mid] < a){
                lo = mid + 1;
            }

            else if (B[mid] > a){
                hi = mid - 1;
            }
            else{
                return true;
            }
        }
        return false;
    }
}
