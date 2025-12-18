package BJ.gold_4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

// L 왼쪽 D 오른쪽
public class B3190_뱀 {
    static int N,K,L;
    static int[][] field;
    static Map<Integer,String> time = new HashMap<>();
    static Map<Integer, int[]> map = new HashMap<>();
    static Deque<int[]> snake = new ArrayDeque<>();
    public static void main(String[] args) throws IOException {
        map.put(0, new int[]{-1, 0}); // 북
        map.put(1, new int[]{0, 1});  // 동
        map.put(2, new int[]{1, 0});  // 남
        map.put(3, new int[]{0, -1}); // 서
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        field = new int[N][N];
        K = Integer.parseInt(br.readLine());
        for (int i = 0; i < K; i++) { //사과 위치. 1로 설정
            String[] input = br.readLine().split(" ");
            field[Integer.parseInt(input[0])-1][Integer.parseInt(input[1])-1] = 1;
        }
        L = Integer.parseInt(br.readLine());
        for (int i = 0; i < L; i++) { // 특정 시간에 행동 처리.
            String[] input2 = br.readLine().split(" ");
            time.put(Integer.parseInt(input2[0]),input2[1]);
        }

        // 머리가 향하는 곳으로 한칸 앞으로 가기.
        // 머리를 한 칸 앞으로 갔을 때 벽이거나 자기자신의 몸에 부딪히면 게임 끝.
        // 사과면 꼬리는 놔두고 사과가 아니라면 꼬리 칸 비우기.
        // 맵으로 시간 확인하고 존재하면 방향 바꾸기.
        // 꼬리 마지막 위치 추적 -> 큐를 쓰자.
        int result = 0;
        field[0][0] = 2;
        snake.add(new int[]{0,0});
        int nowRow = 1; // 동쪽부터 시작.
        int startX = 0;
        int startY = 0;
        while (true){
            result++;
            int[] 방향 = map.get(nowRow);
            int nowX = startX + 방향[0];
            int nowY = startY + 방향[1];
            if (nowX >=0 && nowX < N && nowY >=0 && nowY <N && field[nowX][nowY]!=2){ // 벽, 내 몸인지 확인
                if (field[nowX][nowY]==1) { // 사과일 때
                    field[nowX][nowY]=2;
                    snake.add(new int[]{nowX,nowY});
                    startX = nowX;
                    startY = nowY;
                }else { // 사과가 아니면 꼬리 위치 변경
                    field[nowX][nowY]=2;
                    snake.add(new int[]{nowX,nowY});
                    int[] remove = snake.remove();
                    field[remove[0]][remove[1]] = 0;
                    startX = nowX;
                    startY = nowY;

                }

            }else break; // 벽이랑 내 몸 만나면 끝남.

            if (time.get(result)== null) {
                continue;
            }
            else if (time.get(result).equals("L")){ // 방향 재설정.
                nowRow = (nowRow+3)%4;
            } else if (time.get(result).equals("D")) {
                nowRow = (nowRow+1)%4;
            }
        }
        System.out.println(result);
    }
}
