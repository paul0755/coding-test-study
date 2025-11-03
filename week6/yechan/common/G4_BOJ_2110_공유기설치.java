package week6.yechan.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/*
 *  가장 인접한 두 공유기 사이의 거리를 최대로 하는 프로그램
 * 
 * 입력 : N(집의개수) , C(공유기 개수)
 *      N개의 줄에는 집의 좌표
 * 
 * 출력 : 가장 인접한 두 공유기 사이의 최대 거리 출력
 * 
 * 예시 : 1 2 4 8 9
 * -> 가장 인접한 두 공유기 사이의 거리는 3
 * 
 * 💢1트 -> 실패
 * 가장 인접한 집의 거리 구하기. ( 최대로되는 )
 * 조합을 사용하니 주어진 요소중 가장 차이가 많이나는 걸 골라버림
 * 이분탐색을 이용해야할것같은데 어떤 방식으로 이용해야할지 감이안옴.
 * 
 * 
 * 핵심 -> 거리를 기준으로 이분탐색
 * d = 공유기 사이 최소거리
 * d로 공유기 C개를 설치할 수 있는가?
 * - d가 가능하면 더 멀리 떨어뜨릴수있을까? -> d를 늘린다.
 * - d가 불가능하면 너무 멀리 두었네 -> d를 줄인다
 */

public class G4_BOJ_2110_공유기설치 {

    static int N, C;
    static int[] arr;

    
    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());


        arr = new int[N];

        for(int i=0; i<N; i++){
            arr[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(arr);

        int lo = 1; // 최소거리: 1 (집이 겹치치 않는 최소간격)
        int hi = arr[N-1] - arr[0]; // 최대거리 (가장 양 끝집 거리)
        int answer = 0;

        while(lo<=hi){

            int mid = (lo+hi) / 2;
            //System.out.println("거리시도:" + mid);

            if(canInstall(mid)){
                // 골랐다면 d를 늘려보자.
                answer = mid;
                lo = mid + 1;
            }else{
                // 못골랐다면 d를 줄여보자.
                hi = mid -1;
            }
           // System.out.println("---------------");
        }

        System.out.println(answer);


    }

    // 거리에 맞는 집의 위치 고르기
    private static boolean canInstall(int d) {
        int count = 1;
        int last = arr[0];
        //System.out.println("첫집(" + last + ")에 설치");
        for(int i=0; i<N; i++){
            int gap = arr[i] - last;
            //System.out.printf("집 %d (좌표 %d): last=%d, 거리=%d → ", i, arr[i], last, gap);

            if(gap >= d){
                count ++;
                last = arr[i];
                //System.out.println("공유기 설치");
            }else{

                //System.out.println("패스");
            }
        }
        //System.out.println("총 설치 개수:" + count);
        return count >= C;
    }




}
