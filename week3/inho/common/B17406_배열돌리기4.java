package BJ.gold_4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class B17406_배열돌리기4 {
    static int N, M, K;
    static int[][] filed;
    static int[][] cal;
    static boolean[] visited;
    static int[] order;
    static int minValue = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);
        K = Integer.parseInt(input[2]);
        visited = new boolean[K];
        order = new int[K];
        filed = new int[N][M];
        cal = new int[K][3];
        for (int i = 0; i < N; i++) {
            String[] input2 = br.readLine().split(" ");
            for (int j = 0; j < M; j++) {
                filed[i][j] = Integer.parseInt(input2[j]);
            }
        }
        for (int i = 0; i < K; i++) {
            String[] input3 = br.readLine().split(" ");
            for (int j = 0; j < 3; j++) {
                cal[i][j] = Integer.parseInt(input3[j]);
            }
        }

        //System.out.println(Arrays.deepToString(filed));
        //System.out.println(Arrays.deepToString(cal));
        // cal의 길이만큼 계산 인덱스 기준 (0-1),(1-2) 에서 (0+1),(1+2) 까지의 필드를 시계방향으로 돌리기.
        // K가 2개 이상이면 순서에 따라서 수행 결과가 달라질 수 있음.
        dfs(0);

        System.out.println(minValue);
        // 돌려진 결과를 가지고 최소값을 구해보자
    }

    private static void dfs(int depth) {
        if (depth == K) {
            // 목표 길이에 도달하면 도형회전 진행하고 최소값 갱신
            int[][] copy = deepCopy(filed);
            for (int i = 0; i < K; i++) { // 도형 회전을 K번 진행
                calculation(copy, cal[order[i]]);
            }
            minValue = Math.min(minValue, getMin(copy)); // 회전 완료한 결과로 최소값 갱신
            return;
        }

        for (int i = 0; i < K; i++) {
            if (!visited[i]) {
                visited[i] = true;
                order[depth] = i;
                dfs(depth + 1);
                visited[i] = false;
            }
        }
    }

    private static int getMin(int[][] copy) {
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < copy.length; i++) {
            int sum = 0;
            for (int j = 0; j < copy[0].length; j++) {
                sum += copy[i][j];
            }
            min = Math.min(min, sum);
        }
        return min;
    }

    private static int[][] deepCopy(int[][] filed) {
        int[][] copy = new int[filed.length][filed[0].length];
        for (int i = 0; i < filed.length; i++) {
            copy[i] = filed[i].clone();
        }
        return copy;
    }

    private static void calculation(int[][] copy, int[] order) {
        // 카피 배열과 순서를 받아와서 도형 회전.
        int r = order[0] - 1;
        int c = order[1] - 1;
        int s = order[2];

        for (int layer = 1; layer <= s; layer++) {
            int firstX = r - layer;
            int firstY = c - layer;
            int endX = r + layer;
            int endY = c + layer;

            int temp = copy[firstX][endY];
            for (int i = endY; i > firstY; i--) { // 위 왼쪽 -> 위 오른쪽
                copy[firstX][i] = copy[firstX][i - 1];
            }
            for (int i = firstX; i < endX; i++) { // 아래 왼쪽 -> 위 왼쪽
                copy[i][firstY] = copy[i + 1][firstY];
            }
            for (int i = firstY; i < endY; i++) { // 아래 오른쪽 -> 아래 왼쪽
                copy[endX][i] = copy[endX][i + 1];
            }
            for (int i = endX; i > firstX; i--) { // 위 오른쪽 -> 아래 오른쪽
                copy[i][endY] = copy[i - 1][endY];
            }
            copy[firstX + 1][endY] = temp;
        }

    }
}
