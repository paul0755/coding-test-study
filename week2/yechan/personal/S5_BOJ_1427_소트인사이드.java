import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class S5_BOJ_1427_소트인사이드 {

    static String N;
    public static void main(String args[]) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        N = br.readLine();

        List<Character>list = new ArrayList<>();
        for(char c : N.toCharArray()) list.add(c);

        list.sort((a,b)-> b-a);
        

        for(char c: list) sb.append(c);
        System.out.println(sb);
    
    }
}
