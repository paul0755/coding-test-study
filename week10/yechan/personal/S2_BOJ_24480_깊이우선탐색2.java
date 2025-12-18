package week10.yechan.personal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class S2_BOJ_24480_깊이우선탐색2 {

    static int N, M, R, seq = 1;
    static int[] visited;
    static List<Integer> adj[];
    
    public static void main(String[] args) throws Exception {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        adj = new ArrayList[N+1];
        visited = new int[N+1];
        
        for(int i=1; i<=N; i++){
            adj[i] = new ArrayList<>();
        }

        for(int i=0 ; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            adj[u].add(v);
            adj[v].add(u);
        }

        dfs(R);
        
        StringBuilder sb = new StringBuilder();
        for(int i=1; i<=N; i++){
            sb.append(visited[i]).append("\n");
        }
        System.out.println(sb);

    }

    private static void dfs(int r) {
        
        if(visited[r] != 0)return;
        
        visited[r] = seq;
        seq++;
        adj[r].sort(Collections.reverseOrder());

        for(int v : adj[r]){
            dfs(v);
        }
    
    }
}
