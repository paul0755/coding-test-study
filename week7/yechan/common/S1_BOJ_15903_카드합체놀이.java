package week7.yechan.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 * 첫 시도 -> 배열, 정렬
 * 합체 할때마다 정렬을 해준다음 card[0] + card[1] 을 진행
 * -> O(M*N logN) 복잡도로 비효율적임
 * 
 * 두번째 -> 우선순위 큐 활용
 * 
 */

public class S1_BOJ_15903_카드합체놀이 {

    static int N, M;
    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        PriorityQueue<Long> pq = new PriorityQueue<>();

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            pq.add(Long.parseLong(st.nextToken()));
        }

        for(int i=0; i<M; i++){
            Long card1 = pq.poll();
            Long card2 = pq.poll();

            long mix = card1 + card2;

            pq.add(mix);
            pq.add(mix);
        }

        long answer = 0;
        while(!pq.isEmpty()){
            answer += pq.poll();
        }

        System.out.println(answer);

    }
}
