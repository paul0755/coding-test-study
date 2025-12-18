package week4.yechan.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class G2_BOJ_17136_색종이붙이기 {

    static int[][] map = new int[10][10];
    static int[] paper = {0,5,5,5,5,5};
    static int answer = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        for(int i=0; i<10; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<10; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(0,0,0);

        if(answer == Integer.MAX_VALUE){
            System.out.println(-1);
        }else{
            System.out.println(answer);
        }

    }
    private static void dfs(int y, int x, int cnt) {
        
        // 모든 칸을 다 돌았다면
        if(y>= 10){
            answer = Math.min(answer, cnt);
            return;
        }

        // 이미 현재까지 붙인 수가 최소보다 많으면 가지치기
        if(cnt>= answer) return;

        // 다음 행으로 넘어가기
        if(x >= 10){
            dfs(y+1, 0, cnt);
            return;
        }

        // 이미 0이라 덮을 필요 없으면 오른쪽으로
        if(map[y][x] == 0){
            dfs(y, x+1, cnt);
            return;
        }

        // 이 자리가 1이면 1~5 크기 색종이를 시도
        for(int size = 5; size >= 1; size--){

            if(paper[size] == 0) continue;
            if(canPlace(y, x, size)){
                attach(y,x, size, 0);
                paper[size] --;
                dfs(y, x+size, cnt+1); // 다음칸 탐색
                attach(y, x, size, 1); // 원상복구
                paper[size] ++ ;
            }
        }

    }

    // 붙이거나 되돌리기
    private static void attach(int y, int x, int size, int val) {
        for(int i=y; i<y+size; i++){
            for(int j=x; j<x+size; j++){
                map[i][j] = val;
            }
        }
    }
    private static boolean canPlace(int y, int x, int size) {
        if(y+size > 10 || x+size > 10) return false;

        for(int i=y ; i<y+size; i++){
            for(int j=x; j<x+size; j++){
                if(map[i][j] == 0) return false;
            }
        }

        return true;
        
    }
}

/**
 * 
 * 🔍 문제 핵심 요약

10×10 격자.

1인 칸(색종이를 붙여야 하는 칸)을 모두 덮어야 함.

색종이는 1×1, 2×2, …, 5×5 한 종류당 최대 5장.

색종이는 1이 연속된 정사각형만 덮을 수 있고, 격자를 벗어나면 안 됨.

최소 개수의 색종이로 모든 1을 덮는 것.

덮을 수 없는 경우 -1 출력.

🧠 접근 아이디어
1️⃣ DFS + 백트래킹 기본 구조

(0,0)부터 (9,9)까지 탐색하면서 1을 만나면,
그 위치를 시작점으로 1~5 크기의 색종이를 붙여본다.

단, 해당 크기의 색종이를 붙일 수 있는지(범위 안+모두 1인지) 검사해야 함.

붙였다면:

map을 0으로 바꿔서 “덮었다” 표시

색종이개수[크기]++

DFS 다음 좌표로 진행

돌아올 때 원상복구(=백트래킹)

2️⃣ 가지치기(Pruning)

이미 찾은 최소값보다 현재까지 붙인 색종이 수가 많으면 return

색종이 개수가 5개 다 쓰인 종류는 더 이상 못 붙이게
 */