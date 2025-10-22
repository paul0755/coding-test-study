package week3.yechan.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class G4_BOJ_17140_이차원배열과연산 {
    
    static int r,c,k,cnt =0;
    static int[][] map = new int[4][4];
    
    public static void main(String args[]) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        for(int i=1; i<4; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=1; j<4; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 5.다시 2차원 배열로 채우기

        int rowCount = 3;
        int colCount = 3;

        while(cnt<= 100){
            if (r <= rowCount && c <= colCount && map[r][c] == k) {
                System.out.println(cnt);
                return;
            }
            if(rowCount >= colCount){
                //R연산

                List<List<Integer>> newRows = new ArrayList<>();
                int maxCol = 0;

                for(int i=1; i<= rowCount; i++){
                    List<Integer> newRow = doR(map[i]);
                    newRows.add(newRow);
                    maxCol = Math.max(maxCol, newRow.size());
                }

                // 새 map으로 갱신
                map = new int[rowCount+1][maxCol+1];
                for(int i=1; i<=rowCount; i++){
                    List<Integer> row = newRows.get(i-1);
                    for(int j=1; j<= row.size(); j++){
                        map[i][j] = row.get(j-1);
                    }
                }

                colCount = maxCol;
            }else{
                // C연산 

                List<List<Integer>> newCols = new ArrayList<>();
                int maxRow = 0;

                // 열을 기준으로 반복
                for(int j=1; j<= colCount; j++){
                    List<Integer> col = new ArrayList<>();

                    // 세로로 데이터 모으기
                    for(int i=1; i<=rowCount; i++){
                        if(map[i][j] != 0) col.add(map[i][j]);
                    }

                    List<Integer> newCol = doC(col);
                    newCols.add(newCol);
                    maxRow = Math.max(maxRow, newCol.size());

                }

                map = new int[maxRow + 1][colCount + 1];

                for (int j = 1; j <= colCount; j++) {
                    List<Integer> col = newCols.get(j - 1);
                    for (int i = 1; i <= col.size(); i++) {
                        map[i][j] = col.get(i - 1);
                    }
                }

                rowCount = maxRow;
            }

            cnt++;

            if(cnt>100){
                System.out.println(-1);
                return;
            }
        }
        
    }

    private static List<Integer> doC(List<Integer> col) {
        // 1. 각 열의 등장 횟수 세기
        Map<Integer, Integer> count = new HashMap<>();
        for (int num : col) {
            if (num == 0) continue;
            count.put(num, count.getOrDefault(num, 0) + 1);
        }

        // 2. (숫자, 등장횟수) 쌍을 리스트로 만들기
        List<int[]> pairs = new ArrayList<>();
        for (int key : count.keySet()) {
            pairs.add(new int[]{key, count.get(key)});
        }

        // 3. 정렬: 등장횟수 오름차순 → 숫자 오름차순
        pairs.sort((a, b) -> {
            if (a[1] == b[1]) return a[0] - b[0];
            return a[1] - b[1];
        });

        // 4. 결과 리스트로 재구성
        List<Integer> newCol = new ArrayList<>();
        for (int[] p : pairs) {
            newCol.add(p[0]);
            newCol.add(p[1]);
            if (newCol.size() >= 100) break; // 최대 길이 100 제한
        }

        return newCol;

    }

    private static List<Integer> doR(int[] row) {
         Map<Integer,Integer> count = new HashMap<>();
        // 1. 한 행의 등장횟수 세기
        for (int num : row) {
        if (num == 0) continue; // 0은 무시
        count.put(num, count.getOrDefault(num, 0) + 1);
    }

        // 2. (숫자, 등장횟수) 쌍을 리스트로 만들기
        List<int[]> pairs = new ArrayList<>();
        for(int key : count.keySet()){
            pairs.add(new int[]{key, count.get(key)});
        }

        // 3. 정렬
        pairs.sort((a,b)->{
            if(a[1] == b[1]) return a[0] - b[0];
            return a[1] - b[1];
        });

        // 4. 다시 배열 형태로 재구성
        List<Integer> newRow = new ArrayList<>();
        for(int[] p: pairs){
            newRow.add(p[0]);
            newRow.add(p[1]);
            if(newRow.size() >= 100) break;
        }
        return newRow;
    }
    
}
