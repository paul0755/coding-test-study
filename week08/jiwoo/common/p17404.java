import java.util.Scanner;

public class p17404 {
    static int N,ans;

    public static void main(String[] args) {
        ans=Integer.MAX_VALUE;
        Scanner sc=new Scanner(System.in);
        N=sc.nextInt();
        int[][] dist=new int[N][3];
        int[][] dp=new int[N][3];
        for (int i=0;i<N;i++){
            for (int j=0;j<3;j++){
                dist[i][j]=sc.nextInt();
            }
        }

        for (int j=0;j<3;j++){ //하나씩 고정 //r,g,b

            for (int i=0;i<3;i++){
                if (j==i) dp[0][i]=dist[0][j];
                else dp[0][i]=1001;
            }

            for (int i=1;i<N;i++){ //2번째부터 N-1번째 집까지
                dp[i][0]=Math.min(dp[i-1][1],dp[i-1][2])+dist[i][0];
                dp[i][1]=Math.min(dp[i-1][0],dp[i-1][2])+dist[i][1];
                dp[i][2]=Math.min(dp[i-1][1],dp[i-1][0])+dist[i][2];

                //j 생깔이 고정되어 있으 ㄹ떄
                System.out.println(j+" "+dp[i][0]+" "+dp[i][1]+" "+dp[i][2]);

            }


            for (int i=0;i<3;i++){ //처음과 끝이 같은 색이라면 continue
                if (j==i) continue;
                ans=Math.min(ans,dp[N-1][i]);
            }
        }

        System.out.println(ans);

    }
}
