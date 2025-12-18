import java.io.*;
import java.util.*;

public class S1_BOJ_1325_해킹 {

    static int N, M;
    static List<Integer>[] adj;
    static int[] count;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        adj = new ArrayList[N+1];
        count = new int[N+1];

        for(int i=1; i<=N; i++){
            adj[i] = new ArrayList<>();
        }

        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            // b를 해킹하면 a도 해킹 가능 → b -> a
            adj[b].add(a);
        }

        // 모든 노드를 시작점으로 BFS 수행
        for(int i=1; i<=N; i++){
            bfs(i);
        }

        // 가장 많은 해킹 수 찾기
        int max = 0;
        for(int i=1; i<=N; i++){
            max = Math.max(max, count[i]);
        }

        // 출력 (자동 오름차순)
        StringBuilder sb = new StringBuilder();
        for(int i=1; i<=N; i++){
            if(count[i] == max) sb.append(i).append(" ");
        }

        System.out.println(sb);
    }

    static void bfs(int start){
        Queue<Integer> q = new ArrayDeque<>();
        boolean[] visited = new boolean[N+1];

        visited[start] = true;
        q.add(start);

        int hacked = 1; // 시작 노드 포함

        while(!q.isEmpty()){
            int cur = q.poll();

            for(int next : adj[cur]){
                if(!visited[next]){
                    visited[next] = true;
                    hacked++;
                    q.add(next);
                }
            }
        }

        count[start] = hacked;
    }
}
