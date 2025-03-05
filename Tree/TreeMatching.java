 
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.*;

public class TreeMatching {
    static List<List<Integer>> tree;
    static int[][] dp;
    static void dfs(int cur,int par){
        boolean leaf=true;
        dp[cur][0]=0;dp[cur][1]=0;
        for(int child:tree.get(cur)){
            if(child==par)continue;
            leaf=false;
            dfs(child,cur);
           
        }
        if(leaf)return;
        int sum=0;
        for(int child:tree.get(cur)){
            if(child==par)continue;
            sum+=Math.max(dp[child][0], dp[child][1]);
        }
        dp[cur][0]=sum;

        int mx=0;
        for(int child:tree.get(cur)){
            if(child==par)continue;
            mx=Math.max(mx, sum-Math.max(dp[child][0],dp[child][1])+dp[child][0]+1);
        }
        dp[cur][1]=mx;
        
    }
    public static void main(String[] args) {
        Reader fr=new Reader();
        int n=fr.nextInt();
        tree=new ArrayList<>();
        for(int i=1;i<=n+1;i++){
            tree.add(new ArrayList<>());
        }
        for(int i=1;i<=n-1;i++){
            int x=fr.nextInt(),y=fr.nextInt();
            tree.get(x).add(y);
            tree.get(y).add(x);
        }
        //(i,0) sum (child,1)
        //(i,1) max (child,0)
        dp=new int[n+1][2];
        /*for(int i=0;i<n+1;i++){
            Arrays.fill(dp[i], 0);
        }*/
        dfs(1,-1);
        int ans=Math.max(dp[1][0], dp[1][1]);
        System.out.println(ans);

    }



    static class Reader extends PrintWriter {private InputStream stream;private byte[] buf=new byte[1<<16];private int curChar,numChars;public Reader(){this(System.in,System.out);}public Reader(InputStream i,OutputStream o){super(o);stream=i;}public Reader(String i,String o) throws IOException{super(new FileWriter(o));stream=new FileInputStream(i);}private int nextByte(){if(numChars==-1)throw new InputMismatchException();if(curChar>=numChars){curChar=0;try{numChars=stream.read(buf);}catch(IOException e){throw new InputMismatchException();}if(numChars==-1)return-1;}return buf[curChar++];}public String next(){int c;do{c=nextByte();}while(c<=' ');StringBuilder res=new StringBuilder();do{res.appendCodePoint(c);c=nextByte();}while(c>' ');return res.toString();}public int nextInt(){int c;do{c=nextByte();}while(c<=' ');int sgn=1;if(c=='-'){sgn=-1;c=nextByte();}int res=0;do{if(c<'0'||c>'9')throw new InputMismatchException();res=10*res+c-'0';c=nextByte();}while(c>' ');return res*sgn;}public double nextDouble(){return Double.parseDouble(next());}}

}
