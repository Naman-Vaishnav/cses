import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.*;


public class Test {

   static int[] price;
   static int[] pages;
   static int[][]dp;

    /**
    *  solve(n,x) max((n-1,x),pages[n]+(n-1,x-price[n]))
    */
    static int solve(int n,int x){

        if(x==0|| n<0)return 0;
        if(dp[n][x]!=-1)return dp[n][x];

        int inc=0;
        if(x-price[n]>=0)inc=pages[n]+solve(n-1,x-price[n]);
        int exc=solve(n-1,x);
        
        return dp[n][x]=Math.max(inc,exc);

    }
   

    public static void main(String[] args) {
       
        int n,x;
        Reader fr=new Reader();
        n=fr.nextInt();x=fr.nextInt();

         price=new int[n];
         pages=new int[n];
         dp=new int[n+1][x+1];
         
       

        for(int i=0;i<n;i++){
            price[i]=fr.nextInt();
        }
        for(int i=0;i<n;i++){
            pages[i]=fr.nextInt();
        }

       // int ans=solve(n-1,x);
       for(int i=1;i<=n;i++){
            for(int j=0;j<=x;j++){
                dp[i][j]=dp[i-1][j];
                int rem=j-price[i-1];
                if(rem>=0)dp[i][j]=Math.max(dp[i][j],pages[i-1]+dp[i-1][rem]);
            }
       }
       
        System.out.println(dp[n][x]);

      


    }
}



 class Reader extends PrintWriter {private InputStream stream;private byte[] buf=new byte[1<<16];private int curChar,numChars;public Reader(){this(System.in,System.out);}public Reader(InputStream i,OutputStream o){super(o);stream=i;}public Reader(String i,String o) throws IOException{super(new FileWriter(o));stream=new FileInputStream(i);}private int nextByte(){if(numChars==-1)throw new InputMismatchException();if(curChar>=numChars){curChar=0;try{numChars=stream.read(buf);}catch(IOException e){throw new InputMismatchException();}if(numChars==-1)return-1;}return buf[curChar++];}public String next(){int c;do{c=nextByte();}while(c<=' ');StringBuilder res=new StringBuilder();do{res.appendCodePoint(c);c=nextByte();}while(c>' ');return res.toString();}public int nextInt(){int c;do{c=nextByte();}while(c<=' ');int sgn=1;if(c=='-'){sgn=-1;c=nextByte();}int res=0;do{if(c<'0'||c>'9')throw new InputMismatchException();res=10*res+c-'0';c=nextByte();}while(c>' ');return res*sgn;}public double nextDouble(){return Double.parseDouble(next());}}
