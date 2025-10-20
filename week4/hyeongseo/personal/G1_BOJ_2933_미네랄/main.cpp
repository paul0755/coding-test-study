// https://www.acmicpc.net/problem/2933

// 분류 : 시뮬레이션, BFS
// 성공 : 시뮬레이션, BFS

// 성공 : 시뮬레이션 + BFS (28ms)
// 풀이 과정
// 1) 높이 입력 받아 왼쪽 차례면 왼쪽, 오른쪽 차례면 오른쪽에서 진행하며 미네랄 없애기
// 2) 미네랄 떨어뜨리기
// 2-1) BFS 진행하며 지나온 경로, 현재 클러스터에서 가장 바닥의 X좌표 계속 업데이트함
// 2-2) 만약 클러스터가 바닥과 닿아있으면 그대로 BFS 종료
// 2-3) 클러스터가 공중에 떠있다면 일단 해당 클러스터 모두 빈 칸으로 변경 후 한 칸씩 내려가며 떨어뜨릴 위치 찾기
// 2-4) 떨어뜨릴 위치 찾았으면 클러스터ㅏ 떨어뜨리고 종료

// 헤맨 부분
// 문제 이해를 잘못함
// 내가 이해한 거 : 높이가 주어질 떄마다 좌, 우 번갈아가며 던짐
// 실제 문제 : 높이가 주어질 때 왼쪽이 던질 차례면 왼쪽이, 오른쪽이 던질 차례면 오른쪽이 던짐
// 문제와 예제 입력을 잘 보자!!!

// 총평 : 문제 이해만 더 빨리 했다면 더 빨리 풀었을 문제
// 삼성에 데여서 좌표 떨구기 자주 풀다보니 쉽게 푼 것 같음

// 66분 3초

#include <bits/stdc++.h>

using namespace std;

int n, r, c;

char board[101][101];

bool vis[101][101];

int dx[4] = {-1, 1, 0, 0};
int dy[4] = {0, 0, -1, 1};

void printBoard()
{
    for(int i = 0; i < r; i++)
    {
        for(int j = 0; j < c; j++)
        {
            cout << board[i][j];
        }
        cout << "\n";
    }
}

// 공중에 있는 클러스터가 떨어졌으면 true
int BFS(int x, int y)
{
    vis[x][y] = true;

    queue<pair<int, int>> q;
    q.push({x, y});

    bool isAir = true; // 현재 클러스터가 공중에 떠있는지
    int maxX = x; // 현재 클러스터에서 가장 아래에 있는 지점의 x좌표

    vector<pair<int, int>> pos; // 지나온 경로
    pos.push_back({x, y});

    while(!q.empty())
    {
        auto [curX, curY] = q.front();
        q.pop();

        for(int i = 0; i < 4; i++)
        {
            int nX = curX + dx[i];
            int nY = curY + dy[i];

            if(nX < 0 || nX >= r || nY < 0 || nY >= c) continue;
            if(board[nX][nY] == '.') continue;
            if(vis[nX][nY]) continue;

            // 바닥에 닿는 부분이 있으면 공중에 있는 게 아님
            if(nX == r-1) isAir = false;

            maxX = max(maxX, nX); // 현재 클러스터 중 바닥 X 좌표 업데이트
            pos.push_back({nX, nY}); // 경로 저장

            vis[nX][nY] = true;
            q.push({nX, nY});
        }
    }

    // 공중에 떠 있지 않으면 끝
    if(!isAir) return false;

    // 공중에 떠있는 클러스터 빈 칸으로 변경
    for(auto [pX, pY] : pos)
    {
        board[pX][pY] = '.';
    }

    // 한 칸씩 내려가보며 클러스터 떨어질 위치 찾기
    int idx = 1;
    while(maxX + idx < r)
    {
        bool isPossible = true;
        for(auto [pX, pY] : pos)
        {
            if(board[pX + idx][pY] == 'x')
            {
                isPossible = false;
                break;
            }
        }

        if(!isPossible) break;
        idx++;
    }

    idx--; // 현재 idx에는 설치 못하므로 한 칸 위로

    // 클러스터 떨구기
    for(auto [pX, pY] : pos)
    {
        // cout << "pX: " << pX << " idx: " << idx << "\n";
        board[pX + idx][pY] = 'x';
    }

    return true;
}

// 미네랄 클러스터 떨구기
void drop()
{
    memset(vis, false, sizeof(vis));

    for(int i = 0; i < r; i++)
    {
        for(int j = 0; j < c; j++)
        {
            if(board[i][j] == '.') continue; // 빈 칸이면 패스
            if(vis[i][j]) continue; // 방문했으면 패스
            
            // cout << i << " " << j << "에서 시작\n";

            // 문제에서 2개 이상의 클러스터가 떨어지는 일은 없으므로 하나만 떨어지면 함수 종료
            if(BFS(i, j)) return;
        }
    }
}

int main()
{
    ios::sync_with_stdio(0);
    cin.tie(0);

    cin >> r >> c;

    for(int i = 0; i < r; i++)
    {
        string s;
        cin >> s;

        for(int j = 0; j < c; j++)
        {
            board[i][j] = s[j];
        }
    }

    cin >> n;

    // 헤맨 부분
    // 한 높이에 대해 좌, 우 한 번씩 던지는 게 아니라
    // 각 높이에 대해 좌 던질 차례, 우 던질 차례 나눠져 있는 거임
    for(int i = 0; i < n; i++)
    { 
        int h;
        cin >> h;

        // 좌표 변환
        int newH = r - h;

        // 왼쪽에서 던질 차례
        if(i % 2 == 0)
        {
            for(int j = 0; j < c; j++)
            {
                if(board[newH][j] == 'x')
                {
                    board[newH][j] = '.';
                    break;
                }
            }
        }

        // 오른쪽에서 던질 차례
        else
        {
            for(int j = c - 1; j >= 0; j--)
            {
                if(board[newH][j] == 'x')
                {
                    board[newH][j] = '.';
                    break;
                }
            }
        }

        // cout << "막대 던진 후 h: " << h << " newH: " << newH << "\n";
        // printBoard();

        drop();

        // cout << "떨군 후\n";
        // printBoard();
    }

    printBoard();

    return 0;
}

