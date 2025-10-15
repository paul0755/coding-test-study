// https://www.acmicpc.net/problem/2800

// 분류 : 문자열, 브루트포스, 비트마스킹, 스택
// 성공 : 문자열, 백트래킹, 스택

#include <bits/stdc++.h>

using namespace std;

set<string> ans; // 정답

string s;

stack<int> stk; // 괄호 확인 스택
vector<pair<int,int>> v; // 괄호 <시작, 끝>

bool isDeleted[11]; // 해당 번호의 괄호가 제거되었는가?

// 백트래킹으로 모든 괄호쌍 지우는 경우의 수 탐색
void GetAns(int cur)
{
    if(cur >= (int)v.size())
    {   
        set<int> pos; // 지워질 괄호 위치 저장
        for(int i = 0; i < (int)v.size(); i++)
        {
            if(isDeleted[i])
            {
                auto [st, en] = v[i];
                pos.insert(st);
                pos.insert(en);
            }
        }

        // 지울 괄호가 없으면 패스
        if(pos.empty()) return;

        // 괄호 제거한 문자열 생성
        string newS = "";
        for(int i = 0; i < (int)s.length(); i++)
        {
            if(pos.count(i) > 0) continue;
            newS += s[i];
        }

        // 같은 문자열이 들어갈 수 있기 때문에 set으로 저장
        ans.insert(newS);
        return;
    }

    // 현재 괄호 지우기
    isDeleted[cur] = true;
    GetAns(cur+1);

    // 현재 괄호 유지
    isDeleted[cur] = false;
    GetAns(cur+1);
}

int main()
{
    ios::sync_with_stdio(0);
    cin.tie(0);

    cin >> s;

    // 괄호쌍 찾기
    for(int i = 0; i < (int)s.length(); i++)
    {
        if(s[i] == '(') stk.push(i);
        else if(s[i] == ')')
        {
            v.push_back({stk.top(), i});
            stk.pop();
        }
    }

    GetAns(0);

    for(auto word : ans)
    {
        cout << word << "\n";
    }

    return 0;
}