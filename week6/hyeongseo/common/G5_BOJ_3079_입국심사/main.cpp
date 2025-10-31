// https://www.acmicpc.net/problem/3079

// 분류 : 이분탐색, 매개변수 탐색
// 성공 : 이분탐색, 매개변수 탐색

// 2% 틀림 & 시간 초과: pq
// 풀이 과정
// 1) pq(현재 심사대 끝나는 시간, 처리시간) 오름차순으로 정렬
// 2) pq.pop() 해가며 검사 받은 인원 --
// 3) 다시 끝나는 시간 + 처리시간 pq.push()
// m이 10억이기 때문에 바로 시간 초과 나버림~

// 6% 틀림 : 매개변수 탐색
// 원인 : isPossible() 함수 내에 심사할 수 있는 사람 수 cnt를 int로 뒀었음
// 해결 : int cnt => ll cnt로 변경

// 성공 (챗지피티) -> 48ms
// 풀이 과정
// 1) 매개 변수 탐색 변수 : "모든 사람들이 심사를 모두 마치게 되는 시간"
// 2) isPossible() : "target초 만에 모든 사람들이 심사 받을 수 있는가??"

// 총평 : 이분 탐색은 항상 "매개변수 탐색 함수"를 떠올리는 게 어려운 것 같음!!
// 어떻게 함수를 구성해야 내가 원하는 답을 얻을 수 있는지
// cnt += (x / target) => 이 코드 유용하니까 외워 놓기!

// 그리고 매개변수 탐색의 끝 값을 잘 정해야 할듯
// 이번 문제는 처음에 10억 * m 했더니 터짐
// 로직의 최악의 경우 or 아예 그냥 1e18 값을 끝값으로 세팅해보자
// 주의) INT_MAX나 LLONG_MAX는 연산시 값이 터지므로 이 값을 끝값으로 잡으면 안됨

#include <bits/stdc++.h>

using namespace std;

typedef long long ll;
#define MAX 1000001

int n, m;

ll t[MAX];

ll ans = 0;

ll minTime = LLONG_MAX;

// target초만에 모든 사람들이 심사받을 수 있는지?
bool isPossible(ll target)
{   
    ll cnt = 0;

    // target초 동안 검사할 수 있는 최대 인원 구하기

    // Point!) 이 코드 외워놓기!
    // 활용 예시) n초 동안 최대 몇 명의 인원 검사?? or n거리 안에 최대 몇 개의 건물 설치??
    for(int i = 0; i < n; i++)
    {
        cnt += (target / t[i]);
        if(cnt >= m) return true; // 이미 m명 넘어서면 return => 최적화 부분
    }

    return false;
}

int main()
{
    ios::sync_with_stdio(0);
    cin.tie(0);

    cin >> n >> m;

    for(int i = 0; i < n; i++)
    {
        cin >> t[i];
        minTime = min(minTime, t[i]);
    }
    
    // 매개 변수 탐색
    // 변수 : 모든 사람들이 심사를 모두 마치게 되는 시간

    // Point!) 매개변수 탐색에서 끝값을 어떻게 정할까?
    // 이 문제의 경우 가장 오래 걸리는 시간 => 심사 가장 빨리 끝나는 곳에 모든 사람이 심사받는 것
    // 따라서 t[i]의 최솟값 * 사람 수
    // 다른 사람 풀이 보니 그냥 1e18로 둔 사람도 있음
    ll st = 1;
    ll en = minTime * m;

    while(st <= en)
    {
        ll mid = (st + en) / 2;

        if(isPossible(mid))
        {
            ans = mid;
            en = mid - 1;
        }
        else st = mid + 1;
    }

    cout << ans << "\n";

    return 0;
}