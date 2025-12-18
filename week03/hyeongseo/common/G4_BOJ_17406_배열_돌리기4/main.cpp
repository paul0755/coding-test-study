// https://www.acmicpc.net/problem/17406

// 분류 : 구현, 브루트포스, 백트래킹
#include <bits/stdc++.h>

using namespace std;

int n, m, k;

int board[60][60];

vector<tuple<int, int, int>> rotateInfo; // 회전 정보

int ans = INT_MAX;

bool isUsed[6]; // 해당 회전을 썼는지 안썼는지 표시

// 시계 방향 회전
void rotate(int r, int c, int s)
{
    int tmp[60][60];
    memcpy(tmp, board, sizeof(board));

    int stX = r-s;
    int stY = c-s;
    int enX = r+s;
    int enY = c+s;

    while(stX != r && stY != c)
    {
        for(int j = stY+1; j <= enY; j++) board[stX][j] = tmp[stX][j-1];
        for(int i = stX+1; i <= enX; i++) board[i][enY] = tmp[i-1][enY];
        for(int j = enY-1; j >= stY; j--) board[enX][j] = tmp[enX][j+1];
        for(int i = enX-1; i >= stX; i--) board[i][stY] = tmp[i+1][stY];

        stX++;
        stY++;
        enX--;
        enY--;
    }
    
}

// 각 행의 총합 중 최솟값 찾기
int GetMin()
{
    int ret = INT_MAX;
    for(int i = 1; i <= n; i++)
    {
        int tmp = 0;
        for(int j = 1; j <= m; j++)
        {
            tmp += board[i][j];
        }

        ret = min(ret, tmp);
    }

    return ret;
}

// 백트래킹으로 가능한 모든 경우의 수 구하기
void GetAns(int cur)
{
    if(cur == k)
    {
        ans = min(ans, GetMin());
        return ;
    }

    for(int i = 0; i < (int)rotateInfo.size(); i++)
    {
        if(isUsed[i]) continue; // 이미 이 정보로 돌렸으면 패스

        auto [r, c, s] = rotateInfo[i];
        isUsed[i] = true;
        int tmp[60][60];
        memcpy(tmp, board, sizeof(board)); // 원본 상태 저장

        rotate(r, c, s);

        GetAns(cur + 1);

        isUsed[i] = false;
        memcpy(board, tmp, sizeof(tmp)); // 원본 상태 복구
    }
}


int main()
{
    ios::sync_with_stdio(0);
    cin.tie(0);

    cin >> n >> m >> k;

    for(int i = 1; i <= n; i++)
    {
        for(int j = 1; j <= m; j++)
        {
            cin >> board[i][j];
        }
    }

    // 회전 정보 저장
    for(int i = 0; i < k; i++)
    {
        int r, c, s;
        cin >> r >> c >> s;

        rotateInfo.push_back({r, c, s});
    }

    GetAns(0);

    cout << ans << "\n";

    return 0;
}