package week5.yechan.personal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class G4_BOJ_1753_최단경로{

    static int V, E;
    static int start;
    static List<List<int[]>> graph = new ArrayList<>();
    static int[] dist;

    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        start = Integer.parseInt(br.readLine());

        dist = new int[V+1];
        Arrays.fill(dist, Integer.MAX_VALUE);

        for(int i=0; i<=V; i++){
            graph.add(new ArrayList<>());
        }

        for(int i=0; i<E; i++){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            addEdge(u,v,w);
        }

        dijkstra(start);

        for(int i=1; i<=V; i++){
            if(dist[i] == Integer.MAX_VALUE){
                System.out.println("INF");
            }else{

                System.out.println(dist[i]);
            }
        }
    }

    private static void dijkstra(int i) {
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));

        // 시작점 추가 {노드, 비용}
        pq.add(new int[]{start, 0});
        dist[start] = 0;

        while(!pq.isEmpty()){
            int[] cur = pq.poll();
            int nowNode = cur[0];
            int nowCost = cur[1];

            // ✅ 이미 더 짧은 거리로 처리된 적이 있으면 스킵
            if(dist[nowNode] < nowCost) continue;

            for(int[] next : graph.get(nowNode)){

                int nextNode = next[0];
                int nextCost = nowCost + next[1];
            
                if(nextCost < dist[nextNode]){
                    dist[nextNode] = nextCost;
                    pq.add(new int[]{nextNode, nextCost});
                }
            }

        }
    }

    private static void addEdge(int from, int to, int cost) {
        graph.get(from).add(new int[] {to, cost});
    }

    static class Node implements Comparable<Node>{
        int vertex, cost;
        public Node(int vertex, int cost){
            this.vertex = vertex;
            this.cost = cost;
        }
    
        public int compareTo(Node o){
            return this.cost - o.cost;
        }
    }
}
