import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BOJ_11725 {

    static int N;

    static ArrayList<Integer>[] graph;
    static int[] list;
    static boolean[] visited;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();

        graph = new ArrayList[N + 1];
        list = new int[N + 1];
        visited = new boolean[N + 1];


        for (int i = 0; i < graph.length; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < N - 1; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();

            graph[u].add(v);
            graph[v].add(u);

        }

        bfs();


        for (int i = 2; i < list.length; i++) {
            System.out.println(list[i]);
        }

    }

    private static void bfs() {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(1);
        visited[1] = true;

        while (!queue.isEmpty()) {
            Integer start = queue.poll();

            //자식 인덱스에 부모 저장
            for (Integer next : graph[start]) {
                if (!visited[next]) {
                    list[next] = start;
                    visited[next] = true;
                    queue.add(next);
                }
            }
        }


    }
}