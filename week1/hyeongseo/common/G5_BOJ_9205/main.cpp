// https://www.acmicpc.net/problem/9205
// 분류 : BFS

// 82분 54초

// 처음 풀이 생각)
// 현재 위치에서 도달 가능한 편의점 중 가장 가까운 편의점 계속 방문 => 반례 발생
// 현재 위치에서 도달 가능한 편의점 중 도착점과 가장 가까운 편의점 계속 방문 => 반례 발생
// => 최단 거리로 갔을 때 방문 못하는 편의점 있는데 돌아서 가면 방문 가능한 편의점 생김

// 최종 풀이)
// 맥주 20개로 갈 수 있는 모든 편의점을 돌면서 도착점 갈 수 있는지 없는지 확인

// 최종 풀이했을 때도 로직은 맞았으나 1~2줄 때문에 계속 틀림 => 반성합시다~
#include <bits/stdc++.h>

using namespace std;

int t, n;

int stX, stY; // 시작 위치
int goalX, goalY; // 도착 위치

pair<int ,int> store[101]; // 편의점 좌표

// 현재 편의점에서 도달 가능한 다른 편의점 & 도착점
// <편의점 번호, x, y>
// <-1, goalX, goalY>
vector<tuple<int, int, int>> adj[101];
bool vis[101]; // 편의점 방문 표시

// <편의점 번호, x, y>
queue<tuple<int, int, int>> q; // BFS에서 사용할 큐

// 두 점 사이의 거리
int getDist(int x1, int y1, int x2, int y2)
{
    return abs(x1 - x2) + abs(y1 - y2);
}

void BFS()
{
    while(!q.empty())
    {
        auto [cur, curX, curY] = q.front();
        q.pop();

        // 도착점이면 끝
        if(curX == goalX && curY == goalY)
        {
            cout << "happy\n";
            return;
        }

        for(auto [nxt, nX, nY] : adj[cur])
        {
            // 원인) 원래 여기가 없어서 vis[-1] 이 에러가 나서 틀렸었음! 
            // 도착점이면 끝
            if(nxt == -1)
            {
                cout << "happy\n";
                return;
            }

            
            if(vis[nxt]) continue;

            q.push({nxt, nX, nY});
            vis[nxt] = true;
        }
    }

    cout << "sad\n";
}

// 배열 초기화
void init()
{
    while(!q.empty()) q.pop();

    for(int i = 0; i < 101; i++)
    {
        adj[i].clear();
    }
}

int main()
{
    ios::sync_with_stdio(0);
    cin.tie(0);

    cin >> t;

    while(t--)
    {
        init();

        cin >> n;
        cin >> stX >> stY;

        // 편의점 위치 입력
        for(int i = 0; i < n; i++)
        {
            int x, y;
            cin >> x >> y;
            store[i] = {x, y};
        }

        cin >> goalX >> goalY;

        // 바로 도착점 도착 가능하면 끝
        if(getDist(stX, stY, goalX, goalY) <= 20 * 50)
        {
            cout << "happy\n";
            continue; // 원인) 원래 return 0이어서 바로 프로그램 끝나서 틀렸었음;;
        }

        // 편의점 방문 배열 초기화
        memset(vis, false, sizeof(vis));

        // 현재 지점에서 도달 가능한 편의점 q.push() 후 BFS 진행
        for(int i = 0; i < n; i++)
        {
            auto [x, y] = store[i];
            if(getDist(stX, stY, x, y) <= 20 * 50)
            {
                q.push({i, x, y});
                vis[i] = true;
            }
        }

        // 각 편의점에서 맥주 20개로 도달 가능한 편의점 & 도착점 인접 리스트에 추가
        for(int i = 0; i < n; i++)
        {
            auto [x1, y1] = store[i];

            // 현재 편의점에서 도착점 도착 가능하면 인접 리스트에 추가
            if(getDist(x1, y1, goalX, goalY) <= 20 * 50) adj[i].push_back({-1, goalX, goalY});

            for(int j = i + 1; j < n; j++)
            {
                auto [x2, y2] = store[j];

                if(getDist(x1, y1, x2, y2) <= 20 * 50)
                {
                    adj[i].push_back({j, x2, y2});
                    adj[j].push_back({i, x1, y1});
                }
            }
        }

        BFS();
    }

    return 0;
}