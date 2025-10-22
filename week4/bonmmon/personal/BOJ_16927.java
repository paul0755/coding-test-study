
import java.util.Arrays;
import java.util.Scanner;


/**
 *  R 횟수대로 회전하면 시간 초과
 *  둘레 구한다음 나머지 연산으로 R을 나머지 처리해줘야함.
 *  그다음 배열 돌리기
 *  Collection.rotate 원형배열 돌리기 가능하니 한번 사용해보자
 */
public class BaekJoon_16927 {

    static int N, M, R;
    static int[][] grid;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        R = sc.nextInt();

        grid = new int[N][M];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                grid[i][j] = sc.nextInt();
            }
        }


        int count = Math.min(N, M) / 2;
        for (int i = 0; i < count; i++) {
            rotate(i);
        }


        for (int[] ints : grid) {
            for (int anInt : ints) {
                System.out.print(anInt + " ");
            }
            System.out.println();
        }
    }

    private static void rotate(int i) {


        int startX = i;
        int startY = i;
        int endY = N - i - 1;
        int endX = M - i - 1;

        int len = 2 * ((endY - startY) + (endX - startX));

        int rotate = R % len;

        for (int T = 0; T < rotate; T++) {
            int temp = grid[startY][endX];
            //오른쪽 옮기기
            for (int j = startY+1; j <=endY ; j++) { grid[j-1][endX] = grid[j][endX]; }
            //아래
            for (int x = endX-1; x >=startX ; x--) { grid[endY][x+1] = grid[endY][x]; }
            //왼쪽
            for (int y = endY-1; y >=startY ; y--) { grid[y+1][startX] = grid[y][startX]; }
            //위쪽
            for (int x = startX+1; x<=endX-1 ; x++) { grid[startY][x-1] = grid[startY][x]; }

            grid[startY][endX-1] = temp;


        }

    }
}
