package baekjoon.bruteforce;

import java.util.Scanner;

/**
 * 처음 승 패 숫자가 같아야하고 비긴 나라가 1개면 안되고 이런 조건식으로만 해결할 수 있을줄 알았는데.
 * 하나하나 다 경기를 확인해봐야한다.
 * 어차피 6개국의 승무패 이니 3^6 이니 백트래킹으로 전부 살펴보면된다.
 */

public class BaekJoon_6987 {

    static int[][] score;
    static boolean answer;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        for (int total = 0; total < 4; total++) {
            score = new int[6][3];
            answer = false;


            for (int i = 0; i < 6; i++) {
                for (int j = 0; j < 3; j++) {
                    score[i][j] = sc.nextInt();
                }
            }

            // 기본 조건 체크
            boolean valid = true;
            for (int i = 0; i < 6; i++) {
                if (score[i][0] + score[i][1] + score[i][2] != 5) {
                    valid = false;
                    break;
                }
            }

            if (valid) {
                dfs(0, 1);   //  0부터 시작
            }

            System.out.print((answer ? 1 : 0) + " ");
        }
    }

    private static void dfs(int idx, int nxt) {

        // 모든 경기 완료 (0~4번 팀까지 정상 처리)
        if (idx == 5) {
            answer = true;
            return;
        }

        // idx 승, nxt 패
        if (score[idx][0] > 0 && score[nxt][2] > 0) {
            score[idx][0]--;
            score[nxt][2]--;
            if (nxt == 5) {
                dfs(idx + 1, idx + 2);
            } else {
                dfs(idx, nxt + 1);
            }
            score[idx][0]++;
            score[nxt][2]++;
        }

        // 무승부
        if (score[idx][1] > 0 && score[nxt][1] > 0) {
            score[idx][1]--;
            score[nxt][1]--;
            if (nxt == 5) {
                dfs(idx + 1, idx + 2);
            } else {
                dfs(idx, nxt + 1);
            }
            score[idx][1]++;
            score[nxt][1]++;
        }

        // idx 패, nxt 승
        if (score[idx][2] > 0 && score[nxt][0] > 0) {
            score[idx][2]--;
            score[nxt][0]--;
            if (nxt == 5) {
                dfs(idx + 1, idx + 2);
            } else {
                dfs(idx, nxt + 1);
            }
            score[idx][2]++;
            score[nxt][0]++;
        }
    }

}
