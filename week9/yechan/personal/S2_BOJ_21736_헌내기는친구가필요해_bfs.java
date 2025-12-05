package week9.yechan.personal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class S2_BOJ_21736_헌내기는친구가필요해_bfs {

    static int N, M, cnt = 0;
    static char[][] map;
    static int[][] visited;
    static int[] dy = {-1,0,1,0};
    static int[] dx = {0,1,0,-1};
    public static void main(String[] args) throws Exception {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new char[N][M];
        visited = new int[N][M];

        for(int i=0; i<N; i++){
            String tmp = br.readLine();
            for(int j=0; j<M; j++){
                map[i][j] = tmp.charAt(j);
            }
        }

        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                if(map[i][j] == 'I' && visited[i][j] == 0){
                    bfs(i,j);
                }
            }
        }

        if(cnt ==0){
            System.out.println("TT");
        }else{
            System.out.println(cnt);
        }
    }
    private static void bfs(int sy, int sx) {
        
        Queue<int[]> q = new ArrayDeque<>();
        visited[sy][sx] = 1;
        q.offer(new int[]{sy, sx});

        while(!q.isEmpty()){
            int[] cur = q.poll();
            int y = cur[0];
            int x = cur[1];

            for(int d=0; d<4; d++){
                int ny = y + dy[d];
                int nx = x + dx[d];

                if(ny < 0 || nx <0 || ny>=N || nx>= M) continue;

                if(map[ny][nx] == 'X') continue;

                if(map[ny][nx] == 'O' && visited[ny][nx] == 0){
                    visited[ny][nx] = 1;
                    q.offer(new int[]{ny, nx});
                }

                if(map[ny][nx] == 'P' && visited[ny][nx] == 0){
                    cnt ++;
                    visited[ny][nx] = 1;
                    q.offer(new int[]{ny, nx});
                }
            }
        }
    }
}
