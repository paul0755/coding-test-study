package BJ.gold_4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

// 처음에 그리디로 접근하려고 함.
// 공격시 첫번째 9 두번째 3 세번째 1 데미지
public class B12869_뮤탈리스크 {
    static int N;
    static int[] scv = new int[3];
    static int[][][] dp = new int[61][61][61];
    static boolean[][][] visited = new boolean[61][61][61];

    static int[][] damage = {
            {9, 3, 1},
            {9, 1, 3},
            {3, 9, 1},
            {3, 1, 9},
            {1, 9, 3},
            {1, 3, 9}
    };

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        String[] input = br.readLine().split(" ");
        for (int i = 0; i < N; i++) {
            scv[i] = Integer.parseInt(input[i]);
        }

        Deque<int[]> deque = new ArrayDeque<>();
        int a = scv[0];
        int b = scv[1];
        int c = scv[2];
        deque.add(new int[]{a,b,c});
        visited[a][b][c] = true;
        dp[a][b][c] = 0;

        while (!deque.isEmpty()){
            int[] now = deque.remove();
            int x = now[0];
            int y = now[1];
            int z = now[2];

            if (x==0 && y==0 && z ==0 ) break;

            for (int[] d : damage){
                int nowX = Math.max(0,x-d[0]);
                int nowY = Math.max(0,y-d[1]);
                int nowZ = Math.max(0,z-d[2]);

                if (!visited[nowX][nowY][nowZ]){
                    visited[nowX][nowY][nowZ] = true;
                    dp[nowX][nowY][nowZ] = dp[x][y][z]+1;
                    deque.add(new int[]{nowX, nowY, nowZ});
                }
            }
        }
        System.out.println(dp[0][0][0]);
    }
}
