package BJ.gold_4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

// 왼쪽 위 -> 아래쪽 아래
// 검정색 타일(0)은 통과하지 못함
// 검정색 타일을 흰색 타일로 바꾸어서 가능해지는 최소의 수를 구해야함.


public class B2665_미로만들기 {
    static int[][] field;
    static int[][] rows = {
            {1,0},
            {-1,0},
            {0,1},
            {0,-1}
    };
    static boolean[][] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int input = Integer.parseInt(br.readLine());
        field = new int[input][input];
        visited = new boolean[input][input];

        for (int i = 0; i < input; i++) {
            String[] input2 = br.readLine().split("");
            for (int j = 0; j < input; j++) {
                field[i][j] = Integer.parseInt(input2[j]);
            }
        }

        // 다익스트라 ㄱㄱ
        PriorityQueue<Node> queue = new PriorityQueue<>((a,b)->{
            return a.count - b.count;
        });
        queue.add(new Node(0,0,0));
        visited[0][0] = true;
        while (!queue.isEmpty()){
            Node now = queue.remove();
            for(int[] row : rows){
                int nowX = now.x + row[0];
                int nowY = now.y + row[1];
                if (nowX <0 || nowY <0 || nowX >= input || nowY >= input) continue;
                if (nowX == input-1 && nowY == input-1) {
                    System.out.println(now.count);
                    System.exit(0);
                }
                if (!visited[nowX][nowY]){
                    if (field[nowX][nowY]==1){
                        visited[nowX][nowY] = true;
                        queue.add(new Node(nowX,nowY,now.count));
                    }else {
                        visited[nowX][nowY] = true;
                        queue.add(new Node(nowX,nowY,now.count+1));
                    }
                }
            }
        }
        System.out.println(0);
    }

    static class Node{
        int x;
        int y;
        int count ;
        Node(int x, int y, int count){
            this.x = x;
            this.y = y;
            this.count = count;
        }
    }
}
