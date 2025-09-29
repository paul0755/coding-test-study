import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_9205 {
    static int T, n;
    static int[][] arr; // [0] = 집, [1..n] = 편의점, [n+1] = 페스티벌

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        T = Integer.parseInt(br.readLine());
        while(T-- > 0) {
            n = Integer.parseInt(br.readLine());
            arr = new int[n+2][2]; // (x,y)

            for(int i=0; i<n+2; i++) {
                st = new StringTokenizer(br.readLine());
                arr[i][0] = Integer.parseInt(st.nextToken()); // x
                arr[i][1] = Integer.parseInt(st.nextToken()); // y
            }

            boolean result = bfs();
            System.out.println(result ? "happy" : "sad");
        }
    }

    static boolean bfs() {
        Queue<Integer> q = new ArrayDeque<>();
        boolean[] visited = new boolean[n+2];

        q.offer(0); // 집에서 시작
        visited[0] = true;

        while(!q.isEmpty()) {
            int cur = q.poll();
            if(cur == n+1) return true; // 페스티벌 도착

            for(int next=0; next<n+2; next++) {
                if(!visited[next] && dist(arr[cur], arr[next]) <= 1000) {
                    visited[next] = true;
                    q.offer(next);
                }
            }
        }
        return false;
    }

    static int dist(int[] a, int[] b) {
        return Math.abs(a[0] - b[0]) + Math.abs(a[1] - b[1]);
    }
}


/*
 * 📌 9205번(맥주 마시면서 걸어가기) 핵심 정리
1. 문제의 함정

보통 좌표 BFS 문제처럼 사방탐색 + visited[y][x] 로 풀려고 하면 메모리 초과.

여기서는 집, 편의점들, 페스티벌만 의미 있는 지점 → 총 n+2개만 탐색하면 됨.

2. 그래프 관점으로 바꾸기

노드: 상근이네 집(0), 편의점(1..n), 페스티벌(n+1)

간선: 두 노드 사이의 맨해튼 거리 ≤ 1000 이면 연결

3. BFS 조건

시작: 집(0번 인덱스)

다음 탐색 조건:

아직 방문하지 않았고

거리 ≤ 1000

큐에서 페스티벌(n+1번)을 만나면 → "happy", 아니면 끝까지 탐색 후 "sad"

4. 배운 포인트

모든 좌표를 탐색하는 격자 BFS가 아니라,
특정 지점 집합만 노드로 삼는 BFS도 있다는 것.

탐색 조건: 방문 여부 + 거리 제한.

편의점 들를 때마다 맥주 리필되는 효과는 “거리 1000 이하 조건”으로 자연스럽게 반영됨.
 */