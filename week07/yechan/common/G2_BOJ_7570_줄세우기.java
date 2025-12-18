package week7.yechan.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 첫시도 : 정렬 여부 체크로 풀려했는데 x
 * 
 * 핵심 : 가장 길게 연속된 번호가 이미 증가 순서로 서있는 구간의 길이
 * 최소 이동 = 전체 아이 수 - 건드릴 필요 없는 아이 수
 * 
 * 정답 = N - 가장 긴 증가 연속 수열
 * 
 * 
 * 
 */

public class G2_BOJ_7570_줄세우기 {

    static int N;
    static int[] child;
    static int[] pos;
    
    public static void main(String[] args) throws NumberFormatException, IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        child = new int[N];
        pos = new int[N+1];

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            child[i] = Integer.parseInt(st.nextToken());
        }

        for(int i=0; i<N; i++){
            pos[child[i]] = i;
        }

        int maxLen = 1;
        int curLen = 1;

        for(int i=1; i<N; i++){
            if(pos[i] < pos[i+1]){
                curLen++;
                maxLen = Math.max(maxLen, curLen);
            }else{
                curLen = 1;
            }
        }

        System.out.println(N - maxLen);

        

    }
}
