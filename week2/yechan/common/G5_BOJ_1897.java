package week2.yechan.common;

import java.io.*;
import java.util.*;

public class G5_BOJ_1897 {
    static int D;
    static String start;
    static String[] dict;
    static boolean[] visited;
    static String answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br  = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        D = Integer.parseInt(st.nextToken());
        start = st.nextToken();
        dict = new String[D];
        visited = new boolean[D];

        for (int i = 0; i < D; i++) {
            dict[i] = br.readLine();
        }

        bfs();
        System.out.println(answer);
    }

    static void bfs() {
        Queue<String> q = new ArrayDeque<>();
        q.offer(start);
        answer = start;

        while (!q.isEmpty()) {
            String cur = q.poll();

            for (int i = 0; i < D; i++) {
                if (!visited[i] && canTransform(cur, dict[i])) {
                    visited[i] = true;
                    q.offer(dict[i]);

                    // 길이가 더 길다면 갱신
                    if (dict[i].length() > answer.length() ||
                       (dict[i].length() == answer.length() && dict[i].compareTo(answer) < 0)) {
                        answer = dict[i];
                    }
                }
            }
        }
    }

    static boolean canTransform(String a, String b) {
        if (b.length() != a.length() + 1) return false;

        // b에서 한 글자 제거해보며 a가 나오는지 확인
        for (int i = 0; i < b.length(); i++) {
            String temp = b.substring(0, i) + b.substring(i + 1);
            if (temp.equals(a)) return true;
        }
        return false;
    }
}

// 첫시도
// 세글자 단어에서 맨앞, 단어사이 두공간, 맨뒤를 정점으로보고
// 모든 알파벳을 넣으면서 bfs를 진행하려했는데 너무나도 많은 작업을해야해서 길을 잃음.

// gpt 도움
// 주어진 각 단어를 정점으로보고
// 한 글자 차이로 연결되는 단어끼리 간선을 만들어서
// bfs/dfs로 가장 멀리 가는걸 찾는 구조라함.
// ex) cal -> coal -> coral -> choral
// cal에서 choral로 가는 경로를 찾는 그래프 탐색임을 알아야함.
// B는 A에서 한글자를 추가해서 만든 단어이다.
// A->B 관계를 직접 찾기 어렵다면
// B에서 A를 복원할수있나로 역방향사고를 해야한다.
