package week4.yechan.personal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class S2_BOJ_15663_N과M9 {
    
    static int N, M;
    static List<Integer> list = new ArrayList<>();
    static boolean[] visited;
    static int[] arr;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N];
        visited = new boolean[N];

        st = new StringTokenizer(br.readLine());
        for(int i=0 ; i<N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        dfs(0);

        System.out.println(sb);
    }

    private static void dfs(int depth) {
        
        if(depth == M){
            for(int n : list){
               // System.out.println("n:" + n);
                sb.append(n).append(" ");
            }
            sb.append("\n");
            return;
        }

        // 전 값을 기억하여 비교해주면서 list에 추가
        int before = 0;
        for(int i=0; i<N; i++){
            if(!visited[i]){
                if(before != arr[i]){
                    list.add(arr[i]);
                    before = arr[i];  // 중요
                    visited[i] = true;
                    //System.out.println("arr:" + arr[i]);
                    dfs(depth+1);
                    visited[i] = false;
                    list.remove(list.size()-1);
                }
            }
        }
    }
}
