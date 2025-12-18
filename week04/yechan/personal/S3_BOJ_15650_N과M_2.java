package week4.yechan.personal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class S3_BOJ_15650_N과M_2 {
    
    static int N, M;
    static List<Integer> list = new ArrayList<>();
    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        visited = new boolean[N+1];

        dfs(1, 0);

        
        
        
    }

    private static void dfs(int start, int depth) {
        
        if(depth == M){
            StringBuilder sb = new StringBuilder();
            for(int n : list){
                sb.append(n).append(" ");
            }
            System.out.println(sb);
            return;
        }

        for(int i=start; i<N+1; i++){ // i는 뽑는 숫자들
            if(!visited[i]){
                visited[i] = true;
                list.add(i);
                dfs(i+1, depth+1);
                visited[i] = false;
                list.remove(list.size()-1);
            }
        }
    }
}
