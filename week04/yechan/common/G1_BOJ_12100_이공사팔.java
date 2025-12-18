package week4.yechan.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class G1_BOJ_12100_이공사팔 {

    static int N;
    static int[][] map;
    static int maxBlock = 0;

    public static void main(String[] args) throws Exception, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        map = new int[N][N];

        for(int i=0; i<N; i++ ){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        //printBoard(map);
        

        //move_left(copy_map);
        //move_right(copy_map);
        //move_up(copy_map);
        //move_down(copy_map);


        dfs(0, map);
                System.out.println(maxBlock);
                        
                            }
                            private static void dfs(int depth, int[][] board) {
                if(depth == 5){
                    for(int i=0; i<N; i++){
                        for(int j=0; j<N; j++){
                            maxBlock = Math.max(maxBlock, board[i][j]);
                        }
                    }
                    return;
                }

                for(int i= 0; i<4; i++){
                    int[][]copy = deepCopy(board);
                                        
                                        move(copy, i);
                                        dfs(depth+1, copy);
                                                            
                                                        }
                                                    }
                                                                    private static int[][] deepCopy(int[][] board) {
                                                                        int[][] copy = new int[N][N];
                                                                        for(int i=0; i<N; i++){
                                                                            copy[i] = board[i].clone();
                                                                        }
                                                                        return copy;
                                                }
                                                                    private static void move(int[][] copy, int i) {
                                                    switch (i) {
                                                        case 0:
                                                            move_left(copy);
                                                            break;
                                                        case 1:
                                                            move_right(copy);
                                                            break;
                                                        case 2:
                                                            move_up(copy);
                                                            break;
                                                        case 3:
                                                            move_down(copy);
                                                            break;
                                                        default:
                                                            break;
                                                    }
                                                }
                                                private static void printBoard(int[][] copy_map) {
                for(int i=0; i<N; i++){
                    for(int j=0; j<N; j++){
                        System.out.print(copy_map[i][j] + " ");
                    }
                    System.out.println();
                }
            }
            private static void move_left(int[][] board) {
                for (int i = 0; i < N; i++) {
                    int[] newRow = new int[N];
                    int idx = 0;
                    int prev = 0;
            
                    for (int j = 0; j < N; j++) {
                        if (board[i][j] == 0) continue;
            
                        if (prev == 0) {
                            prev = board[i][j];
                        } else if (prev == board[i][j]) {
                            newRow[idx++] = prev * 2;
                            prev = 0;
                        } else {
                            newRow[idx++] = prev;
                            prev = board[i][j];
                        }
                    }
            
                    if (prev != 0) newRow[idx] = prev;
                    board[i] = newRow; // 새 행으로 교체
                }
            }
            
            private static void move_right(int[][] board) {
                for (int i = 0; i < N; i++) {
                    int[] newRow = new int[N];
                    int idx = N - 1;
                    int prev = 0;
            
                    // 👉 오른쪽 끝부터 왼쪽으로 탐색
                    for (int j = N - 1; j >= 0; j--) {
                        if (board[i][j] == 0) continue;
            
                        if (prev == 0) {
                            prev = board[i][j];
                        } else if (prev == board[i][j]) {
                            newRow[idx--] = prev * 2;
                            prev = 0;
                        } else {
                            newRow[idx--] = prev;
                            prev = board[i][j];
                        }
                    }
            
                    if (prev != 0) newRow[idx] = prev;
                    board[i] = newRow;
                }
            }
            
            private static void move_up(int[][] board) {
                for (int j = 0; j < N; j++) {
                    int[] newCol = new int[N];
                    int idx = 0;
                    int prev = 0;
            
                    // 👆 위쪽으로 이동 → 위에서 아래로 탐색
                    for (int i = 0; i < N; i++) {
                        if (board[i][j] == 0) continue;
            
                        if (prev == 0) {
                            prev = board[i][j];
                        } else if (prev == board[i][j]) {
                            newCol[idx++] = prev * 2;
                            prev = 0;
                        } else {
                            newCol[idx++] = prev;
                            prev = board[i][j];
                        }
                    }
            
                    if (prev != 0) newCol[idx] = prev;
            
                    // 새 열을 원본 보드에 반영
                    for (int i = 0; i < N; i++) {
                        board[i][j] = newCol[i];
                    }
                }
            }
            
            private static void move_down(int[][] board) {
                for (int j = 0; j < N; j++) {
                    int[] newCol = new int[N];
                    int idx = N - 1;
                    int prev = 0;
            
                    // 👇 아래로 이동 → 아래에서 위로 탐색
                    for (int i = N - 1; i >= 0; i--) {
                        if (board[i][j] == 0) continue;
            
                        if (prev == 0) {
                            prev = board[i][j];
                        } else if (prev == board[i][j]) {
                            newCol[idx--] = prev * 2;
                            prev = 0;
                        } else {
                            newCol[idx--] = prev;
                            prev = board[i][j];
                        }
                    }
            
                    if (prev != 0) newCol[idx] = prev;
            
                    // 새 열을 원본 보드에 반영
                    for (int i = 0; i < N; i++) {
                        board[i][j] = newCol[i];
                    }
                }
            }

}   
    