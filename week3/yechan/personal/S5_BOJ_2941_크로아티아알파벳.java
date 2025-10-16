package week3.yechan.personal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class S5_BOJ_2941_크로아티아알파벳 {
    
    static int cnt = 0;
    static String word;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        word = br.readLine();

        String[] croatia = {"c=", "c-", "dz=", "d-", "lj", "nj", "s=", "z="};
        
        for(String pattern : croatia){
            word = word.replace(pattern, "!");

        }
        System.out.println(word.length());
    }

    // 삽질
    //         if(c == 'c'){
    //             prev = c;
    //             continue;
    //         }

    //         if(prev == 'c'){
    //             if(c == '=' || c == '-'){
    //                 cnt ++;
    //                 prev = ' ';
    //                 continue;
    //             }
    //         }

    //         if(c == 'd'){
    //             prev = 'd';
    //             continue;
    //         }

    //         if(prev == 'd'){
    //             if(c == '-'){
    //                 cnt ++;
    //                 prev = ' ';
    //                 continue;
    //             }
    //             if(c == 'z'){
    //                 prev = 'z';
    //                 continue;
    //             }
    //         }

    //         if(prev == 'z'){
    //             if(c == '='){
    //                 cnt++;
    //                 prev = ' ';
    //                 continue;
    //             }
    //         }

    //         if(c == 'l'){
    //             prev = 'l';
    //             continue;
    //         }

    //         if(prev == 'l'){
    //             if(c == 'j'){
    //                 cnt ++;
    //                 prev = ' ';
    //                 continue;
    //             }
    //         }

    //         if(c == 'n'){
    //             prev = 'n';
    //             continue;
    //         }

    //         if(prev == 'n'){
    //             if(c == 'j'){
    //                 cnt++;
    //                 prev = ' ';
    //                 continue;
    //             }
    //         }

    //         if(c == 's'){
    //             prev = 's';
    //             continue;
    //         }

    //         if(prev == 's'){
    //             if(c == '='){
    //                 cnt++;
    //                 prev = ' ';
    //                 continue;
    //             }
    //         }

    //         cnt++;
    //     }
    //     System.out.println(cnt);
    // }
}
