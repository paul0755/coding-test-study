package week6.yechan.personal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

/**
 * Xi를 좌표 압축한 결과 X'i의 값은 Xi > Xj를 만족하는
 * 서로 다른 좌표 Xj의 개수와 같아야한다 -> 뭔소리임?
 * “Xi보다 작은 서로 다른 좌표의 개수가 Xi의 압축된 값(X′i)이다.”
 * 즉, X'i = Xi보다 작은 좌표들의 개수
 * 
 * 
 * 1. 순위를 매기는 시스템으로 볼수있음
 * 2. 같은 요소들은 같은 순위이기에 중복된값을 없애기위해
 * HashMap을 활용한다.
 * 3. 정렬된 배열을 순서대로 순위를 매긴다.
 * 4. 원래 배열에서 요소에맞는 값을 불러와서 sb에 추가해준다.
 */

public class S2_BOJ_18870_좌표압축 {
    
    static int N;
    static int[] origin;
    static int[] sorted;

    public static void main(String[] args) throws Exception, IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        HashMap<Integer, Integer>rangkingMap = new HashMap<Integer, Integer>();

        N = Integer.parseInt(br.readLine());

        origin = new int[N];
        sorted = new int[N];

        st = new StringTokenizer(br.readLine());
        for(int i =0 ; i<N; i++){
            sorted[i] = origin[i] = Integer.parseInt(st.nextToken());
        }

       

        Arrays.sort(sorted); // -10 -9 2 4 4
    
        // 2 4 -10 4 -9
        
        int rank = 0;
        for(int num : sorted){

            if(!rangkingMap.containsKey(num)){
                rangkingMap.put(num, rank);
                rank ++;
            }

        }

        StringBuilder sb =new StringBuilder();
        for(int num : origin){
            int rangking = rangkingMap.get(num);
            sb.append(rangking).append(" ");

        }

        System.out.println(sb);

        
    }
}
