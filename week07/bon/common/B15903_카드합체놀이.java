package baekjoon.greedy;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * 정렬은 눈치깟는데 매번 정렬하다보니 최악의 경우 시간 초과가 발생함
 * 알고리즘 문제 분류 봤는데 우선순위 큐 사용
 * 우선순위 큐로 정렬하게둔다음 앞에있는거 두개 빼낸뒤 합해서 2번 add해주면됨
 */

public class BaekJoon_15903 {
    static int N, M;
    static PriorityQueue<Long> pq;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();

        pq = new PriorityQueue<>();


        for (int i = 0; i < N; i++) {
            pq.add(sc.nextLong());
        }

        while (M-- > 0){
            long x = pq.poll();
            long y = pq.poll();

            pq.add(x+y);
            pq.add(x+y);
        }

        long answer = 0;
        for(long x : pq){
            answer+=x;
        }

        System.out.println(answer);
    }
}
