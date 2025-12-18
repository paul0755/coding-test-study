// https://www.acmicpc.net/problem/17404

// 분류 : DP

// 처음에 3차원 배열로 생각
// dp[i][j][k] : 1번집이 k로 칠해졌고 i번 집을 j색으로 칠했을 때 이전까지 모든 비용의 최솟값
// 이렇게 했을 때 dp 진행 과정에서 굉장히 꼬임

// 성공
// dp[i][j] : i번 집을 j색으로 칠했을 때 이때까지 비용의 최솟값
// 풀이 과정
// 시작점이 R, G, B일때 각각의 경우로 나눠 dp[i][j]를 3번 업데이트해서 최솟값 구함

// 총평
// 뭔가 각각의 단계에서 어디서 출발했는지 기억할 필요 없어서 3차원 배열은 필요 없었음
// 처음에만 기억하면 되서 따로 변수를 둬야하나 생각했는데 그냥 3번의 경우 각각 DP 연산 진행하면 됐었음
// 기억해야 할 정보가 있는데 각 단계별로 유지될 필요가 없다면 DP 연산을 여러 번 하는 풀이 생각해보기

#include <bits/stdc++.h>

using namespace std;

#define MAX 1000001
int n;

// cost[i][j] : i번째 집을 j색으로 칠했을 때 드는 비용
int cost[1001][3];

int main()
{
    ios::sync_with_stdio(0);
    cin.tie(0);

    cin >> n;

    for(int i = 0; i < n; i++)
    {
        for(int j = 0; j < 3; j++)
        {
            cin >> cost[i][j];
        }
    }

    int ans = MAX;

    // 시작 집이 R, G, B일때 각각 경우 나눠서 계산
    for(int st = 0; st < 3; st++)
    {
        int dp[1001][3];
        
        // dp 초기화
        for(int i = 0; i < n; i++)
        {
            for(int j = 0; j < 3; j++)
            {
                dp[i][j] = MAX;
            }
        }

        dp[0][st] = cost[0][st];

        // 이전 집과 칠한 색이 달라야 함
        for(int i = 1; i < n; i++)
        {
            dp[i][0] = min({dp[i-1][1], dp[i-1][2]}) + cost[i][0];
            dp[i][1] = min({dp[i-1][0], dp[i-1][2]}) + cost[i][1];
            dp[i][2] = min({dp[i-1][0], dp[i-1][1]}) + cost[i][2];
        }


        // n번 집은 1번 집과 색이 달라야 함
        for(int j = 0; j < 3; j++)
        {
            if(st == j) continue;
            ans = min(ans, dp[n-1][j]);
        }
    }

    cout << ans << "\n";

    return 0;
}