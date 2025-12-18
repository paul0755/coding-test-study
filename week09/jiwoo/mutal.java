import java.util.*;
public class Main {
    static int N;
    static int[] ar=new int[3];
    static int cnt=0;
    static int[][][] visited=new int[64][64][64];
    static int[][] _a={
        {9, 3, 1},
        {9, 1, 3},
        {3, 1, 9},
        {3, 9, 1},
        {1, 3, 9},
        {1, 9, 3}
    };
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        N=sc.nextInt();

        for (int i=0;i<N;i++){
            ar[i]=sc.nextInt();
        }
        System.out.println(solve(ar[0],ar[1],ar[2]));
    }

    public static int solve(int a,int b,int c){
        Queue<int[]> q=new LinkedList<>();
        visited[a][b][c]=1;
        q.add(new int[]{a,b,c});

        while (!q.isEmpty()){
            int[] cur=q.poll();
            a=cur[0];
            b=cur[1];
            c=cur[2];

            if (visited[0][0][0]>0) break;
            for (int i=0;i<6;i++){
                int na=Math.max(0,a-_a[i][0]);
                int nb=Math.max(0,b-_a[i][1]);
                int nc=Math.max(0,c-_a[i][2]);

                if (visited[na][nb][nc]>0) continue;

                visited[na][nb][nc]=visited[a][b][c]+1;
                q.add(new int[]{na,nb,nc});
            }
        }
        return visited[0][0][0]-1;
    }
}
