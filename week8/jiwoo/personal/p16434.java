import java.util.Scanner;

public class p16434 {
    static int N,ATK;
    static Room[] rooms;
    static long ret=1000000000000L;

    static class Room{
        int t,a,h;

        public Room(int t,int a,int h){
            this.t=t;
            this.a=a;
            this.h=h;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N=sc.nextInt(); ATK=sc.nextInt();
        rooms=new Room[N];
        for (int i=0;i<N;i++){
            int t=sc.nextInt(); int a=sc.nextInt(); int h=sc.nextInt();
            rooms[i]=new Room(t,a,h);
        }

        long lo=1,hi=ret,mid=0;
        long ans=0;
        while (lo<=hi){
            mid=(lo+hi)/2;
            //System.out.println(mid);
            if (check(mid)){ //loop를 다 돌 수 있으면 생명력 줄이기
                ans=mid;
                hi=mid-1;
            }else{
                lo=mid+1;
            }

        }

        System.out.println(ans);

    }

    static boolean check(long hp){
        long maxHp=hp;
        long atk=ATK;
        for (int i=0;i<N;i++){
            Room r=rooms[i];
            int t=r.t,a=r.a,h=r.h;
            //t==1일 때
            if (t==1){
                //몬스터를 죽이는 횟수를 구한다
                long cnt=0;
                if (h%atk==0) cnt=h/atk;
                else cnt=h/atk+1;

                //용사 생명력이 0이 되면 return false
                if (hp-((cnt-1)*a)<=0) return false;

                //else update and continue
                else hp-=((cnt-1)*a);
            }
            //t==2일 때
            else if (t==2){
                //생명력 업데이트(상한선 존재)
                hp+=h;
                if (hp>maxHp) hp=maxHp;

                //공격력 업데이트
                atk+=a;
            }

        }
        return true;
    }

}