package week6.yechan.personal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
/*
 * 
 * 💡 “두 번” 탐색하는 이유

우리는 “3이 시작하는 위치”와 “3이 끝나는 위치”를 알아야 해요.
즉, 같은 값들이 연속된 구간을 찾아야 합니다.

1️⃣ lowerBound(key)

key 이상이 처음 나타나는 인덱스
👉 같은 숫자가 시작되는 위치

2️⃣ upperBound(key)

key 초과가 처음 나타나는 인덱스
👉 같은 숫자가 끝나는 다음 위치
 */

public class S4_BOJ_10816_숫자카드2 {

    static int N, M, cnt =0;
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

        Arrays.sort(arr);

        M = Integer.parseInt(br.readLine());

        StringBuilder sb =new StringBuilder();

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<M; i++){
            int key = Integer.parseInt(st.nextToken());
            int lo = lowerBound(key);
            int up = upperBound(key);
            cnt = up - lo;

            sb.append(cnt).append(" ");
        }
        
        System.out.println(sb);

    }

    /**
     * 같을 때(arr[mid] == key) → 왼쪽(hi = mid) 으로 감
    → “혹시 더 왼쪽에도 같은 값이 있을 수 있잖아?”
    → 첫 번째 3이 나올 때까지 왼쪽으로 좁힘
     */
    private static int lowerBound(int key) {
        int lo = 0, hi = arr.length;
        while(lo < hi){
            int mid = (lo+hi) / 2;
            if(arr[mid] >= key) hi = mid;
            else lo = mid + 1;
        }
        return lo;
    }

    /*
     * 같을 때(arr[mid] == key) → 오른쪽(lo = mid + 1) 으로 감
    → “같은 값들은 다 지나쳐야 해”
    → 3이 끝나는 지점의 다음 인덱스까지 전진
     */
    private static int upperBound(int key){ 
        int lo = 0, hi = arr.length;
        while(lo < hi){
            int mid = (lo+hi) / 2;
            if(arr[mid] > key) hi = mid;
            else lo = mid + 1;
        }
        return lo;
    }
}
