package BJ.gold_5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 문자열 그래프 탐색.. 어떻게 해야할까
// 숫자 그래프 탐색은 List의 인덱스가 그 숫자니까 바로 접근해도 됐는데, 문자열은 어떻게?
public class B1897_토달기 {
    static int d;
    static String first;
    static List<String> words = new ArrayList<>();
    static Map<String, List<String>> graph = new HashMap<>();
    static String longest = "";

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        d = Integer.parseInt(input[0]);
        first = input[1];

        for (int i = 0; i < d; i++) {
            words.add(br.readLine());
        }

        // 그래프 만들기
        for (String a : words) {
            for (String b : words) {
                if (valid(a, b)) {
                    if (!graph.containsKey(a)) {
                        graph.put(a, new ArrayList<>());
                    }
                    graph.get(a).add(b);
                }
            }
        }

        // bfs
        bfs();

        System.out.println(longest);
    }

    private static void bfs() {
        Deque<String> deque = new ArrayDeque<>();
        deque.add(first);
        while (!deque.isEmpty()){
            String now = deque.remove();
            if (longest.length() < now.length()){
                longest = now;
            }
            if (graph.get(now) == null) continue;
            for(String n : graph.get(now)){
                deque.add(n);
            }
        }
    }

    static boolean valid(String a, String b) {
        if (a.length() + 1 != b.length()) {
            return false;
        }
        for (int i = 0; i < b.length(); i++) {
            if ((b.substring(0, i) + b.substring(i + 1)).equals(a)) {
                return true;
            }
        }
        return false;
    }
}
