// https://www.acmicpc.net/problem/21609
// 분류 : 구현, 시뮬레이션, BFS
// 성공 : 72분

#include <bits/stdc++.h>

using namespace std;

int dx[4] = {-1, 1, 0, 0};
int dy[4] = {0, 0, -1, 1};

int n, m;

// 빈 칸 : -2
// 검정 : -1
// 무지개 : 0
// 일반 : 1 ~ m
int board[21][21];

int remain = 0; // 남아 있는 블록 개수
int score = 0; // 점수

// <기준 블록 x, 기준 블록 y, 그룹 내 블록 개수, 무지개 블록 개수>
tuple<int, int, int, int> largestGroup;
vector<pair<int, int>> largestGroupPath; // 가장 큰 그룹의 블록 위치

bool vis[21][21];

// 반시계 방향 90도 회전
void rotate90()
{
    int tmp[21][21];
    memcpy(tmp, board, sizeof(board));

    for(int i = 0; i < n; i++)
    {
        for(int j = 0; j < n; j++)
        {
            board[n - j - 1][i] = tmp[i][j];
        }
    }
}

// 그룹 생성
void makeGroup(int x, int y, int color)
{
    bool groupVis[21][21];
    memset(groupVis, false, sizeof(groupVis));

    queue<pair<int, int>> q;
    q.push({x, y});
    vector<pair<int, int>> tmpPath; // 현재 그룹 내에 포함된 블록 위치

    tmpPath.push_back({x, y});
    vis[x][y] = true;
    groupVis[x][y] = true;

    int rainbowCnt = 0; // 그룹에 속한 무지개블록 개수
    int groupSz = 1; // 그룹에 속한 블록 개수

    // 현재 그룹의 기준점 블록 좌표
    int sX = x;
    int sY = y;

    while(!q.empty())
    {
        auto [curX, curY] = q.front();
        q.pop();

        for(int i = 0; i < 4; i++)
        {
            int nX = curX + dx[i];
            int nY = curY + dy[i];

            if(nX < 0 || nX >= n || nY < 0 || nY >= n) continue; // 범위 벗어나면 패스
            if(board[nX][nY] == -2) continue; // 빈 칸이면 패스
            if(board[nX][nY] == -1) continue; // 검은색 블록이면 패스
            if(board[nX][nY] >= 1 && board[nX][nY] != color) continue; // 무지개가 아니고 현재 블록과 색이 다르면 패스
            if(groupVis[nX][nY]) continue; // 이미 방문한 곳이면 패스
            if(board[nX][nY] == 0) rainbowCnt++; // 그룹에 포함된 무지개 블록 개수++
            
            tmpPath.push_back({nX, nY});
            groupVis[nX][nY] = true;
            vis[nX][nY] = true;
            groupSz++; // 그룹 내 블록 개수 증가
            q.push({nX, nY});
        }
    }

    // 그룹 내 블록 개수 2개 이상이어야 함
    if(groupSz < 2) return;

    // 가장 큰 블록 그룹 찾기
    // 그룹 내 블록 개수 가장 많고
    // 무지개 블럭수 가장 많고
    // 행 가장 크고
    // 열 가장 크고
    auto [largeX, largeY, largeSz, largeRainbow] = largestGroup;
    if(largeSz < groupSz
    || (largeSz == groupSz && largeRainbow < rainbowCnt)
    || (largeSz == groupSz && largeRainbow == rainbowCnt && largeX < sX)
    || (largeSz == groupSz && largeRainbow == rainbowCnt && largeX == sX && largeY < sY))
    {
        largestGroup = {sX, sY, groupSz, rainbowCnt};
        largestGroupPath.clear();

        for(auto [pX, pY] : tmpPath)
        {
            largestGroupPath.push_back({pX, pY});
        }
    }
}

void deleteLargestGroup()
{
    auto [largeX, largeY, largeSz, largeRainbow] = largestGroup;
    score += (largeSz * largeSz); // 점수 증가
    remain -= largeSz; // 블록 삭제

    // 빈칸은 -2로 설정한다.
    for(auto [nX, nY] : largestGroupPath) board[nX][nY] = -2;
}

void dropBlock(int x, int y)
{
    int curX = x + 1;
    int curY = y;

    while(curX < n && board[curX][curY] == -2) curX++;
    
    curX--;

    int cur = board[x][y];
    board[x][y] = -2;
    board[curX][curY] = cur;
}

void drop()
{
    for(int i = n - 1; i >= 0; i--)
    {
        for(int j = 0; j < n; j++)
        {
            if(board[i][j] == -2) continue; // 빈 칸이면 패스
            if(board[i][j] == -1) continue; // 검은색 칸이면 패스

            dropBlock(i, j); // 중력 작용
        }
    }
}

int main()
{
    ios::sync_with_stdio(0);
    cin.tie(0);

    cin >> n >> m;

    memset(vis, false, sizeof(vis));
    for(int i = 0; i < n; i++)
    {
        for(int j = 0; j < n; j++)
        {
            cin >> board[i][j];
        }
    }

    remain = n * n; // 현재 남아 있는 블록 수 
    
    while(remain > 0)
    {
        largestGroup = {21, 21, -1, -1}; // 가장 큰 그룹 초기화
        largestGroupPath.clear();
        memset(vis, false, sizeof(vis));

        for(int i = 0; i < n; i++)
        {
            for(int j = 0; j < n; j++)
            {
                if(board[i][j] == -2) continue; // 빈 칸이면 패스
                if(board[i][j] == -1) continue; // 검정 블록이면 패스
                if(board[i][j] == 0) continue; // 무지개 블록이면 패스
                if(vis[i][j]) continue; // 이미 방문했으면 패스

                // 일반 블록인 경우 그룹 생성 시도
                makeGroup(i, j, board[i][j]);
            }
        }

        // 더 이상 그룹이 생성되지 않으면 끝
        auto [largeX, largeY, largeSz, largeRainbow] = largestGroup;
        if(largeX == 21) break;

        deleteLargestGroup(); // 크기가 가장 큰 블록 그룹
        drop(); // 격자에 중력 작용
        rotate90(); // 격자 반시계 방향 90도 회전
        drop(); // 격자에 중력 작용
    }

    cout << score << "\n";

    return 0;
}