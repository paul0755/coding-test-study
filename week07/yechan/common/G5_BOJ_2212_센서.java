package week7.yechan.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * ? 문제부터 이해못함
 * 
 * 핵심 : 센서를 K개 그룹으로 나누려면
 * 센서 간 거리 중 큰 간격(K-1개)을 제거하면 자연스럽게 그룹이 만들어짐.
 * 
 * 목표 : K개의 그룹으로 나누었을 때,
 * 각 그룹의 길이(= 최댓값 - 최솟값)의 합을 최소로 만들기.
 * 
 * 시행 착오
 * -> 실제로 그룹을 나누는 코드가 필요가 없음.
 * 
 * 정답 아이디어
 * 1. 센서 정렬
 * 2. 인접 거리 배열 dist 만듦
 * 3. dist배열에서 가장 큰 간격을 제거
 * 4. 남아있는 거리들의 합 = 정답
 * 
 * 왜 이 문제가 그리디인가 ?
 * -> 각 순간의 최적 선택(가장 큰 간격을 제거하는 것)이
 * 전체 최적해(최소 거리 합)로 이어지는 구조 -> 그리디
 * 
 * 
 * 
 */

public class G5_BOJ_2212_센서 {

    static Integer[] dist;
    static int[] sensor;
    static int N, K;


    public static void main(String[] args) throws Exception, IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        K = Integer.parseInt(br.readLine());
        
        sensor = new int[N];
        dist = new Integer[N-1];

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            sensor[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(sensor);

        for(int i=0; i<N-1; i++){
            dist[i] = Math.abs(sensor[i+1] - sensor[i]);
        }

        
        Arrays.sort(dist, (a,b) -> b-a); // 내림차순 정렬

       // printDist();

       int answer = 0;
       for(int i=K-1; i<N-1; i++){
            answer += dist[i];
       }

       System.out.println(answer);


    }


    private static void printDist() {
        for(int i=0; i<N-1; i++){
            System.out.print(dist[i] + " ");
        }
    }



}