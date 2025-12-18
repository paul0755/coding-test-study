package weak11.yechan.personal;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/*
출처 : https://www.acmicpc.net/problem/1303

-> 나의 병사의 위력의합, 적국의 병사의 위력의 합
*N명이 뭉쳐있을때, N^2의 위력을 냄
*/

public class S1_BOJ_1303_전쟁_전투{

    static int N, M, B_space, W_space;
    static List<Integer> B_spaces = new ArrayList<>(), W_spaces = new ArrayList<>();
    static char[][] map;
    static int[][] visited;
    
    static int[] dy = {-1,0,1,0};
    static int[] dx = {0,1,0,-1};
    public static void main(String[] args) throws Exception {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        map = new char[N][M];
        visited = new int[N][M];

        for(int i=0; i<N; i++){
            String tmp = br.readLine();
            for(int j=0; j<M; j++){
                map[i][j] = tmp.charAt(j);
            }
        }

        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                if(map[i][j] == 'W' && visited[i][j] == 0){
                    W_space = 0;
                    W_dfs(i,j);
                    W_spaces.add(W_space);

                }

                if(map[i][j] == 'B' && visited[i][j] == 0){
                    B_space = 0;
                    B_dfs(i,j);
                    B_spaces.add(B_space);
                }

            }
        }

        int W_result = 0;
        int B_result = 0;
        for(int n : W_spaces){
            W_result += n*n;
        }
        for(int n : B_spaces){
            B_result += n*n;
        }
        System.out.println(W_result + " " + B_result);

        

    }

    private static void W_dfs(int y, int x){

        W_space ++;
        visited[y][x] = W_space;

        if(map[y][x] == 'B') return;

        for(int d=0; d<4; d++){
            int ny = y + dy[d];
            int nx = x + dx[d];

            if(ny < 0 || nx <0 || ny>=N || nx>=M) continue;

            if(visited[ny][nx] == 0 && map[ny][nx] == 'W'){
                W_dfs(ny,nx);
            }
        }
    }
    private static void B_dfs(int y, int x){

        B_space ++;
        visited[y][x] = B_space;

        if(map[y][x] == 'W') return;

        for(int d=0; d<4; d++){
            int ny = y + dy[d];
            int nx = x + dx[d];

            if(ny < 0 || nx <0 || ny>=N || nx>=M) continue;

            if(visited[ny][nx] == 0 && map[ny][nx] == 'B'){
                B_dfs(ny,nx);
            }
        }
    }
    
}
