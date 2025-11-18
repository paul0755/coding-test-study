package week7.yechan.personal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 첫 시도
 * -> price배열에 j번째와 j+1번째를 비교해서
 * 오르면 profit에 더하고 
 * 똑같으면 same_cnt에 1을 더해서 나중에 곱해서 계산할 생각이었고
 * 내리면 same_cnt를 1로 갱신해주는 로직을 짰는데
 * 이렇게되면 최대이익이 아닌 그냥 이익에 대해 계산이 되고
 * 최대이익을 계산할 수 없었음.
 * 
 * 두번째 시도(GPT도움)
 * -> 뒤에서부터 접근해보기
 * 맨뒤에서 접근하면서 maxPrice를 갱신하고
 * price[j]가 maxprice보다 크면 maxPrice를 갱신만해주고
 * 작으면 profit에 maxprice - price[j]를 넣어서
 * 최대이익을 계산하면 되었었다.
 * 
 * 총평 : 막상 힌트를 보면 이해하겠는데
 * 그 전까지는 어떻게 풀어야할지 가늠이 안잡힌다..
 * 이것이 그리디..?ㅠ
 * 
 * 
 */

public class S2_BOJ_11501_주식 {

    static int T, N;
    static int[] price;
    
    public static void main(String[] args) throws NumberFormatException, IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        T = Integer.parseInt(br.readLine());
        for(int i=0; i<T; i++){
            N = Integer.parseInt(br.readLine());
            price = new int[N];

            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++){
                price[j] = Integer.parseInt(st.nextToken());
            }

            int maxPrice = 0;
            long profit = 0;

            for(int j=N-1; j>=0; j--){

                if(price[j] > maxPrice){
                    maxPrice = price[j]; // 최고가 갱신
                }else{
                    profit += (maxPrice - price[j]);
                }
            }

            System.out.println(profit);

        }


    }
}
