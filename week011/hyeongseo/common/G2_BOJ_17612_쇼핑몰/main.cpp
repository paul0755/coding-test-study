// https://www.acmicpc.net/problem/17612

// 분류 : 자료구조, 우선순위 큐

// 챗지피티 보고 성공

// 60% 틀림

/*
문제 접근
- pq를 이용해
    - 계산대 <종료 시간, 계산대 번호, 회원 id> : 종료 시간 빠른 순, 계산대 번호 작은 순
    - 탈출 <종료 시간, 계산대 번호, 회원 id> : 종료 시간 빠른 순, 계산대 번호 큰 순

- 이렇게 하면 무난히 풀릴 줄 알았음

--------------------------------------------------------------------------------

60% 틀림 해결 (챗지피티)
- 초기에 k개의 계산대에 k명의 고객을 미리 배치해둠
- 여기서 항상 k >= n이라고 생각했는데
- k > n인 경우도 있음
- 따라서, min(n, k)로 바꾸니 통과됨

--------------------------------------------------------------------------------

총평
- 어렵지 않은 로직이었는데 딱 하나 때문에 못 푼게 너무 아쉬움
- 다음에 비슷한 문제 풀 때는 선입견은 버리고 문제의 수의 범위에 더 집중해봐야겠음
- 로직 자체는 어렵지 않았음

*/

#include <bits/stdc++.h>

using namespace std;

typedef long long ll;

int n, k;

// <회원 번호, 계산 시간>
vector<pair<int, int>> customers;

// 계산대는 종료 시간 빠른 순, 계산대 번호 빠른 순
struct counterCmp
{
    bool operator()(tuple<int, int, int> A, tuple<int, int, int> B)
    {
        auto [aEnd, aCounter, aId] = A;
        auto [bEnd, bCounter, bId] = B;

        // 종료시간 빠른 순
        if(aEnd != bEnd) return aEnd > bEnd;

        // 계산대 번호 작은 순
        return aCounter > bCounter;
    }
};
// <종료 시간, 계산대 번호, 회원 번호>
priority_queue<tuple<int, int, int>, vector<tuple<int, int, int>>, counterCmp> counters;

bool orderCmp(tuple<int, int, int> A, tuple<int, int, int> B)
{
    auto [aEnd, aCounter, aId] = A;
    auto [bEnd, bCounter, bId] = B;

    // 종료시간 빠른 순
    if(aEnd != bEnd) return aEnd < bEnd;

    // 계산대 번호 큰 순
    return aCounter > bCounter;
}

// <빠져나온 시간, 계산대 번호, 회원 번호>
vector<tuple<int, int, int>> orders;

int main()
{
    ios::sync_with_stdio(0);
    cin.tie(0);

    cin >> n >> k;

    for(int i = 0; i < n; i++)
    {
        int id, w;
        cin >> id >> w;
        
        customers.push_back({id, w});
    }

    // 초기에 k개 계산대에 k명 고객 배치
    int idx;
    for(idx = 0; idx < min(n, k); idx++)
    {
        auto [id, w] = customers[idx];
        counters.push({w, idx, id});
    }

    // 계산 로직 수행
    for(int i = idx; i < n; i++)
    {
        auto [curId, curW] = customers[i];
        
        // 들어갈 수 있는 계산대 찾기
        auto [endTime, counterNum, endId] = counters.top();
        counters.pop();

        // 계산 끝
        orders.push_back({endTime, counterNum, endId});
        // cout << counterNum << "에 " << endId << " 회원 계산끝 / 종료 시간 : " << endTime << "\n";
        

        // 다음 사람 계산대로
        counters.push({endTime + curW, counterNum, curId});
        // cout << counterNum << "에 " << curId << " 회원 계산 시작 / 종료 시간 : " << endTime + curW << "\n";
    }

    // 계산대에 남아있는 고객 처리
    while(!counters.empty())
    {
        auto [endTime, counterNum, endId] = counters.top();
        counters.pop();

        // 계산 끝
        orders.push_back({endTime, counterNum, endId});
    }

    // cout << "\n=======================\n";

    sort(orders.begin(), orders.end(), orderCmp);

    ll ans = 0;

    // 탈출 순서 기반으로 값 구하기
    for(int i = 0; i < n; i++)
    {
        auto [endTime, counterNum, id] = orders[i];
        ans += (ll)(i+1) * (ll)id;

        // cout << "탈출 순서 endTime: " << endTime << " counterNum: " << counterNum << " id: " << id << "\n";
    }

    cout << ans << "\n";

    return 0;
}