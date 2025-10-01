package baekjoon.graph;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BaekJoon_9205 {

    static int T, N;
    static int[][] grid;
    static boolean[] visited;


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        T = sc.nextInt();

        for (int i = 0; i < T; i++) {
            N = sc.nextInt();
            grid = new int[N + 2][2];
            visited = new boolean[N+2];

            grid[0][0] = sc.nextInt();
            grid[0][1] = sc.nextInt();

            for (int j = 1; j <= N; j++) {
                grid[j][0] = sc.nextInt();
                grid[j][1] = sc.nextInt();
            }

            grid[N+1][0] = sc.nextInt();
            grid[N+1][1] = sc.nextInt();


            if(bfs()) System.out.println("happy");
            else System.out.println("sad");

        }
    }

    private static boolean bfs() {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(0);
        visited[0] = true;

        while(!queue.isEmpty()){
            Integer cur = queue.poll();

            if(cur==N+1){
                return true;
            }
            for (int i = 0; i <N+2; i++) {
                if(!visited[i]) {
                    if (Math.abs(grid[cur][0] - grid[i][0]) + Math.abs(grid[cur][1] - grid[i][1]) <= 1000){
                        //System.out.println(cur+"번째 위치에서 다음 노드 방문 :"+i);
                        visited[i] =true;
                        queue.add(i);
                    }
                }
            }

        }

        return false;

    }
}
