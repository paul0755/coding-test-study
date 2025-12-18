import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class G5_BOJ_2671 {

    static String pattern = "(100+1+|01)+";
    public static void main(String args[]) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String tmp = br.readLine();

        if(tmp.matches(pattern)){
            System.out.println("SUBMARINE");
        }else{
            System.out.println("NOISE");
        }
    }
}