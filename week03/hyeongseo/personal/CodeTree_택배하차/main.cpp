// https://www.codetree.ai/ko/frequent-problems/samsung-sw/problems/delivery-service/description

// 2025 하반기 삼성 오전 1번 문제

// 그 때 실패 원인
// 각 택배의 좌하단 좌표, 우하단 좌표를 직접 저장해놨음 => 떨어뜨릴 때마다 이 값도 변경해줘야 하는데 꼬여서 실패한 듯
// 떨어뜨리는 과정에서도 뭔가 기준을 잡는데도 꼬였었음
// 택배 설치 & 떨어뜨리는 로직 따로 구성함

// 해결
// 왼쪽, 오른쪽에서 제거해야할 택배 뽑아낼 때
// 왼쪽 => 현재 좌표가 택배의 좌하단이냐? => 아래 ~ 위 -> 좌 ~ 우 순회하며 : (board[i+1][j] != board[i][j]) => 택배 번호 작냐? => 업데이트
// 오른쪽 => 현재 좌표가 택배의 우하단이냐? => 아래 ~ 위 -> 우 ~ 좌 순회하며 : board[i+1][j] != board[i][j]) => 택배 번호 작냐? => 업데이트

// 떨어뜨리는 로직
// 아래 ~ 위 -> 좌 ~ 우 순회하며 해당 좌표가 좌하단 (board[i+1][j] != board[i][j] && board[i][j+1] != board[i][j])이면 아래로 떨어뜨리기 시도
// clearPost()로 일단 해당 칸 전부 0으로
// 택배 설치 함수 setPost() 사용해 현재 위치에서 아래로 떨어뜨릴 수 있는 위치 찾은 후 택배 설치

// 풀고 보니 진짜 별 거 없는 문제
// 그 땐 왜 못했을까~~
// 블록 연쇄 작용, 떨어뜨리기 로직 꾸준히 연습하기

#include <bits/stdc++.h>

using namespace std;

int n, m;

int board[55][55]; // 상황판

// 택배 정보, <세로, 가로, 열>
tuple<int, int, int> post[105];

int remain = 0; // 남은 택배 수

void printBoard()
{
    cout << "\n";
    for(int i = 1; i <= n; i++)
    {
        for(int j = 1; j <= n; j++)
        {
            cout << board[i][j] << " ";
        }
        cout << "\n";
    }
    cout << "\n";
}

// 택배 설치 (번호, 세로, 가로, 시작X, 시작Y)
void setPost(int num, int h, int w, int stX, int stY)
{
    // 택배 설치 장소
    int posX = stX;
    int posY = stY;

    // 시작점 ~ 끝까지 순회하며 설치할 수 있는 위치 구하기
    for(int x = stX; x <= n; x++)
    {
        if(x + h - 1 > n) break;
        bool isPossible = true;

        for(int i = x; i <= x + h - 1; i++)
        {
            for(int j = stY; j <= stY + w - 1; j++)
            {
                if(board[i][j] != 0)
                {
                    isPossible = false;
                    break;
                }
            }
            if(!isPossible) break;
        }

        if(!isPossible) break;
        
        posX = x;
    }

    // 택배 설치
    for(int i = posX; i <= posX + h - 1; i++)
    {
        for(int j = posY; j <= posY + w - 1; j++)
        {
            board[i][j] = num;
        }
    }
}

// 택배 제거
void clearPost(int x, int y, int h, int w)
{
    for(int i = x; i <= x + h - 1; i++)
    {
        for(int j = y; j <= y + w - 1; j++)
        {
            board[i][j] = 0;
        }
    }
}

// 빈 칸 있는 택배 떨어뜨리기
void dropPost()
{
    for(int i = n; i >= 1; i--)
    {
        for(int j = 1; j <= n; j++)
        {
            // 빈 칸이면 패스
            if(board[i][j] == 0) continue;

            // 해당 좌표가 좌하단인경우
            if(board[i+1][j] != board[i][j] && board[i][j-1] != board[i][j])
            {
                int num = board[i][j];
                auto [h, w, c] = post[num];

                int stX = i - h + 1;
                int stY = j;

                clearPost(stX, stY, h, w); // 현재 칸 0으로 초기화
                setPost(num, h, w, stX, stY); // 떨어뜨릴 수 있는 칸으로 떨어뜨리기 == 택배 설치하기
            }
        }
    }
}

// 왼쪽에서 택배 꺼내기
void left()
{
    // 꺼내야 할 택배 번호, 좌상단 x,y 위치
    int posX = -1;
    int posY = -1;
    int num = 101;

    // 꺼내야 할 택배 번호 찾기
    for(int i = n; i >= 1; i--)
    {
        for(int j = 1; j <= n; j++)
        {
            // 빈 칸이면 옆으로 진행
            if(board[i][j] == 0) continue;

            // 해당 택배의 좌표가 좌하단인 경우 후보와 비교 후 업데이트
            if(board[i+1][j] != board[i][j])
            {
                int nextNum = board[i][j];
                if(num > nextNum)
                {
                    num = board[i][j];

                    auto [h, w, c] = post[nextNum];

                    // 해당 택배의 좌상단 위치 잡기
                    posX = i - h + 1;
                    posY = j;
                }
            }

            break;
        }
    }

    cout << num << "\n";

    // 택배 꺼내기
    auto [h, w, c] = post[num];
    clearPost(posX, posY, h, w);

    remain--;
}

// 오른쪽에서 택배 꺼내기
void right()
{
    if(remain == 0) return ;

    // 꺼내야 할 택배 번호, 좌상단 x,y 위치
    int posX = -1;
    int posY = -1;
    int num = 101;

    // 꺼내야 할 택배 번호 찾기
    for(int i = n; i >= 1; i--)
    {
        for(int j = n; j >= 1; j--)
        {
            // 빈 칸이면 옆으로 진행
            if(board[i][j] == 0) continue;
            
            // 해당 택배의 좌표가 우하단인 경우 후보와 비교 후 업데이트
            if(board[i-1][j] != board[i][j])
            {
                int nextNum = board[i][j];
                if(num > nextNum)
                {
                    num = board[i][j];

                    auto [h, w, c] = post[nextNum];

                    // 해당 택배의 좌상단 위치 잡기
                    posX = i - h + 1;
                    posY = j - w + 1;
                }
            }

            break;
        }
    }

    cout << num << "\n";

    // 택배 꺼내기
    auto [h, w, c] = post[num];
    clearPost(posX, posY, h, w);

    remain--;
}


int main() {
    ios::sync_with_stdio(0);
    cin.tie(0);

    cin >> n >> m;

    remain = m; // 남은 택배 수

    // 초기에 택배 설치
    for(int i = 0; i < m; i++)
    {
        int k, h, w, c;
        cin >> k >> h >> w >> c;

        post[k] = {h, w, c};

        // 택배 설치
        setPost(k, h, w, 1, c);
    }

    while(remain > 0)
    {
        // 왼쪽에서 택배 제거하고 떨어뜨리기
        left();
        dropPost();

        // 오른쪽에서 택배 제거하고 떨어뜨리기
        right();
        dropPost();
    }

    return 0;
}