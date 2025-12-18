package BJ.gold_4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

public class B1967_트리의지름 {
    // 그래프로 처리해두고 진행
    static List<List<Node>> graph = new ArrayList<>();
    static int N;
    static boolean visited[];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }
        for (int i = 0; i < N-1; i++) {
            String[] input = br.readLine().split(" ");
            graph.get(Integer.parseInt(input[0])).add(new Node(Integer.parseInt(input[1]),Integer.parseInt(input[2])));
            graph.get(Integer.parseInt(input[1])).add(new Node(Integer.parseInt(input[0]),Integer.parseInt(input[2])));
        }

        visited = new boolean[N+1];
        int[] startNum = bfs(1); // 시작은 아무거나 해도 됨.
        int MAXNUM = Integer.MIN_VALUE;
        int start = 0;
        for(int i=1;i<startNum.length;i++){
            if (MAXNUM < startNum[i]){
                MAXNUM = startNum[i];
                start = i;
            }
        }
        visited = new boolean[N+1];
        int[] answerArr = bfs(start); // 가장 먼 노드 2개 중 하나를 찾았으니, 이 노드로 한 번 더 진행해서 최장거리 값 구하기
        Arrays.sort(answerArr);
        System.out.println(answerArr[answerArr.length-1]);
    }

    private static int[] bfs(int s) {
        int[] distance = new int[N+1];
        Deque<Integer> deque = new ArrayDeque<>();
        deque.add(s);
        visited[s] = true;

        while (!deque.isEmpty()){
            int now = deque.remove();
            for (Node g : graph.get(now)){
                if (!visited[g.to]){
                    visited[g.to] = true;
                    distance[g.to] = distance[now] + g.weight;
                    deque.add(g.to);
                }
            }
        }
        return distance;
    }

    static class Node{
        int to;
        int weight;
        Node(int to, int weight){
            this.to = to;
            this.weight = weight;
        }
    }
}
