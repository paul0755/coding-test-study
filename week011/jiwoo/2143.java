import java.util.*;

public class Main {
    static int T,n,m;
    static int[] A;
    static int[] B;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        T=sc.nextInt();
        n=sc.nextInt();
        A=new int[n];
        List<Integer> psumA=new ArrayList<>();
        List<Integer> psumB=new ArrayList<>();

        for (int i=0;i<n;i++){
            A[i]=sc.nextInt();
        }

        m=sc.nextInt();
        B=new int[m];
        for (int i=0;i<m;i++){
            B[i]=sc.nextInt();
        }

        //누적합
        /**
         1 3 1 2
         */
        //a의 누적합
        for (int i=0;i<n;i++){
            psumA.add(A[i]);
            for (int j=i+1;j<n;j++){
                psumA.add(psumA.get(psumA.size()-1)+A[j]);
            }
        }

        Collections.sort(psumA);


        for (int i=0;i<m;i++){
            psumB.add(B[i]);
            for (int j=i+1;j<m;j++){
                psumB.add(psumB.get(psumB.size()-1)+B[j]);
            }
        }Collections.sort(psumB);

        Set<Integer> setA=new HashSet<>(psumA); Set<Integer> setB=new HashSet<>(psumB);
        long ret=0;
        for (Integer a:setA){
            int b=T-a;
            if (!setB.contains(b)) continue;

            int ret1=upper_bound(psumA,a)-lower_bound(psumA,a)-1;
            int ret2=upper_bound(psumB,b)-lower_bound(psumB,b)-1;

            ret+=(long)ret1*(long)ret2;
        }

        //upper_bound()-lowerbound()-1
        System.out.println(ret);

    }

    static int upper_bound(List<Integer> ar,int target){
        int lo=0,hi=ar.size()-1;

        while (lo<=hi){
            int mid=(lo+hi)/2;

            if (ar.get(mid)<=target) lo=mid+1;
            else if (ar.get(mid)>target) hi=mid-1;
        }
        return lo;
    }

    static int lower_bound(List<Integer> ar,int target){
        int lo=0,hi=ar.size()-1,mid=0;

        while (lo<=hi){
            mid=(lo+hi)/2;

            if (ar.get(mid)<target) lo=mid+1;
            else if (ar.get(mid)>=target) hi=mid-1;
        }
        return hi;
    }

}
