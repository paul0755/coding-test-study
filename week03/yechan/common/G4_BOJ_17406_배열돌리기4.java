package week3.yechan.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class G4_BOJ_17406_배열돌리기4 {

    static int N,M,K;
    static int[][] map;
    static int[][] op;
    static int min_num = Integer.MAX_VALUE;

    static boolean[] visited;
    static int[] order;
    static List<int[]> permList = new ArrayList<>();
    
    public static void main(String args[]) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        
        map = new int[N+1][M+1];
        op = new int[K][3];

        for(int i=1; i<N+1; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=1; j<M+1; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // for(int i=1; i<N+1; i++){
        //     for(int j=1; j<M+1; j++){
        //         System.out.printf(map[i][j] + " ");
        //     }
        //     System.out.println();
        // }

        for(int i=0; i<K; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<3; j++){
                op[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        // for(int i=0; i<K; i++){
        //     for(int j=0; j<3; j++){
        //         System.out.print(op[i][j] + " ");
        //     }
        //     System.out.println();
        // }

        visited = new boolean[K];
        order = new int[K];
        makePerm(0);

    for (int[] order : permList) {
        int[][] copy = copyMap(map); // 원본 복사

        for (int idx : order) {
            int r = op[idx][0];
            int c = op[idx][1];
            int s = op[idx][2];

            rotate(copy, r - s, c - s, r + s, c + s);
        }

        getMinRowSum(copy); // 각 행 합 계산
    }

        // 배열의 A값 출력
        System.out.println(min_num);
    }

    private static void getMinRowSum(int[][] copy) {
         // 각 행에 최솟값 연산
        for(int i=1; i<N+1; i++){
            int row_count =0;
            for(int j=1; j<M+1; j++){
                row_count += copy[i][j];
            }
            min_num = Math.min(min_num, row_count);
        }
    }

    private static void rotate(int[][] copy,int ly, int lx, int ry, int rx) {
        
        while(ly<ry && lx< rx){

            // 왼쪽 위 값을 임시 저장
            int temp = copy[ly][lx];

            // 1️⃣ 왼쪽 세로줄: 아래 → 위
            for (int i = ly; i < ry; i++) {
                copy[i][lx] = copy[i + 1][lx];
            }

            // 2️⃣ 아래쪽 가로줄: 오른쪽 → 왼쪽
            for (int j = lx; j < rx; j++) {
                copy[ry][j] = copy[ry][j + 1];
            }

            // 3️⃣ 오른쪽 세로줄: 위 → 아래
            for (int i = ry; i > ly; i--) {
                copy[i][rx] = copy[i - 1][rx];
            }

            // 4️⃣ 위쪽 가로줄: 왼쪽 → 오른쪽
            for (int j = rx; j > lx + 1; j--) {
                copy[ly][j] = copy[ly][j - 1];
            }

            // 5️⃣ 저장해둔 값 복귀
            copy[ly][lx + 1] = temp;

            // 다음 안쪽 layer로 이동
            ly++;
            lx++;
            ry--;
            rx--;
        }
    }

    private static void makePerm(int depth) {
        if (depth == K) {
            permList.add(order.clone()); // 깊은 복사해서 저장
            return;
        }

        for (int i = 0; i < K; i++) {
            if (!visited[i]) {
                visited[i] = true;
                order[depth] = i;
                makePerm(depth + 1);
                visited[i] = false;
            }
        }
    }

    private static int[][] copyMap(int[][] src) {
        int[][] newMap = new int[src.length][src[0].length];

        for (int i = 0; i < src.length; i++) {
            newMap[i] = src[i].clone(); // 깊은 복사
        }

        return newMap;
        }
}

/*
 * 💥생각하지 못한 부분
 * 1. 배열 시계방향 연산
 * - 맨 밖부터 한겹씩 안으로 들어가면서 연산한다.
 * 
 * 2. 배열 복사
 * - 회전 연산 순서에 따라 값이 달라지기에 원본을 복사해야했다.
 * 
 * 3. 모든 연산순서
 * - 모든 연산순서에 대해 순열 만드는 작업 또한 필요했다.
 * 
 * 4. 값손실
 * - 값의 이동방향과 접근순서는 항상 반대여야한다.
 * - 둘이 같게되면 모든 값이 통일되는 현상이 발생한다.
 * ex) 아래 -> 위로 밀려면, 위에서 부터 접근해서 위값 = 아래값 연산적용
 * 
 */