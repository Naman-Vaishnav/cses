import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.InputMismatchException;
import java.util.*;

public class RemovingDigits {

    public static int findSteps(int n,int[] dp){
        //base cases
        if(n==0)return 0;
        if(n<0)return Integer.MAX_VALUE;
        if(dp[n]!=Integer.MAX_VALUE)return dp[n];
        int ans=Integer.MAX_VALUE;
        int temp=n;
        while(temp>0){
            if(temp%10==0){
                temp/=10;
                continue;
            }
            ans=Math.min(ans,1+findSteps(n-temp%10, dp));
            temp/=10;
        }
        return dp[n]=ans;
    }
    public static void main(String[] args) throws IOException {
        Reader fr=new Reader();
        int n=fr.nextInt();
        int[] dp=new int[n+1];
        Arrays.fill(dp, Integer.MAX_VALUE);

           // int ans=findSteps(n,dp);
            //System.out.println(ans);
        dp[0]=0;
        for(int num=1;num<=n;num++){
            for(int temp=num;temp>0;temp/=10){
                if(temp%10==0)continue;
                dp[num]=Math.min(dp[num], 1+dp[num-temp%10]);
            }
        }
        System.out.println(dp[n]);  
    
       
    }
}

class Reader extends PrintWriter {private InputStream stream;private byte[] buf=new byte[1<<16];private int curChar,numChars;public Reader(){this(System.in,System.out);}public Reader(InputStream i,OutputStream o){super(o);stream=i;}public Reader(String i,String o) throws IOException{super(new FileWriter(o));stream=new FileInputStream(i);}private int nextByte(){if(numChars==-1)throw new InputMismatchException();if(curChar>=numChars){curChar=0;try{numChars=stream.read(buf);}catch(IOException e){throw new InputMismatchException();}if(numChars==-1)return-1;}return buf[curChar++];}public String next(){int c;do{c=nextByte();}while(c<=' ');StringBuilder res=new StringBuilder();do{res.appendCodePoint(c);c=nextByte();}while(c>' ');return res.toString();}public int nextInt(){int c;do{c=nextByte();}while(c<=' ');int sgn=1;if(c=='-'){sgn=-1;c=nextByte();}int res=0;do{if(c<'0'||c>'9')throw new InputMismatchException();res=10*res+c-'0';c=nextByte();}while(c>' ');return res*sgn;}public double nextDouble(){return Double.parseDouble(next());}}

