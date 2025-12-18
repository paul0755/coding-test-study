package week6.yechan.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class G5_BOJ_3079_입국심사 {

    static int N, M, T=0;
    static int[] arr;
    
    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());    
        

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N];

        for(int i=0; i<N; i++){
            arr[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(arr);

        // 시간을 기준으로 이분탐색
        long lo = 1; // 최소시간 : 1분
        long hi = (long)arr[N-1] * M; // 최대시간
        // 최대시간 : 가장 느린 심사관이 모든 사람 M명을 다 심사할때 
        long answer = hi;


        while(lo <= hi){
            long mid = (lo+hi) /2;
            
            long people = 0;
            for(int time : arr){
                people += mid/time;
                if(people>=M) break;
            }

            if(people >=M){
                answer = mid;
                // 최대 시간을 낮춰서 진행 -> 최소값 찾기
                hi = mid -1;
            }else{
                // 인원을 다 채우지못해 최소시간 올리기
                lo = mid + 1;
                //System.out.println("lo:" + lo);
            }
        }

        System.out.println(answer);
    }
}
