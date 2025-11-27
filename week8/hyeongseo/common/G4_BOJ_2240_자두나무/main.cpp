// https://www.acmicpc.net/problem/2240

// 분류 : DP

// dp[i][j] : i초까지 j번 움직였을 때 받을 수 있는 자두의 최대 개수

// Point!
// 움직인 횟수 짝수 => 현재 1번 나무
// 움직인 횟수 홀수 => 현재 2번 나무

// dp[i][j] = max(dp[i-1][j], dp[i-1][j-1])
// 움직였을 때, 가만히 있었을 때 중 더 많은 자두 얻는 걸 저장함

// j가 짝수이고 orders[i] == 1이면 dp[i][j]++
// j가 홀수이고 orders[i] == 2이면 dp[i][j]++

// 총평
// 처음에 현재 서 있는 나무 위치도 저장해 dp를 3차원 배열로 돌릴려고 했음
// 하지만, 움직인 횟수로 트래킹 가능해서 안함
// 낫 배드

#include <bits/stdc++.h>

using namespace std;

int t, w;

int order[1001];

// dp[i][j] : i초까지 j번 움직였을 때 받을 수 있는 자두의 최대 개수
int dp[1001][31];


int main()
{
    ios::sync_with_stdio(0);
    cin.tie(0);

    cin >> t >> w;

    for(int i = 1; i <= t; i++)
    {
        cin >> order[i];
    }

    // dp 초기화
    if(order[1] == 1) dp[1][0] = 1;
    else dp[1][1] = 1;

    // 전체 시간 순회
    for(int i = 2; i <= t; i++)
    {
        dp[i][0] = dp[i-1][0];

        if(order[i] == 1) dp[i][0]++;

        // 움직이는 횟수 확인
        // 움직이는 횟수 : 짝수 => 현재 1번 나무
        // 움직이는 횟수 : 홀수 => 현재 2번 나무
        for(int j = 1; j <= i && j <= w; j++)
        {
            dp[i][j] = max(dp[i-1][j], dp[i-1][j-1]);

            if(j % 2 == 0 && order[i] == 1) dp[i][j]++;
            else if(j % 2 == 1 && order[i] == 2) dp[i][j]++;
        }
    }

    int ans = -1;
    for(int i = 0; i <= w; i++)
    {
        ans = max(ans, dp[t][i]);
    }

    cout << ans << "\n";

    return 0;
}