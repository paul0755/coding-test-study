package BJ.silver_1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

// m번 만큼 2개의 카드를 더해고 덮어씀.
// 그 후 모든 n개의 카드의 합을 구함.
// 점수 가장 적게 만들기.
// Long으로 안 풀어서 몇 번 틀림
public class B15903_카드합체놀이 {
    static int n,m;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        n = Integer.parseInt(input[0]);
        m = Integer.parseInt(input[1]);
        PriorityQueue<Long> priorityQueue = new PriorityQueue<>();
        String[] input2 = br.readLine().split(" ");
        for (int i = 0; i < input2.length; i++) {
            priorityQueue.add(Long.parseLong(input2[i]));
        }

        for (int i = 0; i < m; i++) {
            long a = priorityQueue.remove()+priorityQueue.remove();
            priorityQueue.add(a);
            priorityQueue.add(a);
        }

        long result = 0;
        while (!priorityQueue.isEmpty()){
            result+= priorityQueue.remove();
        }

        System.out.println(result);
    }
}
