package week4.yechan.personal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class G5_BOJ_1759_암호만들기 {
    
    static int L, C;
    static char[] alpha;
    static String vowels = "aeiou";
    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        L = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        alpha = new char[C];

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<C; i++){
            alpha[i] = st.nextToken().charAt(0);
        }

        // 정렬조건을 맞추기위해 dfs전에 정렬
        Arrays.sort(alpha);

        // for(int i=0; i<C; i++){
        //     System.out.print(alpha[i] + " ");
        // }
       
        dfs(0,0, sb);
    }
    private static void dfs(int start, int depth, StringBuilder sb) {
        
        if(depth == L){
            if(check(sb.toString())){
                System.out.println(sb.toString());
            }

            return;
        }

        for(int i=start; i<C; i++){
            sb.append(alpha[i]);
            dfs(i+1, depth+1, sb);
            sb.deleteCharAt(sb.length()-1);
        }
    }
    private static boolean check(String word) {

        int vowelCnt = 0;
        int consonantCnt = 0;
        
        // 모음 && 자음 카운트및 조건 체크
        for(char c : word.toCharArray()){
            if(vowels.indexOf(c) != -1) vowelCnt++;
            else consonantCnt++;
        }

        return (vowelCnt>= 1 && consonantCnt >= 2);
    }
}
