// https://www.acmicpc.net/problem/1092

// 1% 틀림 : 우선순위 큐
// 풀이 과정
// 1) 크레인 무게, 박스 무게 내림차순으로 정렬 (박스는 우선순위 큐)
// 2) 크레인, 박스 인덱스 값 비교하며 크레인이 담을 수 있으면 크레인 인덱스++, 박스 인덱스++
// 3) 못 담으면 박스 인덱스 ++
// 4) 크레인이 끝나거나 박스가 끝나면 크레인 = 0, 박스는 안 담은 곳부터 다시 시작

// 틀린 이유
// 우선순위 큐에 담고 꺼내는 과정에서 누락되는 박스 발생
// pq.pop()은 무조건 하고 vector에 담아놨다가 다시 pq에 push하는 과정이 비효율적

// 성공 (챗지피티)
// 그리디 기준 : 현재 크레인이 들 수 있는 가장 무거운 박스를 찾아서 든다.
// 풀이 과정
// 1) 크레인, 박스 무게 내림차순으로 정렬
// 2) 박스가 빌 때까지 while 문 진행
// 2-1) 크레인 전체 순회하며 각 크레인이 들 수 있는 가장 무거운 박스 들기
// 2-1-1) 들 수 있는 박스 있으면 box.erase(box.begin() + idx)

// 총평
// 쉬운데 왜 못 풀었지
// '이미 실은 박스를 없애야 된다' 라는 것에 꽂혀서 냅다 우선순위 큐로 해버림
// 그런데 그냥 vector로 해도 erase()하면 사라지긴함
// 또또, 그리디 기준 잘못 잡음

#include <bits/stdc++.h>

using namespace std;

int n, m;

vector<int> weights; // 크레인 가능 무게
vector<int> boxes; // 박스 무게

int main()
{
    ios::sync_with_stdio(0);
    cin.tie(0);

    cin >> n;

    for(int i = 0; i < n; i++)
    {
        int x;
        cin >> x;

        weights.push_back(x);
    }

    cin >> m;

    for(int i = 0; i < m; i++)
    {
        int box;
        cin >> box;

        boxes.push_back(box);
    }

    // 크레인, 박스 무게 기준 내림차순
    sort(weights.begin(), weights.end(), greater<>());
    sort(boxes.begin(), boxes.end(), greater<>());

    int ans = 0;

    // 가장 무거운 크레인이 가장 무거운 박스 못 들면 전체 박스 실을 수 없음
    if(weights[0] < boxes[0])
    {
        cout << "-1\n";
        
        return 0;
    }

    while(!boxes.empty())
    {
        int idx = 0; // 박스 인덱스

        for(int i = 0; i < n; i++)
        {
            // 현재 크레인이 들 수 있는 박스 찾기
            while(idx < (int)boxes.size() && weights[i] < boxes[idx]) idx++;

            // 들 수 있는 박스 찾으면 제거
            if(idx < (int)boxes.size()) {
                boxes.erase(boxes.begin() + idx);
            }
        }

        ans++;
    }

    cout << ans << "\n";

    return 0;
}