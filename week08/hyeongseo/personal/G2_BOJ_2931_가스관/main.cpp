// https://www.acmicpc.net/problem/2931

// 분류 : 구현, 시뮬레이션

/*
3% 틀림

성공 (다른 사람 풀이)

풀이 과정
- 전체 칸 순회하며 빈 칸 발견 시
- 주위 인접 4방향 검사하며 연결에 필요한 파이프가 설치되어 있는지 확인
- 설치된 형태에 따라 파이프 설치 가능하면 출력하고 끝

시행착오

* 풀이 1
- 먼저 M에서 인접한 4방향 검사 후 파이프 발견 시 빈 칸까지 이동
- 빈 칸에서 가능한 파이프 설치 후 다시 Z까지 도착하는지 확인
=> 무조건 M근처에 파이프가 설치되어있다는 보장이 없음

----------------------------------------------------------

* 풀이 2
- M or Z에서 4방향 검사 후 파이프 발견 시 빈 칸까지 이동
- 빈 칸에서 가능한 파이프 설치 후 M or Z까지 도착하는지 확인
=> 이유는 모르겠는데 안됨

----------------------------------------------------------

총평
- 실제로 직접 이동하지 않아도 설치 가능한 파이프만 찾아면 쉽게 풀렸던 문제
- 이런 생각 도대체 어떻게 하는 거임??
- 뭔가 풀면서 점점 식이 복잡해지고 노가다로 가고 있다 => 내가 잘못 풀고 있다는 걸 느껴야함;;
- 어렵다

*/ 

#include <bits/stdc++.h>

using namespace std;

int r, c;

char board[30][30];

// 범위 밖인지 확인
bool isOOB(int x, int y)
{
    if(x < 0 || x >= r || y < 0 || y >= c) return true;
    return false;
}

// 끊긴 부분 찾기
void findEmpty()
{
    for(int i = 1; i <= r; i++)
    {
        for(int j = 1; j <= c; j++)
        {
            if(board[i][j] != '.') continue;

            // 빈 칸이면 주위 네 칸 보고 파이프 설치 가능한지 & 어떤 파이프 설치 가능한지 확인
            char up = board[i-1][j];
            bool isUp = false;
            if(up == '|' || up == '+' || up == '1' || up == '4') isUp = true;

            char down = board[i+1][j];
            bool isDown = false;
            if(down == '|' || down == '+' || down == '2' || down == '3') isDown = true;

            char left = board[i][j-1];
            bool isLeft = false;
            if(left == '-' || left == '+' || left == '1' || left == '2') isLeft = true;

            char right = board[i][j+1];
            bool isRight = false;
            
            if(right == '-' || right == '+' || right == '3' || right == '4') isRight = true;

            char ansShape = '?';

            if(isUp && isDown && isLeft && isRight) ansShape = '+';
            else if(isUp && isDown) ansShape = '|';
            else if(isLeft && isRight) ansShape = '-';
            else if(isDown && isRight) ansShape = '1';
            else if(isUp && isRight) ansShape = '2';
            else if(isUp && isLeft) ansShape = '3';
            else if(isDown && isLeft) ansShape = '4';

            if(ansShape != '?')
            {
                cout << i << " " << j << " " << ansShape << "\n";
                return;
            }
        }
    }
}

int main()
{
    ios::sync_with_stdio(0);
    cin.tie(0);

    cin >> r >> c;

    for(int i = 1; i <= r; i++)
    {
        string s;
        cin >> s;

        for(int j = 1; j <= c; j++)
        {
            board[i][j] = s[j-1];
        }
    }

    findEmpty();

    return 0;
}

