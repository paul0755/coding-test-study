package weak11.yechan.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class G5_BOJ_3020_개똥벌레 {
    
    static int N, H;
    static List<Integer> bottom, top;
    static List<Integer> result = new ArrayList<>();
    
    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        bottom = new ArrayList<>();
        top = new ArrayList<>();

        for(int i=0; i<N; i++){
            int h = Integer.parseInt(br.readLine());

            if(i%2 == 0){
                bottom.add(h);
            }else{
                top.add(h);
            }
        }

        Collections.sort(bottom);
        Collections.sort(top);

        int min = Integer.MAX_VALUE;
        int cnt =0;

        for(int h=1; h<=H; h++){

            int crashB = count(bottom, h);
            int crashT = count(top, H-h+1);
            int crash = crashB + crashT;

            if(crash < min){
                min = crash;
                cnt = 1;
            }else if(crash == min){
                cnt ++;
            }
        }

        System.out.println(min + " " + cnt);

    }

    private static int count(List<Integer> list, int x) {
        
        int idx = lowerBoundary(list, x);
        return list.size()-idx;

    }

    private static int lowerBoundary(List<Integer> list, int x) {
        
        int lo=0;
        int hi = list.size();
        while(lo < hi){
            int mid = lo + (hi-lo) / 2;
            if(list.get(mid) >= x) hi = mid;
            else lo = mid + 1;
        }
        return lo;
    }
}
