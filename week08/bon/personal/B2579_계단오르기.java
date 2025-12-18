
import java.util.Scanner;

/**
 * TOPDONW으로 풀수있고 bottom업을 풀 수 있는 문제
 */

public class Main {

    static int N;
    static int[] mem;
    static int[] stair;
    public static void main(String[] args) {

        Scanner sc= new Scanner(System.in);
        N = sc.nextInt();
        stair = new int[N+1];
        mem = new int[N+1];

        for (int i = 1; i <=N ; i++) {
            stair[i] = sc.nextInt();
        }

        mem[1] = stair[1];
        if(N>=2) mem[2] = stair[1] + stair[2];

        for (int i = 3; i <=N ; i++) {
            mem[i] = Math.max(mem[i-3]+stair[i-1],mem[i-2])+stair[i];
        }

        System.out.println(mem[N]);

    }
}
