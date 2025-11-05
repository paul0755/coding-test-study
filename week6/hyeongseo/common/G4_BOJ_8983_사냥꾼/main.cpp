// https://www.acmicpc.net/problem/8983

// 분류 : 정렬, 이분탐색
// 성공 : 정렬, 이분탐색

// 성공 : 정렬, 이분탐색 -> 52ms
// 풀이 과정
// 0) 사대 x 사격 범위 (x-l ~ x+l), 동물 위치 입력 & 정렬
// 1) 전체 동물을 순회하며 해당 동물이 잡히는 사대 찾기
// 2) 사대를 찾는 과정을 이분탐색으로 진행

// 최적화 (챗지피티) : 정렬, 이분탐색 -> 48ms
// 동물이 사대에 잡힐 수 있는 조건 확인할 때 => 문제에 있는 |x - a| + b <= l로 확인하면 됨

// 총평 : 가능한 "최대", "최소"를 구하는 게 아니기 때문에 매개변수 탐색은 필요 없음!
// 매개 변수는 대개 정답 즉 여기선 "잡을 수 있는 동물의 개수"를 이분탐색 해야되나 자꾸 고민했는데
// "그냥 동물이 잡힐 수 있는 사대 찾기" 할때도 범위가 10만이라 이분 탐색 썼으면 됐음
// 이분 탐색을 어디 적용할지 빨리빨리 캐치하자!

#include <bits/stdc++.h>

using namespace std;

#define MAX 100001

vector<int> pos; // 사대 x좌표
vector<pair<int, int>> animals; // 동물 위치

// 사대 개수, 동물 수, 사정거리
int m, n, l;

int ans = 0;
int main()
{
    ios::sync_with_stdio(0);
    cin.tie(0);

    cin >> m >> n >> l;

    for(int i = 0; i < m; i++)
    {
        int x;
        cin >> x;

        pos.push_back(x);
    }

    for(int i = 0; i < n; i++)
    {
        int x, y;
        cin >> x >> y;

        animals.push_back({x, y});
    }

    sort(pos.begin(), pos.end());

    for(int i = 0; i < n; i++)
    {
        auto [curX, curY] = animals[i];

        // y값이 l보다 크면 절대 잡을 수 없음
        if(curY > l) continue;

        // 이분 탐색으로 현재 동물을 잡을 수 있는 사대 찾기
        int st = 0;
        int en = m - 1;

        while(st <= en)
        {
            int mid = (st + en) / 2;

            int posFrom = pos[mid] - l;
            int posTo = pos[mid] + l;

            // x범위가 해당 사대 밖이면 이동
            if(curX < posFrom) en = mid - 1;
            else if(curX > posTo) st = mid + 1;

            // x범위가 해당 사대 안이면 y 범위 확인
            else
            {
                if(abs(pos[mid] - curX) + curY <= l)
                {
                    ans++;
                    break;
                }
                
                // y값 사대 범위 밖이면 x값에 조정 후 다시 이분탐색 진행
                else if(curX <= pos[mid]) en = mid - 1;
                else st = mid + 1;
            }
        }
    }

    cout << ans << "\n";

    return 0;
}