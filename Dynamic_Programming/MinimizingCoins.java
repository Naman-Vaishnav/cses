//package Dynamic_Programming;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;

public class MinimizingCoins {
    static int mx=(int)Math.pow(10, 6)+1;
    static int minCoins(int x,int n,int[] c,int[][] dp){
        if(x==0)return 0;
        if(n==0)return mx;
        if(dp[n][x]!=-1)return dp[n][x];
        int ans=mx;
        for(int i=1;i<=n;i++){
            ans=Integer.min(ans, minCoins(x,n-1 ,c , dp));
            if(x-c[n]>=0)ans=Integer.min(ans,1+minCoins(x-c[n],n ,c , dp));
        }
        return dp[n][x]=ans;

    }
    public static void main(String[] args) throws IOException {
        Reader fr=new Reader();
        int n,x;
        n=fr.nextInt();
        x=fr.nextInt();
        int[] c=new int[n+1];
        for(int i=1;i<=n;i++){
            int temp=fr.nextInt();
            c[i]=temp;
        }
        //int[][] dp=new int[n+1][x+1];
        //for(int i=1;i<=n;i++){
        //    Arrays.fill(dp[i], -1);
       // }
        //int ans=minCoins(x,n,c,dp);
        //if(ans==mx)ans=-1;
        //System.out.println(ans);

        int[] dp=new int[x+1];
        Arrays.fill(dp, mx);
        dp[0]=0;
        
            for(int j=1;j<=x;j++){
                for(int i=1;i<=n;i++){
                    if(j-c[i]>=0)dp[j]=Math.min(dp[j],1+dp[j-c[i]]);
                   
                }
            }
            System.out.println(dp[x]==mx?-1:dp[x]);
        


    }




    static class Reader { final private int BUFFER_SIZE = 1 << 16; private DataInputStream din; private byte[] buffer; private int bufferPointer, bytesRead; public Reader() { din = new DataInputStream(System.in); buffer = new byte[BUFFER_SIZE]; bufferPointer = bytesRead = 0; } public Reader(String file_name) throws IOException { din = new DataInputStream( new FileInputStream(file_name)); buffer = new byte[BUFFER_SIZE]; bufferPointer = bytesRead = 0; } public String readLine() throws IOException { byte[] buf = new byte[64]; int cnt = 0, c; while ((c = read()) != -1) { if (c == '\n') { if (cnt != 0) { break; } else { continue; } } buf[cnt++] = (byte)c; } return new String(buf, 0, cnt); } public int nextInt() throws IOException { int ret = 0; byte c = read(); while (c <= ' ') { c = read(); } boolean neg = (c == '-'); if (neg) c = read(); do { ret = ret * 10 + c - '0'; } while ((c = read()) >= '0' && c <= '9'); if (neg) return -ret; return ret; } public long nextLong() throws IOException { long ret = 0; byte c = read(); while (c <= ' ') c = read(); boolean neg = (c == '-'); if (neg) c = read(); do { ret = ret * 10 + c - '0'; } while ((c = read()) >= '0' && c <= '9'); if (neg) return -ret; return ret; } public double nextDouble() throws IOException { double ret = 0, div = 1; byte c = read(); while (c <= ' ') c = read(); boolean neg = (c == '-'); if (neg) c = read(); do { ret = ret * 10 + c - '0'; } while ((c = read()) >= '0' && c <= '9'); if (c == '.') { while ((c = read()) >= '0' && c <= '9') { ret += (c - '0') / (div *= 10); } } if (neg) return -ret; return ret; } private void fillBuffer() throws IOException { bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE); if (bytesRead == -1) buffer[0] = -1; } private byte read() throws IOException { if (bufferPointer == bytesRead) fillBuffer(); return buffer[bufferPointer++]; } public void close() throws IOException { if (din == null) return; din.close(); } }
}
