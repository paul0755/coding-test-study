package week4.yechan.personal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class G4_BOJ_1987_알파벳 {

    static int R,C, result = 0;
    static char[][] map;
    static boolean[] visited = new boolean[26];
    static int[] dy = {-1,0,1,0};
    static int[] dx = {0,1,0,-1};

    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new char[R][C];

        for(int i=0; i<R; i++){
            String tmp = br.readLine();
            for(int j=0; j<C; j++){
                map[i][j] = tmp.charAt(j);
            }
        }

        // for(int i=0; i<R; i++){
        //     for(int j=0; j<C; j++){
        //         System.out.print(map[i][j]);
        //     }
        //     System.out.println();
        // }

        dfs(0,0, 0);

        System.out.println(result);


    }

    private static void dfs(int y,int x, int depth) {
        
        visited[map[y][x] - 'A'] = true;

        for(int d=0; d<4; d++){
            int ny = y + dy[d];
            int nx = x + dx[d];

            if(ny <0 || nx<0 || ny>=R || nx >=C) continue;

            if(visited[map[ny][nx]-'A'] == false ){
                visited[map[ny][nx] - 'A'] = true;
                dfs(ny, nx, depth+1);
                visited[map[ny][nx] - 'A'] = false;
            }
        }
        result = Math.max(result, depth+1);
    }
    
}
