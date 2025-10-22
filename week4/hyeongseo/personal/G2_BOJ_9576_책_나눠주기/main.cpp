// https://www.acmicpc.net/problem/9576

// 분류 : 그리디 + 이분 매칭??
// 성공 : 정렬 + 그리디

// 정렬 + 구현 : 25% 틀림 (시작 책 번호 기준 오름차순 정렬)
// 반례 발생
// 3 3 => 3
// 3 4 => 4
// 4 6 => 여기서 5 골라버림 (6 고르면 뒤에꺼도 되는데)
// 5 5 => 불가능

// 성공 (끝 책 번호 기준으로 오름차순 정렬)

// 총평 : 골드2에 정답률도 낮아서 쫄았는데 왜 이렇게 쉽게 풀리는지 모르겠음
// 문제 분류가 "이분 매칭"인데 이 알고리즘은 처음 봄 

#include <bits/stdc++.h>

using namespace std;

int n, m;

int t;

int ans = 0;

int main()
{
    ios::sync_with_stdio(0);
    cin.tie(0);

    cin >> t;

    while(t--)
    {
        ans = 0;
        cin >> n >> m;

        bool isUsed[1001];
        memset(isUsed, false, sizeof(isUsed));

        vector<pair<int, int>> v;

        for(int i = 0; i < m; i++)
        {
            int a, b;
            cin >> a >> b;

            v.push_back({b, a}); // 끝 책 번호 기준 오름차순으로 정렬
        }

        sort(v.begin(), v.end()); // 끝 책 번호 기준 오름차순으로 정렬

        for(auto [b, a] : v)
        {
            for(int i = a; i <= b; i++)
            {
                if(isUsed[i]) continue;

                isUsed[i] = true;
                ans++;
                break;
            }
        }

        cout << ans << "\n";
    }

    return 0;
}