package week1.yechan.personal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class S3_BOJ_2606 {
	
	static int N, M, cnt=0;
	static int[] visited;
	static ArrayList<Integer> adj[];

	public static void main(String[] args) throws Exception, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		
		adj = new ArrayList[N+1];
		visited = new int[N+1];
		
		for(int i=0; i<N+1; i++) {
			adj[i] = new ArrayList<>();
		}
		
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			adj[x].add(y);
			adj[y].add(x); // 양방향 신경쓰기
		}
		
		dfs(1);
		
		System.out.println(cnt);
	}

	private static void dfs(int x) {
		visited[x] = 1;
		for(int v : adj[x]) {
			if(visited[v] == 1) continue;
			dfs(v);
			cnt++;
		}
		
	}

}