import java.util.Scanner;

public class p2240 {
    static int T,W;
    static int[] jadu=new int[1001];
    static int[][][] dp;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        T = sc.nextInt(); W=sc.nextInt();

        for (int i=1;i<=T;i++){
            jadu[i]=sc.nextInt();
        }
        jadu[0]=-1;

        dp=new int[T+1][3][100];
        go(0,0,1);
        System.out.println(dp[0][1][0]);
    }
    //cnt는 자두가 이동한 거리 loc은 1,2, tot는 자두 개수
    static int go(int cnt,int locIdx,int loc){
        //cnt가 w를 초과하면 return
        if (cnt>W) return Integer.MIN_VALUE;
        if (dp[locIdx][loc][cnt]!=0) return dp[locIdx][loc][cnt];
        //locIdx가 baseCase일 때 비교

        if (locIdx==T){
            if (loc==jadu[locIdx]) dp[locIdx][loc][cnt]=1;
            else dp[locIdx][loc][cnt]=0;

            return dp[locIdx][loc][cnt];
        }

        //비트 반전: 1->2 , 2->1 기억이 안난다;
        int rev=0;
        if (loc==1) rev=2; else rev=1;
        int move=go(cnt+1,locIdx+1,rev);
        //비트 반전 없이
        int stop=go(cnt,locIdx+1,loc);
        dp[locIdx][loc][cnt]+=Math.max(move,stop);
        //cur 위치의 자두와 locIdx 자두가 같다면 tot+1
        if (jadu[locIdx]==loc) dp[locIdx][loc][cnt]++;
        return dp[locIdx][loc][cnt];



    }

}
