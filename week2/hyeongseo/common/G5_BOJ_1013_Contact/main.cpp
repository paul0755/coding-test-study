// https://www.acmicpc.net/problem/1013

// 분류 : 문자열, 정규 표현식

// 실패 : 정규식 표현 법 몰랐음
// 정규식 사용 => 27분 37초

// 정규식 사용 X : 10% 틀림
// 원인 : '0' 한 글자인 경우 반례 못 찾아서 예외 처리 못했음

#include <bits/stdc++.h>

using namespace std;

int t, n;

// regex 정규식 표현 사용해서 풀기
bool isMatchingRegex(string s)
{
    regex e("(100+1+|01)+"); // 정규 표현식
    return regex_match(s, e); // regex_match(target, 정규표현식)
}

// 정규식 표현 안 쓰고 풀기
bool isMatching(string s)
{
    int n = s.length();
    int cur = 0; // 현재 시작점

    while(cur < n)
    {
        // 0으로 시작
        if(s[cur] == '0')
        {
            // 00이면 실패
            // 10% 실패 원인 : '0'인 경우 바로 cur + 2 되서 true가 리턴됨
            // 해결 : 인덱스 범위 검사 추가 
            if(cur + 1 >= n || s[cur + 1] == '0') return false;

            // 01이면 가능
            else cur = cur + 2; // 시작점 옮기기
        }
        
        // 1로 시작
        else
        {
            // 다음 1의 위치 찾기
            // string.find('찾을 문자', 시작점) => 시작점부터 해당 문자 찾아 인덱스 리턴 (없으면 -1)
            int nxtOnePos = s.find('1', cur + 1);

            // 다음 1이 안 나오면 실패
            if(nxtOnePos == string::npos) return false;

            // 다음 1 사이에 0이 2개 미만으로 있으면 실패
            if(nxtOnePos - cur < 3) return false;

            // 다음 0 등장하는 위치 찾기
            int nxtZeroPos = s.find('0', nxtOnePos);

            // 더 이상 0이 등장 안하면 성공
            // 예) 100001111111111
            if(nxtZeroPos == string::npos) return true;

            // 0이 마지막에 등장하면 실패 : 0으로 끝나는 경우 존재 X
            if(nxtZeroPos == (s.length() - 1)) return false;

            // 00인 경우 0으로 시작하는 것 불가능
            // 다음 위치 = 현재 0의 위치 -1 (1인 곳)
            if(s[nxtZeroPos + 1] == '0')
            {
                cur = nxtZeroPos - 1;

                // 만약 다음 위치가 이전에 봤던 다음 1의 위치라면 1이 한 개여서 1에서 시작할 수 없어서 불가능
                if(cur == nxtOnePos) return false;
            }

            // 01인 경우 0으로 시작하는 것 가능
            // 다음 위치 = 01 다음 위치로 옮기기
            else if(s[nxtZeroPos + 1] == '1') cur = nxtZeroPos + 2;
        }
    }

    return true;
}

int main()
{
    ios::sync_with_stdio(0);
    cin.tie(0);

    cin >> t;

    while(t--)
    {
        string s;
        cin >> s;

        // 정규 표현식 사용하고 풀기
        // if(isMatchingRegex(s)) cout << "YES\n";
        // else cout << "NO\n";

        // 정규 표현식 사용하지 않고 풀기
        if(isMatching(s)) cout << "YES\n";
        else cout << "NO\n";
    }

    return 0;
}