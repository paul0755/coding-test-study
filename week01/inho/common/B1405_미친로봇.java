
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 같은 곳 방문하게 되면 단순하지 않음.
// 확률 방향을 누적해서 곱해가면서, 깊이가 N과 같아지면, 누적된 값이 이동경로가 단순할 확률임.
public class B1405_미친로봇 {
    static int[][] rows = {
            {1,0},
            {-1,0},
            {0,1},
            {0,-1}
    };
    static int N; // 행동 횟수
    static double[] par = new double[4];
    static boolean[][] visited = new boolean[50][50];
    static double result = 0.0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        for (int i = 0; i < 4; i++) {
            par[i] = Integer.parseInt(input[i+1]) / 100.0;
        }
        visited[29][29] = true;
        dfs(29,29,0,1.0);
        System.out.println(result);
    }

    private static void dfs(int x, int y, int depth, double far) {
        if (depth==N) {
            result += far;
            return;
        }
        for(int i=0; i<4;i++){
            int nx = x+rows[i][0];
            int ny = y+rows[i][1];
            if (!visited[nx][ny] && par[i] > 0 ){
                visited[nx][ny] = true;
                dfs(nx,ny,depth+1,par[i]*far);
                visited[nx][ny] = false;
            }
        }
    }
}
