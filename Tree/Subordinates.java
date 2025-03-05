//package Tree;
 
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.*;
 
public class Subordinates {
 
    static void dfs(int cur,int par,List<List<Integer>> adj,int[] noOfChilds){
        for(int child:adj.get(cur)){
            if(child==par)continue;
            dfs(child,cur,adj,noOfChilds);
            noOfChilds[cur]+=1+noOfChilds[child];
        }
    }
    public static void main(String[] args) {
        Reader fr=new Reader();
        int n=fr.nextInt();
        List<List<Integer>> adj=new ArrayList<>();
        for(int i=1;i<=n+1;i++)adj.add(new ArrayList<>());
        for(int i=2;i<=n;i++){
            int boss=fr.nextInt();
            adj.get(boss).add(i);
            adj.get(i).add(boss);
        }
 
        int[] noOfChilds=new int[n+1];
        dfs(1,-1,adj,noOfChilds);
        StringBuilder sb=new StringBuilder();
        for(int i=1;i<=n;i++){
            sb.append(noOfChilds[i]+" ");
        }
        System.out.println(sb.toString());
 
 
    }
 
    static class Reader extends PrintWriter {private InputStream stream;private byte[] buf=new byte[1<<16];private int curChar,numChars;public Reader(){this(System.in,System.out);}public Reader(InputStream i,OutputStream o){super(o);stream=i;}public Reader(String i,String o) throws IOException{super(new FileWriter(o));stream=new FileInputStream(i);}private int nextByte(){if(numChars==-1)throw new InputMismatchException();if(curChar>=numChars){curChar=0;try{numChars=stream.read(buf);}catch(IOException e){throw new InputMismatchException();}if(numChars==-1)return-1;}return buf[curChar++];}public String next(){int c;do{c=nextByte();}while(c<=' ');StringBuilder res=new StringBuilder();do{res.appendCodePoint(c);c=nextByte();}while(c>' ');return res.toString();}public int nextInt(){int c;do{c=nextByte();}while(c<=' ');int sgn=1;if(c=='-'){sgn=-1;c=nextByte();}int res=0;do{if(c<'0'||c>'9')throw new InputMismatchException();res=10*res+c-'0';c=nextByte();}while(c>' ');return res*sgn;}public double nextDouble(){return Double.parseDouble(next());}}
 
}