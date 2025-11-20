package week7.yechan.personal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class G5_BOJ_2170_선긋기 {

    static class Line{
        int start;
        int end;

        Line(int start, int end){
            this.start = start;
            this.end = end;
        }
        
    }

    static int N;
    static List<Line> list = new ArrayList<>();
    public static void main(String[] args) throws Exception, IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st ;

        N = Integer.parseInt(br.readLine());

        for(int i=0; i<N; i++){
            
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            list.add(new Line(start, end));            

        }

        list.sort((a,b) -> a.start - b.start);

        int currentStart = list.get(0).start;
        int currentEnd = list.get(0).end;
        //System.out.println("cs: " + currentStart + " " +  "ce:" + currentEnd) ;
        int total=0;

        for(int i=1; i<list.size(); i++){
            Line next = list.get(i);
            //System.out.println("next:" + next.start +  " " + next.end);

            if(next.start <= currentEnd){
                currentEnd = Math.max(currentEnd, next.end);
                //System.out.println("ce:" + currentEnd);
            }else{
                total += currentEnd - currentStart;
                currentStart = next.start;
                currentEnd = next.end;
                //System.out.println("total: " + total);
            }
        }

        total += currentEnd - currentStart;
        System.out.println(total);
        
    }
}
