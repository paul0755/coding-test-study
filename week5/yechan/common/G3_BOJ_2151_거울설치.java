import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * 
 * 어려웠던 문제
 * 1. 거울의 방향에 따라 좌,우/상,하로 퍼지는 부분을 고려하는점
 * 2. 그냥 사방탐색이 아닌 거울에 닿으면 직선방향 움직임 고려하는점
 * 3. 비용처리 기준이 어떤건지 궁금
 * 
 */

public class G3_BOJ_2151_거울설치 {

    static int N;
    static int[][][] dist;
    static char[][] map;
    static List<int[]> list = new ArrayList<>();

    static int[] dy = {-1,0,1,0};
    static int[] dx = {0,1,0,-1};
    public static void main(String[] args) throws NumberFormatException, IOException {
        
        BufferedReader br =new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        map = new char[N][N];
        dist = new int[N][N][4]; // 방향값도 고려
        
        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                for(int k=0; k<4; k++){
                    dist[i][j][k] = Integer.MAX_VALUE;
                }
            }
        }

        for(int i=0; i<N; i++){
            String tmp = br.readLine();
            for(int j=0; j<N; j++){
                map[i][j] = tmp.charAt(j);
            }
        }

        //printBoard();

        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                // 문은 항상 두개
                if(map[i][j] == '#'){
                    list.add(new int[]{i,j});
                }
            }
        }
        

        int[] start_p = list.get(0);

        int sy = start_p[0];
        int sx = start_p[1];

        
        bfs(sy, sx);

        //printDist();

        int[] d_p = list.get(1);

        int dy = d_p[0];
        int dx = d_p[1];

        int answer = Integer.MAX_VALUE;
        for(int i=0; i<4; i++){
            answer = Math.min(answer, dist[dy][dx][i]);
        }

        System.out.println(answer);

    }

    private static void printDist() {
        for(int i=0; i<=N; i++){
            for(int j=0; j<=N; j++){
                System.out.print(dist[i][j] +  " ");
            }
            System.out.println();
        }
    }

    // 거울 설치 횟수 -> 비용 
    private static void bfs(int sy, int sx) {
        
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparing(a -> a[3]));
        
        // 시작지점에서 네방향으로 탐색
        for(int i=0; i<4; i++){
            dist[sy][sx][i] = 0;
            pq.add(new int[]{sy, sx, i, 0});
        }

        while(!pq.isEmpty()){
            int[] cur = pq.poll();
            int y = cur[0];
            int x = cur[1];
            int dir = cur[2];
            int cost = cur[3];

            if(cost > dist[y][x][dir]) continue;

            int ny = y + dy[dir];
            int nx = x + dx[dir];
            while(ny >= 0 && nx >= 0 && ny < N && nx <N && map[ny][nx] != '*'){

                // 도착문을 만나면 비용저장하고 종료
                if(map[ny][nx] == '#'){
                    dist[ny][nx][dir] = Math.min(dist[ny][nx][dir], cost);
                    break;
                }

                // 거울지점을 만나면 방향을 정하고 비용 갱신
                if(map[ny][nx] == '!'){
                    for(int nd : turn(dir)){
                        if(dist[ny][nx][nd] > cost + 1){
                            dist[ny][nx][nd] = cost + 1;
                            pq.offer(new int[]{ny,nx,nd,cost+1});
                        }
                    }
                }

                // 그대로 직진
                if(dist[ny][nx][dir] > cost){
                    dist[ny][nx][dir] = cost;
                    pq.offer(new int[]{ny,nx,dir,cost});
                }

                // 방향 
                ny += dy[dir];
                nx += dx[dir];
                }   
            }
        }
    private static int[] turn(int dir) {
        if (dir == 0) return new int[]{1, 3}; // ↑ : → ←
        if (dir == 1) return new int[]{0, 2}; // → : ↑ ↓ 
        if (dir == 2) return new int[]{1, 3}; // ↓ : → ←
        if (dir == 3) return new int[]{0, 2}; // ← : ↑ ↓
        return new int[]{}; // 기본값 (안 쓰임)
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
