package week7.yechan.personal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/*
 * list로 나이를 기준으로 정렬을 한다면
 * list는 '안전정렬'을 하기 때문에 들어온 순서로 정렬한다.
 * 
 */

public class S5_BOJ_10814_나이순정렬 {
    
    static int N;
    public static void main(String[] args) throws Exception, IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        List<Member> list = new ArrayList<>();

        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            int age = Integer.parseInt(st.nextToken());
            String name = st.nextToken();
            list.add(new Member(age, name));
        }

        list.sort((a,b)-> a.age - b.age);

        StringBuilder sb = new StringBuilder();
        for(Member m : list){
            sb.append(m.age).append(" ").append(m.name).append("\n");
        }

        System.out.println(sb);

        

        
        

    }

    static class Member{
        int age;
        String name;

        public Member(int age, String name){
            this.age = age;
            this.name = name;
        }
    }


}

