import java.util.*;
class Solution {
    public String solution(String new_id) {
        String answer = "";

        //1단계
        String s1=new_id.toLowerCase();

        //2단계
        StringBuilder sb=new StringBuilder();
        char[] ar=s1.toCharArray();
        for (char s:ar){
            if ((s>='a' && s<='z') ||(s>='0' && s<='9') || s=='-' || s=='_' || s=='.'){
                sb.append(s);
            }
        }

        //3단계
        String s3=sb.toString();
        while (s3.contains("..")){
            s3=s3.replace("..",".");
        }

        //4단계
        String s4=s3;
        //마침표가 처음에 있으면 제거한다
        if (s4.charAt(0)=='.'){
            s4=s4.substring(1);
            // System.out.println("apporved "+s4)
        }
        //마침표가 마지막에 있으면 제거한다
        if (s4.endsWith(".")) s4=s4.substring(0,s4.length()-1);

        //5단계
        String s5=s4;
        if (s5.equals("")) s5+="a";

        //6단계
        String s6=s5;
        if (s6.length()>=16){
            s6=s6.substring(0,15);
            //제거 후 마침표가 제일 끝이라면 제거한다
            if (s6.charAt(14)=='.') s6=s6.substring(0,14);
        }


        //7단계
        String s7=s6;
        StringBuilder sb2=new StringBuilder(s7);
        if (s7.length()<=2){
            char c=s7.charAt(s7.length()-1);
            while (sb2.length()<3){
                sb2.append(c);
            }
            s7=sb2.toString();
        }

        //return "hello";
        return s7;
    }
}