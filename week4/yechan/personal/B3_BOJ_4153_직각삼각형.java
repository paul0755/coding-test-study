package week4.yechan.personal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class B3_BOJ_4153_직각삼각형 {
    
    static int a,b,c;
    static List<Integer> triangle = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        while(true){
            
            st = new StringTokenizer(br.readLine());

            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());

            if(a==0 && b == 0 && c==0) break;

            triangle.clear();
            triangle.add(a);
            triangle.add(b);
            triangle.add(c);

            triangle.sort(null);

            if((Math.pow(triangle.get(0), 2) + Math.pow(triangle.get(1), 2)) == Math.pow(triangle.get(2), 2)){
                System.out.println("right");
            }else{
                System.out.println("wrong");
            }

        }
            
        
    }
}
