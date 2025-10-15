

import java.util.*;

/**
 * 경로 저장을 List에 저장을하였지만 큐에 넣을때마다 리스트를 새로 생성해주고 값을 복사해야하기 때문에 시간초과
 * 배열 tracked[next] = cur 이런식으로 저장을 한다음 추적하여 경로를 알 수 있음
 */

public class BaekJoon_13913 {

    static int N,K;
    static boolean[] visited;
    static int[] tracked;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        K = sc.nextInt();

        visited = new boolean[100_001];
        tracked = new int[100_001];

        bfs();

        List<Integer> answer = new ArrayList<>();

        int end = K;

        answer.add(end);
        while(end!=N){
            end = tracked[end];
            answer.add(end);
        }
        Collections.reverse(answer);

        System.out.println(answer.size() -1);
        for (Integer integer : answer) {
            System.out.print(integer+" ");
        }


    }

    private static void bfs() {
        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(N);
        visited[N] =true;

        while(!queue.isEmpty()){
            int cur = queue.poll();

            if (cur == K) {
                return;
            }
            for (int i = 0; i <= 2; i++) {
                int next = cur;
                if(i==0) next-=1;
                else if (i==1)  next+=1;
                else next*=2;

                if(next>=0 && next<=100_000 && !visited[next]){
                    queue.add(next);
                    tracked[next] = cur;
                    visited[next] = true;
                }

            }
        }

    }

}
