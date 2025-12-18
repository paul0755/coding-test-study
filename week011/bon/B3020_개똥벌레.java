package baekjoon.binarysearch;

import java.util.Arrays;
import java.util.Scanner;


/**
 * 각 높이의 누적합 배열을 써서 벌레가 나는 높이에 따라 최소값을 구한다.
 * 위 종유석을 구할때는 H-i+1  +1을 꼭해줘야한다.
 */


public class BaekJoon_3020 {

    static int N,H;
    static int[] up,down;
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        N =sc.nextInt();
        H = sc.nextInt();

        up = new int[H+1];
        down = new int[H+1];

        for (int i = 0; i <N ; i++) {
            int height = sc.nextInt();
            if(i%2==0){
                down[height]++;
            }else{
                up[height]++;
            }
        }

        for (int i = H-1; i >=1 ; i--) {
            up[i] += up[i+1];
            down[i]+=down[i+1];
        }

        //System.out.println(Arrays.toString(up));
        //System.out.println(Arrays.toString(down));

        int min = N;
        int count = 1;
        for (int i = 1; i <=H ; i++) {
            int temp = down[i] + up[H - i+1];

            if(temp<min){
                min = temp;
                count=1;
            }else if(temp==min){
                count++;
            }

        }

        System.out.println(min +" "+count);
    }
}
