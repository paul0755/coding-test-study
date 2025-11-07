package week6.yechan.personal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/*
 * 
 * | 단계  | 변수                                      | 설명                  | 값            |
| --- | --------------------------------------- | ------------------- | ------------ |
| 1️⃣ | `idxArr = [0,1,2]`                      | A의 인덱스를 저장          | [0,1,2]      |
| 2️⃣ | `Arrays.sort(idxArr, (i,j)->A[i]-A[j])` | A의 값 기준으로 인덱스 정렬    | [1,2,0]      |
| 3️⃣ | `P[idxArr[rank]] = rank`                | 정렬된 순서를 원래 자리로 돌려놓기 | → [2,0,1] 완성 |

 */

public class S4_BOJ_1015_수열정렬 {

    static int N;
    static int[] A, sorted;

    public static void main(String[] args) throws Exception, IOException {
    
        BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        A = new int[N];

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            A[i] = Integer.parseInt(st.nextToken());
        }

        Integer[] idxArr = new Integer[N];
        for(int i=0 ; i<N; i++) idxArr[i] = i;

        Arrays.sort(idxArr, (i,j) -> A[i] - A[j]);

        
        int[] P = new int[N];
        for(int i=0; i<N; i++){
            P[idxArr[i]] = i;
        }

        StringBuilder sb = new StringBuilder();
        for(int num: P){
            sb.append(num).append(" ");
        }


        System.out.println(sb);
    }
}
