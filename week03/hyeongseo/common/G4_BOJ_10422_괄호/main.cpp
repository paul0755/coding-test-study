// https://www.acmicpc.net/problem/10422

// 분류 : 수학, DP, 조합
// 25% 틀림

// 시도 : 괄호 안에 있는 괄호 수 기준으로 조합, DP 진행
// 조합 : 1C1 ~ 5000C5000까지 구하기
// DP : 괄호 안에 괄호가 0개 ~ (i/2-1)까지 있을 때 구하기
// 틀림

// 성공 (챗지피티) : 카탈란 수 => 앞쪽 괄호에 따라 나뉘는 두 부분 괄호 문자열 곱의 합
// dp[n] = dp[k] * dp[n - 2 - k] (k = 0 ~ n-2)


#include <bits/stdc++.h>

using namespace std;

#define MOD 1000000007
typedef long long ll;

int t;

// dp[i] : 길이가 i인 올바른 괄호 문자열의 개수
ll dp[5010];

int main()
{
    ios::sync_with_stdio(0);
    cin.tie(0);

    cin >> t;

    dp[0] = 1; 
    
    for(int i = 2; i <= 5000; i += 2)
    {
        for(int j = 0; j <= i - 2; j += 2)
        {
            dp[i] += dp[j] * dp[i - 2 - j];
            dp[i] %= MOD;
        }
    }

    while(t--)
    {
        int l;
        cin >> l;
        cout << dp[l] << "\n"; 
    }

    return 0;
}