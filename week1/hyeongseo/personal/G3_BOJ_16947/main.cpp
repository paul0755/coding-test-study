// https://www.acmicpc.net/problem/16947

// 분류 : DFS, BFS

// 내 풀이 : DFS만 => 132ms
// 각 정점마다 DFS 돌며
// 직전에 방문했던 곳 아니고 방문했던 노드를 만나면 dist[노드] 출력 => 출발 정점과 가장 가까운 순환선
// 비효율적

// 최적화 : 0ms (챗지피티 보고)
// 1) DFS로 사이클에 속한 정점 구하기
// 2) 사이클에 속한 정점에서 BFS 진행해 사이클 - 지선 간의 최단 거리 구하기

// 37분 16초

#include <bits/stdc++.h>

using namespace std;

int n;

vector<int> adj[3001]; // 인접리스트
int dist[3001]; // 순환선 - 지선 간의 최단 거리

bool isInCycle[3001]; // 해당 정점이 사이클에 속하는지 확인
bool vis[3001]; // 사이클 찾을 때 사용할 방문 배열

bool findCycle(int cur, int prev, vector<int>& path)
{
    vis[cur] = true;
    path.push_back(cur);

    for(int nxt : adj[cur])
    {
        if(nxt == prev) continue;
        if(!vis[nxt])
        {
            if(findCycle(nxt, cur, path)) return true;
        }
        else
        {
            int idx = find(path.begin(), path.end(), nxt) - path.begin();
            for(int i = idx; i < (int)path.size(); i++)
            {
                isInCycle[path[i]] = true;
            }

            return true;
        }
    }

    path.pop_back();
    return false;
}

void BFS()
{
    queue<int> q;

    memset(dist, -1, sizeof(dist));
    for(int i = 1; i <= n; i++)
    {
        if(isInCycle[i])
        {
            dist[i] = 0;
            q.push(i);
        }
    }

    while(!q.empty())
    {
        auto cur = q.front();
        q.pop();

        for(auto nxt : adj[cur])
        {
            if(dist[nxt] != -1) continue;

            dist[nxt] = dist[cur] + 1;
            q.push(nxt);
        }
    }
}

int main()
{
    ios::sync_with_stdio(0);
    cin.tie(0);

    cin >> n;

    for(int i = 0; i < n; i++)
    {
        int from, to;
        cin >> from >> to;

        adj[from].push_back(to);
        adj[to].push_back(from);
    }

    vector<int> path; // 사이클 찾기 위해 방문한 경로
    findCycle(1, 0, path); // 사이클 찾기

    BFS(); // 순환선 - 지선 최단 거리 구하기

    for(int i = 1; i <= n; i++)
    {
        cout<< dist[i] << " ";
    }
    cout << "\n";

    return 0;
}