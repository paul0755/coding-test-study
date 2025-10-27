// https://www.acmicpc.net/problem/10282

// 분류 : 최단 경로, 다익스트라
// 성공 : 다익스트라

// 25% 틀림
// 원인 : 테스트케이스가 있어서 매번 여러 변수들을 초기화해야하는데 인접리스트 adj 배열을 초기화 안함
// 해결 : 매 테스트케이스마다 adj배열 초기화시킴

#include <bits/stdc++.h>

using namespace std;

#define MAX 10001
int tc;
int n, d, c; // 컴퓨터 개수, 의존성 개수, 해킹당한 컴퓨터 번호

vector<pair<int, int>> adj[MAX]; // <감염 시간, 컴퓨터 번호>
int dist[MAX]; // dist[i] : i가 감염되는 최단 시간

void Dijkstra(int x)
{
    priority_queue<pair<int, int>, vector<pair<int, int>>, greater<>> pq;
    dist[x] = 0;
    pq.push({dist[x], x});

    while(!pq.empty())
    {
        auto [curT, cur] = pq.top();
        pq.pop();

        for(auto [nT, nxt] : adj[cur])
        {
            if(dist[nxt] > curT + nT)
            {
                dist[nxt] = curT + nT;
                pq.push({dist[nxt], nxt});
            }
        }
    }
}

int main()
{
    ios::sync_with_stdio(0);
    cin.tie(0);

    cin >> tc;

    while(tc--)
    {
        cin >> n >> d >> c;

        for(int i = 0; i <= n; i++)
        {
            adj[i].clear();
        }

        for(int i = 0; i < d; i++)
        {
            int a, b, s;
            cin >> a >> b >> s;

            adj[b].push_back({s, a});
        }

        for(int i = 1; i <= n; i++) dist[i] = INT_MAX;

        Dijkstra(c);

        int cnt = 0;
        int idx = c;
        
        // 감염된 컴퓨터 수, 가장 마지막에 감염된 컴퓨터 감염 시간 구하기
        for(int i = 1; i <= n; i++)
        {
            if(dist[i] != INT_MAX)
            {
                cnt++;
                if(dist[idx] < dist[i]) idx = i;
            }
        }

        cout << cnt << " " << dist[idx] << "\n";
        
    }

    return 0;
}