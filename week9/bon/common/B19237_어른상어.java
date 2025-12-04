
import java.util.Scanner;


/**
 * 풀이 문제에서 주어진 정보들을 배열로 설정할 수 있어야한다.
 * 상어가 움직일때 새로운 배열을 생성해 전부 움직인다음 상어 순위 싸움을 했어야했는데 배열을 새로 안만들고 계속 싸우니 문제가 발생
 * 상어이동 -> 우선순위 계산 -> 피감소 -> 현재위치 피 채움 -> 탈락자 상어 계산
 */

public class Main {

    static int N, M, K;  //배열 수 , 상어 수 , 냄새 유통기한
    static int[][] grid; //처음 상어 입력받는 땅
    static int[][] smellOwner; //피 냄새 주인
    static int[] dir;  // 주어진 상어 방향성
    static int[][] blood; //피 위치
    static int[][][] priorityDir; // 각 상어의 방향마다 다음 위치로 이동할 우선순위 방향
    static int[][] position; // 현재 상어 위치 [M+1][2] 로 선언해 각 상어의 위치를 기록
    static boolean[] exist;  //상어탈락 여부
    static int time;  //정답 시간

    static int[] dx = {999,0, 0, -1, 1};  // index 1부터 시작 위 아래 왼쪽 오른쪽
    static int[] dy = {999,-1, 1, 0, 0};

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        K = sc.nextInt();

        grid = new int[N][N]; //현재 판
        exist = new boolean[M+1];
        blood = new int[N][N];
        smellOwner = new int[N][N];
        position = new int[M+1][2]; //상어 현재 위치

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                grid[i][j] = sc.nextInt();

                if(grid[i][j]!=0){
                    int shark = grid[i][j];
                    position[shark][0] = i;
                    position[shark][1] = j;
                    blood[i][j] = K;
                    smellOwner[i][j] = shark;
                }
            }
        }

        dir = new int[M+1];

        for (int i = 1; i <=M; i++) {
            dir[i] = sc.nextInt();
        }

        priorityDir = new int[M+1][5][5];

        for (int i = 1; i <=M; i++) {
            for (int j = 1; j <=4; j++) {
                for (int k = 1; k <=4; k++) {
                    priorityDir[i][j][k]  = sc.nextInt();
                }
            }
        }


        for (int i = 1; i <=M ; i++) {
            exist[i] = true;
        }

        int answer = move();

        System.out.println(answer);

    }

    private static int move(){


        while(time<=1000){


            //상어 1명만 살아있을때 빠져나옴
            if(liveShark()==1){
                return time;
            }

            //새로운 배열 생성 만약 새로운 배열을 생성하지 않고 기존 배열에서 움직인다면
            //움직인 상어와 움직이지도 않은 상어가 충돌해 움직이지도 않은 상어가 탈락된다.
            int[][] nextGrid= new int[N][N];

            //움직인 상어의 방향성을 저장
            int[] nextDir = new int[M+1];

            //움직인 상어의 위치를 저장
            int[][] nextPosition = new int[M+1][2];

            for (int i = 1; i <=M; i++) {

                /**
                 * 상어 탈락이면 패스
                 */
                if(!exist[i]) continue;

                int curY = position[i][0];
                int curX = position[i][1];

                int nextX =999;
                int nextY =999;
                int nextD =999;
                boolean zeroBlood = false;

                /**
                 * 상어 이동 시작
                 */

                for (int j = 1; j <= 4; j++) {

                    int curDir = priorityDir[i][dir[i]][j];
                    int ny = curY +dy[curDir];
                    int nx = curX +dx[curDir];

                    if(ny<0 || nx <0 || ny>=N || nx>=N) continue;

                    /**
                     * 만약 상어가 움직일 위치에  피가 없을 경우  그 위치와 방향을 다음 방향값으로 설정한다.
                     */
                    if(blood[ny][nx] == 0){
                        nextY = ny;
                        nextX = nx;
                        zeroBlood = true;
                        nextD = curDir;
                        break;
                    }
                }

                /**
                 * 만약 상어 주변에 피가 다 있다면 우선순위 방향대로 움직이면서 자기 피를 찾아야한다.
                 */

                if(!zeroBlood){
                    for (int j = 1; j <=4 ; j++) {
                        int curDir = priorityDir[i][dir[i]][j];
                        int ny = curY +dy[curDir];
                        int nx = curX +dx[curDir];

                        if(ny<0 || nx <0 || ny>=N || nx>=N) continue;

                        /**
                         * 자기 피 찾으면 그 위치와 방향이 다음 위치와 방향이다.
                         */
                        if(smellOwner[ny][nx]==i){
                            nextY= ny;
                            nextX = nx;
                            nextD = curDir;
                            break;
                        }

                    }
                }

                /**
                 * 충돌 단계
                 * 다음 상어 위치 배열에서 0이면 순조롭게 이동하고 다음 방향 ,위치도 설정해준다.
                 * 하지만 다음 움직인 위치에 상어가 있다면 숫자 높은애 탈락시켜버린다.
                 */

                if(nextGrid[nextY][nextX]==0){
                    nextGrid[nextY][nextX]=i;
                    nextPosition[i][0] = nextY;
                    nextPosition[i][1] = nextX;
                    nextDir[i]=nextD;
                }else{
                    int temp = nextGrid[nextY][nextX];
                    if(temp>i){
                        exist[temp]  = false;
                        nextGrid[nextY][nextX] = i;
                        nextPosition[i][0] = nextY;
                        nextPosition[i][1] = nextX;
                        nextDir[i]=nextD;
                    }
                    else{
                        exist[i] =false;
                    }
                }

            }// 전체 상어 이동 반복문 끝

            /**
             * 전체 상어 이동이 끝난 다음  원래 스태틱 배열 주소로 대입
             */

            grid = nextGrid;
            position = nextPosition;
            dir = nextDir;

            /**
             * 그리고 피 전부 1씩 감소
             */
            declineBlood();

            /**
             * 움직임이 끝난 상어들 위치에 피값을 K 값으로 설정
             */
            for (int i = 1; i <=M ; i++) {
                if(!exist[i]) continue;
                int y = position[i][0];
                int x = position[i][1];
                blood[y][x] = K;
                smellOwner[y][x]=i;
            }

            /**
             * 시간 증가
             */
            time++;



        }
        return -1;

    }

    private static void declineBlood() {

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if(blood[i][j]>=1){
                    blood[i][j]--;

                    if(blood[i][j]==0){
                        smellOwner[i][j]=0;
                    }
                }

            }
        }

    }

    private static int liveShark() {
        int count =0;
        for (int i = 1; i <=M ; i++) {
            if(exist[i]) count++;
        }
        return count;
    }


}
