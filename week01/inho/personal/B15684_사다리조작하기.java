package BJ.gold_3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 총 경우의수는 5 5 6 들어오면 6*(5-1) 세로
public class B15684_사다리조작하기 {
    static int N, M, H;
    static boolean[][] field;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] arr = br.readLine().split(" ");
        N = Integer.parseInt(arr[0]);
        M = Integer.parseInt(arr[1]);
        H = Integer.parseInt(arr[2]);
        field = new boolean[H + 1][N + 1];
        for (int i = 0; i < M; i++) {
            String[] input = br.readLine().split(" ");
            field[Integer.parseInt(input[0])][Integer.parseInt(input[1])] = true;
        }

        for (int i = 0; i <= 3; i++) {
            if (dfs(0, i, 1, 1)) {
                System.out.println(i);
                return;
            }
        }
        System.out.println(-1);
    }

    static private boolean dfs(int depth, int max, int x, int y) { // 총 진행한 깊이, 내가 도착해야할 깊이, 지금 x, 지금 y
        if (depth == max) {
            return check(); // 지금 i사다리 내려가면 i가 나오는지 판별해줘야함.
        }

        for (int i = x; i <= H; i++) {
            for (int j = 1; j < N; j++) {
                if (!isValid(i, j)) {
                    continue; // 사다리 연결이 가능한지 판별해줘야함.
                }
                field[i][j] = true;
                if (dfs(depth + 1, max, i, j)) {
                    return true;
                }
                field[i][j] = false;

            }
        }
        return false;
    }

    static private boolean check() {
        for (int i = 1; i <= N; i++) {
            int now = i;
            for (int j = 1; j <= H; j++) {
                if (field[j][now]) { // 고정된 세로줄 기준으로 연결된 가로줄을 확인해야함
                    now++;
                } else if (now > 1 && field[j][now - 1]) {
                    now--;
                }
            }
            if (now != i) {
                return false;
            }
        }
        return true;
    }

    static private boolean isValid(int i, int j) {
        return !field[i][j] && !field[i][j + 1] && !field[i][j - 1];
    }


}
