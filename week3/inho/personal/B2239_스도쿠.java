package BJ.gold_4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class B2239_스도쿠 {
    static int[][] field = new int[9][9];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < 9; i++) {
            String[] input = br.readLine().split("");
            for (int j = 0; j < 9; j++) {
                field[i][j] = Integer.parseInt(input[j]);
            }
        }
        dfs(0,0);
    }

    private static void dfs(int x, int y) {

        boolean finish = true;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (field[i][j] == 0) {
                    finish = false;
                    break;
                }
            }
            if (!finish) break;
        }

        if (finish) {
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    System.out.print(field[i][j]);
                }
                System.out.println();
            }
            System.exit(0);
        }

        for (int i = x; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (field[i][j]==0){
                    for (int k = 1; k <= 9; k++) { // 숫자 판별
                        field[i][j] = k;
                        if(check(i,j)) { // 유효하면
                            //System.out.println("유효"+i+""+j);
                            dfs(i,j);
                        }field[i][j] = 0;
                    }
                    return;
                }
            }
        }
    }

    private static boolean check(int x, int y) {
        // 좌우로 겹치는 숫자가 없는지.
        for (int i = y+1; i < 9; i++) {
            if (field[x][y]==field[x][i]) return false;
        }
        for (int i = 0; i < y; i++) {
            if (field[x][y]==field[x][i]) return false;
        }
        for (int i = x+1; i < 9; i++) {
            if (field[x][y]==field[i][y]) return false;
        }
        for (int i = 0; i < x; i++) {
            if (field[x][y]==field[i][y]) return false;
        }
        // 9칸에서 겹치는 숫자가 없는지.
        int xLocation = (x/3)*3;
        int yLocation = (y/3)*3;
        for (int i = xLocation; i < xLocation+3; i++) {
            for (int j = yLocation; j < yLocation+3; j++) {
                if (x==i&&y==j) continue;
                if (field[x][y] == field[i][j]) return false;
            }
        }
        return true;
    }
}
