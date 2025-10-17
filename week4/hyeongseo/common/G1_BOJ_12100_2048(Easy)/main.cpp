// https://www.acmicpc.net/problem/12100

// 분류 : 구현, 브루트포스, 시뮬레이션, 백트래킹
// 성공 : 구현, 브루트포스, 시뮬레이션, 백트래킹

// 11% 틀림
// 원인 : 만약 삽입해야할 위치가 빈 칸인 경우와 다른 숫자인 경우를 동일한 경우로 봤음
// 빈 칸 => 해당 칸에 바로 삽입
// 다른 숫자 => 해당 칸 위, 아래, 좌, 우, 한 칸 더 가서 삽입

// 성공 : 백트래킹 + 구현
// 1) 백트래킹으로 한 회에서 상, 하, 좌, 우로 이동
// 2) 총 5회 백트래킹 진행했으면 return

// 블록 이동 코드
// 1) 방향에 따라서 전체 블록 순회 (빈 칸이면 패스)
// 2) 현재 칸 값 저장 후 0으로 초기화 (이동 전에 기존의 값 날리기)
// 3) 진행 방향으로 빈 칸일때까지 계속 이동
// 4) 3)에서 구한 좌표에서
//  => 도착 칸이 현재 값과 같고 아직 합쳐지지 않음 => 도착 칸 값*= 2 & 합쳐짐 표시 & ans 업데이트
//  => 도착 칸이 빈 칸임 => 도착 칸 값 = 햔제 값
//  => 도착 칸이 이미 합쳐짐 or 도착 칸 != 현재값 => 도착 칸을 진행 방향 + 1 만큼 이동 후 현재값 대입

// 결론 : 블록 이동 시키는 코드만 구현 잘 했으면 무난했던 문제!

// 54분 54초
#include <bits/stdc++.h>

using namespace std;

int n;

int board[21][21];

bool isMerged[21][21]; // 이미 합쳐진 블록인가?

int ans = 0; // 정답

void printBoard()
{
    cout << "\n===================\n";
    for(int i = 0; i < n; i++)
    {
        for(int j = 0; j < n; j++)
        {
            cout << board[i][j] << " ";
        }
        cout << "\n";
    }
    cout << "===================\n";
}

// 위로 이동
void up()
{
    memset(isMerged, false, sizeof(isMerged));
    for(int i = 0; i < n; i++)
    {
        for(int j = 1; j < n; j++)
        {
            // cout << "j: " << j << " i: " << i << "\n";
            // 빈 칸이면 패스
            if(board[j][i] == 0) continue;

            int num = board[j][i];
            board[j][i] = 0; // 현재 칸 비우기

            int idx = j-1;

            // 빈 칸이면 계속 이동
            while(board[idx][i] == 0 && idx >= 1) idx--;

            // 같은 숫자이고 아직 합쳐지지 않았으면
            if(board[idx][i] == num && !isMerged[idx][i])
            {
                board[idx][i] *= 2;
                ans = max(ans, board[idx][i]);
                isMerged[idx][i] = true;
            }

            // (11% 틀림 원인) : 해당 칸이 빈 칸이면 바로 삽입
            else if(board[idx][i] == 0) board[idx][i] = num;

            // 다른 숫자이거나 이미 합쳐진 칸이면 한 칸 아래쪽에 위치
            else board[idx+1][i] = num;
        }
    }
}

// 아래로 이동
void down()
{
    memset(isMerged, false, sizeof(isMerged));
    for(int i = 0; i < n; i++)
    {
        for(int j = n-2; j >= 0; j--)
        {
            // cout << "j: " << j << " i: " << i << "\n";
            // 빈 칸이면 패스
            if(board[j][i] == 0) continue;

            int num = board[j][i];
            board[j][i] = 0; // 현재 칸 비우기

            int idx = j+1;
            while(board[idx][i] == 0 && idx <= n-2) idx++;

            // 같은 숫자이고 아직 합쳐지지 않았으면
            if(board[idx][i] == num && !isMerged[idx][i])
            {
                
                board[idx][i] *= 2;
                ans = max(ans, board[idx][i]);
                isMerged[idx][i] = true;
            }

            // (11% 틀림 원인) : 해당 칸이 빈 칸이면 바로 삽입
            else if(board[idx][i] == 0) board[idx][i] = num;

            // 다른 숫자이거나 이미 합쳐진 칸이면 한 칸 위쪽에 위치
            else board[idx-1][i] = num;
        }
    }
}

// 좌로 이동
void left()
{
    memset(isMerged, false, sizeof(isMerged));
    for(int i = 0; i < n; i++)
    {
        for(int j = 1; j < n; j++)
        {
            // cout << "j: " << j << " i: " << i << "\n";
            // 빈 칸이면 패스
            if(board[i][j] == 0) continue;

            int num = board[i][j];
            board[i][j] = 0; // 현재 칸 비우기

            int idx = j-1;
            while(board[i][idx] == 0 && idx >= 1) idx--;

            // 같은 숫자이고 아직 합쳐지지 않았으면
            if(board[i][idx] == num && !isMerged[i][idx])
            {  
                board[i][idx] *= 2;
                ans = max(ans, board[i][idx]);
                isMerged[i][idx] = true;  
            }

            // (11% 틀림 원인) : 해당 칸이 빈 칸이면 바로 삽입
            else if(board[i][idx] == 0) board[i][idx] = num;

            // 다른 숫자이거나 이미 합쳐진 칸이면 한 칸 오른쪽에 위치
            else board[i][idx+1] = num;
        }
    }
}

// 우로 이동
void right()
{
    memset(isMerged, false, sizeof(isMerged));
    for(int i = 0; i < n; i++)
    {
        for(int j = n-2; j >= 0; j--)
        {
            // cout << "j: " << j << " i: " << i << "\n";
            // 빈 칸이면 패스
            if(board[i][j] == 0) continue;

            int num = board[i][j];
            board[i][j] = 0; // 현재 칸 비우기

            int idx = j+1;
            while(board[i][idx] == 0 && idx <= n-2) idx++;

            // 같은 숫자이고 아직 합쳐지지 않았으면
            if(board[i][idx] == num && !isMerged[i][idx])
            {
                board[i][idx] *= 2;
                ans = max(ans, board[i][idx]);
                isMerged[i][idx] = true;   
            }

            // (11% 틀림 원인) : 해당 칸이 빈 칸이면 바로 삽입
            else if(board[i][idx] == 0) board[i][idx] = num;

            // 다른 숫자이거나 이미 합쳐진 칸이면 한 칸 왼쪽에 위치
            else board[i][idx-1] = num;
        }
    }
}

void getAns(int cnt)
{
    // 5번 이동했으면 끝
    if(cnt == 5) return;
    
    int tmp[21][21];
    memcpy(tmp, board, sizeof(board));

    // 상, 하, 좌, 우 이동
    up();
    // printBoard();
    getAns(cnt+1);
    memcpy(board, tmp, sizeof(tmp)); // 원상 복구

    down();
    // printBoard();
    getAns(cnt+1);
    memcpy(board, tmp, sizeof(tmp)); // 원상 복구

    left();
    // printBoard();
    getAns(cnt+1);
    memcpy(board, tmp, sizeof(tmp)); // 원상 복구

    right();
    // printBoard();
    getAns(cnt+1);
    memcpy(board, tmp, sizeof(tmp)); // 원상 복구   
}

int main()
{
    ios::sync_with_stdio(0);
    cin.tie(0);

    cin >> n;

    for(int i = 0; i < n; i++)
    {
        for(int j = 0; j < n; j++)
        {
            cin >> board[i][j];
            ans = max(ans, board[i][j]);
        }
    }

    getAns(0);

    cout << ans << "\n";

    return 0;
}