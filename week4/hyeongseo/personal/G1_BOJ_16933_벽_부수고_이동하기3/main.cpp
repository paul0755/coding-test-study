// https://www.acmicpc.net/problem/16933

// 분류 : BFS
// 성공 : BFS
// 첫 시도 : 11% 틀림
// 풀이 과정
// 1) dx[5] = {0, -1, 1, 0, 0}; dy[5] = {0, 0, 0, -1, 1}; => 가만히 있는 시퀀스를 넣음
// => 이동하려는 곳이 벽이 아닐 때는 무조건 기다릴 필요가 없음!
// 따라서, 벽을 부숴야 하는데 밤인 경우에만 현재 위치, 현재 벽 부순 횟수에서 낮/밤만 바뀌고 최단 거리 수정해야 함

// 성공 (챗지피티) : 1276ms
// 풀이 과정
// 1) (0,0)에서 BFS 순회
// 2) 벽인데 밤이라서 못 부수는 경우일때만 가만히 있는 처리 추가

// 총평 : "벽은 낮에만 부술 수 있다" 문장을 잘못 해석한 것이 크다! 앞으로는 문제 해석을 잘하자!

#include <bits/stdc++.h>

using namespace std;

int n, m, k;

int board[1001][1001];

// (x, y) 오는데까지 벽을 k번 부쉈고 현재 낮 or 밤일때 최단 거리
// 0 : 낮
// 1 : 밤
int dist[1001][1001][11][2];

// 11% 틀림 원인
// 낮일 때는 가만히 있을 필요 없어서 다시 상, 하, 좌, 우 4칸으로 변경
int dx[4] = {-1, 1, 0, 0};
int dy[4] = {0, 0, -1, 1};

int ans = INT_MAX;

void BFS()
{
    // x, y, 벽 부순 횟수, 낮 밤 여부
    queue<tuple<int, int, int, int>> q;
    // 낮부터 시작
    q.push({0, 0, 0, 0});

    dist[0][0][0][0] = 1;

    while(!q.empty())
    {
        auto [curX, curY, curCnt, curDay] = q.front();
        q.pop();

        // 도착하면 끝
        if(curX == n - 1 && curY == m - 1)
        {
            ans = dist[curX][curY][curCnt][curDay];
            return;
        }

        for(int i = 0; i < 4; i++)
        {
            int nX = curX + dx[i];
            int nY = curY + dy[i];
            int nCnt = curCnt;
            int nDay = 1 - curDay; // 0 -> 1 / 1 -> 0

            if(nX < 0 || nX >= n || nY < 0 || nY >= m) continue; // 범위 벗어나면 패스
            
            if(board[nX][nY] == 1) // 벽일 때
            {
                nCnt = curCnt + 1;
                if(nCnt > k) continue; // 벽 부순 횟수 넘으면 벽 못 부숨

                // 11% 틀림 원인
                // 밤에 벽을 못 부술 때만 가많이 있는 처리해주면 됨
                // 밤이라면 낮 될때까지 기다리기
                if(curDay == 1) 
                {
                    if(dist[nX][nY][curCnt][nDay] != -1) continue; // 이미 방문했으면 패스

                    dist[curX][curY][curCnt][nDay] = dist[curX][curY][curCnt][curDay] + 1;
                    q.push({curX, curY, curCnt, nDay});
                    continue;
                }
                
            }

            if(dist[nX][nY][nCnt][nDay] != -1) continue; // 이미 방문했으면 패스

            dist[nX][nY][nCnt][nDay] = dist[curX][curY][curCnt][curDay] + 1;
            q.push({nX, nY, nCnt, nDay});
        }
    }
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
            // 입력 예제 틀린 원인
            // 문자열 -> 숫자로 바꿀 때는 꼭 - '0' 해주자
            board[i][j] = s[j] - '0';
        }
    }

    // 최단 거리 배열 초기화
    memset(dist, -1, sizeof(dist));

    BFS();

    if(ans == INT_MAX) ans = -1;
    cout << ans << "\n";

    return 0;
}