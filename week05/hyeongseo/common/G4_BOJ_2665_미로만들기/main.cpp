// https://www.acmicpc.net/problem/2665

// 분류 : BFS, 최단 경로, 다익스트라, 0-1 BFS
// 성공 : 다익스트라 -> 0ms

// 다른 방법 성공 : 0-1 BFS -> 0ms
// 방을 바꾼 횟수가 "최소"여야하므로 되도록 흰 방을 먼저 방문하고 검은 방은 나중에 방문해야 함
// 자료 구조 덱을 써서 흰 방이면 push_front(), 검은 방이면 push_back() 해서 흰 방 부터 방문하도록
// 풀이 과정)
// 1) BFS에서 vis[x][y][k] : (x, y)이고 방을 k번 바꿨을 때 방문 여부 둠
// 2) 0-1 BFS => 흰 방은 push_front(), 검은 방은 push_back()
// 3) 도착점 도착 시 현재 방 바꾼 횟수 정답 업데이트 후 BFS 종료

// 총평 : 사실 처음에 다익스트라보다 0-1 BFS를 시도했는데 예제 조차 틀렸음
// 따라서, 다익스트라로 바꿨는데 알고 보니 0-1 BFS 로직은 맞혔는데
// 너무 당연하게도 검은 방을 1, 흰 방을 0으로 두고 풀었던 것!!!
// 문제를 제대로 읽고 문제에 대한 선입견을 가지지 말 것!!!

#include <bits/stdc++.h>

using namespace std;

int n;
int board[51][51];

int cnt[51][51]; // (x,y)까지 오는데 방 바꾼 최소 횟수 (다익스트라)
bool vis[51][51][255]; // (x,y)까지 왔고 방을 k번 바꿨을 때 방문 여부 (0-1 BFS)

int dx[4] = {-1, 1, 0, 0};
int dy[4] = {0, 0, -1, 1};

int ans = 0; // (0-1 BFS)

// 다익스트라
void Dijkstra()
{
    // 방 바꾼 횟수, x, y
    priority_queue<tuple<int, int, int>, vector<tuple<int, int, int>>, greater<>> pq;
    pq.push({0, 0, 0});

    cnt[0][0] = 0;

    while(!pq.empty())
    {
        auto [curCnt, curX, curY] = pq.top();
        pq.pop();

        for(int i = 0; i < 4; i++)
        {
            int nX = curX + dx[i];
            int nY = curY + dy[i];

            if(nX < 0 || nX >= n || nY < 0 || nY >= n) continue;

            // 흰 방일때
            if(board[nX][nY] == 1)
            {
                if(cnt[nX][nY] > curCnt)
                {
                    cnt[nX][nY] = curCnt;
                    pq.push({cnt[nX][nY], nX, nY});
                }
            }

            // 검은 방일때
            else
            {
                if(cnt[nX][nY] > curCnt + 1)
                {
                    cnt[nX][nY] = curCnt + 1;
                    pq.push({cnt[nX][nY], nX, nY});
                }
            }
        }
    }
}

// 0-1 BFS
void BFS()
{
    // x, y, 방 바꾼 횟수
    deque<tuple<int, int, int>> dq;
    vis[0][0][0] = true;

    dq.push_front({0, 0, 0});

    while(!dq.empty())
    {
        auto [curX, curY, curCnt] = dq.front();
        dq.pop_front();

        if(curX == n - 1 && curY == n - 1)
        {
            ans = curCnt;
            return;
        }

        for(int i = 0; i < 4; i++)
        {
            int nX = curX + dx[i];
            int nY = curY + dy[i];

            if(nX < 0 || nX >= n || nY < 0 || nY >= n) continue;

            // 흰 방일때
            if(board[nX][nY] == 1)
            {
                if(vis[nX][nY][curCnt]) continue;

                vis[nX][nY][curCnt] = true;
                dq.push_front({nX, nY, curCnt});
            }

            // 검은 방일때
            if(board[nX][nY] == 0)
            {
                if(vis[nX][nY][curCnt + 1]) continue;

                vis[nX][nY][curCnt + 1] = true;
                dq.push_back({nX, nY, curCnt + 1});
            }
        }
    }
}

int main()
{
    ios::sync_with_stdio(0);
    cin.tie(0);

    cin >> n;

    for(int i = 0; i < n; i++)
    {
        string s;
        cin >> s;

        for(int j = 0; j < n; j++)
        {
            board[i][j] = s[j] - '0';
        }
    }

    // 다익스트라
    // 방 바꾼 최소 횟수 초기화
    for(int i = 0; i < n; i++)
    {
        for(int j = 0; j < n; j++)
        {
            cnt[i][j] = INT_MAX;
        }
    }

    Dijkstra();
    cout << cnt[n-1][n-1] << "\n";

    // 0-1 BFS
    // BFS();
    // cout << ans << "\n";

    return 0;
}