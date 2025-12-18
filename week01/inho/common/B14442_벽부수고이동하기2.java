package BJ.gold_3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class B14442_벽부수고이동하기2 {
    static int N,M,K;
    static int[][] field;
    static boolean[][][] visited;
    static int[][] rows = {
            {1,0},
            {-1,0},
            {0,1},
            {0,-1}
    };
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] arr = br.readLine().split(" ");
        N = Integer.parseInt(arr[0]);
        M = Integer.parseInt(arr[1]);
        K = Integer.parseInt(arr[2]);
        visited = new boolean[N][M][K+2];
        field = new int[N][M];
        for (int i = 0; i < N; i++) {
            String[] input = br.readLine().split("");
            for (int j = 0; j < M; j++) {
                field[i][j] = Integer.parseInt(input[j]);
            }
        }

        Deque<Node> deque = new ArrayDeque<>();
        deque.add(new Node(0,0,1,0));
        visited[0][0][0] = true;

        while(!deque.isEmpty()){
            Node now = deque.remove();
            if (now.x==N-1 && now.y==M-1) {
                System.out.println(now.count);
                return;
            }
            for(int[] row : rows){
                int nx = now.x + row[0];
                int ny = now.y + row[1];
                if (nx<0||ny<0||nx>=N||ny>=M) continue;
                if (!visited[nx][ny][now.magic] && field[nx][ny] ==0){
                    visited[nx][ny][now.magic] = true;
                    deque.add(new Node(nx,ny,now.count+1, now.magic));
                }else if (!visited[nx][ny][now.magic+1] && field[nx][ny] ==1 &&now.magic<K ){
                    visited[nx][ny][now.magic+1] = true;
                    deque.add(new Node(nx,ny,now.count+1, now.magic+1));
                }
            }
        }
        System.out.println(-1);
    }
    static class Node{
        int x;
        int y;
        int count;
        int magic;
        Node(int x, int y, int count,int magic){
            this.x = x;
            this.y = y;
            this.count = count;
            this.magic = magic;
        }
    }
}
