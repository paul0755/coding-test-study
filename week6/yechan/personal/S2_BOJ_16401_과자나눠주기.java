package week6.yechan.personal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class S2_BOJ_16401_과자나눠주기 {

    static int N, M;
    static int[] arr;
    
    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        arr = new int[N];

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        long lo = 1;
        long hi = arr[arr.length-1];
        long result = 0;

        while(lo<=hi){

            long mid = (lo + hi) /2;
            long cnt = 0;
            
            for(int l : arr ){
                cnt += l / mid;
                //System.out.println("cnt : " + cnt);
            }

            if(cnt >= M){
                result = mid;
                lo = mid + 1;
               //System.out.println("result : " + result);
            }
            else if(cnt < M){
                hi = mid - 1;
            }
        }

        System.out.println(result);
    }
}
