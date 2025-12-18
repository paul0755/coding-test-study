package BJ.gold_2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import org.w3c.dom.ls.LSOutput;

// 0또는 1이 적혀있는 색종이 5개.
// 1이 적힌 칸은 모두 덮어져야 함. 0이 적힌 칸에는 색종이가 있으면 안 됨.
// 모든 1을 덮는데 필요한 색종이의 최소 개수를 출력. 불가능시 -1
// 색종이 크기는 1*1, 2*2, 3*3, 4*4, 5*5 가 있고, 5개씩 있음.
// 서로 겹치게 붙이면 안 됨.

// 1을 만났을 때, 왼쪽 위로 가정하고 1,2,3,4,5 색종이중 뭘 붙일 수 있는지 판단. -> 최소값이니까 가장 큰 5부터 확인해야할 듯.
// 그 후 해당 색종이 정보를 쌓아감. 5개 넘어가면 보다 작은 색종이 붙이기. 1에 도착했는데도 5개를 넘어가면 -1 반환.

public class B17136_색종이붙이기 {
    static int[][] field = new int[10][10];
    static int[] paper = {0, 5, 5, 5, 5, 5}; // 색종이 크기별 남은 개수 (1~5)
    static int answer = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < 10; i++) {
            String[] input = br.readLine().split(" ");
            for (int j = 0; j < 10; j++) {
                field[i][j] = Integer.parseInt(input[j]);
            }
        }

    dfs(0,0,0);

        if (answer != Integer.MAX_VALUE) {
            System.out.println(answer);
        } else {
            System.out.println(-1);
        }

    }

    private static void dfs(int x, int y, int count){
        if (x == 10){
            //System.out.println("도착");
            answer = Math.min(answer,count);
            return;
        }

        if (count >= answer) return;

        if (y == 10){
            //System.out.println("새로운 x줄 시작");
            dfs(x+1,0,count);
            return;
        }

        if (field[x][y] == 1){
            for (int i = 5; i >=1 ; i--) {
                if (paper[i] > 0&& can(x,y,i)){ // 붙일 수 있으면
                    //System.out.println("붙이기 가능");
                    attach(x,y,i,0);
                    paper[i]--;
                    dfs(x,y+1,count+1);
                    attach(x,y,i,1);
                    paper[i]++;
                }

            }
        }else {
            dfs(x,y+1,count);
        }
    }

    private static void attach(int x, int y, int size, int number) {
        for (int i = x; i < x+size; i++) {
            for (int j = y; j < y+size; j++) {
                field[i][j] = number;
            }
        }
    }

    private static boolean can(int x, int y, int i) {
        if (x+i>10 || y+i>10) return false;
        for (int j=x;j<x+i;j++){
            for(int k=y;k<y+i;k++){
                if (field[j][k]!=1) return false;
            }
        }
        return true;
    }
}
