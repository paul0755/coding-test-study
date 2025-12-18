package baekjoon.greedy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * 구간합을 최소화 하려면 구간을 나눌때 센서 거리가 먼것을 기준으로 나눠야
 * 최소가 될 수 있다.
 * 센서 거리를 다 구한뒤 역순으로 정렬해 거리가 큰것부터 배제한다.
 */
public class BaekJoon_2212 {

    static int N, K;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        K = sc.nextInt();
        List<Integer> list = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            list.add(sc.nextInt());
        }
        Collections.sort(list);

        List<Integer> gap = new ArrayList<>();

        for (int i = 1; i <list.size() ; i++) {
            int temp = list.get(i) - list.get(i - 1);
            gap.add(temp);
        }

        gap.sort((a, b) -> b - a);

        int answer= 0;
        for (int i = K-1; i <gap.size() ; i++) {

            answer+= gap.get(i);
        }

        System.out.println(answer);

    }
}
