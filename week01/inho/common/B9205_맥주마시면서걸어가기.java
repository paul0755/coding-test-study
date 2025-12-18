package BJ.gold_5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

public class B9205_맥주마시면서걸어가기 {
    static int t;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        t = Integer.parseInt(br.readLine());

        for (int i = 0; i < t; i++) {
            int n = Integer.parseInt(br.readLine());

            List<int[]> positions = new ArrayList<>();

            for (int j = 0; j < n + 2; j++) {
                String[] line = br.readLine().split(" ");
                int x = Integer.parseInt(line[0]);
                int y = Integer.parseInt(line[1]);
                positions.add(new int[]{x, y});
            }

            boolean result = bfs(positions);
            System.out.println(result ? "happy" : "sad");
        }

    }

    private static boolean bfs(List<int[]> positions) {
        boolean[] visited = new boolean[positions.size()];
        Deque<Integer> deque = new ArrayDeque<>();
        deque.add(0);
        visited[0] = true;

        while (!deque.isEmpty()){
            int now = deque.remove();
            if (now == positions.size()-1) return true;
            for (int i = 0; i < positions.size(); i++) {
                if (!visited[i] && canCalculate(positions.get(now),positions.get(i))){
                    deque.add(i);
                    visited[i]= true;
                }

            }
        }
        return false;
    }

    private static boolean canCalculate(int[] ints, int[] ints1) {

        return (Math.abs(ints[0]-ints1[0])+Math.abs(ints[1]-ints1[1])) <=1000;
    }
}
