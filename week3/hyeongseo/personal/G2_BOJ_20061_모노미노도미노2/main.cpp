// https://www.acmicpc.net/problem/20061

// 분류 : 구현, 시뮬레이션

// 틀린 이유
// int gFullCnt[6], bFullCnt[6]을 두어 초록/파랑의 행/열이 가득 찼는지 판별함
// int isInSoftGreen[2], isInSoftBlue[2]을 두어 초록/파라의 연한 부분에 블록이 있는지 확인함
// => 블록 떨어뜨리거나 이동하는 과정에서 이 것도 같이 업데이트해야되는데 이 과정에서 자꾸 틀림
// 따라서, 캐싱을 하지 않고 그 때 그때 확인함

#include <bits/stdc++.h>

using namespace std;

int n;

// t = 1 : 크기가 1x1인 블록을 (x,y)에
// t = 2 : 크기가 1x2인 블록을 (x,y), (x, y+1)에 (가로)
// t = 3 : 크기가 2x1인 블록을 (x,y), (x+1, y)에 (세로)
int t, x, y;

int topGX, topGY; // 현재 초록 블록의 좌상단 좌표
int lX, lY; // t에 따라 확인해야 할 값 (좌상단 블록 기준으로 +lX, +lY하면 블록 위치)

int green[6][4];
int blue[4][6];

int num = 1; // 현재 블록 번호

int score = 0;

void printBoard()
{
    cout << "\n=========초록색==========\n";
    for(int i = 0; i < 6; i++)
    {
        for(int j = 0; j < 4; j++)
        {
            cout << green[i][j] << " ";
        }
        cout << "\n";
    }

    cout << "\n=========파란색==========\n";
    for(int i = 0; i < 4; i++)
    {
        for(int j = 0; j < 6; j++)
        {
            cout << blue[i][j] << " ";
        }
        cout << "\n";
    }
}


// 초록 타일 떨어뜨림
void dropG(int x, int y, int lX, int lY, int num)
{
    int dropX = x;
    int dropY = y;

    int curX = x;

    while(curX < 6)
    {
        if(curX + lX >= 6) break;

        bool isPossible = true;
        for(int i = curX; i <= curX + lX; i++)
        {
            for(int j = y; j <= y + lY; j++)
            {
                if(green[i][j] != 0)
                {
                    isPossible = false;
                    break;
                }
            }

            if(!isPossible) break;
        }

        if(!isPossible) break;

        dropX = max(dropX, curX);
        dropY = max(dropY, y);

        curX++;
    }

    for(int i = dropX; i <= dropX + lX; i++)
    {
        for(int j = dropY; j <= dropY + lY; j++)
        {
            green[i][j] = num;
        }
    }
    
}

// 파랑 타일 떨어뜨림
void dropB(int x, int y, int lX, int lY, int num)
{
    int dropX = x;
    int dropY = y;

    int curY = y;

    while(curY < 6)
    {
        if(curY + lY >= 6) break;

        bool isPossible = true;
        for(int i = x; i <= x + lX; i++)
        {
            for(int j = curY; j <= curY + lY; j++)
            {
                if(blue[i][j] != 0)
                {
                    isPossible = false;
                    break;
                }
            }

            if(!isPossible) break;
        }

        if(!isPossible) break;

        dropX = max(dropX, x);
        dropY = max(dropY, curY);

        curY++;
    }

    for(int i = dropX; i <= dropX + lX; i++)
    {
        for(int j = dropY; j <= dropY + lY; j++)
        {
            blue[i][j] = num;
        }
    }
}

// 행 기준으로 떨구기
void dropG(int x)
{
    for(int i = x; i >= 1; i--)
    {
        for(int j = 0; j < 4; j++)
        {
            green[i][j] = green[i-1][j];
            green[i-1][j] = 0;
        }
    }
}

// 초록 보드에서 가득찬 행 있으면 떨구기
void deleteFullG()
{
    for(int i = 0; i < 6; i++)
    {
        // 한 행이 다 찼으면 삭제
        int cnt = 0;
        for(int j = 0; j < 4; j++)
        {
            if(green[i][j] == 0) continue;
            cnt++;
        }

        if(cnt == 4)
        {
            score++;
            for(int j = 0; j < 4; j++)
            {
                green[i][j] = 0;
            }

            dropG(i);
        }
    }
}

// 열 기준으로 떨구기
void dropB(int y)
{
    for(int i = y; i >= 1; i--)
    {
        for(int j = 0; j < 4; j++)
        {
            blue[j][i] = blue[j][i-1];
            blue[j][i-1] = 0;
        }
    }
}

// 파랑 보드에서 가득찬 열 있으면 떨구기
void deleteFullB()
{
    for(int i = 0; i < 6; i++)
    {
        // 한 열이 다 찼으면 삭제
        int cnt = 0;
        for(int j = 0; j < 4; j++)
        {
            if(blue[j][i] == 0) continue;
            cnt++;
        }

        if(cnt == 4)
        {
            score++;
            for(int j = 0; j < 4; j++)
            {
                blue[j][i] = 0;
            }

            dropB(i);
        }
    }
}

// 초록 연한 부분에 블록 있는지 확인 후 삭제 후 떨구기
void deleteSoftG()
{
    int cnt = 0;
    for(int i = 0; i < 2; i++)
    {
        for(int j = 0; j < 4; j++)
        {
            if(green[i][j] > 0)
            {
                cnt++;
                break;
            }
        }
    }

    if(cnt == 0) return; // 연한 부분에 블록 없으면 패스

    for(int i = 5; i >= 2; i--)
    {
        for(int j = 0; j < 4; j++)
        {
            green[i][j] = green[i-cnt][j];
            green[i-cnt][j] = 0;
            
        }
    }
}

// 파랑 연한 부분에 블록 있는지 확인 후 삭제 후 떨구기
void deleteSoftB()
{
    int cnt = 0;
    for(int i = 0; i < 2; i++)
    {
        for(int j = 0; j < 4; j++)
        {
            if(blue[j][i] > 0)
            {
                cnt++;
                break;
            }
        }
    }

    if(cnt == 0) return; // 연한 부분에 블록 없으면 떨구기

    for(int i = 0; i < 4; i++)
    {
        for(int j = 5; j >= 2; j--)
        {
            blue[i][j] = blue[i][j-cnt];
            blue[i][j-cnt] = 0;
        }
    }
}

int main()
{
    ios::sync_with_stdio(0);
    cin.tie(0);

    cin >> n;

    while(n--)
    {
        int t, x, y;
        cin >> t >> x >> y;

        // 초록색, 파란색 타일 설치
        if(t == 1)
        {
            lX = 0;
            lY = 0;
        }
        else if(t == 2) // 가로
        {
            lX = 0;
            lY = 1;
        }
        else if(t == 3) // 세로
        {
            lX = 1;
            lY = 0;
        }

        dropG(0, y, lX, lY, num); // 초록색 떨구기
        dropB(x, 0, lX, lY, num); // 파란색 떨구기

        deleteFullG(); // 초록색 보드에서 행 가득차면 삭제
        deleteFullB(); // 파란색 보드에서 열 가득차면 삭제

        deleteSoftG(); // 초록색 보드의 연한 행 개수에 따라 블록 삭제
        deleteSoftB(); // 초록색 보드의 연한 행 개수에 따라 블록 삭제

        num++; // 블록 번호++
    }

    int ans = 0;
    for(int i = 0; i < 6; i++)
    {
        for(int j = 0; j < 4; j++)
        {
            if(green[i][j] > 0) ans++;
        }
    }

    for(int i = 0; i < 4; i++)
    {
        for(int j = 0; j < 6; j++)
        {
            if(blue[i][j] > 0) ans++;
        }
    }

    cout << score << "\n";
    cout << ans << "\n";

    return 0;
}