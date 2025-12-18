// https://www.acmicpc.net/problem/20183

// 분류 : 이분 탐색, 최단 경로, 다익스트라, 매개 변수 탐색

// 31% SegFault
// 원인 : money 입력 범위 10^14인데 int로 받았었음

// 시도 : 다익스트라 => (32 / 43) -> 300ms

// 성공 (챗지피티) : 다익스트라 + 이분탐색 + 매개변수 탐색 -> 836ms
// 매개변수 탐색 : 시작 ~ 도착점까지의 최대 거리가 mid일 때 시작 ~ 도착까지 money 이하로 도착할 수 있는가?

// 총평 : 순회해야할 값이 엄청 크다면 이분탐색 풀이 떠올리자!
// "매개 변수 탐색"은 정답이 될 변수로 진행하면 됨!
// 이번 문제에서는 "시작 ~ 도착점까지의 최단 거리" 가 아니라 "시작 ~ 도착점 경로 중 최댓값"을 매개 변수로 놓고 되는지 / 안되는지 검사
// 어려웠음

#include <bits/stdc++.h>

using namespace std;

#define MAX 100001
typedef long long ll;

int n, m, st, en;
ll money;

vector<pair<ll, int>> adj[MAX];

ll dist[MAX];
ll ans = -1;

ll maxEdge = 0; // 간선 중 최댓값

// 지나가는 경로가 limit보다 크면 안됨
bool check(ll limit)
{
    fill(dist, dist + n + 1, LLONG_MAX);

    // <현재까지 쓴 돈, 현재 위치>
    priority_queue<tuple<ll, int>, vector<tuple<ll, int>>, greater<>> pq;
    
    dist[st] = 0;
    pq.push({dist[st], st});

    while(!pq.empty())
    {
        auto [curDist, cur] = pq.top();
        pq.pop();

        if(dist[cur] < curDist) continue;

        for(auto [nxtDist, nxt] : adj[cur])
        {
            if(nxtDist > limit) continue; // 다음 가야할 길이 limit보다 크면 못 감
            if(dist[nxt] > curDist + nxtDist)
            {
                dist[nxt] = curDist + nxtDist;
                pq.push({dist[nxt], nxt});
            }
        }
    }

    return dist[en] <= money; // 도착점까지 현재 보유한 돈으로 도착 가능한 지 확인

}

int main()
{
    ios::sync_with_stdio(0);
    cin.tie(0);

    cin >> n >> m >> st >> en >> money;

    for(int i = 0; i < m; i++)
    {
        int from, to;
        ll c;

        cin >> from >> to >> c;

        adj[from].push_back({c, to});
        adj[to].push_back({c, from});
        maxEdge = max(maxEdge, c); // 간선 중 최댓값
    }

    ll l = 0, r = maxEdge;

    // 이분 탐색
    while(l <= r)
    {
        ll mid = (l + r) / 2;

        // 매개 변수 : 경로 중 최댓값의 최솟값
        // 만약 수치심이 mid일 때 현재 보유한 돈으로 시작 ~ 도착점까지 갈 수 있나?? 확인
        if(check(mid))
        {
            ans = mid;
            r = mid - 1;
        }
        else l = mid + 1;
    }

    cout << ans << "\n";

    return 0;
}