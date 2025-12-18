package week4.yechan.personal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class S3_BOJ_15649_N과M1 {
    static int N, M;
    static boolean[] visited;
    static List<Integer> list = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        visited = new boolean[N+1];

        dfs(0);


    }
    private static void dfs(int depth) {
        
        if(depth == M){
            StringBuilder sb =new StringBuilder();
            for(int num : list){
                sb.append(num).append(" ");
            }
            System.out.println(sb);
            return;
        }

        for(int i=1; i<N+1; i++){
            System.out.println("visited[i]:"+ i+ " " + visited[i]);
            if(!visited[i]){
                System.out.println("depth:" + depth+", i: " +i+", list:" +list );
                visited[i] = true;
                list.add(i);
                dfs(depth+1);
                System.out.println("---------원상복귀---------");
                visited[i] = false;
                list.remove(list.size()-1);
                System.out.println("depth:" + depth+", i: " +i+", list:" +list );
                System.out.println("----------------------------");
            }
        }

    }
}
