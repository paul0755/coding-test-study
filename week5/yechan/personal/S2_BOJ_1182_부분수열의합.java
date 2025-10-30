package week5.yechan.personal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 💥 이해 안갔던 부분
 * 입력 : 5 0
 *       -7 -3 -2 5 8
 * 
 * dfs(3,3)
 * -> for문 i=3 일때,
 * 1. list.add(5) -> [-7,-3,-2,5]
 * 2. visited[3] = true;
 * 3. dfs(4,4) 호출 
 * -> i = 4 실행 -> list.add(8) -> dfs(5,5)호출 -> 종료 -> 복귀
 * -> visited[4] = false, list.remove() -> [-7,-3,-2,5]
 * 4. dfs(3,3)로 복귀
 * -> dfs() 호출 다음인 visited[3]= false, list.remove()실행
 * 
 * 💦 놓친 부분
 * 1. 공집합은 빼고 계산하게끔 설정
 * 2. 부분수열의 합에서의 return
 * if (!list.isEmpty() && check()) {
    cnt++;      // ✅ 합이 같더라도
    // return ❌ 하면 안 됨!
}
여기서 return을 써버리면
“지금 합이 맞으니까 더 이상 이 길이 이후는 안 본다”가 돼버림 → 틀림 ❌
 * 
 * 
 */

public class S2_BOJ_1182_부분수열의합 {
    
    static int N, S, cnt=0;
    static int[] arr;
    static List<Integer> list = new ArrayList<>();
    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());

        arr = new int[N];
        visited = new boolean[N];

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        dfs(0,0);

        System.out.println(cnt);
    }
    private static void dfs(int start, int depth) {
        
        if(!list.isEmpty() && check()){
            // 공집합은 세지않게 해야함.
            cnt ++;
            //return;
        }

        for(int i = start; i<N; i++){
            if(!visited[i]){
                list.add(arr[i]);
                //System.out.println("before:"+list);
                visited[i] = true;
                dfs(i+1,depth+1);
                visited[i] = false;
                list.remove(list.size()-1);
                //System.out.println("after:"+list);
            }

        }
    }
    private static boolean check() {
        
        int result = 0;
        for(int n : list){
            result += n;
        }
        // 중간합 비교가 아닌 전체합 비교로 변경
        if(result == S) return true;
        return false;
    }
}
