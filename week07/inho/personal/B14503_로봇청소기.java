package BJ.gold_5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

// 현재 칸 더러우면 청소
// 주변 4칸이 다 청소되어 있으면 바라보는 방향을 유지한 채 한 칸 후진. 뒤가 벽이라면 작동 멈춤.
// 주변 칸중 청소되지 않은 칸이 있으면 반시계 방향으로 90도 회전. 만약 앞쪽 칸이 청소되지 않으면 전진.
// 0이면 청소되지 않은 빈 칸, 1이면 벽. 청소 완료는 2로

// 0. 현재 위치 칸이 더러우면 청소. 2로 수정
// 1. 현재 위치 기준 4칸에서 0의 유무 확인.
        // 1. 없다면 map.get((지금 방향 +2)%4)로 이동. 이 때 1을 만나면 작동을 멈춤.
        // 2. 있다면 map.get((지금 방향 +3)%4)로 이동하고 앞을 볼 때 0이면 전진.

public class B14503_로봇청소기 {
    static int N,M;
    static int[] start = new int[3];
    static int[][] field;
    static Map<Integer, int[]> map = new HashMap<>();
    public static void main(String[] args) throws IOException {
        map.put(0,new int[]{-1,0}); // 북
        map.put(1,new int[]{0,1}); // 동
        map.put(2,new int[]{1,0}); // 남
        map.put(3,new int[]{0,-1}); // 서

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);
        String[] input2 = br.readLine().split(" ");
        start[0] = Integer.parseInt(input2[0]);
        start[1] = Integer.parseInt(input2[1]);
        start[2] = Integer.parseInt(input2[2]);
        field = new int[N][M];

        for (int i = 0; i < N; i++) {
            String[] input3 = br.readLine().split(" ");
            for (int j = 0; j < M; j++) {
                field[i][j] = Integer.parseInt(input3[j]);
            }
        }
        int answer = 0;
        int nowX = start[0];
        int nowY = start[1];
        int nowTo = start[2];
        // 0. 현재 위치 칸이 더러우면 청소. 2로 수정
        // 1. 현재 위치 기준 4칸에서 0의 유무 확인.
        // 매 칸마다 그 앞에 0을 만나면 전진하고, 2나 1이면 후진하기. 후진할 때 1이면 작동 멈추기.
        while(true){
            if (field[nowX][nowY]==0) {
                answer++;
                field[nowX][nowY]=2;
            }
            boolean check = false;
            // 4 칸에서 0의 유무 확인
            for (int i = 0; i < 4; i++) {
                nowTo = (nowTo + 3) % 4;
                int[] dir = map.get(nowTo);
                int nextX = nowX + dir[0];
                int nextY = nowY + dir[1];

                if (nextX >= 0 && nextX < N && nextY >= 0 && nextY < M && field[nextX][nextY] == 0) {
                    nowX = nextX;
                    nowY = nextY;
                    check = true;
                    break;
                }
            }

            if (check) continue;

            // 네 방향 모두 청소 또는 벽이면 후진
            int[] backDir = map.get((nowTo + 2) % 4);
            int backX = nowX + backDir[0];
            int backY = nowY + backDir[1];

            if (backX < 0 || backX >= N || backY < 0 || backY >= M || field[backX][backY] == 1) {
                break;
            } else {
                nowX = backX;
                nowY = backY;
            }
        }
        System.out.println(answer);
    }
}
