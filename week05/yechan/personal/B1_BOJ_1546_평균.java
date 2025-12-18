package week5.yechan.personal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B1_BOJ_1546_평균 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        double[] subject = new double[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        double max = 0;

        for (int i = 0; i < N; i++) {
            subject[i] = Double.parseDouble(st.nextToken());
            if (subject[i] > max) max = subject[i];
        }

        double sum = 0;
        for (int i = 0; i < N; i++) {
            sum += (subject[i] / max) * 100;
        }

        System.out.println(sum / N);
    }
}
