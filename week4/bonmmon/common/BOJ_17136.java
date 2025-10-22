package baekjoon.bruteforce;

import java.util.Scanner;

public class BaekJoon_17136 {


    static int[] paper = {0,5,5,5,5,5};
    static int[][] grid;
    static boolean[][] visited;
    static int min = Integer.MAX_VALUE;
    public static void main(String[] args) {

        Scanner sc= new Scanner(System.in);

        grid = new int[10][10];
        visited = new boolean[10][10];

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j <10; j++) {
                grid[i][j] = sc.nextInt();
            }
        }

        dfs(0,0,0);

        if(min == Integer.MAX_VALUE){
            System.out.println(-1);
        }else{
            System.out.println(min);
        }

    }

    /**
     *
     * 백트래킹인데 옛날 하던대로 하는 백트래킹이 아닌 원본에서 백트래킹 하는 방식이다.
     * 원본 배열에 큰 색종이부터 작은 색종이 순서대로 붙혀야한다. 그래야 최소 색종이를 구할 수 있다.
     * 행부터 차례대로 이동하면서 색종이를 붙힐 수있는 지 확인한다.
     * dfs 다 돌았을시 색종이 카운트
     */

    private static void dfs(int y, int x, int count) {
        if(count>=min)return;

        if(y==10){
            min = Math.min(count,min);
            return;
        }

        if(x==10){
            dfs(y+1,0,count);
            return;
        }
        if(grid[y][x] == 0){
            dfs(y,x+1,count);
            return ;
        }


        for (int i = 5; i >0 ; i--) {
            if(paper[i]>0 && canAttach(y,x,i)){
                paper[i]--;
                attach(y,x,i,0);
                dfs(y,x+i,count+1);
                attach(y,x,i,1);
                paper[i]++;

            }


        }
    }

    private static void attach(int y, int x, int size, int number) {

        for (int i = y; i < y+size; i++) {
            for (int j = x; j <x+size ; j++) {
                grid[i][j] = number;
            }
        }


    }

    private static boolean canAttach(int y, int x, int size) {
        if(y+size>10 ||x+size> 10) return false;


        for (int i = y; i < y+size; i++) {
            for (int j = x; j <x+size ; j++) {
                if(grid[i][j]==0) return false;
            }
        }
        return true;

    }
}