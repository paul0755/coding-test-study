package BJ.gold_4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class B14938_서강그라운드 {
    static int n,m,r;
    static int[] items;
    static List<List<Node>> graph = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        n = Integer.parseInt(input[0]);
        m = Integer.parseInt(input[1]);
        r = Integer.parseInt(input[2]);
        items = new int[n+1];
        String[] input2 = br.readLine().split(" ");
        for (int i = 1; i <= n; i++) {
            items[i] = Integer.parseInt(input2[i-1]);
        }
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int i = 0; i < r; i++) {
            String[] input3 = br.readLine().split(" ");
            int a = Integer.parseInt(input3[0]);
            int b = Integer.parseInt(input3[1]);
            int c = Integer.parseInt(input3[2]);
            graph.get(a).add(new Node(b,c));
            graph.get(b).add(new Node(a,c));
        }

        // 수색범위 m으로 최대 아이템을 구하는 로직 구현.
        // 단순히 매 지점마다 최대값을 구해야하나? 더 좋은 방법은 없을까.
        // 없는듯 ㅋㅋ
        int answer = Integer.MIN_VALUE;
        for (int i = 1; i <= n; i++) {
            int[] dir = new int[n+1];
            Arrays.fill(dir,Integer.MAX_VALUE);

            PriorityQueue<Node> priorityQueue = new PriorityQueue<>((a,b)-> a.weight-b.weight);
            priorityQueue.add(new Node(i,0));
            dir[i] = 0;
            while (!priorityQueue.isEmpty()){
                Node now = priorityQueue.remove();

                for (Node g : graph.get(now.nowNumber)){
                    if (dir[g.nowNumber] > g.weight+now.weight){
                        dir[g.nowNumber] = g.weight+now.weight;
                        priorityQueue.add(new Node(g.nowNumber,g.weight+now.weight));
                    }
                }
            }

            int total = 0;
            for (int j = 1; j <= n; j++) { // 거리가 수색 범위 내부에 있으면 그 값을 삽입
                if (dir[j] <=m) total += items[j];
            }
            answer = Math.max(answer,total);
        }
        System.out.println(answer);
    }
    static class Node{
        int nowNumber;
        int weight;
        Node(int to, int weight){
            this.nowNumber = to;
            this.weight = weight;
        }
    }
}
