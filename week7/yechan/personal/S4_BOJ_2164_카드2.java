package week7.yechan.personal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class S4_BOJ_2164_카드2 {

    public static void main(String[] args) throws Exception, IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        Queue<Integer> card = new LinkedList<>();

        for (int i = 1; i <= N; i++) {
            card.add(i);
        }

        while (card.size() > 1) {
            card.poll();                 // 맨 위 버리기
            card.add(card.poll());       // 그 다음 맨 아래로 보내기
        }

        System.out.println(card.peek());
    }
}
