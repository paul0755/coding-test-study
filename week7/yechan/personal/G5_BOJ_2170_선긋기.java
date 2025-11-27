package week7.yechan.personal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/*
    1차시도 : 살패
    boolean 배열 접근 -> 메모리 초과문제 및 인덱스 마이너스 구간 커버x
    set 자료구조 -> 메모리, 시간 초과
    
    2차시도 : gpt 힌트
    구간 병합
    핵심
    1. 들어온 좌표들을 list에 저장
    2. 앞 좌표를 기준으로 정렬
    3. 앞 좌표에 end위치와 다음 좌표에 start위치를 비교
    4. 앞end >= 뒤start  -> 앞 end = 뒤 end (구간 병합)
    5. 위 비교에 해당안하면 total += 앞 - 뒤 (길이 계산) 

*/

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
