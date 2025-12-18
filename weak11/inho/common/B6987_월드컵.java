package BJ.gold_4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 승, 무승부, 패
// 총 4줄씩 6개의 나라를 보여줌.
// 가능하면 1, 불가능하면 0.
// 백트래킹
// 각 경우에 승을 감소 -> 나머지 나라의 패를 감소 반복
// 패면 다른 나라의 승을 감소 반복
// 무승부면 다른 나라의 무승부를 감소 반복.
// 이 방법으로 했다가 실패..

// 매칭 가능한 경우를 리스트업.
// dfs를 돌려봄 -> 정상적으로 15게임이 다 매칭이 되도록 결과가 나오는 경우에 1
public class B6987_월드컵 {
    static int[] result = new int[4];
    static int[][] matches = new int[15][2]; // 대진표
    public static void main(String[] args) throws IOException {
        int[][][] field = new int[4][6][3]; // 테스트, 나라수, 승무패

        // 대진표 작업
        int idx = 0;
        for (int i = 0; i < 6; i++) {
            for (int j = i + 1; j < 6; j++) {
                matches[idx][0] = i;
                matches[idx][1] = j;
                idx++;
            }
        }

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < 4; i++) {
            String[] input = br.readLine().split(" ");
            int sum = 0;
            for (int j = 0; j < 18; j++) {
                    field[i][j/3][j%3] = Integer.parseInt(input[j]);
                    sum += field[i][j/3][j%3];
            }
            if (sum != 30){
                continue;
            }
            dfs(field, 0, i); // 필드값과, 15까지 셀 카운트, 몇 번째 올림픽
        }
        System.out.println(result[0] + " " + result[1] + " " + result[2] + " " + result[3]);
    }
    // 0 -> 2 , 1 -> 1 , 2 -> 0
    private static void dfs(int[][][] field, int count, int judge) {

        // 15경기를 모두 다 배정했을 때
        if (count == 15) {
            result[judge] = 1; // 여기까지 도달했다면 가능한 경우임
            return;
        }

        int a = matches[count][0];
        int b = matches[count][1];

        if (field[judge][a][0] > 0 && field[judge][b][2] > 0){
            field[judge][a][0]--;
            field[judge][b][2]--;
            dfs(field,count+1,judge);
            field[judge][a][0]++;
            field[judge][b][2]++;
        }
        if (field[judge][a][1] > 0 && field[judge][b][1] > 0){
            field[judge][a][1]--;
            field[judge][b][1]--;
            dfs(field,count+1,judge);
            field[judge][a][1]++;
            field[judge][b][1]++;
        }
        if (field[judge][a][2] > 0 && field[judge][b][0] > 0){
            field[judge][a][2]--;
            field[judge][b][0]--;
            dfs(field,count+1,judge);
            field[judge][a][2]++;
            field[judge][b][0]++;
        }
    }
}
