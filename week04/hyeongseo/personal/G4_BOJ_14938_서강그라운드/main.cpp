// https://www.acmicpc.net/problem/14938

// 분류 : 최단 경로, 데이크스트라, 플로이드-워셜
// 성공 : 최단 경로, 다익스트라

// 9% 틀림 : BFS * n번
// 1 ~ n까지 BFS 진행 => vis 배열로 방문만 표시 queue<pair<int, int>> (현재까지의 거리, 정점) 으로 진행
// 틀린 이유 : 같은 지점을 연결하는 경로가 여러 개 있을 수도 있겠다고 생각함
// 만약 길이가 3인 경로가 1인 경로보다 먼저 들어오면 3인 경로를 먼저 타서 1인 경로는 vis 때문에 방문 못함

// 15% 틀림 : BFS * n번 + adj 인접 리스트 간선 기준으로 오름차순
// 위의 반례를 보고 adj 배열을 간선 오름차순으로 정렬 후 똑같이 BFS 진행
// 틀린 이유 : 정렬해봤자 아무 소용 없음
// 정렬을 이미 해서 vist[nxt] = true가 되기 때문에 더 짧은 경로 찾아도 방문 못하는 건 매한가지

// 성공 : 다익스트라 * n번
// 그냥 한 정점에서 다익스트라로 다른 정점 갈 수 있는 최단 거리 (dist 배열)를 구함
// 마지막에 dist를 순회하며 수색범위 (m) 보다 작거나 같으면 itemCnt 값을 더함

// 결론 : 그래프의 가중치가 있는 상태에서 최단 거리 구하는 거면 고민하지 말고 다익스트라 사용하자!

#include <bits/stdc++.h>

using namespace std;

// 정점 개수, 수색 범위, 길의 개수
int n, m, r;

// 각 지점별 아이템 개수
int itemCnt[101];

// <간선, 도착점>
vector<pair<int, int>> adj[101];
int ans = 0;

int Dijkstra(int x)
{
    int dist[101];
    fill(dist, dist + 101, INT_MAX);
    dist[x] = 0;

    priority_queue<pair<int, int>> pq;
    pq.push({dist[x], x});

    while(!pq.empty())
    {
        auto [curW, curV] = pq.top();
        pq.pop();

        for(auto [nxtW, nxtV] : adj[curV])
        {
            if(dist[nxtV] > dist[curV] + nxtW)
            {
                dist[nxtV] = dist[curV] + nxtW;
                pq.push({dist[nxtV], nxtV});

            }
        }
    }

    int ret = 0;
    for(int i = 1; i <= n; i++)
    {
        if(dist[i] <= m) ret += itemCnt[i];
    }
    
    return ret;
}

int main()
{
    ios::sync_with_stdio(0);
    cin.tie(0);

    cin >> n >> m >> r;

    for(int i = 1; i <= n; i++)
    {
        cin >> itemCnt[i];
    }

    for(int i = 0; i < r; i++)
    {
        int a, b, l;
        cin >> a >> b >> l;

        adj[a].push_back({l, b});
        adj[b].push_back({l, a});
    }

    // 각 지점에서 Dijkstra
    for(int i = 1; i <= n; i++)
    {
        ans = max(ans, Dijkstra(i));
    }

    cout << ans << "\n";
    
    return 0;
}