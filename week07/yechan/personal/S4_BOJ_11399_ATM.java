package week7.yechan.personal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class S4_BOJ_11399_ATM {

    static int N;
    static int[] time;
    public static void main(String[] args) throws NumberFormatException, IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        time = new int[N];

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            time[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(time);

        int total = 0;
        for(int i=1; i<=N; i++){
            for(int j=0; j<i; j++){
                //System.out.println("i : " + i);
                total += time[j];
                //System.out.println("total : " + total);
            }
        }

        System.out.println(total);

    }
}
/*
 *
 *  두 변수를 통해서도 가능   
 *      int time=0;
        int hap =0;

        for(int i=0; i<N; i++){
            time = time+arr[i];
            hap += time;
        }
 * 
 * 
 */