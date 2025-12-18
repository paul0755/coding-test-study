package BJ.gold_2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.TreeMap;

// K만큼 먼저 채워지고 진행 (우선순위큐)
// 단순히 비면 넣어서 처리하면 되지만, 동일할 때 출구에 가까운 높은 번호 계산대 고객부터 나간다고 함.
// 하나가 빠지면 그 시간으로 워프를 해야함. 그러면서 새롭게 들어오는 사람은 (현재시간 + 그 사람이 걸리는 시간)으로 넣어둬야할 듯!
// 같으면 나갈 때는 큰 수부터 나감. 들어올 때는 작은 수부터 들어옴.

// 처음  for (int i = 0; i < Math.min(K,N); i++) { // 처음 진입. K와 N의 관계를 고려하지 않고 바로 K 돌려서 런타임 에러 나옴.
public class B17612_쇼핑몰 {
    static int N,K;
    static List<Number> numberList = new ArrayList<>();
    static List<Integer> result = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        K = Integer.parseInt(input[1]);
        for (int i = 0; i < N; i++) {
            String[] input2 = br.readLine().split(" ");
            numberList.add(new Number(Integer.parseInt(input2[0]), Integer.parseInt(input2[1]),0));
        }

        PriorityQueue<Number> priorityQueue = new PriorityQueue<>(
                (a,b) -> {
                    if(a.weight == b.weight) return b.counter - a.counter;
                    return a.weight - b.weight;
                }
         );
        PriorityQueue<Number> priorityQueue2 = new PriorityQueue<>(
                (a,b) -> {
                    if(a.weight == b.weight) return a.counter - b.counter;
                    return a.weight - b.weight;
                }
        );
        for (int i = 0; i < Math.min(K,N); i++) { // 처음 진입.
            Number number = numberList.get(i);
            priorityQueue.add(new Number(number.number, number.weight, i+1));
            priorityQueue2.add(new Number(number.number, number.weight, i+1));

        }

        // 시간이 전부 동일하게 처리되야 함.
        int time = 0;
        for (int i = K; i < N; i++) { // 하나씩 빼서 확인. 시간을 더함.
            Number now = priorityQueue.remove(); // 가장 최근 큐 빼고.
            Number now2 = priorityQueue2.remove();
            result.add(now.number);
            time = now.weight; // 현재 시간
            priorityQueue.add(new Number(numberList.get(i).number, numberList.get(i).weight + time, now2.counter));
            priorityQueue2.add(new Number(numberList.get(i).number, numberList.get(i).weight + time, now2.counter));
        }

        // 짬처리
        int num = priorityQueue.size();
        for(int i=0;i<num;i++){
            result.add(priorityQueue.remove().number);
        }

        long answer = 0;
        for (int i = 0; i < result.size(); i++) {
            answer += (i + 1) * (long) result.get(i);
        }
        System.out.println(answer);
    }
    static class Number{
        int number, weight, counter;
        Number(int number, int weight, int counter){
            this.number = number;
            this.weight = weight;
            this.counter = counter;
        }
    }
}
