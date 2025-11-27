// https://www.acmicpc.net/problem/1949

// 분류 : DP, 트리, 트리 DP

/*
일단 못 풀어서 분류 봐버림 -> 트리 DP

첫 번째 시도 : Bottom-Up DP
dp[i][0] : i가 우수 마을이 아닐 때 최대 주민 수
dp[i][1] : i가 우수 마을일 때 최대 주민 수

풀이 과정
- inDegree[i] == 0인 곳을 루트로 잡고 인접리스트 순회하며 트리 구조를 만듦
- dp[i][0] = dp[i-1][1];
- dp[i][1] = dp[i-1][0] + people[i];

=> 뭔가 트리를 따라하긴 했지만 이상하게 따라함

---------------------------------------------------------------

성공 : Top-Down DP (챗지피티)
dp[i][0] : i가 우수 마을이 아닐 때 최대 주민 수
dp[i][1] : i가 우수 마을일 때 최대 주민 수

풀이 과정
- 임의의 노드 (1번)를 루트로 잡고 dfs(1) 수행
- Top-Down DP : dfs()
  - dp[cur][1] = people[cur];
    - 현재 마을 우수 마을일 때 일단 현재 마을 사람 수 더하기

  - dp[cur][0] += max(dp[nxt][0], dp[nxt][1]);
    - 현재 마을 우수 마을 X => max(다음 마을 우수 마을 X, 다음 마을 우수 마을 O)
        
  - dp[cur][1] += dp[nxt][0];
    - 현재 마을 우수 마을 O => 다음 마을 우수 마을 X

- 정답 : max(dp[1][0], dp[1][1]);
---------------------------------------------------------------

총평
- dp 배열 구성은 맞았는데 아쉽

- 트리 DP는 무조건 Top-Down 방식
  - 트리는 방향성이 없어서 Bottom-Up처럼 선형적인 계산이 불가능함

- 임의의 정점을 루트로 잡아도 되는 이유
  - 트리에는 사이클 X, 모든 정점은 단 하나의 경로로 연결되므로 어떤 노드를 루트로 잡아도 트리 유지됨

- 생각보다 그렇게 트리 DP 그렇게 복잡하지 않은 듯
- 다음에 나올 때는 바로 Top-Down으로 시도해 봐야겠다

*/

#include <bits/stdc++.h>

using namespace std;

int n;

int people[10005];

vector<int> adj[10005];

// dp[i][0] : i가 우수마을이 아닐 때 최대 주민 수
// dp[i][1] : i가 우수마을일 때 최대 주민 수
int dp[10005][2];

bool vis[10005];

void dfs(int cur)
{
    vis[cur] = true;
    dp[cur][1] = people[cur];

    for(int nxt : adj[cur])
    {
        if(vis[nxt]) continue;

        dfs(nxt);

        // 현재 마을 우수 마을 X => max(다음 마을 우수 마을 X, 다음 마을 우수 마을 O)
        dp[cur][0] += max(dp[nxt][0], dp[nxt][1]);

        // 현재 마을 우수 마을 O => 다음 마을 우수 마을 X
        dp[cur][1] += dp[nxt][0];
    }
}

int main()
{
    ios::sync_with_stdio(0);
    cin.tie(0);

    cin >> n;

    for(int i = 1; i <= n; i++)
    {
        cin >> people[i];
    }

    for(int i = 1; i < n; i++)
    {
        int a, b;
        cin >> a >> b;

        adj[a].push_back(b);
        adj[b].push_back(a);
    }

    dfs(1); // 임의의 노드를 루트로

    int ans = max(dp[1][0], dp[1][1]);

    cout << ans << "\n";

    return 0;
}