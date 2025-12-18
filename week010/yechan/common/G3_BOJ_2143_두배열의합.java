package week10.yechan.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

/*

1. 부분합 리스트 만들기
모든 부분합을 저장하는 리스트를 만들고 
이분탐색을 위해 정렬시킴 -> 완탐 진행시 시간초과
이때, A배열은 오름차순, B배열은 내림차순(이거는 취향차이)

2. 투포인터
A배열의 합과 B배열의 합을 더해서 T와 비교
1) sum == T -> 중복되는 부분합이 있을수있기에
A에서는 i를 늘려가며 비교하고, B에서는 j를 늘려가며 비교
마지막에 answer += aCount * bCount를 통해 저장해줌
2) sum < T -> a가 갈수록 커지고 b가 갈수록 작아지기에 
i++
3) sum > A+B
j++

*/

public class G3_BOJ_2143_두배열의합 {

    static int n, m, T;
    static int[] A, B;
    static List<Integer> A_sub = new ArrayList<>();
    static List<Integer> B_sub = new ArrayList<>();
    public static void main(String[] args) throws Exception, IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        T = Integer.parseInt(br.readLine());
        n = Integer.parseInt(br.readLine());
        A = new int[n];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++){
            A[i] = Integer.parseInt(st.nextToken());
        }

        m = Integer.parseInt(br.readLine());
        B = new int[m];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<m; i++){
            B[i] = Integer.parseInt(st.nextToken());
        }

        // A 부분합 리스트 만들기
        for(int i=0; i<n; i++){
            int sum = 0;
            for(int j=i ; j<n; j++){
                sum += A[j];
                A_sub.add(sum);
            }
        }

        // B 부분합 리스트 만들기
        for(int i=0; i<m; i++){
            int sum = 0;
            for(int j=i ; j<m; j++){
                sum += B[j];
                B_sub.add(sum);
            }
        }

        // 이분탐색을 위해 정렬
        Collections.sort(A_sub); // 오름차순
        Collections.sort(B_sub, Collections.reverseOrder()); // 내림차순

        //System.out.println(A_sub);
        //System.out.println(B_sub);

        // 투 포인터
        int i = 0;
        int j = 0;

        long answer = 0;

        while(i < A_sub.size() && j < B_sub.size()){

            long sum = A_sub.get(i) + B_sub.get(j);
            //System.out.println("sum:" + sum);

            if(sum == T){
                long aVal = A_sub.get(i);
                long bVal = B_sub.get(j);

                // 공통되는 부분합이 있는 지 확인
                long aCount = 0;
                while(i < A_sub.size() && A_sub.get(i) == aVal){
                    aCount++;
                    i++;
                }

                long bCount = 0;
                while(j < B_sub.size() && B_sub.get(j) == bVal){
                    bCount++;
                    j++;
                }

                // 공통되는 부분합이 있을경우 서로의 값을 곱해준다.
                answer += aCount * bCount;
                //System.out.println("answer: " + answer);
            }
            else if(sum < T) i++;
            else j++;
        }

        System.out.println(answer);






    }
}
