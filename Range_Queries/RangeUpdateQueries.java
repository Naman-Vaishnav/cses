import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.InputMismatchException;
class FenwickTree{
    long[] BIT;
    int size;

    FenwickTree(int n){
        this.BIT=new long[n+1];
        Arrays.fill(BIT, 0);
        this.size=n;
    }

    void update(int i,int delta){
        for(;i<=size;i+=(i&-i)){
            BIT[i]+=delta;
        }
    }

    long preFixSum(int i){
        long ans=0;
        for(;i>0;i-=(i&-i)){
            ans+=BIT[i];
        }
        return ans;
    }

   /*  int sum(int l,int r){
        return preFixSum(r)-preFixSum(l-1);
    }*/
}
public class RangeUpdateQueries {


    public static void main(String[] args) {
        Reader fr=new Reader();
        int n,q;
        n=fr.nextInt();
        q=fr.nextInt();
        FenwickTree bit=new FenwickTree(n+2);
        for(int i=1;i<=n;i++){
            int x=fr.nextInt();
            bit.update(i, x);
            bit.update(i+1, -x);
        }

        for(int i=0;i<q;i++){
            int type=fr.nextInt();
            if(type==1){
                int u=fr.nextInt();
                int v=fr.nextInt();
                int val=fr.nextInt();
                bit.update(u, val);
                bit.update(v+1, -val);
                
            }
            else{
                int k=fr.nextInt();
                long ans=bit.preFixSum(k);
                System.out.println(ans);
            }
        }

    }
    static class Reader extends PrintWriter {private InputStream stream;private byte[] buf=new byte[1<<16];private int curChar,numChars;public Reader(){this(System.in,System.out);}public Reader(InputStream i,OutputStream o){super(o);stream=i;}public Reader(String i,String o) throws IOException{super(new FileWriter(o));stream=new FileInputStream(i);}private int nextByte(){if(numChars==-1)throw new InputMismatchException();if(curChar>=numChars){curChar=0;try{numChars=stream.read(buf);}catch(IOException e){throw new InputMismatchException();}if(numChars==-1)return-1;}return buf[curChar++];}public String next(){int c;do{c=nextByte();}while(c<=' ');StringBuilder res=new StringBuilder();do{res.appendCodePoint(c);c=nextByte();}while(c>' ');return res.toString();}public int nextInt(){int c;do{c=nextByte();}while(c<=' ');int sgn=1;if(c=='-'){sgn=-1;c=nextByte();}int res=0;do{if(c<'0'||c>'9')throw new InputMismatchException();res=10*res+c-'0';c=nextByte();}while(c>' ');return res*sgn;}public double nextDouble(){return Double.parseDouble(next());}}

}
