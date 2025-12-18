
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BaekJoon_7569 {


    static int N, M, H;
    static int[][][] grid;
    static boolean[][][] visited;

    static int[] dx = {1, -1, 0, 0, 0, 0};
    static int[] dy = {0, 0, 1, -1, 0, 0};
    static int[] dz = {0, 0, 0, 0, -1, 1};
    static int leastDays = -1;
    static int days = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        M = sc.nextInt();
        N = sc.nextInt();
        H = sc.nextInt();


        grid = new int[N][M][H];
        visited = new boolean[N][M][H];
        Queue<int[]> queue = new LinkedList<>();
        boolean isAll = true;


        //입력 부분
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < M; k++) {
                    grid[j][k][i] = sc.nextInt();

                }
            }
        }


        //토마토 보이는 족족 큐에 추가
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < M; k++) {
                    if (grid[j][k][i] == 1 && !visited[j][k][i]) {
                        queue.add(new int[]{k, j, i, 0});
                        visited[j][k][i] = true;
                    }

                }
            }
        }

        //bfs 돌리면서 최소일수 구하기
        leastDays = bfs(queue);

        for (int i = 0; i < H; i++) {
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < M; k++) {
                    if (grid[j][k][i] == 0) {
                        isAll = false;
                        break;
                    }

                }
            }
        }

        //bfs 실행 후 토마토 안익었으면 -1 출력
        if (!isAll) {
            //System.out.println("이거 실행");
            System.out.println(-1);
            return;
        }

        System.out.println(leastDays);


    }

    static private int bfs(Queue<int[]> queue) {
        int maxDays = 0;

        while (!queue.isEmpty()) {
            int[] poll = queue.poll();
            int curX = poll[0];
            int curY = poll[1];
            int curZ = poll[2];
            int curDay = poll[3];

            maxDays = Math.max(curDay, maxDays);

            for (int i = 0; i < 6; i++) {
                int nx = curX + dx[i];
                int ny = curY + dy[i];
                int nz = curZ + dz[i];

                if (nx >= 0 && ny >= 0 && nz >= 0 && nx < M && ny < N && nz < H) {

                    if (grid[ny][nx][nz] == 0 && !visited[ny][nx][nz]) {
                        visited[ny][nx][nz] = true;
                        grid[ny][nx][nz] = 1;
                        queue.add(new int[]{nx, ny, nz, curDay + 1});
                    }

                }
            }
        }

        return maxDays;
    }
}
