
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    static int N, M;
    static char[][] grid;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    static int startX, startY;
    static boolean answer = false;

    static boolean[][] visited;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();

        grid = new char[N][M];

        sc.nextLine();

        for (int y = 0; y < N; y++) {
            String s = sc.nextLine();
            for (int x = 0; x < M; x++) {
                grid[y][x] = s.charAt(x);
            }
        }


        for (int y = 0; y < N; y++) {
            for (int x = 0; x < M; x++) {
                visited = new boolean[N][M];
                char c = grid[y][x];
                int k = 0;


                startX = x;
                startY = y;
                dfs(x, y, c, k);

                if (answer) {
                    System.out.println("Yes");
                    return;
                }

            }
        }

        System.out.println("No");
    }

    private static void dfs(int x, int y, char c, int k) {

        visited[y][x] = true;
        //System.out.print(grid[y][x]+" " +"좌표 : "+y+ " "+x+" ");

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx >= 0 && ny >= 0 && nx < M && ny < N) {

                if (grid[ny][nx] == c && ny == startY && nx == startX && k >= 3) {
                    answer = true;
                    return;
                }

                if (!visited[ny][nx] && grid[ny][nx] == c) {

                    dfs(nx, ny, c, k + 1);
                }

            }


        }


    }
}
