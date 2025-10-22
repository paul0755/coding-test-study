// https://www.acmicpc.net/problem/18428

// 분류 : 브루트포스, 백트래킹
// 성공 : 브루트포스, 백트래킹

// 성공 : 0ms
// 풀이 과정
// 1) 백트래킹 진행하며 전체 순회하며 X인 곳에 장애물 설치 후 장애물 개수 ++한 후 백트래킹
// 2) 장애물 3개 다 설치 완료하면 전체 순회하며 선생 위치에서 진행 방향으로 쭉 가면서 학생 만나면 false

// 최적화 : 0ms
// 풀이 과정
// 1) 선생 위치, X인 빈 칸 위치 미리 저장
// 2) 백트래킹 진행하며 빈 칸만 순회하며 장애물 설치
// 3) 장애물 3개 다 설치하면 선생 위치만 순회하며 진행 방향에서 학생 만나면 false
// => 시간적으로 최적화는 없지만 더 불필요한 순회 없앰

// 총평 : 첫 번째로 풀었을 때는 전체 빈 칸 위치를 순회 안하고 내가 기준을 정해서 특정 빈 칸만 순회해서 못 풀었던 것 같음
// 이번에는 전체 다 순회하니 쉽게 풀렸던 문제

#include <bits/stdc++.h>

using namespace std;

int n;

char board[7][7];

int dx[4] = {-1, 1, 0, 0};
int dy[4] = {0, 0, -1, 1};

vector<pair<int, int>> tPos; // 선생 위치
vector<pair<int, int>> xPos; // 빈 칸 위치 (장애물 설치 후보)

// 현재 장애물 현황에서 학생들이 모두 안 걸릴 수 있는지
bool isPossible()
{
    for(auto [x, y] : tPos)
    {
        for(int dir = 0; dir < 4; dir++)
        {
            int nX = x;
            int nY = y;

            while(true)
            {
                nX += dx[dir];
                nY += dy[dir];

                if(nX < 0 || nX >= n || nY < 0 || nY >= n) break;
                if(board[nX][nY] == 'O') break;
                if(board[nX][nY] == 'S') return false; // 선생이 학생 만나면 실패
            }
        }
    }

    return true; // 끝까지 학생 안 만났으면 성공
}

// 백트래킹
void getAns(int idx, int cnt)
{
    if(cnt == 3)
    {
        // 학생들 모두 안 걸리는 조합 찾으면 출력 후 프로그램 종료
        if(isPossible())
        {
            cout << "YES\n";
            exit(0);
        }

        return;
    }

    // 빈 칸 위치만 순회
    for(int i = idx; i < (int)xPos.size(); i++)
    {
        auto [x, y] = xPos[i];

        board[x][y] = 'O';
        getAns(i+1, cnt+1);
        board[x][y] = 'X';
    }
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

            if(board[i][j] == 'T') tPos.push_back({i, j}); // 선생 위치 추가
            else if(board[i][j] == 'X') xPos.push_back({i, j}); // 빈 칸 위치 추가
        }
    }

    getAns(0, 0);

    cout << "NO\n";

    return 0;
}