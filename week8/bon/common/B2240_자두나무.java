
import java.util.Scanner;

/**
 * 자두나무 문제
 * DP
 * 경우의수를 고려해보면 이동해서 먹지 않기와 먹기, 이동하지 않은 상태에서 먹기와 먹지 않기를고려해
 * 점화식을 세워야한다.
 * w(이동횟수)를 각 자두가 떨어질대마다 전부 고려해야한다는 점이 너무 어려웠다.
 *
 */

public class Main {

    static int T, W;
    static int[][] mem;
    static int[] tree;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        T = sc.nextInt();
        W = sc.nextInt();

        mem = new int[T + 1][W + 1];
        tree = new int[T + 1];

        for (int i = 1; i <= T; i++) {
            tree[i] = sc.nextInt();
        }


        for (int t = 1; t <= T; t++) {

            for (int w = 0; w <= W; w++) {
                int pos = (w % 2 == 0) ? 1 : 2;


                //이동하지 않고 먹기
                mem[t][w] = mem[t - 1][w] + (tree[t] == pos ? 1 : 0);


                //이동해서 먹기
                if (w > 0) {
                    mem[t][w] = Math.max(mem[t][w], mem[t - 1][w - 1] + (tree[t] == pos ? 1 : 0));
                }
            }
        }

        int answer = 0;
        for (int w = 0; w <=W; w++) {
            answer = Math.max(answer,mem[T][w]);
        }

        System.out.println(answer);


    }
}
