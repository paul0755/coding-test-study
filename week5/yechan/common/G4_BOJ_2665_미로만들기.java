import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
/**
 * 이 문제의 핵심은 “벽을 부순 개수(=비용)”을 최소화하면서 도착점에 가는 최단경로를 구하는 거예요.

    1. 벽 부순 횟수를 기준으로 우선순위 큐 사용
    2. dist[][]에는 부순횟수를 최소로 저장하게끔 함.
    3. 이미 더 짧은 경로를 방문했다면 Skip
    if ( cost < dist[y][x] ) continue
    4. 4방탐색
    5. 다음 비용 계산
    nextCost = cost(부순횟수) + map[ny][nx] == 1 ? 1: 0;
    6. 다음 비용이 원래 비용보다 작다면 갱신후 우선순위에 넣기
    if(nextCost < dist[ny][nx]){
        dist[ny][nx] = nextCost;
        pq.offer(new int[]{ny,nx,nextCost})
    } 

 */

public class G4_BOJ_2665_미로만들기 {

    static int n, cnt=0;
    static int[][] map;
    static int[][] dist;
    //static List<List<int[]>> graph = new ArrayList<>();

    static int[] dy = {1,0,-1,0};
    static int[] dx = {0,1,0,-1};
    
    public static void main(String[] args) throws Exception, IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        map = new int[n][n];
        dist = new int[n][n];
        
        for(int i=0; i<n;i++){
            for(int j=0; j<n; j++){
                dist[i][j] = 100;
            }
        }

        for(int i=0; i<n; i++){
            String tmp = br.readLine();
            for(int j=0; j<n ; j++){
                map[i][j] = tmp.charAt(j) - '0';
            }
        }

        bfs(0,0,0);

        System.out.println(dist[n-1][n-1]);
        
    }

    private static void bfs(int sy, int sx, int b_cnt) {
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparing(a -> a[2]));
        dist[sy][sx] = 0;
        pq.offer(new int[]{sy,sx, b_cnt}); // y,x, 벽 부순 횟수

        while(!pq.isEmpty()){
            int[] cur = pq.poll();
            int y = cur[0];
            int x = cur[1];
            int cost = cur[2];

            // 이미 더 짧은 경로로 방문했다면 skip
            if(cost > dist[y][x]) continue;
            
            for(int d=0; d<4; d++){
                int ny = y + dy[d];
                int nx = x + dx[d];

                if(ny < 0 || nx <0 || ny>=n || nx>=n) continue;

                // 다음 칸의 비용 계산
                int nextCost = cost + (map[ny][nx] == 0 ? 1 : 0);

                // 더 적은 비용으로 갈 수 있으면 갱신
                if(nextCost < dist[ny][nx]){
                    dist[ny][nx] = nextCost;
                    //System.out.println("dist[ny][nx] : " + dist[ny][nx]) ;
                    pq.offer(new int[]{ny,nx, nextCost});
                }
            }
        }

    }

    

}


/*
 * < 우선순위 큐로 안푸는 방법 >
 *   public static void bfs(){
        Queue<Node> q = new LinkedList<>();
        q.add(new Node(0,0));   //시작점
        dist[0][0] = 0;

        while(!q.isEmpty()){
            Node now = q.poll();

            for(int i=0;i<4;i++){   //상하좌우 탐색
                int nextX = now.x+dx[i];
                int nextY = now.y+dy[i];

                if(nextX >=0 && nextY >= 0 && nextX < N && nextY < N){
                    if(dist[nextX][nextY] > dist[now.x][now.y]){
                        if(map[nextX][nextY] == 1) dist[nextX][nextY] = dist[now.x][now.y];
                        else dist[nextX][nextY] = dist[now.x][now.y]+1; //이동
                        q.add(new Node(nextX,nextY));
                    }
                }
            }
        }
 * 
 * 
 */