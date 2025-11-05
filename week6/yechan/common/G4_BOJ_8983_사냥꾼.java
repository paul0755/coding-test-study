package week6.yechan.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 1트
 * -> 각 사대마다 사정거리내에 동물을 count (60점)
 * 기준 : 사대
 * 
 * 2트(gpt 도움)
 * 핵심 : 각 동물에 대해 "가장 가까운 사대"를 이진탐색으로 찾기
 * 왜? -> 멀리있는 사대들은 어차피 탈락
 * 기준 : 동물
 * 동물을 기준으로 하기에 사대가 겹치도 상관없음
 * 
 * "삽입 위치"를 이분탐색 
 * 예시 )
 *  사대 위치 shoot = [1, 4, 8, 11]
    동물 위치 x = 6

    shoot = [1, 4, (6), 8, 11]
             ↑
        6이 들어가야 할 자리 → 삽입 위치 = 인덱스 2

    삽입위치를 활용해서
    양 옆 사대를 비교 (오른쪽, 왼쪽)
    오른쪽 :  가장 먼 M보다 크지않고 거리가되면 가능
    왼쪽 : 0보다 크고 거리가되면 가능
    카운트 ++
 * 
 * 
 * 
 * 
 */

public class G4_BOJ_8983_사냥꾼 {

    static int M, N, L, cnt =0;
    static int[] shoot;
    static List<int[]> animal = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken()); // 사대의 수
        N = Integer.parseInt(st.nextToken()); // 동물의 수
        L = Integer.parseInt(st.nextToken()); // 사정거리

        shoot = new int[M];

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<M; i++){
            shoot[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(shoot);

        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            animal.add(new int[]{x,y});
        }
        
        for(int[] a : animal){
            int x = a[0];
            int y = a[1];

            // 사정거리보다 L이크면 어차피 못잡음
            if(y > L) continue;

            int idx = binarySearch(shoot, x);

            boolean can = false;
            if (idx < M && Math.abs(shoot[idx] - x) + y <= L) can = true;     // 오른쪽 사대
            if (idx > 0 && Math.abs(shoot[idx - 1] - x) + y <= L) can = true; // 왼쪽 사대

            if(can) cnt++;
        }

        System.out.println(cnt);

    }

    // 해당 동물과 가장 가까운 사대 이분 탐색
    // -> 삽입위치를 반환!!
    private static int binarySearch(int[] s, int x) {
        
        int lo = 0;
        int hi = s.length-1;
        int result = s.length;

        while(lo <= hi){
            int mid = (lo + hi) /2;
            
            if(s[mid] >= x){
                result = mid; // x보다 크거나 같은 첫 사대 위치 저장
                hi = mid - 1;
            }else {
                lo = mid + 1;
            }
        }

        return result;

    }
}
