package weak11.yechan.common;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

/*

출처 : https://www.acmicpc.net/problem/17612

문제 포인트
1. 우선순위 큐 자료구조로 해결하기
고객과 카운터에 대한 큐 자료구조를 만듬이 필요
또한 퇴장순서를 모두 받은 순간에도
정렬 기준으로 조건을 만족 시켜줘야 했음.

잘못 이해한 부분
안내원이 걸리는 시간을 이미 다 알고있어서
기다리는 순으로 하는것이 아닌 고객중에서 물품이 적은사람부터
계산을 시키는 줄 알았는데 그런게 아닌
온 순서에서 카운터가 빌 때, 빨리빨리 계산시키는 것이였다.


*/

public class G2_BOJ_17612_쇼핑몰 {

    static Queue<int[]> customer;
    static PriorityQueue<Counter> counters;
    static List<Exit> exitList = new ArrayList<>();
    static int N, K;

    static class Counter {
        int idx;      // 계산대 번호
        int endTime;  // 이 계산대가 비는 시점

        Counter(int idx, int endTime){
            this.idx = idx;
            this.endTime = endTime;
        }
    }

    static class Exit{
        int endTime;
        int counterIdx;
        int customerId;

        Exit(int endTime, int counterIdx, int customerId){
            this.endTime = endTime;
            this.counterIdx = counterIdx;
            this.customerId = customerId;
        }
    }


    public static void main(String[] args) throws Exception {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        customer = new ArrayDeque<>();

        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            int id = Integer.parseInt(st.nextToken());
            int item = Integer.parseInt(st.nextToken());

            customer.offer(new int[]{id, item});
        }

        counters = new PriorityQueue<>((a,b) -> {
            // 더 빨리 비는 계산대
            if(a.endTime != b.endTime) return a.endTime - b.endTime;
            return a.idx - b.idx; // 같으면 번호 작은 계산대
        });

        // 카운터 초기화
        for(int i=1; i<=K; i++){
            counters.offer(new Counter(i, 0));
        }

        // 고객 한명씩 배정
        while(!customer.isEmpty()){
            int[] cur = customer.poll();

            int id = cur[0];
            int item =cur[1];
            
            // 가장 빨리 비는 계산대 선택 -> 우선순위 큐 자료구조가 해줌
            Counter counter = counters.poll();

            int startTime = counter.endTime;
            int endTime = startTime + item;

            counter.endTime = endTime;

            counters.add(counter);

            
            exitList.add(new Exit(endTime, counter.idx, id));
        }
        
        Collections.sort(exitList, (a, b) -> {
            if (a.endTime != b.endTime)
                return a.endTime - b.endTime;      // 먼저 끝난 순
            return b.counterIdx - a.counterIdx;    // 같으면 계산대 번호 큰 순
        });

        long result = 0;
        for(int i=0; i<exitList.size(); i++){
            result += (long)(i+1) * exitList.get(i).customerId;
        }

        System.out.println(result);




    }
}
