package week6.yechan.personal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 
 * 1. 자료형을 놓쳐서 틀림.
 *  랜선의 길이 = 2^31 - 1 까지 올수있기에 ( 2^31 = 21억 )
 * lo + hi 또한 42억까지 커질수있어서 int범위 초과
 * -> lo,hi,mid는 long 자료형으로 선언해야했음.
 * 
 */

public class S2_BOJ_1654_랜선자르기 {

    static int K, N, count;
    static int[] arr;
    public static void main(String[] args) throws IOException {

        BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());


        K = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        arr = new int[K];

        for(int i=0 ; i<K ; i++){
            arr[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(arr);


        long lo = 1;
        long hi = arr[arr.length-1];
        long result = 0;
        //int i=0;

        while(lo<=hi){
            count = 0;
            long mid = (lo+hi)/2;

            for(int len : arr){
                count += len / mid;
            }

            if(count >= N){
                result = mid;
                //System.out.println(result);
                lo = mid +1;
            }else{
                //i++;
                //System.out.println(i);
                //System.out.println("mid:" + mid);
                hi = mid -1;
            }
        }


        System.out.println(result);
    }
}
