package weak11.yechan.common;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/*
출처 : https://www.acmicpc.net/problem/6987
생각하기 어려웠던 부분

1. 경기 목록을 만드는 것 
-> 모든 경기 목록은 15 (Base Condition)
2. 입력 받은 각 국가의 승/무/패를 티켓으로 활용
-> dfs()를 돌면서 차감 
-> 모든 경기목록에 대해서 dfs를 돌림
-> 각 팀의 승/무/패가 0이 아니면 return;
3. 사전 검사
-> 입력받은 승/무/패가 합이 5가 아니면 유효하지않은 값


*/

public class G4_BOJ_6987_월드컵 {

    static boolean possible;
 
    static class Team{
        
        int win;
        int draw;
        int lose;

        Team(int win, int draw, int lose){
            this.win = win;
            this.draw = draw;
            this.lose = lose;
        }
    }

    static List<Team> ls ;
    static List<int[]> matches;

    public static void main(String[] args) throws Exception {
    
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        for(int a=0; a<4; a++){
            st = new StringTokenizer(br.readLine());
            ls = new ArrayList<>();
            possible = false;
            matches = new ArrayList<>();
            // 각팀의 승,패 저장
            for(int i=0; i<6; i++){
                int win = Integer.parseInt(st.nextToken());
                int draw = Integer.parseInt(st.nextToken());
                int lose = Integer.parseInt(st.nextToken());
                ls.add(new Team(win, draw, lose));            
            }
            // 경기 목록 구하기
            for(int i=0; i<6; i++){
                for(int j=i+1; j<6; j++){
                    matches.add(new int[]{i,j});
                }
            }

            boolean valid = true;
            // 사전 검증 
            for(Team t : ls){
                if(t.win + t.draw + t.lose != 5){
                    valid =false;
                    break;
                }
            }
            if(!valid){
                System.out.println(0);
                continue;
            }
    
            dfs(0);
            
            if(possible){
                System.out.println(1);
            }else{
                System.out.println(0);
            }
        }


    }

    private static void dfs(int idx) {
        if(possible) return;
        
        if(idx == 15){
            for(Team x : ls){
                if(x.win != 0 || x.draw != 0 || x.lose !=0) return;
            }
            possible = true;
            return;
        }

        int [] match = matches.get(idx);
        int a = match[0];
        int b = match[1];


        // A승, B패
        if(ls.get(a).win >0 && ls.get(b).lose>0){
            ls.get(a).win --;
            ls.get(b).lose --;
            dfs(idx + 1);
            ls.get(a).win ++;
            ls.get(b).lose ++;
        }
        
        // 무승부
        if(ls.get(a).draw >0 && ls.get(b).draw>0){
            ls.get(a).draw --;
            ls.get(b).draw --;
            dfs(idx + 1);
            ls.get(a).draw ++;
            ls.get(b).draw ++;
        }

        // A패 B승
        if(ls.get(a).lose >0 && ls.get(b).win>0){
            ls.get(a).lose --;
            ls.get(b).win --;
            dfs(idx + 1);
            ls.get(a).lose ++;
            ls.get(b).win ++;
        }
        
    }
}
