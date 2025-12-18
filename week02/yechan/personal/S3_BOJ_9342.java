import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class S3_BOJ_9342 {

    static int T;
    static String pattern = "^[A-F]?A+F+C+[A-F]?$";
    public static void main(String args[]) throws NumberFormatException, IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        T = Integer.parseInt(br.readLine());
            

        for(int i=0; i<T; i++){
            String str = br.readLine();

            if(str.matches(pattern)){
                System.out.println("Infected!");
            }else{
                System.out.println("Good");
            }   
        }
    }
}