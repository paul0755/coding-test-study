package BJ.gold_4;

import java.io.*;
import java.util.*;

public class B10422_괄호 {
    static final int MAX = 2500;
    static long[] catalan = new long[MAX + 1];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        precomputeCatalan();

        StringBuilder sb = new StringBuilder();
        while (T-- > 0) {
            int L = Integer.parseInt(br.readLine());
            if (L % 2 != 0) {
                sb.append(0).append("\n");
            } else {
                sb.append(catalan[L / 2]).append("\n");
            }
        }
        System.out.print(sb);
    }

    // 카탈란 공식
    static void precomputeCatalan() {
        catalan[0] = 1;
        for (int i = 1; i <= MAX; i++) {
            for (int j = 0; j < i; j++) {
                catalan[i] = (catalan[i] + catalan[j] * catalan[i - 1 - j]) % 1000000007;
            }
        }
    }
}

