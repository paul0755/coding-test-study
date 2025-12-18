// https://www.acmicpc.net/problem/19237

// 분류 : 구현, 시뮬레이션
// 성공 : 구현, 시뮬레이션

/*
풀이 과정

1) 현재 위치에 냄새 남김
2) 현재 상어가 바라보는 방향, 우선순위에 따라 다음 방향 정하기
3) 실제 상어 이동 (한 칸에 한 상어만 남기기)
4) 냄새 삭제

헤맨 포인트!
- 상어가 한 칸에 하나만 있어야 하고, 이동 처리를 할 때 막 꼬였음
- 그래서 처음에 priority_queue<int> pos[30][30]; 으로 해서 한 칸에 상어가 여러 마리 들어올 수 있고 pop해서 하나만 남기고
- 이동 마친 상어는 바로 pq.pop() 빌 떄까지 계속 수행함

수정
- 상어의 다음 방향 정하기 - 실제 상어 이동 로직 분리
- 상어 이동 로직
  - newBoard[30][30]을 만들어서 아예 새로운 판에 다음 이동 기록
  - 이후 기존 board에 newBoard 복사해 업데이트 함


총평
- 처음에 계속 헤매다가 상어 이동 로직을 새로운 배열 만들어서 개발해보니 잘 풀림!!
- 앞으로 비슷한 유형의 문제를 풀 때는 이렇게 풀어봐야겠음

*/
#include <bits/stdc++.h>

using namespace std;

int n, m, k;

// 1 : 상, 2 : 하, 3 : 좌, 4 : 우
int dx[5] = {0, -1, 1, 0, 0};
int dy[5] = {0, 0, 0, -1, 1};

// 빈 칸 : 0
// 번호 : 상어
int board[30][30]; // 격자

// shark[i][j][k] : i번 상어가 j 방향을 보고 있을 때 이동 우선순위 k
int priorityDir[402][5][5];

pair<int, int> smell[30][30]; // 현재 위치의 냄새 정보 <상어 번호, 남은 냄새 지속 시간>
pair<int, int> pos[402]; // 상어의 현재 위치

int sDir[403]; // 현재 i번 상어가 바라보고 있는 방향

int remain = 0; // 남은 상어 수

bool isDeleted[402]; // 현재 상어가 쫓겨났는지 확인

// 테스트용
void printBoard()
{
    cout << "\n============= Board 현황 =============\n";
    for(int i = 1; i <= n; i++)
    {
        for(int j = 1; j <= n; j++)
        {
            cout << board[i][j] << " ";
        }
        cout << "\n";
    }
}

// 현재 상어 위치에 냄새 남기기
void markSmell()
{
    // 남아 있는 상어 
    for(int i = 1; i <= m; i++)
    {
        // 쫓겨난 상어면 패스
        if(isDeleted[i]) continue;

        auto [curX, curY] = pos[i];

        // 현재 위치에 냄새 남기기
        smell[curX][curY] = {i, k};
    }
}

// 현재 남아있는 냄새 지속 시간 확인 후 지속 시간 감소 or 삭제
void deleteSmell()
{
    for(int i = 1; i <= n; i++)
    {
        for(int j = 1; j <= n; j++)
        {
            auto [curShark, curTime] = smell[i][j];

            // 현재 칸에 냄새 없으면 패스
            if(curTime == 0) continue;

            curTime--;

            // 현재 칸 지속 시간 끝나면 삭제
            if(curTime <= 0) smell[i][j] = {0, 0};
            else smell[i][j] = {curShark, curTime};
        }
    }
}

// 현재 상어의 다음 방향 구하기
int getNextDir(int cur)
{
    auto [curX, curY] = pos[cur];
    int curDir = sDir[cur];

    // 우선순위 방향 돌며 냄새 없는 칸 먼저 검사
    for(int i = 1; i <= 4; i++)
    {
        int nDir = priorityDir[cur][curDir][i];

        int nX = curX + dx[nDir];
        int nY = curY + dy[nDir];

        if(nX < 1 || nX > n || nY < 1 || nY > n) continue;

        auto [curShark, curTime] = smell[nX][nY];

        // 냄새가 없으면 상어 방향 업데이트
        if(curShark == 0) return nDir;
    }

    // 우선순위 방향 돌며 내 냄새 칸 검사
    for(int i = 1; i <= 4; i++)
    {
        int nDir = priorityDir[cur][curDir][i];

        int nX = curX + dx[nDir];
        int nY = curY + dy[nDir];

        if(nX < 1 || nX > n || nY < 1 || nY > n) continue;

        auto [curShark, curTime] = smell[nX][nY];

        // 내 냄새면 상어 방향 업데이트
        if(curShark == cur) return nDir;
    }

    return curDir;
}

// 현재 상어의 다음 진행 방향 정하기
void chooseSharksDir()
{
    for(int i = 1; i <= m; i++)
    {
        if(isDeleted[i]) continue;

        sDir[i] = getNextDir(i);
    }
}

// 상어 현재 방향으로 한 칸씩 이동
void moveSharks()
{
    int newBoard[30][30];
    memset(newBoard, 0, sizeof(newBoard));

    for(int i = 1; i <= m; i++)
    {
        if(isDeleted[i]) continue;

        auto [curX, curY] = pos[i];

        int nX = curX + dx[sDir[i]];
        int nY = curY + dy[sDir[i]];
        int nShark = newBoard[nX][nY];

        // 상어가 없으면 그냥 가면 됨
        if(nShark == 0)
        {
            newBoard[nX][nY] = i;
            pos[i] = {nX, nY};

            // cout << "빈 자리: " << nX << " " << nY << " 상어 번호: " << i << " newBoard[nX][nY]: " << newBoard[nX][nY] << "\n";
        }

        // 이미 있는 상어보다 내가 더 작으면
        // 상어가 바뀜
        else if(nShark > i)
        {
            // cout << "내가 더 작음: " << nX << " " << nY << " 상어 번호: " << i << "\n";
            isDeleted[nShark] = true;
            remain--;

            newBoard[nX][nY] = i;
            pos[i] = {nX, nY};
        }

        else
        {
            // cout << "내가 더 큼: " << nX << " " << nY << " 상어 번호: " << i << "\n";
            remain--;
            isDeleted[i] = true;
        }
    }

    // cout << "\n===========new Board ==========\n";
    // for(int i = 1; i <= n; i++)
    // {
    //     for(int j = 1; j <= n; j++)
    //     {
    //         cout << newBoard[i][j] << " ";
    //     }
    //     cout << "\n";
    // }
    // cout << "\n";

    memcpy(board, newBoard, sizeof(board));
}

int main()
{
    ios::sync_with_stdio(0);
    cin.tie(0);

    cin >> n >> m >> k;

    remain = m; // 현재 상어 수 업데이트

    for(int i = 1; i <= n; i++)
    {
        for(int j = 1; j <= n; j++)
        {
            int x;
            cin >> x;

            board[i][j] = x;

            if(x > 0) pos[x] = {i, j}; // 상어의 현재 위치
        }
    }

    // 상어가 현재 바라보는 방향 입력
    for(int i = 1; i <= m; i++) cin >> sDir[i];

    // 상어의 현재 방향에 따른 이동 우선순위 입력
    for(int i = 1; i <= m; i++)
    {
        for(int j = 1; j <= 4; j++)
        {
            for(int l = 1; l <= 4; l++)
            {
                cin >> priorityDir[i][j][l];
            }
        }
    }


    int ans = 0;
    while(ans <= 1000)
    {
        if(remain == 1) break; // 1번 상어만 남으면 끝

        ans++;

        markSmell(); // 현재 위치에 냄새 남기기
        chooseSharksDir(); // 필드 위의 상어들 이동 방향 정하기
        moveSharks(); // 상어 한 칸 이동
        deleteSmell(); // 냄새 지속시간 감소

        // printBoard();
    }

    if(ans > 1000) ans = -1;

    cout << ans << "\n";

    return 0;
}