package week6.yechan.personal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

public class G5_BOJ_18869_멀티버스2 {

    static int N, M;
    static int[][] universe, compressedNums;
    static List<Integer>[] sortedNums;

    public static void main(String[] args) throws IOException {
    
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken()); // 우주의 수
        N = Integer.parseInt(st.nextToken()); // 행성의 수

        universe = new int[M][N];
        sortedNums = new ArrayList[M];
        
        for(int i=0; i<M; i++){
            Set<Integer> set = new HashSet<>();

            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++){
                int num = Integer.parseInt(st.nextToken());
                universe[i][j] = num;
                set.add(num);
            }
            
            sortedNums[i] = new ArrayList<>(set);
            Collections.sort(sortedNums[i]);

        }

        compressedNums = new int[M][N];
        for(int i=0; i<M; i++){
            for(int j=0; j<N; j++){
                compressedNums[i][j] = Collections.binarySearch(sortedNums[i], universe[i][j]);
            }
        }

        int cnt =0;
        for(int i=0; i<M; i++){
            for(int j= i+1; j<M; j++){
                if(Arrays.equals(compressedNums[i], compressedNums[j])) cnt++;
            }
        }
        System.out.println(cnt);
        

    }
}
