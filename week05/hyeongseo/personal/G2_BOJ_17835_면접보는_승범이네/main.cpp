// https://www.acmicpc.net/problem/17835

// 1% 시간 초과 -> 다익스트라 * n번
// 처음에 당연히 면접장 -> 도시로 가는 최단 경로 중 최댓값을 구하면 된다고 생각함
// 하지만, 망설였던 점
// 1) 그래프가 양방향이 아니라서 현재의 그래프로 면접장 -> 도시로 갈 수 없음
// 2) 설령 진행하더라도, 출발점이 여러 개이기 때문에 다익스트라를 여러 번 해야 한다고 생각했음

// 진실
// 1) 실제로 면접장 -> 도시로 가야하는 건 맞음 => 따라서, "역방향 그래프"를 생성해서 다익스트라를 진행한다.
// 2) 출발점이 여러개인 다익스트라 -> 그냥 첫 시작을 pq에 여러 개 넣는다면 dist 배열은 이렇게 만들어진다.
// 만약 출발점이 0, 1, 2라면 dist[i] = "0, 1, 2 세 곳 중 한 곳에서 출발했을 때 최단거리"
// 자연스럽게 다익스트라 진행 시 최단 거리를 구하기 때문에 쉽게 할 수 있었음!!

// 81% 시간 초과 -> 다익스트라 with 역방향 그래프
// 다익스트라 내에 if(dist[cur] < curDist) continue; 추가
// 이때까지 curDist와 dist[cur]은 항사 같은 값을 가진다고 생각해서 항상 코드에서 저 부분을 작성 안 함!
// 먼저 큐에 들어가고 이후에 해당 최단 거리가 바뀌었으면 현재 정점에서 다익스트라 안해도 되므로 continue
// 앞으로 이 부분 놓치지 말고 다익스트라 시 꼭 작성하기!!

// 83% 틀림
// dist long long 인데 INT_MAX로 초기화했었음

// 총평 : 출발점이 여러개더라도 다익스트라 진행 가능!, curDist와 dist[cur]은 달라질 수 있음!!

#include <bits/stdc++.h>

using namespace std;

typedef long long ll;
#define MAX 100001

int n, m, k;

vector<pair<ll, int>> adj[MAX];

priority_queue<pair<ll, ll>, vector<pair<ll, ll>>, greater<>> pq; // 다익스트라 우선순위 큐
ll dist[MAX]; // 면접장에서 출발했을 때 각 정점까지 갈 수 있는 최단 거리

void Dijkstra()
{
    while(!pq.empty())
    {
        auto [curDist, cur] = pq.top();
        pq.pop();

        if(dist[cur] < curDist) continue;

        for(auto [nxtDist, nxt] : adj[cur])
        {
            if(dist[nxt] > curDist + nxtDist)
            {
                dist[nxt] = curDist + nxtDist;
                pq.push({dist[nxt], nxt});
            }
        }
    }
}

int main()
{
    ios::sync_with_stdio(0);
    cin.tie(0);

    cin >> n >> m >> k;

    for(int i = 0; i < m; i++)
    {
        int u, v;
        ll c;

        cin >> u >> v >> c;

        // 역방향 그래프를 생성해야 면접장 -> 도시까지의 최단 거리 구할 수 있음
        adj[v].push_back({c, u});
    }

    fill(dist, dist + n + 1, LLONG_MAX); // 최단거리 배열 초기화

    // 면접 장소 저장
    for(int i = 0; i < k; i++)
    {
        int x;
        cin >> x;

        dist[x] = 0;
        pq.push({dist[x], x});
    }

    Dijkstra();

    ll ans = 0;
    ll ansIdx = 0;

    for(int i = 1; i <= n; i++)
    {
        if(ans < dist[i])
        {
            ansIdx = i;
            ans = dist[i];
        }
    }

    cout << ansIdx << "\n";
    cout << ans << "\n";

    return 0;
}