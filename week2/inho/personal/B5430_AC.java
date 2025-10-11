package BJ.gold_5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class B5430_AC {
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            boolean check = true;
            boolean isReverse = false;
            String[] inputString = br.readLine().split("");
            int inputNum = Integer.parseInt(br.readLine());
            String numberList = br.readLine();
            String[] x = numberList.substring(1, numberList.length() - 1).split(",");
            List<Integer> list = new ArrayList<>();
            for (String s : x) {
                if (s.equals("[") || s.equals("]") || s.equals(",") || s.equals("")) {
                    continue;
                }
                list.add(Integer.parseInt(s));
            }

            for (int j = 0; j < inputString.length; j++) {
                if (inputString[j].equals("R") && !isReverse) {
                    isReverse = true;
                } else if (inputString[j].equals("R") && isReverse) {
                    isReverse = false;
                } else if (inputString[j].equals("D") && !isReverse) {
                    if (list.isEmpty()) {
                        System.out.println("error");
                        check = false;
                        break;
                    }
                    list.remove(0);
                } else if (inputString[j].equals("D") && isReverse) {
                    if (list.isEmpty()) {
                        System.out.println("error");
                        check = false;
                        break;
                    }
                    list.remove(list.size() - 1);
                }
            }
            if (check) {
                StringBuilder sb = new StringBuilder();
                sb.append("[");
                if (!isReverse) {
                    for (int j = 0; j < list.size(); j++) {
                        sb.append(list.get(j));
                        if (j != list.size() - 1) sb.append(",");
                    }
                } else {
                    for (int j = list.size() - 1; j >= 0; j--) {
                        sb.append(list.get(j));
                        if (j != 0) sb.append(",");
                    }
                }
                sb.append("]");
                System.out.println(sb.toString());
            }

        }
    }
}
