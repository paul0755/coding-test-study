import java.util.*;

public class Main {

    static class Pair {
        int idx, cnt;
        public Pair(int idx, int cnt) {
            this.idx = idx;
            this.cnt = cnt;
        }
    }

    static int N, H;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        H = sc.nextInt();

        Map<Integer, Integer> mapA = new HashMap<>();
        Map<Integer, Integer> mapB = new HashMap<>();

        for (int i = 0; i < N; i++) {
            int x = sc.nextInt();
            if (i % 2 == 0) {
                mapA.put(x, mapA.getOrDefault(x, 0) + 1);
            } else {
                int t = H + 1 - x;
                mapB.put(t, mapB.getOrDefault(t, 0) + 1);
            }
        }

        int[] psumA = new int[H + 2];
        int[] psumB = new int[H + 2];
        int[] psum  = new int[H + 2];

        for (int i = H; i >= 1; i--) {
            psumA[i] = mapA.getOrDefault(i, 0) + psumA[i + 1];
        }

        for (int i = 1; i <= H; i++) {
            psumB[i] = mapB.getOrDefault(i, 0) + psumB[i - 1];
        }

        for (int i = 1; i <= H; i++) {
            psum[i] = psumA[i] + psumB[i];
        }

        List<Pair> p = new ArrayList<>();
        for (int i = 1; i <= H; i++) {
            p.add(new Pair(i, psum[i]));
        }
        p.sort((p1, p2) -> p1.cnt - p2.cnt);

        int cnt = p.get(0).cnt;
        int ret = 0;
        for (Pair pp : p) {
            if (pp.cnt == cnt) ret++;
            else break;
        }

        System.out.println(cnt + " " + ret);
    }
}
