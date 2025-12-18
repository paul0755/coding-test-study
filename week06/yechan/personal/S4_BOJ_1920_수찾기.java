package week6.yechan.personal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 
*이분탐색을 하기위해선 배열이 반드시 정렬되어있어야함.
 * 
 */

public class S4_BOJ_1920_수찾기 {

    static int N, M;
    static int[] arr;
    public static void main(String[] args) throws Exception, IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        arr = new int[N];

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        StringBuilder sb = new StringBuilder();

        M = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<M; i++){
            if(binarySearch(Integer.parseInt(st.nextToken()))>=0 ){
                sb.append(1).append("\n");
            }
            else{
                sb.append(0).append("\n");
            }
        }

        System.out.println(sb);


        
        
    }
    private static int binarySearch(int key) {
        
        int lo = 0;
        int hi = arr.length-1;

        while(lo<=hi){
            int mid = (lo+hi) / 2;

            if(arr[mid] > key){
                hi = mid -1;
            }

            else if(arr[mid] < key){
                lo = mid + 1;
            }

            else{
                return mid;
            }

        }

        return -1;
    }
}