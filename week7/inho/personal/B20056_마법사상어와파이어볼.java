package BJ.gold_4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//  N*N 격자에 파이어볼 M개를 발사. K번 실행.
// 위치 r,c 질량 m, 방향 d, 속력 s
// 방향은 위부터 0 시계방향으로.
// 끝까지가면 다시 처음부터 오는 구조. 사라지지 않음.
//
public class B20056_마법사상어와파이어볼 {
    static int N,M,K;
    static Map<Integer, int[]> map = new HashMap<>();
    static List<FireBall> fireBalls = new ArrayList<>();
    static List<FireBall> copy = new ArrayList<>();
//    static FireBall[][] fireBalls = new ArrayList<>();
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);
        K = Integer.parseInt(input[2]);

        map.put(0,new int[]{-1,0});
        map.put(1,new int[]{-1,1});
        map.put(2,new int[]{0,1});
        map.put(3,new int[]{1,1});
        map.put(4,new int[]{1,0});
        map.put(5,new int[]{1,-1});
        map.put(6,new int[]{0,-1});
        map.put(7,new int[]{-1,-1});

        for (int i = 0; i < M; i++) {
            String[] arr = br.readLine().split(" ");
            fireBalls.add(new FireBall(Integer.parseInt(arr[0])-1,Integer.parseInt(arr[1])-1,Integer.parseInt(arr[2]),Integer.parseInt(arr[4]),Integer.parseInt(arr[3])));
        }

        // 1. 리스트에 있는 파이어볼을 각각 이동하고.
        // 2. 겹쳐지는 파이어볼에 대한 계산 진행.
        // 3. 리스트 바꿔치기. fireBalls -> copy;

        for (int i = 0; i < K; i++) {
            move();
            cal();
        }

        int result = 0;
        for(FireBall f : fireBalls){
            result += f.m;
        }
        System.out.println(result);
    }
    // 방향으로 속력만큼 이동. 근데 m으로 %해야함.
    private static void move() {
        copy.clear();
        for(FireBall f : fireBalls){
            int[] row = map.get(f.d);
            int newX = (f.r + f.s * row[0] + N * 1000) % N;
            int newY = (f.c + f.s * row[1] + N * 1000) % N;
            copy.add(new FireBall(newX,newY,f.m,f.d,f.s));
        }
    }

    // 겹치는 거 확인해야함. 즉 위치에 대해서 size를 봐야함
    private static void cal() {
        fireBalls.clear();
        Map<String, List<FireBall>> s = new HashMap<>();
        for(FireBall f : copy){
            s.putIfAbsent(f.r+","+f.c, new ArrayList<>());
            s.get(f.r+","+f.c).add(f);
        }

        for (String j :s.keySet()){
            List<FireBall> list = s.get(j);
            // 사이즈가 0일 때
            if (list.size()==1) {
                fireBalls.add(list.get(0));
            }else {
                int newM = 0;
                int newS = 0;
                boolean isHol = true;
                boolean isj = true;
                for (FireBall fire : list){
                    newM += fire.m;
                    newS += fire.s;
                    if (fire.d %2 == 0) isHol = false;
                    else isj = false;
                }
                newM = newM/5;
                newS = newS/list.size();
                if (newM ==0) continue;

                if (isHol || isj) { // 모두 홀수거나 모두 짝수면 2 4 6 0
                    fireBalls.add(new FireBall(list.get(0).r,list.get(0).c, newM, 0,newS));
                    fireBalls.add(new FireBall(list.get(0).r,list.get(0).c, newM, 2,newS));
                    fireBalls.add(new FireBall(list.get(0).r,list.get(0).c, newM, 4,newS));
                    fireBalls.add(new FireBall(list.get(0).r,list.get(0).c, newM, 6,newS));
                }else {
                    fireBalls.add(new FireBall(list.get(0).r,list.get(0).c, newM, 1,newS));
                    fireBalls.add(new FireBall(list.get(0).r,list.get(0).c, newM, 3,newS));
                    fireBalls.add(new FireBall(list.get(0).r,list.get(0).c, newM, 5,newS));
                    fireBalls.add(new FireBall(list.get(0).r,list.get(0).c, newM, 7,newS));
                }
            }
        }
    }

    static class FireBall{
        int r,c,m,d,s;
        FireBall(int r, int c, int m, int d, int s){
            this.r =r;
            this.c =c;
            this.m = m;
            this.d = d;
            this.s = s;
        }
    }
}
