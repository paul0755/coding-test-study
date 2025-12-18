// https://www.acmicpc.net/problem/6987

// 분류 : 브루트포스, 백트래킹

// 실패
// 11% 틀림

/*
문제 접근
- 전체 승 / 무 / 패 조건들만 확인해서 판별 가능한지 알았음
- But, 11% 틀림
- 따라서, 모든 경우의 수를 수행해야 한다는 걸 알았음

------------------------------------------------------------------------

백트래킹 접근
- 각각 경기마다 승 / 무 / 패 시나리오를 진행하고 마지막 경기까지 성공하면 => 가능한 케이스라고 판단함
- 챗지피티 보고 풂

------------------------------------------------------------------------

총평
- 백트래킹인데 "경기 정보"를 가지고 하는 것을 생각 못함
- 백트래킹 (나, 상대) 이렇게 2차원 배열로 가야하나?? 생각함
- 그런데 경기 정보<나, 상대> 이렇게 두고 1차원 배열로 진행하니 훨씬 수월해짐
- 챗지피티 없으면 못 풀었음
- 반성합시다!

*/
#include <bits/stdc++.h>

using namespace std;

int tc = 4;

int country[6][3]; // 각 국가의 <승, 무, 패>

int isPossible = 0;

vector<pair<int, int>> matches; // 각 경기마다 <나, 상대 팀>

// 백트래킹 들어가기 전에 전체 승 / 무 / 패 수로 일단 되는지 판단하기
int checkBefore()
{
    int winAll, drawAll, loseAll; // 전체 승 / 무 / 패 개수

    winAll = drawAll = loseAll = 0;
    for(int i = 0; i < 6; i++)
    {
        auto [w, d, l] = country[i];

        // 한 국가의 경기 수가 5번이 아닐 떄
        if(w + d + l != 5) return 0;

        winAll += w;
        drawAll += d;
        loseAll += l;
    }

    // 전체 경기 수가 30이 아닐 때
    if(winAll + drawAll + loseAll != 30) return 0;

    // 전체 승 != 전체 패일 떄
    else if(winAll != loseAll) return 0;

    // 전체 무승부 수가 홀수 일 때
    else if(drawAll % 2 != 0) return 0;

    return 1;
}

// 경기 정보를 기준으로 백트래킹
void checkIsPossible(int idx)
{
    if(idx >= (int)matches.size())
    {
        isPossible = 1;
        return;
    }

    if(isPossible == 1) return;

    auto [me, you] = matches[idx];

    // me 승
    if(country[me][0] > 0 && country[you][2] > 0)
    {
        country[me][0]--;
        country[you][2]--;

        checkIsPossible(idx+1);

        country[me][0]++;
        country[you][2]++;
    }

    // 무승부
    if(country[me][1] > 0 && country[you][1] > 0)
    {
        country[me][1]--;
        country[you][1]--;

        checkIsPossible(idx+1);

        country[me][1]++;
        country[you][1]++;
    }

    // you 승
    if(country[me][2] > 0 && country[you][0] > 0)
    {
        country[me][2]--;
        country[you][0]--;

        checkIsPossible(idx+1);

        country[me][2]++;
        country[you][0]++;
    }
}



int main()
{
    ios::sync_with_stdio(0);
    cin.tie(0);

    while(tc--)
    {
        isPossible = 0;
        matches.clear();
        for(int i = 0; i < 6; i++)
        {
            for(int j = 0; j < 3; j++)
            {
                cin >> country[i][j];
            }
        }

        // 승, 무, 패 개수로만 가능한지 사전 점검
        int beforeResult = checkBefore();
        if(beforeResult == 0)
        {
            cout << "0 ";
            continue;
        }

        // 경기 정보 저장
        for(int i = 0; i < 5; i++)
        {
            for(int j = i+1; j < 6; j++)
            {
                matches.push_back({i, j});
            }
        }

        // 백트래킹 수행
        checkIsPossible(0);

        cout << isPossible << " ";
    }

    cout << "\n";

    return 0;
}