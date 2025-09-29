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

// ++리팩토링
// 굳이 인접 리스트 안 만들고 편의점 전체 순회하며 방문 가능하면 방문, 아니면 방문 X
#include <bits/stdc++.h>

using namespace std;

int t, n;

int stX, stY; // 시작 위치
int goalX, goalY; // 도착 위치

pair<int ,int> store[101]; // 편의점 좌표

// 두 점 사이의 거리가 맥주 20개로 갈 수 있는 거리인지 아닌지 판단
bool isReachable(int x1, int y1, int x2, int y2)
{
    if(abs(x1 - x2) + abs(y1 - y2) <= 20 * 50) return true;
    else return false;
}

void BFS()
{
    bool vis[101]; // 편의점 방문 표시
    memset(vis, false, sizeof(vis));

    queue<pair<int, int>> q;
    q.push({stX, stY});

    while(!q.empty())
    {
        auto [curX, curY] = q.front();
        q.pop();

        // 현재 위치에서 도착점 갈 수 있으면 끝
        if(isReachable(curX, curY, goalX, goalY))
        {
            cout << "happy\n";
            return;
        }

        for(int i = 0; i < n; i++)
        {
            auto [sX, sY] = store[i];

            if(vis[i]) continue;

            // 현재 위치에서 도달 가능한 편의점이면 q.push()
            if(isReachable(curX, curY, sX, sY))
            {
                q.push({sX, sY});
                vis[i] = true;
            }
        }
    }

    cout << "sad\n"; // 도착 못하면 sad
}

int main()
{
    ios::sync_with_stdio(0);
    cin.tie(0);

    cin >> t;

    while(t--)
    {
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

        BFS();
    }

    return 0;
}