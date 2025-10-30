// https://www.acmicpc.net/problem/2151

// 3% 메모리 초과

// 성공 : 다익스트라 -> 0ms
// 풀이 과정
// 1) cnt[x][y] : (x, y) 까지 오는 데에 설치한 최소 거울 개수
// 2) 다익스트라 전에 cnt[x][y] = INT_MAX로 초기화
// 3) 다익스트라 진행 (첫 시작 : 문 위치) (pq : <현재 설치 거울 개수, x, y, 진행 방향>)
// 4) 현재 위치에서 한 방향으로 쭉 진행
// 4-1) 범위 넘거나, 벽이면 패스
// 4-2) 도착 지점의 거울 설치 개수 <= 현재 지점 까지의 거울 설치 개수면 패스
// 5) cnt[nX][nY] = curCnt로 최소 거울 개수 업데이트
// 6) 거울 후보 위치인 경우
// 6-1) 거울 설치 안하는 경우 pq.push()
// 6-2) 거울 설치하는 경우 2가지 cnt[nX][nY] = curCnt + 1; pq.push()

// 최적화 (챗지피티) : 다익스트라 -> 0ms
// 기존에 한 방향으로 쭉 가던 거 => 한 칸씩 이동으로 변경
// cnt[x][y] => cnt[x][y][dir]로 변경 (중복 방지)

// 다른 방법 (챗지피티) : 0-1 BFS -> 0ms
// deuqe 사용
// 거울 설치 안하면 push_front()
// 거울 설치 하면 push_back();

// 총평 : 처음에 헷갈리긴 했지만 나쁘지 않았음
// 다른 조건보다 먼저 방문해야할 조건 (2중 택 1)이 있다면 0-1 BFS 풀이 떠올리기!!

// 57분 23초
#include <bits/stdc++.h>

using namespace std;

int n;

// # : 문
// . : 빈 칸
// ! : 거울 설치 위치 후보
char board[51][51];

// 상, 좌, 하, 우
int dx[4] = {-1, 0, 1, 0};
int dy[4] = {0, -1, 0, 1};

int stX = -1, stY = -1, enX = -1, enY = -1;

int cnt[51][51][4]; // (x, y) 까지 dir 방향으로 오는 데에 설치한 최소 거울 개수

// 다익스트라
void Dijkstra(int x, int y)
{
    // 거울 개수, x, y, 진행 방향
    priority_queue<tuple<int, int, int, int>, vector<tuple<int, int, int, int>>, greater<>> pq;

    for(int i = 0; i < 4; i++)
    {
        cnt[x][y][i] = 0;
        pq.push({cnt[x][y][i], x, y, i});
    }

    while(!pq.empty())
    {
        auto [curCnt, curX, curY, curDir] = pq.top();
        pq.pop();

        int nX = curX + dx[curDir];
        int nY = curY + dy[curDir];

        if(nX < 0 || nX >= n || nY < 0 || nY >= n) continue; // 범위 넘으면 패스
        if(board[nX][nY] == '*') continue; // 벽이면 패스

        // 같은 방향 진행
        if(cnt[nX][nY][curDir] > curCnt)
        {
            cnt[nX][nY][curDir] = curCnt;
            pq.push({cnt[nX][nY][curDir], nX, nY, curDir});
        }

        // 거울 위치 후보면 거울 설치
        if(board[nX][nY] == '!')
        {
            
            int prevDir = (curDir - 1) % 4;
            if(prevDir < 0) prevDir += 4;

            if(cnt[nX][nY][prevDir] > curCnt + 1)
            {
                cnt[nX][nY][prevDir] = curCnt + 1;
                pq.push({cnt[nX][nY][prevDir], nX, nY, prevDir});
            }

            int nxtDir = (curDir + 1) % 4;

            if(cnt[nX][nY][nxtDir] > curCnt + 1)
            {
                cnt[nX][nY][nxtDir] = curCnt + 1;
                pq.push({cnt[nX][nY][nxtDir], nX, nY, nxtDir});
            }
        }
    }
}



// 0-1 BFS
void BFS(int x, int y)
{
    // <x, y, 방향>
    deque<tuple<int, int, int>> dq;

    for(int i = 0; i < 4; i++)
    {
        cnt[x][y][i] = 0;
        dq.push_front({x, y, i});
    }

    while(!dq.empty())
    {
        auto [curX, curY, curDir] = dq.front();
        dq.pop_front();

        int nX = curX + dx[curDir];
        int nY = curY + dy[curDir];

        if(nX < 0 || nX >= n || nY < 0 || nY >= n) continue;
        if(board[nX][nY] == '*') continue;

        
        // 거울 설치 안하는 방법을 먼저 방문
        if(cnt[nX][nY][curDir] > cnt[curX][curY][curDir])
        {
            cnt[nX][nY][curDir] = cnt[curX][curY][curDir];
            dq.push_front({nX, nY, curDir});
        }

        if(board[nX][nY] == '!')
        {
            int prevDir = (curDir - 1) % 4;
            if(prevDir < 0) prevDir += 4;

            if(cnt[nX][nY][prevDir] > cnt[curX][curY][curDir] + 1)
            {
                cnt[nX][nY][prevDir] = cnt[curX][curY][curDir] + 1;
                dq.push_back({nX, nY, prevDir});
            }

            int nDir = (curDir + 1) % 4;

            if(cnt[nX][nY][nDir] > cnt[curX][curY][curDir] + 1)
            {
                cnt[nX][nY][nDir] = cnt[curX][curY][curDir] + 1;
                dq.push_back({nX, nY, nDir});
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
            board[i][j] = s[j];

            if(board[i][j] == '#')
            {
                if(stX == -1)
                {
                    stX = i;
                    stY = j;
                }
                else
                {
                    enX = i;
                    enY = j;
                }
                
            }
        }
    }

    for(int i = 0; i < n; i++)
    {
        for(int j = 0; j < n; j++)
        {
            for(int dir = 0; dir < 4; dir++)
            {
                cnt[i][j][dir] = INT_MAX;
            }
        }
    }

    // 다익스트라
    Dijkstra(stX, stY);

    // 0-1 BFS
    // BFS(stX, stY);

    int ans = INT_MAX;
    for(int i = 0; i < 4; i++)
    {
        ans = min(ans, cnt[enX][enY][i]);
    }
    
    cout << ans << "\n";

    return 0;
}