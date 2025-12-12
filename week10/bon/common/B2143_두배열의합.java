
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

    /**
     * 부분합(구간합, 누적합)을 사용해서 문제를 풀어야한다.
     * 처음 T가 10억이상 주어졌다고했을시 이분탐색으로 풀려했지만 접근어려웠음
     * 그래서 각 배열 부분합 을 Map 에 넣어 카운팅 시작
     * 만약 A 배열 중 부분합이 x 이다 가정하면 T-x 인 값을 부분합 Map에서 찾고 반환
     * 그 후 부분합을 이루는 경우의 수가 밸류값으로 나오고 A,B에서 나온 밸류값들을 곱해주면된다.
     */

    static int T, n, m;
    static int[] A, B;
    static int[] prefixA, prefixB;
    static Map<Long,Long> countA = new HashMap<>(), countB = new HashMap<>();
    static Long result = 0L;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        T = sc.nextInt();
        n = sc.nextInt();

        A = new int[n];
        prefixA = new int[n];

        for (int i = 0; i < n; i++) {
            A[i] = sc.nextInt();
            countA.put((long) A[i],countA.getOrDefault((long)A[i],0L)+1);
        }
        prefixA[0] = A[0];

        for (int i = 1; i < n; i++) {
            prefixA[i] = prefixA[i - 1] + A[i];
        }

        m = sc.nextInt();
        B = new int[m];
        prefixB = new int[m];

        for (int i = 0; i < m; i++) {
            B[i] = sc.nextInt();
            countB.put((long) B[i],countB.getOrDefault((long)B[i],0L)+1);
        }
        prefixB[0] = B[0];
        for (int i = 1; i < m; i++) {
            prefixB[i] = prefixB[i - 1] + B[i];
        }



        for (int i = 0; i < n; i++) {
            for (int j = i+1; j <n ; j++) {
                long sum = sumPrefix(prefixA,i,j);
                countA.put(sum,countA.getOrDefault(sum,0L)+1);
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = i+1; j <m ; j++) {
                long sum = sumPrefix(prefixB,i,j);
                countB.put(sum,countB.getOrDefault(sum,0L)+1);
            }

        }
        //System.out.println(countA);
        //System.out.println(countB);

        for (Long keyA : countA.keySet()) {
            Long temp = T - keyA;
            //System.out.println("temp = " + temp +" keyA = "+ keyA );
            if(!countB.containsKey(temp)) continue;
            result += countA.get(keyA) * countB.get(temp);

        }

        System.out.println(result);

    }


    private static long sumPrefix(int[] prefix, int start, int end){
        if(start ==0) return prefix[end];
        else return prefix[end] - prefix[start-1];
    }
}
