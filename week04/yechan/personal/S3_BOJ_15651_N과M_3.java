package week4.yechan.personal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class S3_BOJ_15651_N과M_3 {

    static int N, M;
    static List<Integer> list = new ArrayList<>();
    static StringBuilder sb = new StringBuilder();
    //static int[] arr ;
    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        //arr = new int[N];

        dfs(0);
        System.out.println(sb);

    }
    private static void dfs(int depth) {
        if(depth == M){
            for(int n: list){
                sb.append(n).append(" ");
            }
            sb.append("\n");
            //System.out.println(sb); -> 매번 출력했더니 시간초과
            return;
        }

        for(int i = 1 ; i<N+1; i++){
            list.add(i);
            dfs(depth+1);
            list.remove(list.size()-1);
        }
    }
}
