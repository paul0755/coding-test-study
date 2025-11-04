package BJ.gold_4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class B2110_공유기설치 {
    static int N,C;
    static int[] house;
    static int answer = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]); // 집 개수
        C = Integer.parseInt(input[1]); // 공유기 개수
        house = new int[N];
        for (int i = 0; i < N; i++) {
            house[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(house);

//        int left = house[0];
//        int right = house[N-1];
        // 처음에 공유기 좌표로 선언하고 진행해서 오답. 우리가 mid로 처리해야하는 것은 거리임.
        int left = 1;
        int right = house[N-1] - house[0];
        while (left <= right){
            int mid = (left+right) /2;
            if (can(mid)){ // 설치 가능하면 거리를 넓여보기
                answer = Math.max(answer,mid);
                left = mid+1;
            }else { // 설치 불가능하면 거리를 줄여야함
                right = mid-1;
            }
        }
        System.out.println(answer);
    }

    static private boolean can(int mid){ // 설치 가능한지 판단하기.
        int count = 1; // 시작 설치
        int start = house[0];
        for (int i = 1; i < N; i++) {
            if (house[i] - start >= mid) { // mid 거리에 만족한다면
                count++;
                start = house[i]; // 여기 설치
            }
        }
        if (count >= C){
            return true;
        }
        return false;
    }
}
