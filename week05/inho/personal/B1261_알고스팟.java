package BJ.gold_4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class B1261_알고스팟 {
    static int N,M;
    static int[][] field;
    static int[][] dir;
    static int[][] rows = {
            {1,0},
            {-1,0},
            {0,1},
            {0,-1}
    };
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);
        field = new int[M][N];
        dir = new int[M][N];
        for (int i = 0; i < M; i++) {
            String[] input2 = br.readLine().split("");
            for (int j = 0; j < N; j++) {
                field[i][j] = Integer.parseInt(input2[j]);
                dir[i][j] = Integer.MAX_VALUE;
            }
        }

        PriorityQueue<Node> priorityQueue = new PriorityQueue<>((a,b)->a.count -b.count);
        priorityQueue.add(new Node(0,0,0));
        dir[0][0] = 0;

        while (!priorityQueue.isEmpty()){
            Node now = priorityQueue.remove();
            if (now.x==M-1&&now.y==N-1){
                System.out.println(now.count);
                return;
            }
            
            for (int[] row : rows){
                int nowX = now.x + row[0];
                int nowY = now.y + row[1];
                if (nowX <0 || nowY <0 || nowX >= M ||  nowY >= N) continue;
                if (field[nowX][nowY]==0){
                    if (dir[nowX][nowY] > now.count){
                        dir[nowX][nowY] = now.count;
                        priorityQueue.add(new Node(nowX,nowY,now.count));
                    }
                }else{
                    if (dir[nowX][nowY] > now.count+1){
                        dir[nowX][nowY] = now.count+1;
                        priorityQueue.add(new Node(nowX,nowY,now.count+1));
                    }
                }
            }
        }
    }

    static class Node{
        int x;
        int y;
        int count;
        Node(int x, int y, int count){
            this.x = x;
            this.y = y;
            this.count = count;
        }
    }
}
