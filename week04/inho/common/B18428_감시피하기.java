package BJ.gold_5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// 장애무 3개를 설치해서 선생님의 감시를 피할 수 있는지
// 0 빈칸 1 선생님 2 학생 3 장애물
// 0인 곳에 장애물 놓고 3개가 쌓일 때마다 막을 수 있는지 판단 반복.
public class B18428_감시피하기 {
    static int[][] rows = {
            {1,0},
            {-1,0},
            {0,1},
            {0,-1}
    };
    static int[][] field;
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        field = new int[N][N];
        for (int i = 0; i < N; i++) {
            String[] input = br.readLine().split(" ");
            for (int j = 0; j < N; j++) {
                if (input[j].equals("X")) field[i][j] = 0;
                else if (input[j].equals("T")) field[i][j] = 1;
                    else field[i][j] = 2;
                }
        }

        dfs(0);
        System.out.println("NO");
    }

    private static void dfs(int depth) {
        if (depth==3){
//            System.out.println("도착");
//            System.out.println(Arrays.deepToString(field));
            if(cal(field)){
                System.out.println("YES");
                System.exit(0);
            }
            return;
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (field[i][j]==0){
                    field[i][j] = 3;
                    dfs(depth+1);
                    field[i][j] = 0;
                }
            }
        }
    }

    // 피할 수 있는지 계산.
    // true 반환 시 다 피한 거
    private static boolean cal(int[][] field) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (field[i][j]==1){
                    for(int[] row : rows){
                        int x = row[0];
                        int y = row[1];

                        for (int k = 1; k < N; k++) {
                            if (i+k*x>=0 && i+k*x <N && j+k*y>=0 && j+k*y <N){
                                if (field[i+k*x][j+k*y]==2) return false;
                                if (field[i+k*x][j+k*y]==3) break;
                            }
                        }
                    }
                }
            }
        }
        return true;
    }
}
