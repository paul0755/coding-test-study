import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
/**
 * https://www.acmicpc.net/problem/10282
 * 
 * 1. 해킹에 걸리는시간 -> 비용 -> 다익스트라
 * 2. 의존 <-> 해킹 
 * 그래프를 연결할 때, 반대로 연결 (a->b) -> (b->a)
 * 3. 해킹된 컴퓨터 갯수 -> cnt(Integer.MaxValue아닌거 갯수세기)
 * 4. 마지막 컴 해킹까지 걸린시간 (생각 못함)✨
 * -> dist[] 배열에서 가장 큰 숫자 탐색 
 * -> nextCost = nowCost + next[1]이기에 
 * -> 배열내 최댓값이 마지막 컴까지 해킹하는데 걸린시간
 * 
 * 💥주의할점
 *  테스트케이스가 주어지는 문제에서는 
 *  전역 변수 사용을 주의하자!
 */

public class G4_BOJ_10282_해킹 {
    
    static int T,n,d,c,a,b,s;
    static int[] dist;
    static int maxTime;
    //static List<List<int[]>> graph = new ArrayList<>();
    // 런타임 에러 발생 -> 테스트 케이스가 여러개이기 때문
    static List<List<int[]>> graph;

    public static void main(String[] args) throws Exception, IOException {
        
        BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
        
        T = Integer.parseInt(br.readLine());

        for(int i=0; i<T; i++){
            // 테스트 케이스마다 분리하기 위해 지역선언
            maxTime = 0;

            StringTokenizer st =new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken()); // 컴의 갯수
            d = Integer.parseInt(st.nextToken()); // 의존 갯수
            c = Integer.parseInt(st.nextToken()); // 해킹 시작컴

            graph = new ArrayList<>();
            for(int j=0; j<=n; j++){
                graph.add(new ArrayList<>());
            }

            dist = new int[n+1];
            Arrays.fill(dist, Integer.MAX_VALUE);

            for(int j=0; j<d; j++){
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken()); // 의존 함
                int b = Integer.parseInt(st.nextToken()); // 의존 당함
                int s = Integer.parseInt(st.nextToken()); // 비용

                graph.get(b).add(new int[]{a,s});
            }

            int result = 0;

            dij(c);
            result = cntCom();

            cntMaxTime();

            //System.out.println("dist[lastCom]:" + dist[lastCom]);
            System.out.println(result+" "+maxTime );

        }


    }

    // dist[] 배열에서 최댓값이 마지막 컴퓨터을 해킹하기까지
    // 걸리는 시간이 될수밖에없다.
    private static void cntMaxTime() {
        for(int i=1; i<=n; i++){
            if(dist[i] != Integer.MAX_VALUE){
                maxTime = Math.max(maxTime, dist[i]);
            }
        }
    }

    private static void printdist() {
        for(int i=0; i<=n; i++){
            System.out.print(dist[i] + " ");
        }
        System.out.println();
    }


    private static int cntCom() {
        int cnt = 0;
        for(int i=0; i<=n; i++){
            if(dist[i] != Integer.MAX_VALUE){
                cnt ++;
            }
        }
        return cnt;
    }

    private static void dij(int start) {
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparing(a -> a[1]));
        dist[start] = 0;
        pq.offer(new int[]{start, 0});
        
        while(!pq.isEmpty()){
            int[] cur = pq.poll();
            int nowCom = cur[0];
            int nowS = cur[1];

            if(dist[nowCom] < nowS) continue;

            for(int[] next : graph.get(nowCom)){
                int nextCome = next[0];
                int nextS = nowS + next[1];

                if(dist[nextCome] > nextS){
                    dist[nextCome] = nextS;
                    //System.out.println("nextCome:" + nextCome + " " + "dist:" + dist[nextCome]);
                    pq.offer(new int[] { nextCome, nextS});
                }
            }
        }
    }
}
