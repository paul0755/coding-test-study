// https://www.acmicpc.net/problem/25515


// 분류 : DP, 트리 DP, DFS

/*
첫 시도 : 틀림
- dfs(cur) : cur - 자식까지 가는데 방문한 정수의 최대 합

틀린 부분
- {자식 안 가는 경우, 최대 자식 가는 경우, 모든 자식 가는 경우} + 현재 정점의 정수
=> 트리가 최대 2개의 자식만 가진다고 생각함
=> 자식이 4개일때 2개 자식만 가는 게 최대 경로이면 이 떄 트래킹 불가능

-------------------------------------------------------------------

두 번째 시도 : 32% 메모리 초과
- vector<ll> v : 현재 - 자식에 갔을 떄 가능한 정수의 합
- dp[i] : 1번째 자식부터 i번째 자식까지 봤을 때 가능한 정수의 최대 합

틀린 부분
- dfs()안에서 매번 dp[100001]을 생성 & 초기화 했기 때문에 메모리 초과 난 듯

--------------------------------------------------------------------

세 번째 시도 : 48ms 성공
- 굳이 dp 배열을 안 만들고 vector<ll> v를 순회하며
- sum = max(sum, sum + v[i]);
- return sum + weights[cur];

=> 정답

--------------------------------------------------------------------

최적화 (다른 사람 풀이) : 36ms
- vector<ll> 을 사용하지 않음
- 애초에 <부모, 자식> 입력이 들어오기 때문에 양방향 인접 리스트로 받을 필요 X

dfs()
- sum = weights[cur];
- sum = max(sum, sum + dfs(nxt));
- return sum;

--------------------------------------------------------------------

총평
- 트리 DP 기본 문제


*/
#include <bits/stdc++.h>

using namespace std;

#define MAX 100001

typedef long long ll;

int n;

vector<int> adj[MAX];

ll weights[MAX];

// bool vis[MAX];

// dfs(cur) : cur - 자식까지 가는데 방문 가능한 정수의 최대 합
ll dfs(int cur)
{
    ll sum = weights[cur];

    for(auto nxt : adj[cur])
    {
        sum = max(sum, sum + dfs(nxt));
    }

    return sum;
}

int main()
{
    ios::sync_with_stdio(0);
    cin.tie(0);

    cin >> n;

    for(int i = 0; i < n - 1; i++)
    {
        int p, c;
        cin >> p >> c;

        adj[p].push_back(c);
    }

    for(int i = 0; i < n; i++) cin >> weights[i];

    cout << dfs(0) << "\n";

    return 0;
}