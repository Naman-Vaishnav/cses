 
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.*;

public class TreeDistancesI {
    static List<List<Integer>> tree;
    static boolean[] vis;
    static int[][] dis;
    static int mx=0,end;
    static void dfs(int cur,int par,int curDis,int i){
        dis[cur][i]=curDis;
        if(curDis>mx){
            mx=curDis;
            end=cur;
        }
        for(int child:tree.get(cur)){
            if(child==par)continue;
            dfs(child,cur,curDis+1,i);
        }
    }
    public static void main(String[] args) {
        Reader fr=new Reader();
        int n=fr.nextInt();
        tree=new ArrayList<>();
        vis=new boolean[n+1];
        for(int i=1;i<=n+1;i++){
            tree.add(new ArrayList<>());
        }
        for(int i=1;i<=n-1;i++){
            int x=fr.nextInt(),y=fr.nextInt();
            tree.get(x).add(y);
            tree.get(y).add(x);
        }
        //(i,0) sum (child,1)
        dis=new int[n+1][2];
        dfs(1,-1,0,0);
        mx=0;
        int end1=end;
        dfs(end1,-1,0,0);
        mx=0;
        int end2=end;
        dfs(end2,-1,0,1);
        StringBuilder sb=new StringBuilder();
        for(int i=1;i<=n;i++){
            sb.append(Math.max(dis[i][0],dis[i][1])+" ");
        }

        System.out.println(sb.toString());

    }



    static class Reader extends PrintWriter {private InputStream stream;private byte[] buf=new byte[1<<16];private int curChar,numChars;public Reader(){this(System.in,System.out);}public Reader(InputStream i,OutputStream o){super(o);stream=i;}public Reader(String i,String o) throws IOException{super(new FileWriter(o));stream=new FileInputStream(i);}private int nextByte(){if(numChars==-1)throw new InputMismatchException();if(curChar>=numChars){curChar=0;try{numChars=stream.read(buf);}catch(IOException e){throw new InputMismatchException();}if(numChars==-1)return-1;}return buf[curChar++];}public String next(){int c;do{c=nextByte();}while(c<=' ');StringBuilder res=new StringBuilder();do{res.appendCodePoint(c);c=nextByte();}while(c>' ');return res.toString();}public int nextInt(){int c;do{c=nextByte();}while(c<=' ');int sgn=1;if(c=='-'){sgn=-1;c=nextByte();}int res=0;do{if(c<'0'||c>'9')throw new InputMismatchException();res=10*res+c-'0';c=nextByte();}while(c>' ');return res*sgn;}public double nextDouble(){return Double.parseDouble(next());}}

}
