// https://www.acmicpc.net/problem/1897

// 분류 : 문자열, BFS, 집합과 맵

// 첫 시도 : BFS + unordered_set + 완탐 => 12 ms
// 풀이
// 1) BFS로 현재 문자열의 처음 ~ 끝 & a ~ z까지 무작정 문자열 삽입하며 새로운 문자열 만듦
// 2) 이 문자열이 사전에 있으면 방문 확인으로 dict.erase()하고 큐에 추가
// 3) 정답 문자열 길이 비교 후 업데이트
// => 시간 복잡도 1000 * 26 * 80이라서 약 200만이라서 그냥 시도했음
// => 비효율적

// 최적화 (챗지피티) : 정렬 + unorderd_set => 4ms
// Point) "현재 단어가 도달 가능한 단어인가?" 를 확인하는 것
// 풀이
// 1) 단어를 길이 오름차순으로 정렬
// 2) 사전 처음 ~ 끝까지 순회하며 단어의 처음 ~ 끝까지 문자 중 하나씩을 빼가면서 문자열을 생성
// 3) 새로운 문자열이 도달 가능하면 있음 => 현재 문자열은 도달 가능함!
// 4) 정답 문자열 업데이트 후 break
// 5) 정답 문자열 출력


// 23분 20초
#include <bits/stdc++.h>

using namespace std;

int d;
string s; // 시작 문자열
string ans; // 정답 문자열

vector<string> dict; // 사전
unordered_set<string> reachable; // 도달 가능한 문자열

bool cmp(string A, string B)
{
    return (int)A.length() < (int)B.length();
}

void findAns()
{
    sort(dict.begin(), dict.end(), cmp); // 사전 길이 오름차순으로 정렬

    reachable.insert(s); // 시작 문자열은 무조건 도달 가능

    for(auto word : dict)
    {
        for(int i = 0; i < (int)word.length(); i++)
        {
            // 현재 단어보다 한 글자 짧은 단어 중 도달 가능한 단어 있는지 확인하기
            string shorter = word.substr(0, i) + word.substr(i + 1);

            // 짧은 단어가 도달 가능 => 현재 단어도 도달 가능
            if(reachable.find(shorter) != reachable.end())
            {
                reachable.insert(word);

                if((int)ans.length() < (int)word.length()) ans = word;
                break;
            }
        }
    }
}
int main()
{
    ios::sync_with_stdio(0);
    cin.tie(0);

    cin >> d >> s;

    ans = s;

    for(int i = 0; i < d; i++)
    {
        string word;
        cin >> word;

        dict.push_back(word);
    }

    findAns();

    cout << ans << "\n";

    return 0;
}