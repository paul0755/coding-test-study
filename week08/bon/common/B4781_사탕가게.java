
import java.util.Scanner;


/**
 * 사탕가게 문제를 잘 못 이해해서 좀 많이 어려웠다.
 * 점화식을 고려해보면 이것도 자두나무와 같이 사탕이 주어졌을때 먹기와 안먹기로 점화식을 고려해 세우면된다.
 * 하지만 소수점 계산과정에서 실수를 너무 많이했다.
 * 점화식 mem[현재 쓴돈] = max(mem[현재 쓴돈], mem[현재 쓴돈 - 이번에 쓸돈] + 주어진 사탕칼로리)
 *
 */

public class Main {

    static int n;
    static double money;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int[] price;
        int[] cal;
        int[] mem;

        while (true) {
            n = sc.nextInt();
            money = sc.nextDouble();
            int M = (int) (money * 100 + 0.5);
            if (n == 0 && money == 0.00) break;

            price = new int[n];
            cal = new int[n];
            mem = new int[M + 1];

            for (int i = 0; i < n; i++) {
                cal[i] = sc.nextInt();
                price[i] = (int) (sc.nextDouble() * 100 + 0.5);
            }

            for (int i = 0; i < n; i++) {
                for (int m = price[i]; m <= M; m++) {
                    mem[m] = Math.max(mem[m], mem[m - price[i]] + cal[i]);
                }
            }

            System.out.println(mem[M]);
        }
    }


}
