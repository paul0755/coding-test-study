package BJ.gold_4;

// N마리의 동물이 특정 위치에 있음
// M 사로에서 사격 가능
// 각 좌표는 (x,y) 로 표현
// L 만큼의 거리까지 저격 가능. -> 계산법은 |x-a| + y
// 잡을 수 있는 동물의 수 구하기.

// 동물의 좌표 기준에서 잡히는 동물인지 판별하고 count하기.

// 주어진 계산 값이 1차원으로 풀 수 있기 때문에 동물이 잡히는지 사대들을 정렬하고 이분탐색으로 풀어준다.
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class B8983_사냥꾼 {
    static int M,N;
    static long L;
    static int[] saList; // 사대
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        M = Integer.parseInt(input[0]);
        N = Integer.parseInt(input[1]);
        L = Long.parseLong(input[2]);
        saList = new int[M];
        String[] input2 = br.readLine().split(" ");
        for (int i = 0; i <M; i++) {
            saList[i] = Integer.parseInt(input2[i]);
        }
        Arrays.sort(saList);
        int result = 0;

        for (int i = 0; i < N; i++) {
            String[] input3 = br.readLine().split(" ");
            int a = Integer.parseInt(input3[0]);
            int b = Integer.parseInt(input3[1]);

            // 사냥 가능 구간을 1차원으로 표현. a를 기준으로 좌우 대칭. 멘헤튼 거리라서 가능.
            long min = a-(L-b);
            long max = a+(L-b);

            int left = 0;
            int right = M - 1;
            boolean canCatch = false;

            while (left <= right) {
                int mid = (left + right) / 2;

                if (saList[mid] >= min) { // 중간값이 최소값보다 더 클 때
                    if (saList[mid] <= max) { // 중간값이 최대값보다 더 작을 때
                        canCatch = true; // 잡기 가능
                        break;
                    } else { // 중간값이 최대값보다 더 크면 작은 범위로 가야함.
                        right = mid - 1;
                    }
                } else { // 중간값이 최소값보다 더 작으면 더 큰 범위로 가야함.
                    left = mid + 1;
                }
            }
            if (canCatch) result++;
        }
        System.out.println(result);
    }
}
