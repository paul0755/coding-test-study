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

public class S2_BOJ_18352_특정거리의도시찾기 {

    static int N, M, K, X;
    static int[] dist;
    static List<List<Integer>> graph = new ArrayList<>();
    
    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        dist = new int[N+1];
        Arrays.fill(dist, Integer.MAX_VALUE);

        for(int i=0; i<=N; i++){
            graph.add(new ArrayList<>());
        }

        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            
            graph.get(a).add(b);
        }

        dij(X);

        StringBuilder sb =new StringBuilder();

        for(int i=0; i<=N; i++){
            if(dist[i] == K){
                sb.append(i).append("\n");
            }
        }

        if(sb.length() == 0){
            System.out.println(-1);
        }else{
            System.out.println(sb);
        }
    }

    private static void dij(int start) {
        
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparing(a -> a[1]));
        dist[start] = 0;
        pq.offer(new int[]{start, 0});

        while(!pq.isEmpty()){
            int[] cur = pq.poll();
            int nowN = cur[0];
            int nowC = cur[1];

            if(nowC > dist[nowN])continue;

            for(int next : graph.get(nowN)){

                int nextN = next;
                int nextC = nowC + 1;

                if(nextC < dist[nextN]){
                    dist[nextN] = nextC;
                    pq.offer(new int[]{nextN, nextC});
                }

            }
        }
    }
}
