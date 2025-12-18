// https://www.acmicpc.net/problem/16120

// 분류 : 자료 구조, 문자열, 스택

// 첫 시도 : 1% 시간 초과
// 성공 : 16ms
// 풀이
// 1) v & stack에 현재 문자를 계속 push
// 2) stack 크기가 4 이상이면 마지막 4자리가 PPAP인지 검사 (v를 활용해)
// 3) PPAP 이면 v & stack pop()하고 p 삽입
// 4) stack에 남아있는 문자열이 PPAP or P이면 정답

// 최적화 (다른 사람 풀이 참고) stack 하나만 사용하기 : 12ms

// 근데 풀이 보니까 길이 4 이상이면 4번 pop() 하고 push() 해도 실행 시간 별로 차이 안 나는 듯

// 46분 54초

#include <bits/stdc++.h>

using namespace std;

string s;

stack<char> stk;

int main()
{
    ios::sync_with_stdio(0);
    cin.tie(0);

    cin >> s;

    int pCnt = 0; // 이전까지 P 나온 횟수
    for(int i = 0; i < (int)s.length(); i++)
    {
        if(s[i] == 'P')
        {
            if(pCnt >= 2 && stk.top() == 'A') // 현재 P 이고 이전까지 PPA 이면 => PPAP
            {
                for(int i = 0; i < 3; i++) stk.pop(); // PPA pop();

                if(!stk.empty() && stk.top() == 'P') pCnt = 1; // stk.top()이 P이면 pCnt = 1
                else pCnt = 0;
            }

            pCnt++;
        }

        else
        {
            if(pCnt < 2) pCnt = 0; // A 등장 전에 P가 2번 미만으로 나옴 => 불가능
            else if(!stk.empty() && stk.top() == 'A') pCnt = 0; // A 전에 A가 나옴 => 불가능
        }

        stk.push(s[i]);
        
    }

    string ans = "";
    while(!stk.empty())
    {
        ans += stk.top();
        stk.pop();
    }

    reverse(ans.begin(), ans.end());

    if(ans == "PPAP" || ans == "P") cout << "PPAP\n";
    else cout << "NP\n";
    
    return 0;
}