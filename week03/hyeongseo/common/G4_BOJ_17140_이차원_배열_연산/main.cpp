// https://www.acmicpc.net/problem/17140

// 분류 : 구현, 정렬, 시뮬레이션

#include <bits/stdc++.h>

using namespace std;

int board[105][105];

int r, c, k;
int n = 3, m = 3; // 현재 행 크기, 열 크기

int ans = 0;

void calR()
{
    int newM = m;
    for(int i = 0; i < n; i++)
    {
        map<int, int> tmp;
        for(int j = 0; j < m; j++)
        {
            if(board[i][j] == 0) continue;

            int cur = board[i][j];
            board[i][j] = 0; // 원래 값 날리기
            tmp[cur]++;
        }

        // <등장 횟수, 수>
        vector<pair<int, int>> v;
        for(auto [num, cnt] : tmp)
        {
            v.push_back({cnt, num});
        }

        sort(v.begin(), v.end());

        int idx = 0;
        for(int j = 0; j < (int)v.size(); j++)
        {
            auto [cnt, num] = v[j];

            board[i][idx++] = num;
            board[i][idx++] = cnt;

            // 100개 넘으면 버림
            if(idx > 100) break;
        }

        if(idx > newM) newM = idx;
    }

    m = newM;
    
}

void calC()
{
    int newN = n;
    for(int i = 0; i < m; i++)
    {
        map<int, int> tmp;
        for(int j = 0; j < n; j++)
        {
            if(board[j][i] == 0) continue;

            int cur = board[j][i];
            board[j][i] = 0; // 원래 값 날리기
            tmp[cur]++;
        }

        // <등장 횟수, 수>
        vector<pair<int, int>> v;
        for(auto [num, cnt] : tmp)
        {
            v.push_back({cnt, num});
        }

        sort(v.begin(), v.end());

        int idx = 0;
        for(int j = 0; j < (int)v.size(); j++)
        {
            auto [cnt, num] = v[j];

            board[idx++][i] = num;
            board[idx++][i] = cnt;

            // 100개 넘으면 버림
            if(idx > 100) break;
        }

        if(idx > newN) newN = idx;
    }

    n = newN;
}

int main()

{
    ios::sync_with_stdio(0);
    cin.tie(0);

    cin >> r >> c >> k;

    // 0-indexed로 변경
    r--; 
    c--;

    for(int i = 0; i < 3; i++)
    {
        for(int j = 0; j < 3; j++)
        {
            cin >> board[i][j];
        }
    }

    while(ans <= 100)
    {
        if(board[r][c] == k) break;

        // r 연산
        if(n >= m) calR();
        
        // c 연산
        else calC();
        
        ans++;
    }

    if(ans > 100) ans = -1;
    cout << ans << "\n";

    return 0;
}