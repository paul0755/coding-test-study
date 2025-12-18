import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class S3_BOJ_9996 {

    static int N;
    static String pt;

    public static void main(String args[]) throws NumberFormatException, IOException{
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        N = Integer.parseInt(br.readLine());
        pt = br.readLine();

        int idx = pt.indexOf('*');
        if(idx == -1) return;

        String first = pt.substring(0, idx);
        String Last = pt.substring(idx+1);
        //String Last = pt.substring(length-idx);
        //System.out.println("L:" + Last);
        int f_length = first.length();
        int L_length = Last.length();

        for(int i=0 ; i<N ; i++){
            String tmp = br.readLine();
            if (tmp.length() < first.length() + Last.length()) {
                System.out.println("NE");
                continue;
            }
               
            int t_length = tmp.length();

            String tmp_first = tmp.substring(0, f_length);
            String tmp_last = tmp.substring(t_length-L_length);

            if(first.equals(tmp_first)&& Last.equals(tmp_last)){
                System.out.println("DA");
            }else{
                System.out.println("NE");
            }
        }

    }
}