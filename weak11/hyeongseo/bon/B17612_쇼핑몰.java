package baekjoon.queue;

import javax.security.sasl.SaslClient;
import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

/**
 * 계산대, 남은 계산대는 우선순위 큐로 설정하고
 * 동시에 끝나는 사람들을 잘 처리해줘야한다.
 */

class BaekJoon_17612{

    static int N,K;
    static Queue<int[]> queue = new ArrayDeque<>();
    static long answer = 0L;

    public static void main(String[] args) {

        Scanner sc=  new Scanner(System.in);

        N = sc.nextInt();
        K  = sc.nextInt();

        for (int i = 0; i < N; i++) {

            int id = sc.nextInt();
            int w = sc.nextInt();
            queue.add(new int[]{id,w});
        }

        cal();


    }

    private static void cal() {

        //비어있는 계산대 번호
        PriorityQueue<Integer> freePQ = new PriorityQueue<>();
        for(int i = 1; i <=K;i++)  freePQ.add(i);

        //고객 id, 끝나는 시간, 계산대 번호
        PriorityQueue<int[]> counter = new PriorityQueue<>((a,b)->{
            if(a[1]!=b[1]) return a[1]-b[1];
            return b[2] - a[2];
        });

        int time;
        int order=1;


        //초기값 주입
        while (!queue.isEmpty() && !freePQ.isEmpty()) {
            int[] poll = queue.poll();
            counter.add(new int[]{poll[0], poll[1], freePQ.poll()});
        }

        // 카운터
        while(!counter.isEmpty()){

            int[] done = counter.poll();
            int doneTime = done[1];
            int doneCounter = done[2];
            int doneId = done[0];

            time = doneTime;
            answer+= (long) order * done[0];
            order++;

            freePQ.add(done[2]);



            //같은 시간대에 끝나는 사람들
            while(!counter.isEmpty()&& counter.peek()[1] == time){
                int[] poll = counter.poll();

                answer += (long) order *poll[0];
                order++;
                freePQ.add(poll[2]);
            }

            //고객 대기줄에서 채워넣기
            while(!queue.isEmpty()&&!freePQ.isEmpty()){
                int[] next = queue.poll();
                counter.add(new int[]{next[0], time+next[1],freePQ.poll()});
            }

        }


        System.out.println(answer);
    }

}