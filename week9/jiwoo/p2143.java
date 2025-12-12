package d1209to;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class p2143 {
    static int T,n,m;
    static int[] A;
    static int[] B;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        T=sc.nextInt();
        n=sc.nextInt();
        A=new int[n];

        for (int i=0;i<n;i++){
            A[i]=sc.nextInt();
        }

        m=sc.nextInt();
        B=new int[m];
        for (int i=0;i<m;i++){
            B[i]=sc.nextInt();
        }

        Map<Long,Long> mapA=new HashMap<>();
        Map<Long,Long> mapB=new HashMap<>();

        for (int i=0;i<n;i++){
            long sum=A[i];
            mapA.put(sum,mapA.getOrDefault(sum,0L)+1);
            for (int j=i+1;j<n;j++){
                sum+=A[j];
                mapA.put(sum,mapA.getOrDefault(sum,0L)+1);
            }
        }

        for (int i=0;i<m;i++){
            long sum=B[i];
            mapB.put(sum,mapB.getOrDefault(sum,0L)+1);
            for (int j=i+1;j<m;j++){
                sum+=B[j];
                mapB.put(sum,mapB.getOrDefault(sum,0L)+1);
            }
        }

        List<Long> aKey=new ArrayList<>(mapA.keySet()); List<Long> bKey=new ArrayList<>(mapB.keySet());
        Collections.sort(aKey);
        Collections.sort(bKey);

        int i=0,j=bKey.size()-1;
        long ret=0;

        while (true){
            if (i==aKey.size() || j==-1) break;

            long curA=aKey.get(i); long curB=bKey.get(j);

            if (curA+curB==T){
                ret+=(mapA.get(curA)*mapB.get(curB));
                i++;j--;
            }
            else if (curA+curB<T){
                i++;
            }
            else if (curA+curB>T){
                j--;
            }

        }
        System.out.println(ret);



    }

}
