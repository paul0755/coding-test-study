package week9.yechan.common;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 [⭐ 문제 풀이 핵심 정리 ⭐]

 1) 방향 전환: dir = (dir + 1) % 4
    → 방향(dir)을 0~3 사이에서 순환시키는 표현.
      예: 0(오른쪽) → 1(위) → 2(왼쪽) → 3(아래) → 다시 0
    → if문 없이 방향을 자연스럽게 순환시키기 위한 간단하고 논리적인 방식.

 2) dx, dy 배열의 의미
    → 보통 y 증가가 '아래', x 증가가 '오른쪽'이라고 생각할 수 있으나,
      이 문제에서는 좌표계를 (x=row, y=col)로 사용.
      즉 행(x)은 상하 이동, 열(y)은 좌우 이동을 담당.
      
      예시)
        dx = {0, -1, 0, 1}
        dy = {1,  0, -1, 0}

        dir=0 → 오른쪽 이동  (dx=0,  dy=1)
        dir=1 → 위쪽 이동    (dx=-1, dy=0)
        dir=2 → 왼쪽 이동    (dx=0,  dy=-1)
        dir=3 → 아래 이동    (dx=1,  dy=0)

      → 방향 이동을 dx, dy로 깔끔하게 표현함으로써 코드 복잡도가 크게 줄어듦.

 3) 자릿수 계산: maxLen = (int)Math.log10(max)
    → log10(x)는 "10을 몇 번 곱하면 x가 되는가" → 즉 x의 자릿수 - 1.
       예:  9 → log10=0.xxx  → 1자리
            99 → log10=1.xxx → 2자리
            123 → log10=2.xxx → 3자리

    → 가장 큰 수(max)의 자릿수 - 1을 이용해,
      출력 시 정렬을 맞추기 위한 공백 개수를 쉽게 계산할 수 있음.

 4) 기능 단위 함수 분리 (fill / print / isFinish)
    → 좋은 코드 구조의 대표적인 예.
        fill(): 소용돌이를 채움 (로직 중심)
        print(): 정렬하여 출력 (표현 중심)
        isFinish(): 종료 조건 판단 (조건 중심)

    → 이렇게 역할을 분리하면 문제를 단계적으로 해결할 수 있고,
      디버깅과 코드 이해가 훨씬 쉬워짐.
    → 특히 구현 문제에서 “기능 분리”는 가독성과 유지보수에 매우 중요함.

 5) (r2 - r1), (c2 - c1)을 통한 map 좌표 변환
    → 실제 소용돌이 좌표는 (x, y) 전체 범위에 존재하지만,
      우리는 r1~r2, c1~c2 사이의 작은 사각형만 출력하면 됨.

    → map 크기:
         행 개수 = r2 - r1 + 1
         열 개수 = c2 - c1 + 1

    → 실제 좌표 (x, y)를 map에 저장할 때:
         map[x - r1][y - c1]

       왜냐하면:
         - x == r1 이면 map[0][?]
         - x == r2 이면 map[r2-r1][?]
         - y 도 마찬가지로 c1 기준으로 0부터 시작.

    → 즉, 전체 좌표계에서 우리가 필요한 "부분 좌표계"로 옮겨오기 위한 변환 과정.
      좌표 변환을 정확히 하면 문제 범위 내 값만 저장하므로 메모리/성능 모두 절약됨.
*/


public class G3_BOJ_1022_소용돌이예쁘게출력하기{

    static int r1,c1,r2,c2, max=0;
    static int[][] map;

    static int[] dx = {0,-1,0,1}; 
    static int[] dy = {1,0,-1,0}; 

    public static void main(String[] args) throws Exception {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        r1 = Integer.parseInt(st.nextToken());
        c1 = Integer.parseInt(st.nextToken());
        r2 = Integer.parseInt(st.nextToken());
        c2 = Integer.parseInt(st.nextToken());

        map = new int[r2-r1+1][c2-c1+1];

        fill();
        print();
        
    }

    
    private static void fill() {
        
        int x =0, y=0, dnum =1, cnt =0, num=1, dir=0;
        

        while(!isFinish()){
            
            if(x>= r1 && x <=r2 && y>=c1 && y<=c2){
                map[x-r1][y-c1] = num;
            }

            num ++;
            cnt ++;

            x = x+ dx[dir];
            y = y+ dy[dir];

            if(cnt == dnum){
                cnt = 0;
                if(dir == 1 || dir == 3) dnum ++;
                dir = (dir+1) % 4; // 방향 변환 (오->위->왼->아)
            }
        }

        max = num -1;
    }
    
    private static boolean isFinish() {
        return map[0][0] != 0 && map[r2-r1][0] != 0 && map[0][c2-c1] != 0 && map[r2-r1][c2-c1] != 0;
    }


    private static void print() {
        int maxLen = (int)Math.log10(max), len;

        for(int i=0; i<=r2-r1; i++){
            for(int j=0; j<=c2-c1; j++){
                len = maxLen - (int) Math.log10(map[i][j]);
                for(int k=0; k<len; k++){
                    System.out.print(" ");
                }
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }

}