import java.util.Scanner;


/**
 * 방정식에 대한 점화식이라 한번 풀어볼만한문제
 *
 *
 */

public class Main {

    static int D, K;
    static int[] mem;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        D = sc.nextInt();
        K = sc.nextInt();


        if (D == 3) {
            System.out.println(1);
            System.out.println(K - 1);
            return;
        } else {
            int x = 1, y = 1;
            int answerA = 0,answerB =0;
            for (int i = 4; i <= D; i++) {
                int temp = y;
                y = x + y;
                x = temp;
            }
            //System.out.println("x : "+x);
            //System.out.println("y : "+y);
            //System.out.println("최대 사이즈 : "+K/y);
            for (int i = (K / y) -1; i >= 0; i--) {

                if((K - (i * y)) % x == 0) {
                    answerB = i;
                    answerA = (K-(i*y))/x;
                    break;
                }

            }

            System.out.println(answerA);
            System.out.println(answerB);

        }

    }


}
