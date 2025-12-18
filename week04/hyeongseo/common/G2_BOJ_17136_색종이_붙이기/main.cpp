// https://www.acmicpc.net/problem/17136

// 첫 시도 : 무한 루프
// 풀이 과정
// 1) 처음에 board[i][j] == 1인 위치 vector<pair<int,int>> pos 저장 후 백트래킹에서 이 부분만 순회
// 2) 백트래킹 : getAns(int idx, int cnt) = <pos 인덱스, 붙인 색종이 개수>
// 무한 루프 원인
// 만약 전체가 1로 덮여 있을 때 (0,0)에서 5x5를 붙여도 바로 (0,1)부터 검사함
// 따라서 중복 탐색이 많고 가지 치기가 거의 없어 5x5 ~ 1x1까지 검사했을 때 시간 초과 나는 것으로 추정

// 성공 : 35% 틀림 => 이후 디버깅 => 32ms
// 풀이 과정
// 1) 처음에 색종이 위치 후보 저장 X
// 2) 백트래킹 : getAns(int x, int y, int cnt) = <현재 x, 현재 y, 붙인 색종이 개수>
// 3) 백트래킹을 좌표 기준으로 진행하며 x >= 10 이면 백트래킹 종료

// 33% 틀림 원인 : 색종이 붙일 떄 x >= 10 || y >= 10 일때 조건 없었음
// 해결 : 조건 추가해주니 32ms 성공

// 총평 : 로직 자체는 어렵지 않았음 백트래킹을 진행할 때 가지치기 조건을 잘 확인하자!

// 42분 18초
#include <bits/stdc++.h>

using namespace std;

// 0 : 빈 칸
// 1 : 색종이 붙일 수 있는 위치지만 아직 붙여지지 않은 곳
// 2 : 이미 색종이 붙인 공간
int board[10][10];

int ans = INT_MAX;

// 종이 크기 별 남아 있는 개수
int paper[6] = {0, 5, 5, 5, 5, 5};

// 색종이 붙일 곳에 빈 곳이 있는지 확인
bool isFullAttached()
{
    for(int i = 0; i < 10; i++)
    {
        for(int j = 0; j < 10; j++)
        {
            if(board[i][j] == 1) return false;
        }
    }
    return true;
}

// (x,y) 에서 sz * sz 크기의 색종이를 붙일 수 있는 위치인지 확인
bool isAvailable(int x, int y, int sz)
{
    for(int i = x; i < x + sz; i++)
    {
        for(int j = y; j < y + sz; j++)
        {
            // 33% 틀린 원인 : 색종이 붙이는 공간이 판을 넘어가면 불가능
            if(i >= 10 || j >= 10) return false;
            if(board[i][j] != 1) return false;
        }
    }
    return true;
}

// 색종이 붙이기
void attachPaper(int x, int y, int sz)
{
    for(int i = x; i < x + sz; i++)
    {
        for(int j = y; j < y + sz; j++)
        {
            board[i][j] = 2;
        }
    }
}

void getAns(int x, int y, int cnt)
{
    // 다음 붙일 수 있는 위치 찾기
    while(x < 10 && (board[x][y] == 0 || board[x][y] == 2))
    {
        y++;
        if(y >= 10)
        {
            x++;
            y = 0;
        }
    }

    // 전체 판 다 봤으면
    if(x >= 10)
    {
        // 색종이 다 붙였으면 정답 업데이트
        if(isFullAttached()) ans = min(ans, cnt);
        return ;
    }

    int tmp[10][10];
    memcpy(tmp, board, sizeof(board)); // 원본 저장

    // 5x5 ~ 1x1 색종이 붙일 수 있는 거 붙이기
    for(int sz = 5; sz >= 1; sz--)
    {
        if(paper[sz] == 0) continue;

        if(isAvailable(x, y, sz))
        {
            attachPaper(x, y, sz); // 색종이 붙이기
            paper[sz]--; // 색종이 하나 씀
            getAns(x, y, cnt+1); // 백트래킹
            paper[sz]++; // 색종이 쓴 거 원상 복구
            memcpy(board, tmp, sizeof(tmp)); // 원상 복구
        }
    }
}

int main()
{
    ios::sync_with_stdio(0);
    cin.tie(0);

    for(int i = 0; i < 10; i++)
    {
        for(int j = 0; j < 10; j++)
        {
            cin >> board[i][j];
        }
    }

    getAns(0, 0, 0);

    if(ans == INT_MAX) ans = -1;

    cout << ans << "\n";

    return 0;
}