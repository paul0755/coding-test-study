// https://www.acmicpc.net/problem/1405

// 분류 : 수학, 그래프, 브루트포스, DFS, 백트래킹, 확률론

// 스스로 못 풂

// 45분 36초

#include <bits/stdc++.h>

using namespace std;

// 동 서 남 북
int dx[4] = {0, 0, 1, -1};
int dy[4] = {1, -1, 0, 0};

// 이동 횟수 <= 14
int n;

// 동 서 남 북 확률 저장
double prob[4];

// 단순한 경우의 수
double cnt = 0;

// x : -14 ~ 14
// y : -14 ~ 14
bool vis[51][51]; // 방문 확인 배열

// (x, y, 현재 이동 횟수, 현재 저장된 방향으로 현재 지점가지 올 수 있는 확률)
// 백트래킹
void DFS(int x, int y, int cur, double val)
{
    // 끝까지 도달함 => 단순한 경로 => 횟수 저장
    if(cur == n)
    {
        cnt += val;
        return;
    }

    for(int i = 0; i < 4; i++)
    {
        int nX = x + dx[i];
        int nY = y + dy[i];

        // 이미 방문했다면 패스
        // 미리 가지치기 한 덕분에 4^14인데도 시간 초과 안남
        if(vis[nX][nY]) continue;

        vis[nX][nY] = true;

        // Point) 현재까지의 확률 * 현재 방향의 확률 곱하면 됨!
        // 이 생각 못했음
        DFS(nX, nY, cur + 1, val * prob[i]); 
        vis[nX][nY] = false;
    }
}

int main()
{
    ios::sync_with_stdio(0);
    cin.tie(0);

    cin >> n;

    // 각 방향 별 확률로 변환
    for(int i = 0; i < 4; i++)
    {
        cin >> prob[i];
        prob[i] /= 100.0;
    }

    // 시작점 평면 정중앙에서 시작
    vis[25][25] = true;
    DFS(25, 25, 0, 1);

    // 절대 / 상대 오차 : 10^9 => 소수점 9자리 고정 출력
    cout << fixed;
    cout.precision(9);
    cout << cnt << "\n";
    
    return 0;
}