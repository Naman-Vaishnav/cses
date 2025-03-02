import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.InputMismatchException;

public class ForestQueries {
    public static void main(String[] args) {
        int n,m,q;
        Reader fr=new Reader();
        n=fr.nextInt();q=fr.nextInt();
        m=n;
        char[][] mat=new char[n][m];
        for(int i=0;i<n;i++){
                mat[i]=fr.next().toCharArray();
        }
        int[][] dp=new int[n+1][m+1];
        for(int i=0;i<=n;i++){
            Arrays.fill(dp[i], 0);
        }
        for(int i=1;i<=n;i++){
            for(int j=1;j<=m;j++){
                if(mat[i-1][j-1]=='*')dp[i][j]=1;
                dp[i][j]+=dp[i-1][j]+dp[i][j-1]-dp[i-1][j-1]; 
            }
        }
        StringBuilder sb=new StringBuilder();
        for(int i=0;i<q;i++){
            int x1,y1,x2,y2;
            x1=fr.nextInt();
            y1=fr.nextInt();
            x2=fr.nextInt();
            y2=fr.nextInt();
            sb.append(dp[x2][y2]-dp[x1-1][y2]-dp[x2][y1-1]+dp[x1-1][y1-1]);
            sb.append("\n");
        }
        System.out.println(sb.toString());

    }

    static class Reader extends PrintWriter {private InputStream stream;private byte[] buf=new byte[1<<16];private int curChar,numChars;public Reader(){this(System.in,System.out);}public Reader(InputStream i,OutputStream o){super(o);stream=i;}public Reader(String i,String o) throws IOException{super(new FileWriter(o));stream=new FileInputStream(i);}private int nextByte(){if(numChars==-1)throw new InputMismatchException();if(curChar>=numChars){curChar=0;try{numChars=stream.read(buf);}catch(IOException e){throw new InputMismatchException();}if(numChars==-1)return-1;}return buf[curChar++];}public String next(){int c;do{c=nextByte();}while(c<=' ');StringBuilder res=new StringBuilder();do{res.appendCodePoint(c);c=nextByte();}while(c>' ');return res.toString();}public int nextInt(){int c;do{c=nextByte();}while(c<=' ');int sgn=1;if(c=='-'){sgn=-1;c=nextByte();}int res=0;do{if(c<'0'||c>'9')throw new InputMismatchException();res=10*res+c-'0';c=nextByte();}while(c>' ');return res*sgn;}public double nextDouble(){return Double.parseDouble(next());}}

}
