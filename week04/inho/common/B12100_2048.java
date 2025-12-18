package BJ.gold_1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 4방향으로 처리.
// 같은 방향으로 이동하는 상에 같은 값을 만나면 합치기.
// 연쇄작용 없음.
// 5단계 후 최대값 구하기..
// 시점마다 뭐가 최선인지 모르니까 모든 경우의 수를 돌아야할 듯 ?
// 5번째 도착했을 때 가장 큰 값을 갱신.
public class B12100_2048 {
    static int[][] field;
    static int[][] rows = {
            {1, 0},
            {-1, 0},
            {0, 1},
            {0, -1}
    };
    static int max = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        field = new int[N][N];
        for (int i = 0; i < N; i++) {
            String[] input = br.readLine().split(" ");
            for (int j = 0; j < N; j++) {
                field[i][j] = Integer.parseInt(input[j]);
            }
        }

        dfs(field, 0);
        System.out.println(max);
    }

    private static void dfs(int[][] field, int depth) {
        if (depth == 5) {
            int maxField = mF(field);
            max = Math.max(max, maxField);
            return;
        }

        for (int[] row : rows) {
            int[][] copyField = copy(field);
            int[][] moveFiled = move(row, copyField);
            dfs(moveFiled, depth + 1);
        }
    }

    private static int[][] move(int[] row, int[][] copyField) {
        int N = copyField.length;
        int[][] newField = new int[N][N];
        boolean[][] check = new boolean[N][N];

        int rowX = row[0];
        int rowY = row[1];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {

                // 무엇을 고정할지 판단한다.
                int x, y;
                if (rowX != 0) { // 세로 방향 이동 (위, 아래)
                    if (rowX == 1) { // 1이면 아래로 미는 거. x값을 아래서부터 위로 확인한다.
                        x = N - 1 - j;
                    } else {
                        x = j;
                    }
                    y = i;
                } else { // 가로 방향 이동 (좌, 우)
                    x = i;
                    if (rowY == 1) {
                        y = N - 1 - j;
                    } else {
                        y = j;
                    }
                }

                // 현재 위치의 값이 0이면 건너뜀. 밀 필요가 없으니까
                if (copyField[x][y] == 0) {
                    continue;
                }

                // 현재 블록 값 저장하고 원래 위치 비움. 이동할 준비.
                int value = copyField[x][y];
                copyField[x][y] = 0;

                // 이동할 위치 초기화
                int nx = x;
                int ny = y;

                // 0이 아닌 블록을 이동 방향으로 끌어당김
                while (true) {
                    int tx = nx + rowX;
                    int ty = ny + rowY;

                    // 보드 밖이면 이동 안 함
                    if (tx < 0 || tx >= N || ty < 0 || ty >= N) {
                        break;
                    }

                    // 다음 칸이 0이면 이동
                    if (newField[tx][ty] == 0) {
                        nx = tx;
                        ny = ty;
                    }
                    // 다음 칸이 같은 숫자이고, 아직 합쳐지지 않았다면
                    else if (newField[tx][ty] == value && !check[tx][ty]) {
                        nx = tx;
                        ny = ty;
                        value *= 2; // 블록 합치기
                        check[tx][ty] = true; // 해당 위치는 이미 합쳐졌다고 표시
                        break;
                    }
                    // 0이 아닌 숫잔데 이미 합쳐졌거나 같은 숫자가 아니면 끝가지 옮긴 거.
                    else {
                        break;
                    }
                }
                // 마지막까지 끌어내렸으면 미리 저장했던 value로 초기화
                newField[nx][ny] = value;
            }
        }

        return newField;
    }

    private static int[][] copy(int[][] field) {
        int[][] copyF = new int[field.length][field.length];
        for (int i = 0; i < field.length; i++) {
            copyF[i] = field[i].clone();
        }
        return copyF;
    }

    private static int mF(int[][] field) {
        int answer = 0;
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[0].length; j++) {
                answer = Math.max(answer, field[i][j]);
            }
        }
        return answer;
    }
}
