// https://www.acmicpc.net/problem/1826

// 분류 : 그리디, 정렬, 우선순위 큐

// 3% 틀림 : BFS
// pair<현재 인덱스, 현재 연료량> 으로 두고 풂
// 문제점
// 1) dist[nxt] != -1이면 이미 방문으로 간주해 재방문 X
// - 실제로는 더 많은 연료를 가진 채로 같은 주유소에 도착할 수 있음

// 성공 (챗지피티)
// 그리디 기준 : "현재 연료로 갈 수 있는 모든 주유소 중에서 최대 연료를 주는 주유소 방문"

// 총평
// 그리디는 무조건 '진행 기준' 정하기!!
// 현재 상황에서 무엇을 우선으로 봐야하는지 계속 생각하는 연습 필요
// 단순한데 어렵네

#include <bits/stdc++.h>

using namespace std;

int n;
int l, p; // 마을까지의 거리, 현재 연료 양

vector<pair<int, int>> station; // {거리, 연료}

int main()
{
    ios::sync_with_stdio(0);
    cin.tie(0);

    cin >> n;

    for(int i = 0; i < n; i++)
    {
        int a, b;
        cin >> a >> b;

        station.push_back({a, b});
    }

    cin >> l >> p;

    sort(station.begin(), station.end());

    // 지금 갈 수 있는 주유소 중에서 "연료 제일 큰 곳"을 고르기 위함
    priority_queue<int> pq;

    int cnt = 0; // 주유 횟수
    int curFuel = p; // 현재 연료 (현재 연료로 도달 가능한 최대 거리)
    int idx = 0; // 주유소 인덱스

    while(curFuel < l)
    {
        while(idx < (int)station.size() && station[idx].first <= curFuel) {
            pq.push(station[idx].second);
            idx++;
        }

        // 갈 수 있는 주유소 없으면 마을 도착 불가능
        if(pq.empty())
        {
            cnt = -1;
            break;
        }

        // 연료 가장 많이 주는 주유소에서 주유
        // 연료를 직접 소모 X => 계속 더해서 '0 ~ curFuel까지 도달 가능하다'로 판단
        curFuel += pq.top();
        pq.pop();
        cnt++;
    }

    cout << cnt << "\n";

    return 0;
}