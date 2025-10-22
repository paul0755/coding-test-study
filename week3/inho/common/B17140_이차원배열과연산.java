package BJ.gold_4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 수의 등장이 커지는 순서대로
// 등장이 같으면 수를 오름차순으로
// R 연산 -> 행의 개수 >= 열의 개수
// C 연산 -> 행의 개수 < 열의 개수
// 열과 행의 크기가 100이 넘어가면 100개를 제외한 나머지는 버림.
// 최대 100초까지만 확인.
public class B17140_이차원배열과연산 {
    static int r,c,k;
    static int[][] field;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        r = Integer.parseInt(input[0])-1; // 찾아야할 x 위치
        c = Integer.parseInt(input[1])-1; // 찾아야할 y 위치
        k = Integer.parseInt(input[2]); // 찾아야할 값
        field = new int[3][3];
        for (int i = 0; i < 3; i++) {
            String[] input2 = br.readLine().split(" ");
            for (int j = 0; j < 3; j++) {
                field[i][j] = Integer.parseInt(input2[j]);
            }
        }

        if (r < field.length && c < field[0].length && field[r][c] == k) {
            System.out.println(0);
            return;
        }
        for (int i = 1; i <= 100; i++) {
            if (field.length >= field[0].length){ // R 연산
                field = cul1();
            }else { // C 연산
                field = cul2();
            }
            if (r < field.length && c < field[0].length && field[r][c] == k) {
                System.out.println(i);
                return;
            }

        }

        System.out.println(-1);
    }

    private static int[][] cul1() {
        int[][] newField = new int[field.length*2][field[0].length*2];
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < field.length; i++) {
            // map에 쌓기
            Map<Integer,Integer> map = new HashMap<>();
            for (int j = 0; j < field[i].length; j++) {
                int num = field[i][j];
                if (num==0) continue;
                map.put(num, map.getOrDefault(num,0)+1);
            }

            // 정렬 조지기
            List<Map.Entry<Integer,Integer>> list = new ArrayList<>(map.entrySet());
            list.sort((a,b)->{
                if (a.getValue().equals(b.getValue())){
                    return a.getKey() - b.getKey();
                }else {
                    return a.getValue() - b.getValue();
                }
            });

            // 정렬된 값으로 배열 만들기
            int idx = 0;
            for (Map.Entry<Integer, Integer> entry : list) {
                if (idx >= 100) break; // 열 길이 100 제한
                newField[i][idx++] = entry.getKey();
                if (idx >= 100) break;
                newField[i][idx++] = entry.getValue();
            }

            max = Math.max(max,idx);
        }

        // 최대 길이 기준으로 맞추기
        int[][] answerField = new int[field.length][max];
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < max; j++) {
                answerField[i][j] = newField[i][j];
            }
        }
        return answerField;
    }

    private static int[][] cul2() {
        int[][] newField = new int[101][101];
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < field[0].length; i++) {
            Map<Integer,Integer> map = new HashMap<>();
            for (int j = 0; j < field.length; j++) {
                int num = field[j][i];
                if (num==0) continue;
                map.put(num, map.getOrDefault(num,0)+1);
            }

            List<Map.Entry<Integer,Integer>> list = new ArrayList<>(map.entrySet());
            list.sort((a,b)->{
                if (a.getValue().equals(b.getValue())){
                    return a.getKey() - b.getKey();
                }else {
                    return a.getValue() - b.getValue();
                }
            });

            int idx = 0;
            for (Map.Entry<Integer, Integer> entry : list) {
                if (idx >= 100) break; // 열 길이 100 제한
                newField[i][idx++] = entry.getKey();
                if (idx >= 100) break;
                newField[i][idx++] = entry.getValue();
            }

            max = Math.max(max,idx);
        }

        int[][] answerField = new int[max][field[0].length];
        for (int i = 0; i < field[0].length; i++) {
            for (int j = 0; j < max; j++) {
                answerField[j][i] = newField[i][j];
            }
        }
        return answerField;
    }
}
