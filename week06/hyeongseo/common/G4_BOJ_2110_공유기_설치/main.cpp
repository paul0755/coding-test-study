// https://www.acmicpc.net/problem/2110

// 분류 : 이분탐색, 매개변수 탐색
// 성공 : 이분탐색, 매개변수 탐색

// 40% 틀림
// 원인 : 이분 탐색시 st < en 썼음
// 해결 : st <= en으로 변경

// 성공 : 이분탐색, 매개변수 탐색 -> 36mss
// 풀이 과정
// '가장 인접한 두 공유기 사이의 거리를 최대' => 매개변수 : 가장 인접한 두 공유기 사이의 거리
// 1) 1 ~ 1e18까지 가장 인접한 두 공유기 사이 업데이트하며 이분 탐색

// 총평 : 매개변수 탐색 기본 문제

// 성공

#include <bits/stdc++.h>

using namespace std;
typedef long long ll;

int n, c;

vector<ll> pos; // 집 위치

ll ans = 0;

// 매개변수 탐색
// 가장 인접한 두 공유기 사이의 거리가 limit일때 공유기를 c개 이상 설치할 수 있는지?
bool isPossible(ll limit)
{
    int cnt = 1;
    int last = 0;
    for(int i = 1; i < n; i++)
    {
        // 현재 공유기 설치 안 된 곳이 c보다 크면 현재 지점에 설치
        if(pos[i] - pos[last] >= limit)
        {
            cnt++;
            last = i;
        }
    }
    
    if(cnt >= c) return true;
    else return false;
}

int main()
{
    ios::sync_with_stdio(0);
    cin.tie(0);

    cin >> n >> c;

    for(int i = 0; i < n; i++)
    {
        int x;
        cin >> x;

        pos.push_back(x);
    }

    sort(pos.begin(), pos.end());

    // 이분 탐색
    ll st = 1;
    ll en = pos[n-1] - pos[0];
    
    while(st <= en)
    {
        ll mid = (st + en) / 2;

        if(isPossible(mid))
        {
            ans = mid;
            st = mid + 1;   
        }

        else en = mid - 1;
    }

    cout << ans << "\n";

    return 0;
}