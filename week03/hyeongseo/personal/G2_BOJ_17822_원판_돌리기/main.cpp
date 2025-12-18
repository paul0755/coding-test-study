// https://www.acmicpc.net/problem/17822

// 분류 : 구현, 시뮬레이션

// 처음에 틀린 이유
// 인접하면서 같은 수를 삭제할 때 바로바로 삭제해서 조건에 맞아도 삭제가 안 되는 수가 있었음
// 따라서, set에 저장 후 한 번에 삭제하니까 됨 -> vector.push_back() 하면 중복 좌표 들어갈 수도 있음!

// 80분 26초
#include <bits/stdc++.h>

using namespace std;

// 원판 번호, 원판에 있는 숫자의 수, 회전 횟수
int n, m, t;

int board[53][53];

int ans = 0;

int dx[4] = {-1, 1, 0, 0};
int dy[4] = {0, 0, -1, 1};

// d : 0 => 시계 방향
// d : 1 => 반시계 방향
void rotate(int x, int d, int k)
{
    int tmp[53];

    for(int i = 0; i < m; i++)
    {
        tmp[i] = board[x][i];
    }

    int dir = d;

    // 시계 방향이면 진행 방향 -1
    if(dir == 0) dir = -1;

    for(int i = 0; i < m; i++)
    {
        int idx = (i + (dir * k)) % m;
        if(idx < 0) idx += m;

        board[x][i] = tmp[idx];
    }
}

void deleteNearNum()
{
    set<pair<int, int>> delPos;
    for(int i = 0; i < n; i++)
    {
        for(int j = 0; j < m; j++)
        {
            bool isDeleted = false;

            // 삭제된 수이면 패스
            if(board[i][j] == 0) continue;

            for(int dir = 0; dir < 4; dir++)
            {
                int nX = i + dx[dir];
                int nY = (j + dy[dir]) % m;

                if(nX < 0 || nX >= n || nY < 0 || nY >= m) continue;

                if(board[i][j] == board[nX][nY])
                {
                    delPos.insert({nX, nY});
                    isDeleted = true;
                }
            }

            if(isDeleted)
            {
                delPos.insert({i, j});
            }
        }
    }

    // 삭제되어야 할 수 삭제
    if((int)delPos.size() > 0)
    {
        for(auto [i, j] : delPos)
        {
            board[i][j] = 0;
        }

        return;
    }
    
    // 삭제해야 할 수 없다면 평균 구하기
    double avg = 0.0;
    int sum = 0;
    int cnt = 0;
    
    for(int i = 0; i < n; i++)
    {
        for(int j = 0; j < m; j++)
        {
            if(board[i][j] == 0) continue;

            sum += board[i][j];
            cnt++;
        }
    }
    

    // 평균보다 큰 수--, 작은 수++
    avg = (double)sum / (double)cnt;

    for(int i = 0; i < n; i++)
    {
        for(int j = 0; j < m; j++)
        {
            if(board[i][j] == 0) continue;

            if(board[i][j] > avg) board[i][j]--;
            
            else if(board[i][j] < avg) board[i][j]++;
        }
    }
}

int main()
{
    ios::sync_with_stdio(0);
    cin.tie(0);

    cin >> n >> m >> t;

    for(int i = 0; i < n; i++)
    {
        for(int j = 0; j < m; j++)
        {
            cin >> board[i][j];
        }
    }

    while(t--)
    {
        int x, d, k;
        
        cin >> x >> d >> k;

        // 0부터 시작해서 x만큼 ++ 하면 0부터 시작해도 x의 배수인 원판만 회전 가능
        for(int i = 0 + (x - 1); i < n; i += x)
        {
            rotate(i, d, k);
        }

        deleteNearNum(); // 인접한 수 있는지 확인 후 삭제
    }

    for(int i = 0; i < n; i++)
    {
        for(int j = 0; j < m; j++)
        {
            ans += board[i][j];
        }
    }

    cout << ans << "\n";

    return 0;
}