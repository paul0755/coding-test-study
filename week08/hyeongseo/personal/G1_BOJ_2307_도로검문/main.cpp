// https://www.acmicpc.net/problem/2307

// 분류 : 최단 경로, 다익스트라, 역추적

// 성공 : 88ms
// 풀이 과정
// 1) 다익스트라로 1 ~ n까지 최단 거리 구함, pre 배열을 통해 경로 구함
// 2) 경로에서 from, to 뽑아내고 이 경로를 막은 채로 다익스트라 진행 후 최단 거리 비교해 답 구하면 됨
#include <bits/stdc++.h>

using namespace std;

int n, m;

vector<pair<int, int>> adj[5001]; // 인접 리스트
int dist[1001]; // 최단거리 배열
int pre[1001]; // 역추적 배열

vector<pair<int, int>> checkEdges; // 최단 경로에 사용한 Edge 모음

int ans = -1;

// 다익스트라로 최단 거리, 최단 거리 경로 구함
void Dijkstra()
{
    // <현재까지 거리, 정점>
    priority_queue<pair<int, int>, vector<pair<int, int>>, greater<>> pq;

    fill(dist, dist + n + 1, INT_MAX);
    dist[1] = 0;

    memset(pre, -1, sizeof(pre));
    pre[1] = 0;

    pq.push({dist[1], 1});

    while(!pq.empty())
    {
        auto [curDist, cur] = pq.top();
        pq.pop();

        if(curDist > dist[cur]) continue;

        for(auto [nxtDist, nxt] : adj[cur])
        {
            if(dist[nxt] > curDist + nxtDist)
            {
                dist[nxt] = curDist + nxtDist;
                pq.push({dist[nxt], nxt});
                pre[nxt] = cur;
            }
        }
    }
}

// from - to 경로는 막은 채로 1 ~ n까지 최단 거리 구함
void DijkstraWithCheck(int from, int to)
{
    // <현재까지 거리, 정점>
    priority_queue<pair<int, int>, vector<pair<int, int>>, greater<>> pq;

    int tmpDist[1001];
    fill(tmpDist, tmpDist + n + 1, INT_MAX);
    tmpDist[1] = 0;

    pq.push({tmpDist[1], 1});

    while(!pq.empty())
    {
        auto [curDist, cur] = pq.top();
        pq.pop();

        if(curDist > tmpDist[cur]) continue;

        for(auto [nxtDist, nxt] : adj[cur])
        {
            // 검문 중이라 패스
            if((cur == from && nxt == to) || (cur == to && nxt == from)) continue;

            if(tmpDist[nxt] > curDist + nxtDist)
            {
                tmpDist[nxt] = curDist + nxtDist;
                pq.push({tmpDist[nxt], nxt});
                pre[nxt] = cur;
            }
        }
    }

    // 검문해서 도착 못하는 경우
    if(tmpDist[n] == INT_MAX) ans = INT_MAX;
    else ans = max(ans, tmpDist[n] - dist[n]);
}

int main()
{
    ios::sync_with_stdio(0);
    cin.tie(0);

    cin >> n >> m;

    for(int i = 0; i < m; i++)
    {
        int a, b, t;
        cin >> a >> b >> t;

        adj[a].push_back({t, b});
        adj[b].push_back({t, a});
    }

    Dijkstra();

    // 최단 경로에 사용한 Edges 구해서 저장
    int nxt = pre[n];
    checkEdges.push_back({n, pre[n]});
    while(pre[nxt] != -1)
    {
        checkEdges.push_back({nxt, pre[nxt]});
        nxt = pre[nxt];        
    }

    for(auto [a, b] : checkEdges)
    {
        DijkstraWithCheck(a, b);
    }

    if(ans == INT_MAX) ans = -1;

    cout << ans << "\n";

    return 0;
}