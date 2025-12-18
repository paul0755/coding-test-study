// https://www.acmicpc.net/problem/1041

#include <bits/stdc++.h>

using namespace std;

typedef long long ll;

int n;

ll dice[6];

// 1면이 최소 : 5(n-2)^2 + 4(n-2)
// 인접한 2면이 최소일 때의 합 : n^2*5 - (1면이 최소 + 3면이 최소)
// 인접한 3면의 최소일 때의 합 : 3 * 4 = 12 
ll min1 = LLONG_MAX, min2 = LLONG_MAX, min3 = LLONG_MAX;

ll ans = 0;

int main()
{
    ios::sync_with_stdio(0);
    cin.tie(0);

    cin >> n;

    for(int i = 0; i < 6; i++)
    {
        cin >> dice[i];
        min1 = min(min1, dice[i]);
    }

    // 인접한 2면의 최솟값 구하기
    for()
    {

    }







    
    return 0;
}