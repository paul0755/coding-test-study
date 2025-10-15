
import java.util.Scanner;


/**
 * 배열 돌릴때 마지막 수는 저장을 해야한다..
 */
public class BaekJoon_17406 {

    static int N, M, K;
    static int[][] arr;
    static int[][] list;
    static boolean[] visited;
    static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        K = sc.nextInt();

        arr = new int[N][M];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                arr[i][j] = sc.nextInt();
            }
        }

        list = new int[K][3];
        visited = new boolean[K];

        int a = 0;

        for (int i = 0; i < K; i++) {
            int r = sc.nextInt();
            int c = sc.nextInt();
            int s = sc.nextInt();

            list[a][0] = r;
            list[a][1] = c;
            list[a][2] = s;
            a++;
        }

        Back(0,arr);
        System.out.println(answer);
    }

    private static void Back(int depth, int [][] arr) {

        if(depth==K){
            int minTemp = Integer.MAX_VALUE;
            for (int i = 0; i < arr.length; i++) {
                int temp = 0;
                for (int j = 0; j < arr[i].length; j++) {
                    temp +=arr[i][j];
                }
                minTemp = Integer.min(temp,minTemp);
            }

            answer = Integer.min(minTemp,answer);
            return ;

        }

        for (int i = 0; i < K; i++) {
            if(!visited[i]){
                visited[i] = true;
                int[][] copy = copyArray(arr);
                rotate(list[i][0],list[i][1],list[i][2],copy);
                Back(depth+1,copy);
                visited[i] =false;

            }
        }


    }

    static int[][] copyArray(int[][] original) {
        int[][] newArr = new int[N][M];
        for (int i = 0; i < N; i++) {
            System.arraycopy(original[i], 0, newArr[i], 0, M);
        }
        return newArr;
    }

    static void rotate(int r, int c, int s, int[][] arr) {
        int startY = r - s - 1;
        int startX = c - s - 1;
        int endY = r + s - 1;
        int endX = c + s - 1;

        while (startY < endY && startX < endX) {
            int temp = arr[startY][startX];

            // 왼쪽 → 위로
            for (int i = startY; i < endY; i++) {
                arr[i][startX] = arr[i + 1][startX];
            }

            // 아래쪽 → 왼쪽으로
            for (int i = startX; i < endX; i++) {
                arr[endY][i] = arr[endY][i + 1];
            }

            // 오른쪽 → 아래로
            for (int i = endY; i > startY; i--) {
                arr[i][endX] = arr[i - 1][endX];
            }

            // 위쪽 → 오른쪽으로
            for (int i = endX; i > startX + 1; i--) {
                arr[startY][i] = arr[startY][i - 1];
            }

            arr[startY][startX + 1] = temp;


            startY++;
            startX++;
            endY--;
            endX--;
        }
    }

}
