package BJ.gold_3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 배열 두 개를 주면
// 목표하는 값을 만들기 위한 경우의 수를 구하기.
// 조합으로.
public class B2143_두배열의합 {
    static int T, n, m;
    static int[] a, b;
    static List<Long> aList = new ArrayList<>();
    static Map<Long, Integer> mapB = new HashMap<>();
    static long answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        n = Integer.parseInt(br.readLine());
        a = new int[n];
        String[] aList2 = br.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(aList2[i]);
        }
        m = Integer.parseInt(br.readLine());
        b = new int[m];
        String[] bList2 = br.readLine().split(" ");
        for (int i = 0; i < m; i++) {
            b[i] = Integer.parseInt(bList2[i]);
        }

        // 모든 합의 경우의수로 다시 set에 저장.
        for (int i = 0; i < n; i++) {
            long sum = 0;
            for (int j = i; j < n; j++) {
                sum += a[j];
                aList.add(sum);
            }
        }
        Collections.sort(aList);
        for (int i = 0; i < m; i++) {
            long sum = 0;
            for (int j = i; j < m; j++) {
                sum += b[j];
                mapB.put(sum, mapB.getOrDefault(sum, 0) + 1);
            }
        }

        // containsKey로 목표하는 값이 있는지 판단하는 로직. 적중시 +
        for (long num : aList) {
            long target  = T-num;
            if (mapB.containsKey(target)){
                answer += mapB.get(target);
            }
        }
        System.out.println(answer);
    }
}
