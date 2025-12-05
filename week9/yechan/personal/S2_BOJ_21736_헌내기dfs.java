package week9.yechan.personal;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class S2_BOJ_21736_헌내기dfs {
    
    static int N, M, cnt=0;
    static int[][] map;
    static int[][] visited;
    static int[] dy = {-1, 0, 1, 0};
    static int[] dx = {0, 1, 0, -1};
    public static void main(String[] args) throws Exception {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        visited = new int[N][M];

        for(int i=0; i<N; i++){
            String tmp = br.readLine();
            for(int j=0; j<M; j++){
                map[i][j] = tmp.charAt(j);
            }
        }

        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                if(map[i][j] == 'I') dfs(i,j);
            }
        }

        if(cnt == 0){
            System.out.println("TT");
        }else{
            System.out.println(cnt);
        }
    }
    private static void dfs(int y, int x) {
        
        visited[y][x] = 1;
        if(map[y][x] == 'P') cnt++;
        for(int d=0; d<4; d++){
            int ny = y + dy[d];
            int nx = x + dx[d];

            if(ny<0 || nx<0 || ny>=N || nx>=M)continue;

            if(map[ny][nx] != 'X' && visited[ny][nx] == 0 )
                dfs(ny,nx);
        }

    }
}
