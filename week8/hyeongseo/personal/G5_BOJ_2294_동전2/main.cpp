// https://www.acmicpc.net/problem/2294

// 분류 : DP

// 성공
// dp[i] : 동전의 합이 i일때 사용한 동전의 최소 개수
// 풀이 과정
// 1 ~ k까지 순회하며 가능한 동전의 합을 구함 => i
// coins[0] ~ coins[n-1]까지 순회하며 => 동전 i를 만들 수 있으면 dp 업데이트

// 총평 : 기본 문제

#include <bits/stdc++.h>

using namespace std;

int n, k;

int coins[101];

// dp[i] : 동전의 합이 i일때 사용한 동전의 최소 개수
int dp[10001];

int main()
{
    ios::sync_with_stdio(0);
    cin.tie(0);

    cin >> n >> k;

    for(int i = 0; i < n; i++)
    {
        cin >> coins[i];
    }

    // // 1 ~ k까지 순회하며 가능한 동전의 합 구하기
    for(int i = 1; i <= k; i++)
    {
        int tmp = INT_MAX;
        for(int j = 0; j < n; j++)
        {
            if(i - coins[j] < 0) continue; // 동전 하나의 값이 더 크면 패스
            if(dp[i - coins[j]] == INT_MAX) continue; // i - coins[j]가 만들 수 없는 조합이면 패스

            tmp = min(tmp, dp[i-coins[j]] + 1);
        }

        dp[i] = tmp;
    }

    // 갖고 있는 동전으로 k 못 만들면 -1 출력
    if(dp[k] == INT_MAX) dp[k] = -1;

    cout << dp[k] << "\n";

    return 0;
}