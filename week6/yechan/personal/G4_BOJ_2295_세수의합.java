package week6.yechan.personal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class G4_BOJ_2295_세수의합 {

    static int N;
    static int[] arr;
    public static void main(String[] args) throws Exception, IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        N = Integer.parseInt(br.readLine());

        arr = new int[N];

        for(int i=0; i<N; i++){
            arr[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(arr);

        List<Integer> twoSum = new ArrayList<>();
        for(int i=0; i<N; i++){
            for(int j=i; j<N; j++){
                twoSum.add(arr[i] + arr[j]);
            }
        }

        Collections.sort(twoSum);

        int result = 0;
        for(int i = N-1; i>=0 ; i--){
            for(int j =0; j<i; j++){
                int target = arr[i] - arr[j];
                if(Collections.binarySearch(twoSum, target)>=0){
                    result = arr[i];
                    System.out.println(result);
                    return;
                }
            }
        }

    }
}
