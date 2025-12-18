package week6.yechan.personal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class S2_BOJ_2805_나무자리기 {
    
    static int N, M;
    static int[] hei;

    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        hei = new int[N];

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N ; i++){
            hei[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(hei);

        long lo = 1;
        long hi = hei[N-1];
        long result = 0;

        while(lo <= hi){
            
            long mid = (lo + hi) / 2;
            long cnt = 0;

            for(int h : hei){
                if(h >= mid){
                    // mid의 높이만큼을 제외한 나무의 길이를 얻음.
                    cnt += h - mid;
                }
            }

            if(cnt >=M ){
                result = mid;
                lo = mid + 1;
            }

            else if(cnt < M){
                hi = mid - 1;
            }
        }

        System.out.println(result);

    }
}
