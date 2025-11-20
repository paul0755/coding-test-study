// https://www.acmicpc.net/problem/3665

// 분류 : 그래프, 방향 비순환 그래프 (DAG), 위상 정렬

// 위상 정렬 inDegree, outDegree : 25% 틀림
// 원인 : impossible인 경우에는 순위 출력 안해야 하는데 출력해버림
// 해결 : impossible 출력 후 continue로 아래 구문 더 이상 진행 X

// 분류 보고 성공 (위상 정렬 inDegree) : 12ms
// ?인 경우는 고려 안했는데 왜 정답 처리가 될까??

// 총평
// 아니 바킹독 DP 문제집 타고 들어갔는데 왜 DP가 아닌거임???
// 챗지피티가 DP 풀이는 100% 없다고 함
// 분류 보고 순위니까 위상 정렬 이해함
// 처음에 인접 리스트 다 생성할라고 했는데 그냥 inDegree만 고려하면 된다는 걸 깨달음
// 성공

#include <bits/stdc++.h>

using namespace std;

int tc, n, m;

int ranking[501]; // ranking[i] : 작년에 i 순위 차지한 팀의 번호

int num[501]; // 해당 팀의 작년 순위
int inDegree[501];

int main()
{
    ios::sync_with_stdio(0);
    cin.tie(0);

    cin >> tc;

    while(tc--)
    {
        cin >> n;

        for(int i = 1; i <= n; i++)
        {
            cin >> ranking[i];

            num[ranking[i]] = i;
            inDegree[ranking[i]] = i - 1;
        }

        cin >> m;

        for(int i = 0; i < m; i++)
        {
            int x, y;
            cin >> x >> y;

            if(num[x] < num[y])
            {
                inDegree[x]++;
                inDegree[y]--;
            }

            else
            {
                inDegree[x]--;
                inDegree[y]++;
            }
        }

        set<int> inDegreeSet;

        // <inDegree, 팀번호>
        vector<pair<int, int>> v;
        bool isPossible = true;
        bool isAmbiguous = false;

        for(int i = 1; i <= n; i++)
        {
            int cur = ranking[i];

            if(inDegreeSet.count(inDegree[cur]) > 0)
            {
                isPossible = false;
                break;
            }

            v.push_back({inDegree[cur], cur});
            inDegreeSet.insert(inDegree[cur]);
        }

        if(!isPossible)
        {
            cout << "IMPOSSIBLE\n";
            continue;
        }

        sort(v.begin(), v.end());

        for(int i = 0; i < (int)v.size(); i++)
        {
            auto [out, cur] = v[i];
            cout << cur << " ";
        }
        cout << "\n";
    }

    return 0;
}