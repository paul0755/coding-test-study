package BJ.gold_3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;

// #는 문이 설치된 곳으로 항상 두 곳
// .은 아무것도 없음
// !은 거울을 설치할 수 있는 위치
// *은 벽으로 아무것도 통과할 수 없다.

// 방향 때문에 고민을 많이 함.
// 미로만들기 문제와 같은 결인데, 거울을 만났을 때 그냥 직진( +0) 혹은 90도 꺾기 (+1)이 가능한 점만 다름.
// 각 위치 기준 + 방향 기준으로 가장 적게 거울을 설치한 수자를 저장해 두자.
public class B2151_거울설치 {
    static int N;
    static String[][] field;
    static int[][] rows = {
            {1,0},
            {-1,0},
            {0,1},
            {0,-1}
    };
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        int[] start = new int[2];
        boolean check = false;
        field = new String[N][N];
        int[][][] dist = new int[N][N][4];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < 4; k++) {
                    dist[i][j][k] = Integer.MAX_VALUE;
                }
            }
        }
        for (int i = 0; i < N; i++) {
            String[] input = br.readLine().split("");
            for (int j = 0; j < N; j++) {
                field[i][j] = input[j];
            }
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if(field[i][j].equals("#")){
                    start[0] =i;
                    start[1] = j;
                    check = true;
                    break;
                }
                if (check) break;
            }
        }

        // 로직 진행
        PriorityQueue<Node> priorityQueue= new PriorityQueue<>((a,b)-> a.count - b.count);
        for (int i = 0; i < 4; i++) {
            dist[start[0]][start[1]][i] = 0;
            priorityQueue.add(new Node(start[0], start[1], i, 0));
        }

        while (!priorityQueue.isEmpty()){
            Node now = priorityQueue.remove();
            if (now.count > dist[now.x][now.y][now.dir]) {
                continue;
            }

            int nowDir = now.dir;
            int dX = rows[nowDir][0];
            int dY = rows[nowDir][1];
            int nowX = now.x + dX;
            int nowY = now.y + dY;
            int newCount = now.count;

            // 오버되거나 *이면 못 가고
            if (nowX <0 || nowY<0 || nowX>=N || nowY>=N) continue;
            if (field[nowX][nowY].equals("*")) continue;

            // # 만나면 그 곳이 답이고
            if (field[nowX][nowY].equals("#") && (nowX != start[0] || nowY != start[1])) {
                System.out.println(now.count);
                return;
            }

            // !나 .일 때는 직진 가능.
            if (dist[nowX][nowY][nowDir] > newCount){
                dist[nowX][nowY][nowDir] = newCount;
                priorityQueue.add(new Node(nowX,nowY,nowDir,newCount));
            }

            // ?인데 꺾을 상황
            if (field[nowX][nowY].equals("!")){
                // 0,1 이면 2,3으로 꺾음. 순서대로 0하,1상,2우,3좌
                for (int i = 0; i < 4; i++) {
                    // 그냥 지나갈 때랑 반대로 지나갈 때는 넘어감.
                    if (nowDir == i)
                        continue;
                    if (nowDir == 0 && i == 1)
                        continue;
                    if (nowDir == 1 && i == 0)
                        continue;
                    if (nowDir == 2 && i == 3)
                        continue;
                    if (nowDir == 3 && i == 2)
                        continue;

                    // 이제 꺾기 가능
                    int turnCount = now.count + 1;
                    if (dist[nowX][nowY][i] > turnCount) {
                        dist[nowX][nowY][i] = turnCount;
                        priorityQueue.add(new Node(nowX, nowY, i, turnCount));
                    }
                }
            }
        }
    }
    static class Node{
        int x;
        int y;
        int dir;
        int count;
        Node(int x, int y, int dir, int count){
            this.x = x;
            this.y = y;
            this.dir =dir;
            this.count = count;
        }
    }
}
