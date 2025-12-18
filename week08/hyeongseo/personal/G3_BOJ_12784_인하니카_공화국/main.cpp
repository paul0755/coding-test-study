// https://www.acmicpc.net/problem/12784

// 분류 : DP, 트리 DP, DFS

/*

22% 틀림
- dp[i] : i까지 오는 데에 폭파 가능한 최소 다이너마이트 수
- dfs()
    - dp[nxt] = min(dp[nxt], cnt);
    - dfs(nxt);
    - sum += dp[nxt];

    - if(sum > 0) dp[cur] = min(dp[cur], sum);

=> 암튼 틀림;

------------------------------------------------------

성공 (다른 사람 풀이 + 챗지피티) : 12ms
- dp 배열 사용 X
- int dfs(cur) : dfs(cur) => cur - 자식들 분리시키기 위한 최소 비용
    - if(dynamite[cur].size() == 1 && cur != 1) return INT_MAX;
    - sum += min(dfs(nxt), cnt); // 자식의 자식에서 온 경로 vs 현재-자식 잇는 경로 중 최솟값 선택


------------------------------------------------------

총평
- 다리가 1개만 연결된 = 리프 노드 = 트리 DP 까지는 떠올림
- 항상 dp배열을 만들 생각만 했는데 Top-Down에서 배열 없이 리턴값으로만 DP 진행할 수 있다는 걸 깨달음
- DP는 어렵다
- 일단 DP[i] or dfs()가 무엇을 뜻하는지 정확히 정의하고 넘어가자!!

*/
#include <bits/stdc++.h>

using namespace std;

int t, n, m;

vector<pair<int, int>> dynamite[1005];

bool vis[1005];

// dfs(cur) : cur - 자식들 분리시키기 위한 최소 비용
int dfs(int cur)
{
    // 리프 노드일 때는 자식이 없으므로 리프 노드 - 자식을 분리할 수 없어 MAX값 리턴
    if(dynamite[cur].size() == 1 && cur != 1) return INT_MAX;

    vis[cur] = true;

    int sum = 0;
    for(auto [cnt, nxt] : dynamite[cur])
    {
        if(vis[nxt]) continue;

        sum += min(dfs(nxt), cnt); // 자식의 자식에서 온 경로 vs 현재-자식 잇는 경로 중 최솟값 선택
    }

    return sum;
}

int main()
{
    ios::sync_with_stdio(0);
    cin.tie(0);

    cin >> t;

    while(t--)
    {
        cin >> n >> m;

        for(int i = 0; i <= n; i++)
        {
            vis[i] = false;
            dynamite[i].clear();
        }

        for(int i = 0; i < m; i++)
        {
            int a, b, d;
            cin >> a >> b >> d;

            dynamite[a].push_back({d, b});
            dynamite[b].push_back({d, a});
        }

        cout << dfs(1) << "\n";
    }

    return 0;
}