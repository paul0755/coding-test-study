package week1.yechan.personal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class S1_BOJ_2468 {
	
	static int N;
	static int max_num = 0;
	static int[][] visited;
	static int[][] map;
	static int[] dy = {-1,0,1,0};
	static int[] dx = {0,1,0,-1};

	public static void main(String[] args) throws Exception, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		visited = new int[N][N];
		
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
//		for(int i=0; i<N; i++) {
//			for(int j=0; j<N; j++) {
//				System.out.print(map[i][j]);
//			}
//			System.out.println();
//		}
		int K = 1;
		while(K <=100) {
			int cnt =0;
			visited = new int[N][N];
			// 장마수위에 잠기는 위치를 방문처리
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					if(map[i][j]<K && visited[i][j] == 0) visited[i][j] = 1;
				}
			}
			
//			for(int i=0; i<N; i++) {
//				for(int j=0; j<N; j++) {
//					System.out.print(visited[i][j]);
//				}
//				System.out.println();
//			}
			
			// 방문처리 이후 bfs를 통해 안전영역 체크
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					if(visited[i][j] == 0) {						
						bfs(i,j);
						cnt ++;
					}
				}
			}
			max_num = Math.max(cnt, max_num);
			K++;
		}// while
		
		System.out.println(max_num);

	}

	private static void bfs(int sy, int sx) {
		Queue<int[]> q = new ArrayDeque<>();
		visited[sy][sx] = 1;
		q.offer(new int[] {sy,sx});
		while(!q.isEmpty()) {
			int [] cur = q.poll();
			int y = cur[0], x = cur[1];
			for(int d=0; d<4 ; d++) {
				int ny = y + dy[d];
				int nx = x + dx[d];
				
				if(ny <0 || nx <0 || ny>= N || nx>= N) continue;
				
				if(visited[ny][nx] == 0) {
					visited[ny][nx] = 1;
					q.offer(new int[] {ny,nx});
				}
			}
		}// while
	}

}