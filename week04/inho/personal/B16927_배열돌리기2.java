package BJ.gold_5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class B16927_배열돌리기2 {
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

            cal();

        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                System.out.print(field[i][j]+" ");
            }
            System.out.println();
        }
    }

    private static void cal() {
        int layers = Math.min(x, y) / 2;

        for (int i = 0; i < layers; i++) {
            int top = i;
            int left = i;
            int bottom = x - 1 - i;
            int right = y - 1 - i;

            int perimeter = 2 * (bottom - top + right - left);

            int r = n % perimeter;
            if (r == 0) continue;

            int[] temp = new int[perimeter];
            int idx = 0;

            for (int j = left; j < right; j++) temp[idx++] = field[top][j];
            for (int j = top; j < bottom; j++) temp[idx++] = field[j][right];
            for (int j = right; j > left; j--) temp[idx++] = field[bottom][j];
            for (int j = bottom; j > top; j--) temp[idx++] = field[j][left];

            int[] rotated = new int[perimeter];
            for (int j = 0; j < perimeter; j++) {
                rotated[j] = temp[(j + r) % perimeter];
            }

            idx = 0;
            for (int j = left; j < right; j++) field[top][j] = rotated[idx++];
            for (int j = top; j < bottom; j++) field[j][right] = rotated[idx++];
            for (int j = right; j > left; j--) field[bottom][j] = rotated[idx++];
            for (int j = bottom; j > top; j--) field[j][left] = rotated[idx++];
        }
    }
}
