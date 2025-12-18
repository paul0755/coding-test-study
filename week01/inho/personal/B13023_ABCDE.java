package BJ.gold_5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class B13023_ABCDE {
    static int N,M;
    static List<List<Integer>> graph  = new ArrayList<>();
    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br  = new BufferedReader(new InputStreamReader(System.in));
        String[] arr = br.readLine().split(" ");
        N = Integer.parseInt(arr[0]);
        M = Integer.parseInt(arr[1]);
        for (int i = 0; i < N; i++) {
            graph.add(new ArrayList<>());
        }
        for(int i=0 ; i<M;i++){
            String[] input = br.readLine().split(" ");
            graph.get(Integer.parseInt(input[0])).add(Integer.parseInt(input[1]));
            graph.get(Integer.parseInt(input[1])).add(Integer.parseInt(input[0]));
        }

        for (int i = 0; i < N; i++) {
            visited = new boolean[N];
            visited[i] = true;
            if (dfs(i, 1) == 1){
                System.out.println(1);
                return;
            }
        }
        System.out.println(0);
    }

    private static int dfs(int start, int depth) {
        if(depth == 5) {
            return 1;
        }

        for( int now :graph.get(start)){
            if (!visited[now]) {
                visited[now] = true;
//                System.out.println(numberList);
                if (dfs(now,depth+1) == 1) return 1;
                visited[now] = false;
            }
        }

        return 0;
    }
}
