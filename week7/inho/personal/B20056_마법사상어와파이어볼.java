package BJ.gold_4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

//  N*N 격자에 파이어볼 M개를 발사. K번 실행.
// 위치 r,c 질량 m, 방향 d, 속력 s
// 방향은 위부터 0 시계방향으로.

public class B20056_마법사상어와파이어볼 {
    static int N, M, K;
    static ArrayList<Fireball> fireballs = new ArrayList<>();

    static ArrayList<Fireball>[][] mapGrid;

    static Map<Integer,int[]> map = new HashMap<>();

    static class Fireball {
        int r, c, m, s, d;

        public Fireball(int r, int c, int m, int s, int d) {
            this.r = r; // 행
            this.c = c; // 열
            this.m = m; // 질량
            this.s = s; // 속력
            this.d = d; // 방향
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);
        K = Integer.parseInt(input[2]);

        mapGrid = new ArrayList[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                mapGrid[i][j] = new ArrayList<>();
            }
        }

        map.put(0,new int[]{-1,0});
        map.put(1,new int[]{-1,1});
        map.put(2,new int[]{0,1});
        map.put(3,new int[]{1,1});
        map.put(4,new int[]{1,0});
        map.put(5,new int[]{1,-1});
        map.put(6,new int[]{0,-1});
        map.put(7,new int[]{-1,-1});

        for (int i = 0; i < M; i++) {
            String[] input2 = br.readLine().split(" ");
            int r = Integer.parseInt(input2[0]);
            int c = Integer.parseInt(input2[1]);
            int m = Integer.parseInt(input2[2]);
            int s = Integer.parseInt(input2[3]);
            int d = Integer.parseInt(input2[4]);
            fireballs.add(new Fireball(r, c, m, s, d));
        }

        for (int i = 0; i < K; i++) {
            moveFireballs();
            processMap();
        }

        int total = 0;
        for (Fireball fb : fireballs) {
            total += fb.m;
        }
        System.out.println(total);
    }

    static void moveFireballs() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                mapGrid[i][j].clear();
            }
        }

        for (Fireball fb : fireballs) {
            int[] delta = map.get(fb.d);
            int dr = delta[0];
            int dc = delta[1];

            int nr = (fb.r + dr * (fb.s % N) + N) % N;
            int nc = (fb.c + dc * (fb.s % N) + N) % N;

            fb.r = nr;
            fb.c = nc;

            mapGrid[nr][nc].add(fb);
        }
    }

    static void processMap() {
        ArrayList<Fireball> nextFireballs = new ArrayList<>();

        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                int size = mapGrid[r][c].size();

                if (size == 0) continue;

                if (size == 1) {
                    nextFireballs.add(mapGrid[r][c].get(0));
                }
                else {
                    int sumM = 0;
                    int sumS = 0;
                    boolean allEven = true;
                    boolean allOdd = true;

                    for (Fireball fb : mapGrid[r][c]) {
                        sumM += fb.m;
                        sumS += fb.s;
                        if (fb.d % 2 == 0) allOdd = false;
                        else allEven = false;
                    }

                    int newM = sumM / 5;

                    if (newM == 0) continue;

                    int newS = sumS / size;

                    int[] newDirs = (allEven || allOdd) ? new int[]{0, 2, 4, 6} : new int[]{1, 3, 5, 7};

                    for (int d : newDirs) {
                        nextFireballs.add(new Fireball(r, c, newM, newS, d));
                    }
                }
            }
        }

        fireballs = nextFireballs;
    }
}