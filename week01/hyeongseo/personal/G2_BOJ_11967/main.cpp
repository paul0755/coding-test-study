// https://www.acmicpc.net/problem/11967

// 8% 시간 초과 => turnLight() & move()
// 2% 틀림 => turnLight()만

// 성공 : 248ms
// 풀이 과정
// 1) BFS 진행하며 현재 칸에 스위치 켤 수 있으면 스위치 켜고 "방문 후보 큐"에 해당 칸 삽입
// 2) "방문 후보 큐" 순회하며 주위 4방향 중 불이 켜져 있고 이미 방문한 칸이 있음 => 현재 위치에서 방문 가능
// 따라서, BFS 큐에 삽입

// 최적화 : 8ms (챗지피티)
// 풀이 과정 : 방문 후보 큐 사용 X
// 1) BFS 진행하며 현재 칸에 스위치 켤 수 있으면 스위치 켜고 바로 접근 가능하면 BFS Q에 해당 칸 삽입
// 2) 현재 칸에서 4방향 검사 후 불 켜진 방 있으면 방문 표시 후 BFS 큐에 삽입

// 65분 15초

#include <bits/stdc++.h>

using namespace std;

int dx[4] = {-1, 1, 0, 0};
int dy[4] = {0, 0, -1, 1};

int n, m;

// 각 방 별 불 켤 수 있는 스위치 목록
// 1-indexed
vector<pair<int, int>> switches[103][103];
bool vis[103][103]; // 방문 확인 배열

// 각 방 별 불 켜져 있는지 => 불 켜진 곳만 이동 가능
// 1-indexed
bool isLightOn[103][103];

int turnLight()
{
    queue<pair<int, int>> q;

    q.push({1, 1});

    isLightOn[1][1] = true;
    vis[1][1] = true;
    int cnt = 1;

    while(!q.empty())
    {
        auto [curX, curY] = q.front();
        q.pop();

        // 불 켜기
        for(auto [nX, nY] : switches[curX][curY])
        {
            // 전에 방문했던 적이 있다면 방문 후보에 추가 X
            if(isLightOn[nX][nY]) continue;
            
            isLightOn[nX][nY] = true;
            cnt++;

            // 현재 불 켠 방이 바로 접근 가능하면 큐에 삽입
            for(int i = 0; i < 4; i++)
            {
                int aX = nX + dx[i];
                int aY = nY + dy[i];

                if(aX < 1 || aX > n || aY < 1 || aY > n) continue; // 범위 벗어나면 패스
                if(isLightOn[aX][aY] && vis[aX][aY]) // 주위에 불 켜져 있고 방문한 곳 있으면 현재 위치에서 방문 가능
                {
                    q.push({nX, nY});
                    vis[nX][nY] = true;
                    break;
                }
            }
        }

        // 인접한 불 켜진 방으로 이동
        for(int i = 0; i < 4; i++)
        {
            int nX = curX + dx[i];
            int nY = curY + dy[i];

            if(nX < 1 || nX > n || nY < 1 || nY > n) continue;
            if(vis[nX][nY]) continue;
            if(!isLightOn[nX][nY]) continue;
            
            q.push({nX, nY});
            vis[nX][nY] = true;
        }
    }

    return cnt;
}

int main()
{
    ios::sync_with_stdio(0);
    cin.tie(0);

    cin >> n >> m;

    for(int i = 0; i < m; i++)
    {
        int x, y, a, b;
        cin >> x >> y >> a >> b;

        switches[x][y].push_back({a, b});
    }

    cout << turnLight() << "\n";

    return 0;
}