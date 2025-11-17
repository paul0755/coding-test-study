// https://www.acmicpc.net/problem/2212

// 4% 틀림
// 풀이 과정
// - 각 좌표 순회하며 집중국 설치 한 곳과 현재 위치가 (전체 길이 / k)보다 크거나 같으면 집중국 설치함
// 생각해보니 균등하게 나눌 필요가 없음

// 성공 (챗지피티)
// 풀이 과정
// - 그리디의 기준 : 각 구간 중 거리 차이가 제일 많이 나는 구간을 집중국 설치해서 제거한다!
// - k개의 집중국이 설치되면 k-1개의 구간이 없어짐

// 잘못 생각한 것
// - 처음에 집중국이 양방향 커버된다고 이해했음 따라서, 거리가 2면 -2 ~ +2까지 커버 가능한 걸로
// - 균등하게 나눈 것이 최소의 거리를 만든다고 착각함

// 총평
// 원래 거리 차이가 가장 많이 나는 것을 그리디로 보면서 어떤 연산을 하려고 했는데 갑자기 균등하게 나눠버림
// "가장 차이가 많이 나는 구간"을 집중국 설치로 제거해나가야하는 것이 Point!
// 그리디를 풀려면 어떤 기준이 있어야 함 : "지금 가장 ~~ 한 것에 연산을 해야겠다"

// 다른 애들한테도 물어봐야지

// 62분;;

#include <bits/stdc++.h>

using namespace std;

typedef long long ll;

int n, k;

vector<int> v; // 센서 위치
vector<int> diff; // 센서 간 거리

int main()
{
    ios::sync_with_stdio(0);
    cin.tie(0);

    cin >> n >> k;

    for(int i = 0; i < n; i++)
    {
        ll x;
        cin >> x;
        
        v.push_back(x);
    }

    sort(v.begin(), v.end());

    // 각 센서 간 거리 계산
    for(int i = 1; i < n; i++)
    {
        diff.push_back(v[i] - v[i-1]);
    }

    // 정렬
    sort(diff.begin(), diff.end());

    // 집중국 k설치 시 k-1개의 구간이 없어짐
    // 따라서, 뒤에서부터 k-1개를 제거한 후, 앞에서부터 거리의 합을 계산하면 정답
    int ans = 0;
    for(int i = 0; i < (int)diff.size() - (k - 1); i++)
    {
        ans += diff[i];
    }

    cout << ans << "\n";
    return 0;
}