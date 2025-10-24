package week5.yechan.personal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * ✨흐름 설명 (한번에 성공!)
 * 6개를 고른다, 순서를 고려한다 -> 조합
 * 조합 -> start, depth, visited 필요
 * 
 */

public class S2_BOJ_6603_로또 {
    
    static int K, S;
    static int[] arr;
    static boolean [] visited;
    static List<Integer> list = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        while(true){
            
            st = new StringTokenizer(br.readLine());
            K = Integer.parseInt(st.nextToken());
            if(K == 0) break;
            
            arr = new int[K];
            visited = new boolean[K];

            for(int i=0; i<K; i++){
                arr[i] = Integer.parseInt(st.nextToken());
            }

            dfs(0,0);
            System.out.println();

        }
    }

    private static void dfs(int start, int depth) {
        
        if(depth == 6){
            StringBuilder sb = new StringBuilder();
            for(int n : list){
                sb.append(n).append(" ");
            }
            System.out.println(sb);
            return;
        }

        for(int i = start; i<K; i++){
            if(!visited[i]){
                list.add(arr[i]);
                visited[i] = true;
                dfs(i+1, depth+1);
                visited[i] = false;
                list.remove(list.size()-1);
            }
        }
    }
}
