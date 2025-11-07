package week6.yechan.personal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 
 *  a+b+c = k
 * -> a+b = k-c
 * 
 * 1. a+b의 조합을 List에 모두저장
 * 2. 조합안에서 k-c가 존재하는지 이분탐색 
 * *이때 가장 큰값인 끝에서부터 탐색하기에 맞는 값이 있다면 바로 출력하고 끝
 * 
 */

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
