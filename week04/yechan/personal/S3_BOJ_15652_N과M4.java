package week4.yechan.personal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class S3_BOJ_15652_N과M4 {
    
    static int N, M;
    static List<Integer>list = new ArrayList<>();
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        dfs(1, 0);
        System.out.println(sb);
    }
    private static void dfs(int start , int depth) {
        
        if(depth == M){
            for(int n : list){
                sb.append(n).append(" ");
            }
            sb.append("\n");
            return;
        }

        for(int i = start; i <= N; i++){
            list.add(i);
            dfs(i, depth+1);
            list.remove(list.size()-1);
        }
    }
}
