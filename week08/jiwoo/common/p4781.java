import java.util.Scanner;

public class p4781 {
    static int n;
    static float m;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true){
            n=sc.nextInt(); m=sc.nextFloat();
            int money = (int)(m * 100 + 0.5);

            if (n==0 && m==0) break;
            int[] dp=new int[10001]; //dp[가격]=칼로리
            //입력
            for (int cnt=0;cnt<n;cnt++){
                int c;float p; //사탕 칼로리와 가격
                c=sc.nextInt(); p=sc.nextFloat();
                int price = (int)(p * 100 + 0.5);
                for (int i=price;i<=money;i++){
                    dp[i]=Math.max(dp[i],dp[i-price]+c);
                }
            }

            System.out.println(dp[money]);


        }
    }

}
