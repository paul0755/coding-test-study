package BJ.gold_4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

// 첫 번째 도전 실패함 -> 왜? 걍 구현이 꼬임..
// visited + 최솟값 속성으로 제어하려다가 실패
// 최소 거리를 Node 한 클래스 내부의 속성으로 제어하면 안 됨. Node는 그 길을 표현한 클래스임.
// 따라서 어디서 시작하는지, 어디로 도착하는지, 어느 정도가 걸리는지는 속성으로 가질 수 있지만, 그 위치까지의 최소 거리는 저장할 수 없는 거.


// 이 문제는 최소 거리를 처리하는 dist 배열을 사용해야했음!

public class B10282_해킹 {
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        // N개의 테스트 진행
        for (int i = 0; i < N; i++) {
            String[] input = br.readLine().split(" ");
            int n = Integer.parseInt(input[0]); // 컴퓨터 개수
            int d = Integer.parseInt(input[1]); // 의존성 개수
            int c = Integer.parseInt(input[2]); // 해킹당한 컴퓨터의 번호
            List<List<Node>> graph = new ArrayList<>();
            for (int j = 0; j <= n; j++) {
                graph.add(new ArrayList<>());
            }
            int[] dist = new int[n+1];
            for (int j = 0; j <= n; j++) {
                dist[j] = Integer.MAX_VALUE;
            }

            // 로직 진행
            for (int j = 0; j < d; j++) {
                String[] innerInput = br.readLine().split(" ");
                int a = Integer.parseInt(innerInput[0]); // 컴퓨터 a 가
                int b = Integer.parseInt(innerInput[1]); // 컴퓨터 b 를 의존 / 감염 경로 b -> a
                int s = Integer.parseInt(innerInput[2]); // s초 후 감염
                graph.get(b).add(new Node(a,s));
            }
            // 총 감염되는 컴퓨터 수, 마지막 컴퓨터가 감염되기까지 걸리는 시간
            PriorityQueue<Node> priorityQueue = new PriorityQueue<>((a,b)-> a.weight - b.weight);
            priorityQueue.add(new Node(c,0));
            dist[c] = 0;

            while (!priorityQueue.isEmpty()){
                Node now = priorityQueue.remove();
                if (now.weight > dist[now.from]) continue;

                for(Node g : graph.get(now.from)){ // 해당하는 그래프
                    int newTime = now.weight + g.weight;
                    if (newTime < dist[g.from]){
                        dist[g.from] = newTime;
                        priorityQueue.add(new Node(g.from, newTime));
                    }
                }
            }

            int count = 0;
            int time = 0;
            // 결과 dist로 호출
            for (int j = 1; j < dist.length; j++) {
                if (dist[j] != Integer.MAX_VALUE){
                    count++;
                    time = Math.max(time,dist[j]);
                }
            }
            System.out.println(count + " " + time);
        }
    }

    static class Node{
        int from;
        int weight;

        Node(int from,int weight){
            this.weight = weight;
            this.from = from;
        }
    }
}
