package week4.yechan.personal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class S1_BOJ_2667_단지번호붙이기 {
    static int N, cnt;
    static int[][] map;
    static int[][] visited;
    static int[] dy = {1,0,-1,0};
    static int[] dx = {0,1,0,-1};
    static List<Integer> list = new ArrayList<>();
    public static void main(String[] args) throws Exception, IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        N = Integer.parseInt(br.readLine());

        map = new int[N][N];
        visited = new int[N][N];

        for(int i=0; i<N; i++){
            String tmp = br.readLine();
            for(int j=0; j<N; j++){
                map[i][j] = tmp.charAt(j) - '0';
            }
        }
        //printBoard();
        
        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                if(map[i][j] == 1 && visited[i][j] == 0){
                    cnt = 0;
                    dfs(i,j);
                    list.add(cnt);
                }
            }
        }

        list.sort(null);

        System.out.println(list.size());
        for(int i=0; i<list.size(); i++){
            System.out.println(list.get(i));
        }



    }
    private static void dfs(int y, int x) {
        visited[y][x] = 1;
        cnt ++;

        for(int d=0; d<4; d++){
            int ny = y + dy[d];
            int nx = x + dx[d];

            if(ny < 0 || nx < 0 || ny >= N || nx >= N) continue;

            if(visited[ny][nx] == 0 && map[ny][nx] == 1){
                dfs(ny,nx);
            }
        }
    }
    private static void printBoard() {
        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                System.out.print(map[i][j]);
            }
            System.out.println();
        }
    }
}
