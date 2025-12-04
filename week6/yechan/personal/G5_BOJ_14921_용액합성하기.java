package week6.yechan.personal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class G5_BOJ_14921_용액합성하기 {

    static int N;
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

        int left = 0;
        int right = N-1;

        long bestSum = Long.MAX_VALUE;
        long realSum = 0;

        while(left < right){

            long sum = arr[left] + arr[right];

            if(Math.abs(sum) < bestSum){

                bestSum = Math.abs(sum);
                realSum = sum;
            }

            if(sum > 0) right --;
            else left ++;
        }

        System.out.println(realSum);

    }
}
