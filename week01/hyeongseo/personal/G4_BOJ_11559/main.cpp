// https://www.acmicpc.net/problem/11559

// 분류 : 구현, BFS

// 20% 틀림
// 원인 : 범위 검사 => x : 0 ~ 12, y : 0 ~ 12로 함
// 해결 : 범위 검사 => x : 0 ~ 12, y : 0 ~ 6으로 수정

#include <bits/stdc++.h>

using namespace std;

char board[12][6]; // 뿌요 판
int dx[4] = {-1, 1, 0 ,0};
int dy[4] = {0, 0, -1, 1};

int ans = 0; // 연쇄 뿌요 횟수
bool vis[12][6]; // 방문 확인 배열

// 실제 뿌요 떨어질 위치 찾고 떨어뜨리기
void dropPuyo(int x, int y)
{
    int curX = x + 1;
    int curY = y;

    // 아래로 떨어질 위치
    // 빈 칸이면 계속 아래로
    while(curX < 12 && board[curX][curY] == '.') curX++;

    curX--;

    char cur = board[x][y];
    board[x][y] = '.'; // 원래 칸 빈 칸으로
    board[curX][curY] = cur; // 떨어질 위치에 떨어뜨림
}

// 아래로 떨어질 뿌요가 있는지 찾기
void drop()
{
    for(int i = 11; i >= 0; i--)
    {
        for(int j = 0; j < 6; j++)
        {
            if(board[i][j] == '.') continue;
            dropPuyo(i, j);
        }
    }
}

// 같은 색상 4개 이상이면 뿌요 (삭제)
bool puyo(int x, int y)
{
    queue<pair<int, int>> q;
    q.push({x, y});
    vis[x][y] = true;

    vector<pair<int, int>> path;
    path.push_back({x, y});

    int cnt = 1;

    while(!q.empty())
    {
        auto [curX, curY] = q.front();
        q.pop();

        for(int i = 0; i < 4; i++)
        {
            int nX = curX + dx[i];
            int nY = curY + dy[i];

            // 20% 틀림 원인 : y 범위 검사는 0 ~ 12가 아니라 0 ~ 6으로 해줘야 함!
            if(nX < 0 || nX >= 12 || nY < 0 || nY >= 6) continue;
            if(board[curX][curY] != board[nX][nY]) continue;
            if(vis[nX][nY]) continue;

            vis[nX][nY] = true;
            path.push_back({nX, nY});
            q.push({nX, nY});
            cnt++;
        }
    }

    if(cnt >= 4)
    {
        for(auto [pX, pY] : path)
        {
            board[pX][pY] = '.';
        }

        return true;
    }

    return false;
}

int main()
{
    ios::sync_with_stdio(0);
    cin.tie(0);

    for(int i = 0; i < 12; i++)
    {
        string s;
        cin >> s;
        for(int j = 0; j < 6; j++)
        {
            board[i][j] = s[j];
        }
    }

    while(true)
    {
        memset(vis, false, sizeof(vis));
        bool isFinished = true;
        for(int i = 0; i < 12; i++)
        {
            for(int j = 0; j < 6; j++)
            {
                if(board[i][j] == '.') continue; // 빈 칸이면 패스
                if(vis[i][j]) continue; // 방문했으면 패스
                if(puyo(i, j)) isFinished = false; // 뿌요 성공했으면
            }
        }

        if(isFinished) break; // 뿌요 성공 못했으면 더 이상 진행 X

        drop(); // 아래로 떨어뜨리기

        ans++; // 연쇄 횟수 ++
    }

    cout << ans << "\n";

    return 0;
}