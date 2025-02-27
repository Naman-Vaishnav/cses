//package Graph;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.*;

public class RoundTrip {

    static int end=-1,start=-1;
    static boolean dfs(List<List<Integer>> adj,int cur,int[] vis,int par,int[] p){
        vis[cur]=1;
        for(int child:adj.get(cur)){
            if(child==par)continue;
            if(vis[child]==1){
                start=child;
                end=cur;
                return true;
            }
            p[child]=cur;
            if(dfs(adj,child,vis,cur,p))return true;
            
        }
        return false;
    }
    public static void main(String[] args) {
        Reader fr=new Reader();
        int n,m;
        n=fr.nextInt();
        m=fr.nextInt();
        List<List<Integer>> adj=new ArrayList<>();
        for(int i=0;i<=n;i++){
            adj.add(new ArrayList<>());
        }
        for(int i=0;i<m;i++){
            int x,y;
            x=fr.nextInt();
            y=fr.nextInt();
            adj.get(x).add(y);
            adj.get(y).add(x);
        }
        int[] vis=new int[n+1];
        int[] p=new int[n+1];
        Arrays.fill(vis, -1);
        Arrays.fill(p, -1);
       
        for(int i=1;i<=n;i++){
          if(vis[i]==-1&&dfs(adj,i,vis,-1,p))break;
        }

            if(start==-1){
            System.out.println("IMPOSSIBLE");
            }
            else{
                int size=2;
                StringBuilder sb=new StringBuilder();
                int temp=end;
                while(end!=start){
                    sb.append(end+" ");
                    end=p[end];
                    size++;
                }
                sb.append(start+" ");
                sb.append(temp+" ");
                System.out.println(size);
                System.out.println(sb.toString());
            }

        

        
    }








    static class Reader extends PrintWriter {private InputStream stream;private byte[] buf=new byte[1<<16];private int curChar,numChars;public Reader(){this(System.in,System.out);}public Reader(InputStream i,OutputStream o){super(o);stream=i;}public Reader(String i,String o) throws IOException{super(new FileWriter(o));stream=new FileInputStream(i);}private int nextByte(){if(numChars==-1)throw new InputMismatchException();if(curChar>=numChars){curChar=0;try{numChars=stream.read(buf);}catch(IOException e){throw new InputMismatchException();}if(numChars==-1)return-1;}return buf[curChar++];}public String next(){int c;do{c=nextByte();}while(c<=' ');StringBuilder res=new StringBuilder();do{res.appendCodePoint(c);c=nextByte();}while(c>' ');return res.toString();}public int nextInt(){int c;do{c=nextByte();}while(c<=' ');int sgn=1;if(c=='-'){sgn=-1;c=nextByte();}int res=0;do{if(c<'0'||c>'9')throw new InputMismatchException();res=10*res+c-'0';c=nextByte();}while(c>' ');return res*sgn;}public double nextDouble(){return Double.parseDouble(next());}}

}



