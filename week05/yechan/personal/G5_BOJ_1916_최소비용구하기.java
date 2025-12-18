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

public class G5_BOJ_1916_최소비용구하기 {
    
    static int N, M;
    static int[] dist;
    static List<List<Node>> graph = new ArrayList<>();
    
    public static void main(String[] args) throws Exception, IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        dist = new int[N+1];
        Arrays.fill(dist, Integer.MAX_VALUE);

        for(int i=0; i<=N; i++){
            graph.add(new ArrayList<>());
        }

        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            graph.get(u).add(new Node(v, w));
        }

        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());

        dij(start);

        System.out.println(dist[d]);

    }

    private static void dij(int start) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(start, 0));
        dist[start] = 0;

        while(!pq.isEmpty()){
            Node cur = pq.poll();
            int nowNode = cur.vertex;
            int nowCost = cur.cost;

            if(dist[nowNode] < nowCost) continue;

            for(Node next : graph.get(nowNode)){
                int nextNode = next.vertex;
                int nextCost = nowCost + next.cost;

                if(nextCost < dist[nextNode]){
                    dist[nextNode] = nextCost;
                    pq.offer(new Node(nextNode, nextCost));
                }
            }

        }
    }


    static class Node implements Comparable<Node>{

        int vertex;
        int cost;
        
        private Node(int vertex, int cost){
            this.vertex = vertex;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return this.cost - o.cost;
        }
    }
}
