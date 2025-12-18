package week4.yechan.personal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class G4_BOJ_2580_스도쿠 {

    static int[][] map = new int[9][9];
    static List<int[]> arr = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        for(int i=0; i<9; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<9; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i=0; i<9; i++){
            for(int j=0; j<9; j++){
                // 빈칸을 리스트에 저장
                if(map[i][j] == 0) arr.add(new int[]{i,j});
            }
        }

        dfs(0);


        

    }
    private static void dfs(int depth) {
        if(depth == arr.size()){
            printMap();
            System.exit(0);
        }

        int y = arr.get(depth)[0];
        int x = arr.get(depth)[1];

        for(int num = 1; num <=9; num++){
            if(isValid(y,x,num)){
                map[y][x] = num;
                dfs(depth+1);
                map[y][x] = 0;
            }
        }
    }
    private static boolean isValid(int y, int x, int num) {
        
        // 열 검사
        for(int i=0; i<9; i++){
            if(map[y][i] == num)return false;
        }

        // 행 검사
        for(int i=0; i<9; i++){
            if(map[i][x] == num) return false;
        }

        int startRow = (y/3) * 3;
        int startCol = (x/3) * 3;

        // 3x3 박스안 검사
        for(int i=startRow; i<startRow+3; i++){
            for(int j= startCol; j<startCol+3; j++){
                if(map[i][j] == num) return false;
            }
        }

        return true;
    }
    private static void printMap() {
        for(int i=0; i<9; i++){
            for(int j=0; j<9; j++){
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }
    
}
