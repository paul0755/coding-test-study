import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1405 {
	
	static int N;
	static double total = 0;
	static double[] percent;
	static int[] dy = {0,0,1,-1};  // 동, 서, 남, 북
	static int[] dx = {1,-1,0,0};
	static int[][] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		visited = new int[30][30];  // 충분히 큰 배열
		percent = new double[4];
		
		for (int i = 0; i < 4; i++) {
			percent[i] = Integer.parseInt(st.nextToken()) * 0.01;
		}
		
		visited[15][15] = 1;  // 시작점
		dfs(15, 15, 0, 1.0);
		
		System.out.printf("%.10f\n", total);
	}

	private static void dfs(int sy, int sx, int idx, double result) {
		if (idx == N) {
			total += result;
			return;
		}
		
		for (int d = 0; d < 4; d++) {
			int ny = sy + dy[d];
			int nx = sx + dx[d];
			
			if (ny < 0 || nx < 0 || ny >= 30 || nx >= 30) continue;
			
			if (visited[ny][nx] == 0) {
				visited[ny][nx] = 1;

				
				dfs(ny, nx, idx + 1, result * percent[d]);
				visited[ny][nx] = 0; // 원복
			}
		}
	}
}

/*
 * ✔ 생각 못한 부분 
 * 
 *  단순한 확률을 계산하는 방식
 * -> 각방향의 확률을 곱해가는 것
 * N = 2
 * 동쪽, 남쪽을 이동하는 경우
 * 1/4 * 1/4 = 1/16
 * 그리고 이와 같은 확률을 다 더하면
 * 12/16 = 3/4 = 0.75
 * 
 * 
 * 📌놓친 부분
 * 
 *  동,서,남,북에 맞춰서 dx, dy를 했어야했는데
 *  원래 해오던 방식으로 했다가 오류발생.
 */
