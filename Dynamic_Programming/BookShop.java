import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.InputMismatchException;
import java.util.Arrays;

public class BookShop {
    public static void main(String[] args) throws IOException {
        Reader fr=new Reader();
        int n=fr.nextInt(),mod=(int)Math.pow(10, 9)+7;
        String[] grid=new String[n];
        for(int i=0;i<n;i++){
                grid[i]=fr.next();
        }

        int[][] dp=new int[n][n];
        for(int i=0;i<n;i++){
            Arrays.fill(dp[i], 0);
        }
        if(grid[0].charAt(0)=='.')dp[0][0]=1;
        else dp[0][0]=0;

        for(int i=1;i<n;i++){
            if(grid[0].charAt(i)=='.')dp[0][i]+=dp[0][i-1];
            dp[0][i]%=mod;
        }

        for(int i=1;i<n;i++){
            if(grid[i].charAt(0)=='.')dp[i][0]+=dp[i-1][0];
            dp[i][0]%=mod;
        }

        for(int i=1;i<n;i++){
            for(int j=1;j<n;j++){
                if(grid[i].charAt(j)=='.'){
                    dp[i][j]+=dp[i-1][j];
                    dp[i][j]%=mod;
                    dp[i][j]+=dp[i][j-1];
                    dp[i][j]%=mod;
                }
            }
        }

        System.out.println(dp[n-1][n-1]);




    }
   

}

class Reader extends PrintWriter {private InputStream stream;private byte[] buf=new byte[1<<16];private int curChar,numChars;public Reader(){this(System.in,System.out);}public Reader(InputStream i,OutputStream o){super(o);stream=i;}public Reader(String i,String o) throws IOException{super(new FileWriter(o));stream=new FileInputStream(i);}private int nextByte(){if(numChars==-1)throw new InputMismatchException();if(curChar>=numChars){curChar=0;try{numChars=stream.read(buf);}catch(IOException e){throw new InputMismatchException();}if(numChars==-1)return-1;}return buf[curChar++];}public String next(){int c;do{c=nextByte();}while(c<=' ');StringBuilder res=new StringBuilder();do{res.appendCodePoint(c);c=nextByte();}while(c>' ');return res.toString();}public int nextInt(){int c;do{c=nextByte();}while(c<=' ');int sgn=1;if(c=='-'){sgn=-1;c=nextByte();}int res=0;do{if(c<'0'||c>'9')throw new InputMismatchException();res=10*res+c-'0';c=nextByte();}while(c>' ');return res*sgn;}public double nextDouble(){return Double.parseDouble(next());}}

