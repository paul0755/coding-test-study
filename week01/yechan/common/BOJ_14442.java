import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_14442 {
	
	static int N, M, K;
	static int[][][] visited;
	static int[][] map;
	static int[] dy = {-1,0,1,0};
	static int[] dx = {0,1,0,-1};
	static int min_num = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		visited = new int[N][M][K+1];
		
		for(int i =0; i<N ; i++) {
			String tmp = br.readLine();
			for(int j=0; j<M; j++) {
				map[i][j] = tmp.charAt(j) - '0';
			}
		}
		
//		for(int i =0; i<N ; i++) {
//			for(int j=0; j<M; j++) {
//				System.out.print(map[i][j]);
//			}
//			System.out.println();
//		}
		
		int result = bfs(0,0);
		System.out.println(result);
		

	}

	private static int bfs(int sy, int sx) {
		Queue<int[]> q = new ArrayDeque<>();
		visited[sy][sx][0] = 1;
		q.offer(new int[] {sy,sx,0});
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			int y = cur[0], x=cur[1], state=cur[2];
			if(y == N-1 && x == M-1) {
				return visited[y][x][state];
			}
			
			for(int d=0; d<4; d++) {
				int ny = y + dy[d];
				int nx = x + dx[d];
				
				if(ny<0 || nx<0|| ny>=N || nx>=M) continue;
				
				if(visited[ny][nx][state] == 0 && map[ny][nx] == 0) {
					visited[ny][nx][state] = visited[y][x][state]+ 1;
					q.offer(new int[] {ny,nx, state});
				}
				
				if (map[ny][nx] == 1 && state < K && visited[ny][nx][state+1] == 0) {
				    visited[ny][nx][state+1] = visited[y][x][state] + 1;
				    q.offer(new int[] {ny, nx, state+1});
				}
			}
		} // while
		
		return -1;
	}

}

/**
 * 
 * 🚨 놓친 부분 정리
1. visited 배열 크기

visited = new int[N][M][K];

이렇게 하면 벽을 최대 K번까지 부수는 상태를 저장할 수 없음.

BFS에서는 state+1을 써서 K까지 접근해야 하므로 **크기는 K+1**이어야 함.

✅ 수정

visited = new int[N][M][K+1];


2. 벽 부수기 조건 순서

if (visited[ny][nx][state+1] == 0 && state < K && map[ny][nx]==1)

state+1을 먼저 검사하면 state == K일 때 배열 인덱스 초과 발생.

반드시 state < K → 그 다음에 visited[ny][nx][state+1] 순서로 검사해야 안전함.

✅ 수정

if (map[ny][nx] == 1 && state < K && visited[ny][nx][state+1] == 0) {
    visited[ny][nx][state+1] = visited[y][x][state] + 1;
    q.offer(new int[]{ny, nx, state+1});
}

 */