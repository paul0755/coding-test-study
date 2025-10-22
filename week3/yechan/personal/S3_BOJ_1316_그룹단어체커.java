package week3.yechan.personal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class S3_BOJ_1316_그룹단어체커 {

    static int N, cnt=0;
    static String word;
    static boolean[] appear;
    
    public static void main(String[] args) throws Exception, IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        N = Integer.parseInt(br.readLine());
        for(int i=0; i<N; i++){
            appear = new boolean[26];
            word = br.readLine();
            char prev = ' ';
            boolean isGroup = true;

            for(char c : word.toCharArray()){
                if(c != prev){ // 이전 문자와 다른지 검사
                    if(appear[c-'a']){ // 이전 문자와 다른데 등장했다면 그룹단어 x
                        isGroup = false;
                        break;
                    }
                    
                }
                if(!appear[c-'a']) appear[c - 'a'] = true; // 등장시 true

                prev = c; // 이전 단어 저장
            }

            if(isGroup) cnt++;
            
        }
        System.out.println(cnt);   

    }
}
