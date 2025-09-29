// https://www.acmicpc.net/problem/14442

// 2회차 풀이

// 24% 시간 초과
// 원인
// 벽 일 때는 dist[nX][nY][curBreak + 1]
// 빈 칸일 때는 dist[nX][nY][curBreak]
// 방문 여부 나눠서 검사해야 함!

#include <bits/stdc++.h>

using namespace std;

int n, m, k;

int board[1001][1001];

// dist[i][j][k] : (i, j) 지점 까지 오는 동안 벽을 k번 부쉈을 때 최단 경로
int dist[1001][1001][11];

int dx[4] = {-1, 1, 0, 0};
int dy[4] = {0, 0, -1, 1};

int BFS()
{
    // <x, y, 벽 부순 횟수>
    queue<tuple<int, int, int>> q;

    dist[0][0][0] = 1;

    q.push({0, 0, 0});

    while(!q.empty())
    {
        auto [curX, curY, curBreak] = q.front();
        q.pop();

        // 도착하면 끝
        if(curX == n - 1 && curY == m - 1) return dist[curX][curY][curBreak];

        for(int i = 0; i < 4; i++)
        {
            int nX = curX + dx[i];
            int nY = curY + dy[i];

            if(nX < 0 || nX >= n || nY < 0 || nY >= m) continue; // 범위 벗어나면 패스
            
            // if(dist[nX][nY][curBreak] != 0) continue;
            // => 시간 초과 원인
            // 벽일때는 dist[curX][curY][curBreak + 1]
            // 빈 칸일 때는 dist[curX][curY][curBreak]
            // 의 방문 여부를 나눠서 확인해야 함

            // 벽이라면
            if(board[nX][nY] == 1)
            {
                // 이미 벽 부순 횟수 다 소진했다면 패스
                if(curBreak >= k) continue;
                if(dist[nX][nY][curBreak+1] != 0) continue; // 방문했다면 패스

                dist[nX][nY][curBreak + 1] = dist[curX][curY][curBreak] + 1;
                q.push({nX, nY, curBreak + 1});
            }

            // 빈 칸이라면
            else if(board[nX][nY] == 0)
            {
                if(dist[nX][nY][curBreak] != 0) continue; // 방문했다면 패스

                dist[nX][nY][curBreak] = dist[curX][curY][curBreak] + 1;
                q.push({nX, nY, curBreak});
            }
        }
    }

    return -1; // 도착 못하면 -1
}

int main()
{
    ios::sync_with_stdio(0);
    cin.tie(0);

    cin >> n >> m >> k;

    for(int i = 0; i < n; i++)
    {
        string s;
        cin >> s;

        for(int j = 0; j < m; j++)
        {
            board[i][j] = s[j] - '0';
        }
    }

    cout << BFS() << "\n";
    
    return 0;
}