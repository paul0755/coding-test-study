package BJ.gold_4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 공기청정기 -1
// 매 초마다 미세먼지 확산. 상하좌우로 확산되며 [현재 값의 /5]로 확산됨.
// 확산후 미세먼지의 양은 Ar,c - ⌊Ar,c/5⌋×(확산된 방향의 개수)

// 그 뒤에 공기청정기 작동. 윗쪽은 반시계 방향으로, 아래쪽은 시계 방향으로 작동하며 해당되는 위치는 한칸씩 옮겨짐.
public class B17144_미세먼지안녕 {
    static int R, C, T;
    static int[][] field;
    static int[][] rows = {
            {1, 0},
            {-1, 0},
            {0, 1},
            {0, -1}
    };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        R = Integer.parseInt(input[0]);
        C = Integer.parseInt(input[1]);
        T = Integer.parseInt(input[2]);
        field = new int[R][C];
        int[][] clean = new int[2][2];
        int count = 0;
        for (int i = 0; i < R; i++) {
            String[] input2 = br.readLine().split(" ");
            for (int j = 0; j < C; j++) {
                field[i][j] = Integer.parseInt(input2[j]);
                if (field[i][j] == -1 && count == 0) {
                    clean[0][0] = i;
                    clean[0][1] = j;
                    count++;
                } else if (field[i][j] == -1 && count == 1) {
                    clean[1][0] = i;
                    clean[1][1] = j;
                }
            }
        }

        // T초 진행
        for (int i = 0; i < T; i++) {
            // 먼지 확산
            int[][] temp = new int[R][C];

            for (int r = 0; r < R; r++) {
                for (int c = 0; c < C; c++) {
                    if (field[r][c] == -1) {
                        temp[r][c] = -1;
                    }
                }
            }

            for (int j = 0; j < R; j++) {
                for (int k = 0; k < C; k++) {
                    if (field[j][k] != 0 && field[j][k] != -1) { // 해당 필드가 0,-1이 아니면 퍼짐.
                        int now = field[j][k];
                        int sp = field[j][k] / 5;
                        int count1 = 0;
                        for (int[] row : rows) {
                            int nowX = j + row[0];
                            int nowY = k + row[1];
                            if (nowX >= 0 && nowY >= 0 && nowX < R && nowY < C && field[nowX][nowY] != -1) {
                                count1++;
                                temp[nowX][nowY] += sp;
                            }
                        }
                        temp[j][k] += now - sp * count1;
                    }
                }
            }
            field = temp;
            // 공가청정기 작동
            // 위(clean[0][0], clean[0][1]) -1은 반시계 방향
            // 아래(clean[1][0], clean[1][1]) -1는 시계 방향

            // 위 공기청정기 (반시계)
            for (int j = clean[0][0]; j > 0; j--) { // 왼쪽 아래로 땡기기
                if (field[j][0] == -1) {
                    continue;
                }
                field[j][0] = field[j - 1][0];
            }
            for (int j = 0; j < C - 1; j++) { // 위 왼쪽으로 땡기기
                field[0][j] = field[0][j + 1];
            }
            for (int j = 0; j < clean[0][0]; j++) { // 오른쪽 위로 땡기기
                field[j][C - 1] = field[j + 1][C - 1];
            }
            for (int j = C - 1; j > 0; j--) { // 아래 오른쪽으로 땡기기
                if (field[clean[0][0]][j - 1] == -1) { // 땡기려고 하는 값이 -1이면
                    field[clean[0][0]][j] = 0;
                    continue;
                }
                field[clean[0][0]][j] = field[clean[0][0]][j - 1];
            }

            // 아래 공기청정기 (시계)
            for (int j = clean[1][0]; j < R - 1; j++) {
                if (field[j][0] == -1) {
                    continue;
                }
                field[j][0] = field[j + 1][0];
            }
            for (int j = 0; j < C - 1; j++) { // 아래 왼쪽으로 땡기기
                field[R - 1][j] = field[R - 1][j + 1];
            }
            for (int j = R - 1; j > clean[1][0]; j--) { // 오른쪽 아래로 땡기기
                field[j][C - 1] = field[j - 1][C - 1];
            }
            for (int j = C - 1; j > 0; j--) { // 위 오른쪽으로 땡기기
                if (field[clean[1][0]][j - 1] == -1) {
                    field[clean[1][0]][j] = 0;
                    continue;
                }
                field[clean[1][0]][j] = field[clean[1][0]][j - 1];
            }
        }
        int answer = 2;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                answer += field[i][j];
            }

        }
        System.out.println(answer);
    }
}
