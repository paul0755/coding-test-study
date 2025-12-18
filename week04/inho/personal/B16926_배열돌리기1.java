package BJ.gold_5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class B16926_배열돌리기1 {
    static int x,y,n;
    static int[][] field;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        x = Integer.parseInt(input[0]);
        y = Integer.parseInt(input[1]);
        n = Integer.parseInt(input[2]);
        field = new int[x][y];
        for (int i = 0; i < x; i++) {
            String[] input2 = br.readLine().split(" ");
            for (int j = 0; j < y; j++) {
                field[i][j] = Integer.parseInt(input2[j]);
            }
        }

        for (int i = 0; i < n; i++) {
            cal();
        }

        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                System.out.print(field[i][j]+" ");
            }
            System.out.println();
        }
    }

    private static void cal() {
        int min = Math.min(x,y);
        for (int i = 0; i < min/2; i++) {
            int deletedNum = field[i][i]; // 왼쪽 위에서 두번째 값에 넣어줄 용도
            for (int j = i ; j < y-i-1; j++) { // 오른쪽 위 -> 왼쪽 위
                field[i][j] = field[i][j+1];
                //System.out.println(field[i][j]);
            }
            for (int j = i ; j < x-i-1; j++) { // 오른쪽 아래 -> 오른쪽 위
                field[j][y-i-1] = field[j+1][y-i-1];
            }
            for (int j = y-i-1; j >i ; j--) { // 왼쪽 아래 -> 오른쪽 아래
                field[x-i-1][j] = field[x-i-1][j-1];
            }
            for (int j = x-i-1; j >i; j--) { // 왼쪽 위 -> 왼쪽 아래
                field[j][i] = field[j-1][i];
            }
            field[i+1][i] = deletedNum;
        }
    }
}
