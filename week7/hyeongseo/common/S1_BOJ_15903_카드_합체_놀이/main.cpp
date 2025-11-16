// https://www.acmicpc.net/problem/15903

// 분류 : 그리디, 우선순위 큐
// 성공 : 그리디, 우선순위 큐

// 1% 틀림
// 원인 : int
// 해결 : long long

// 풀이 과정
// - 우선순위 큐 써서 가장 작은 두 개 pop() 이후 두 개 더한 값 push() 2번 진행


#include <bits/stdc++.h>

using namespace std;

typedef long long ll;

int n, m;

priority_queue<ll, vector<ll>, greater<>> pq;

int main()
{
    ios::sync_with_stdio(0);
    cin.tie(0);

    cin >> n >> m;

    for(int i = 0; i < n; i++)
    {
        ll x;
        cin >> x;

        pq.push(x);
    }

    while(m--)
    {
        ll x = pq.top();
        pq.pop();

        ll y = pq.top();
        pq.pop();

        ll z = x + y;
        pq.push(z);
        pq.push(z);
    }

    ll ans = 0;
    while(!pq.empty())
    {
        ans += pq.top();
        pq.pop();
    }

    cout << ans << "\n";

    return 0;
}