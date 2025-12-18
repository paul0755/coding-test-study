
import java.util.Arrays;
import java.util.Scanner;

public class BaekJoon_12100 {

    static int N;
    static int[][] grid;
    static int answer = Integer.MIN_VALUE;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();

        grid = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                grid[i][j] = sc.nextInt();
            }
        }
       /* int[][] temp = moveRight(grid);
        for (int[] ints : temp) {
            System.out.println(Arrays.toString(ints));
        };*/

        dfs(0, grid); // 초기 배열 그대로 넘김

        System.out.println(answer);
    }

    private static void dfs(int depth, int[][] temp) {

        // 깊이가 5일때 최대값 구한뒤 탈출
        if (depth == 5) {
            for (int[] row : temp) {
                for (int v : row) {
                    answer = Math.max(answer, v);
                }
            }
            return;
        }

        // 0: 오른쪽, 1: 왼쪽, 2: 아래, 3: 위
        for (int i = 0; i < 4; i++) {
            int[][] next = copyArray(temp);
            if (i == 0) dfs(depth + 1, moveRight(next));
            else if (i == 1) dfs(depth + 1, moveLeft(next));
            else if (i == 2) dfs(depth + 1, moveDown(next));
            else dfs(depth + 1, moveUp(next));
        }
    }


    /**
     * 한번 실행할때 과정은
     * 1. 동 서 남 북 중  선택한 방향으로 쭉민다.
     * 2. 합을 구한다.
     * 3. 합을 구했을 시 칸에 0 이 있으니 다시 선택한 방향으로 쭉 민다.
     */
    private static int[][] moveLeft(int[][] arr) {
        for (int i = 0; i < N; i++) {
            int point = 0; // 0이 아닌 숫자를 채울 위치
            for (int j = 0; j < N; j++) {
                if (arr[i][j] != 0) {
                    arr[i][point] = arr[i][j];
                    if (point != j) arr[i][j] = 0;
                    point++;
                }
            }
        }

        // 합치기
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N - 1; j++) {
                if (arr[i][j] != 0 && arr[i][j] == arr[i][j + 1]) {
                    arr[i][j] *= 2;
                    arr[i][j + 1] = 0;
                }
            }
        }

        // 다시 밀기
        for (int i = 0; i < N; i++) {
            int point = 0; // 0이 아닌 숫자를 채울 위치
            for (int j = 0; j < N; j++) {
                if (arr[i][j] != 0) {
                    arr[i][point] = arr[i][j];
                    if (point != j) arr[i][j] = 0;
                    point++;
                }
            }
        }

        return arr;
    }

    private static int[][] moveRight(int[][] arr) {
        for (int i = 0; i < N; i++) {
            int point = N - 1; // 0이 아닌 숫자를 채울 위치
            for (int j = N - 1; j >= 0; j--) {
                if (arr[i][j] != 0) {
                    arr[i][point] = arr[i][j];
                    if (point != j) arr[i][j] = 0;
                    point--;
                }
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = N - 1; j > 0; j--) {
                if (arr[i][j] != 0 && arr[i][j] == arr[i][j - 1]) {
                    arr[i][j] *= 2;
                    arr[i][j - 1] = 0;
                }
            }
        }

        for (int i = 0; i < N; i++) {
            int point = N - 1; // 0이 아닌 숫자를 채울 위치
            for (int j = N - 1; j >= 0; j--) {
                if (arr[i][j] != 0) {
                    arr[i][point] = arr[i][j];
                    if (point != j) arr[i][j] = 0;
                    point--;
                }
            }
        }

        return arr;
    }

    private static int[][] moveUp(int[][] arr) {
        for (int j = 0; j < N; j++) {
            int point = 0;
            for (int i = 0; i < N; i++) {
                if (arr[i][j] != 0) {
                    arr[point][j] = arr[i][j];
                    if (point != i) arr[i][j] = 0;
                    point++;
                }
            }
        }

        for (int j = 0; j < N; j++) {
            for (int i = 0; i < N - 1; i++) {
                if (arr[i][j] != 0 && arr[i][j] == arr[i + 1][j]) {
                    arr[i][j] *= 2;
                    arr[i + 1][j] = 0;
                }
            }
        }

        for (int j = 0; j < N; j++) {
            int point = 0;
            for (int i = 0; i < N; i++) {
                if (arr[i][j] != 0) {
                    arr[point][j] = arr[i][j];
                    if (point != i) arr[i][j] = 0;
                    point++;
                }
            }
        }

        return arr;
    }

    private static int[][] moveDown(int[][] arr) {
        for (int j = 0; j < N; j++) {
            int point = N - 1;
            for (int i = N - 1; i >= 0; i--) {
                if (arr[i][j] != 0) {
                    arr[point][j] = arr[i][j];
                    if (point != i) arr[i][j] = 0;
                    point--;
                }
            }
        }

        for (int j = 0; j < N; j++) {
            for (int i = N - 1; i > 0; i--) {
                if (arr[i][j] != 0 && arr[i][j] == arr[i - 1][j]) {
                    arr[i][j] *= 2;
                    arr[i - 1][j] = 0;
                }
            }
        }

        for (int j = 0; j < N; j++) {
            int point = N - 1;
            for (int i = N - 1; i >= 0; i--) {
                if (arr[i][j] != 0) {
                    arr[point][j] = arr[i][j];
                    if (point != i) arr[i][j] = 0;
                    point--;
                }
            }
        }

        return arr;
    }



    //배열 복사

    private static int[][] copyArray(int[][] arr) {
        int[][] copy = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                copy[i][j] = arr[i][j];
            }
        }
        return copy;
    }
}
