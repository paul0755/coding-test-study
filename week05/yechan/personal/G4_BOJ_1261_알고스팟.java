package week5.yechan.personal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
/**
 * 
 * 무난하게 풀린 문제!
 * 좌표를 풀땐 따로 그래프는 필요하지않다.
 * 벽부수기를 비용으로 두고 PQ에 저장~
 * 
 */

public class G4_BOJ_1261_알고스팟 {
    static int N, M;
    static int INF = 200000000;
    static int[][] dist;
    static int[][] map;
    static int[] dy = {-1,0,1,0};
    static int[] dx = {0,1,0,-1};

    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        dist = new int[M][N];
        for(int i=0; i<M; i++){
            for(int j=0; j<N; j++){
                dist[i][j] = INF;
            }
        }
        map = new int[M][N];

        for(int i=0; i<M ; i++){
            String tmp = br.readLine();
            for(int j=0; j<N; j++){
                map[i][j] = tmp.charAt(j) - '0';
            }
        }

        dij(0,0);

        //printDist();
        
        System.out.println(dist[M-1][N-1]);

    }

    private static void printDist() {
        
        for(int i=0; i<M; i++){
            for(int j=0; j<N; j++){
                System.out.print(dist[i][j]);
            }
            System.out.println();
        }
        
    }

    private static void dij(int sy, int sx) {
        
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparing(a -> a[2]));
        dist[sy][sx] = 0;
        pq.offer(new int[]{sy,sx,0});

        while(!pq.isEmpty()){
            int[] cur = pq.poll();
            int y = cur[0];
            int x = cur[1];
            int cost = cur[2];

            if(cost > dist[y][x]) continue;

            for(int d=0; d<4; d++){
                int ny = y + dy[d];
                int nx = x + dx[d];
                
                if(ny < 0 || nx < 0 || ny >= M || nx >=N ) continue;
                int nextCost = cost + (map[ny][nx] == 1 ? 1 : 0);

                if(nextCost < dist[ny][nx]){
                    dist[ny][nx] = nextCost;
                    pq.offer(new int[]{ny,nx,nextCost});
                }
            }

        }



    }
}
