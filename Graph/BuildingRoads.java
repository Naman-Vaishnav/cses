package Graph;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


public class BuildingRoads {
    public static void main(String[] args)throws IOException {
        Reader fr = new Reader();
        int n=fr.nextInt(),m=fr.nextInt();
        DSU dsu=new DSU();
        dsu.build(n);
        for(int i=0;i<m;i++){
            int x=fr.nextInt(),y=fr.nextInt();
            dsu.mergeSet(x, y);
        }
        List<Integer> ans=new ArrayList<>();
        for(int i=1;i<=n;i++){
            if(dsu.findSet(i)==i){
                ans.add(i);
            }
        }

        System.out.println(ans.size()-1);
        for(int i=1;i<ans.size();i++){
            System.out.println(ans.get(i-1)+" "+ans.get(i));
        }
    }

    public static class  DSU {
        
        int[] par,sz;

        void build(int n){
            par=new int[n+1];
            sz=new int[n+1];

            for(int i=1;i<=n;i++){
                par[i]=i;
                sz[i]=1;
            }
        }

        int findSet(int x){
            if(par[x]==x)return x;
            return par[x]=findSet(par[x]);
        }

        void mergeSet(int x,int y){
            x=findSet(x);
            y=findSet(y);
            if(x==y)return;
            if(sz[x]<sz[y]){
                x=x+y-(y=x);
            }
            sz[x]+=sz[y];
            sz[y]=0;
            par[y]=x;

        }
        
    }








    static class Reader { final private int BUFFER_SIZE = 1 << 16; private DataInputStream din; private byte[] buffer; private int bufferPointer, bytesRead; public Reader() { din = new DataInputStream(System.in); buffer = new byte[BUFFER_SIZE]; bufferPointer = bytesRead = 0; } public Reader(String file_name) throws IOException { din = new DataInputStream( new FileInputStream(file_name)); buffer = new byte[BUFFER_SIZE]; bufferPointer = bytesRead = 0; } public String readLine() throws IOException { byte[] buf = new byte[64]; int cnt = 0, c; while ((c = read()) != -1) { if (c == '\n') { if (cnt != 0) { break; } else { continue; } } buf[cnt++] = (byte)c; } return new String(buf, 0, cnt); } public int nextInt() throws IOException { int ret = 0; byte c = read(); while (c <= ' ') { c = read(); } boolean neg = (c == '-'); if (neg) c = read(); do { ret = ret * 10 + c - '0'; } while ((c = read()) >= '0' && c <= '9'); if (neg) return -ret; return ret; } public long nextLong() throws IOException { long ret = 0; byte c = read(); while (c <= ' ') c = read(); boolean neg = (c == '-'); if (neg) c = read(); do { ret = ret * 10 + c - '0'; } while ((c = read()) >= '0' && c <= '9'); if (neg) return -ret; return ret; } public double nextDouble() throws IOException { double ret = 0, div = 1; byte c = read(); while (c <= ' ') c = read(); boolean neg = (c == '-'); if (neg) c = read(); do { ret = ret * 10 + c - '0'; } while ((c = read()) >= '0' && c <= '9'); if (c == '.') { while ((c = read()) >= '0' && c <= '9') { ret += (c - '0') / (div *= 10); } } if (neg) return -ret; return ret; } private void fillBuffer() throws IOException { bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE); if (bytesRead == -1) buffer[0] = -1; } private byte read() throws IOException { if (bufferPointer == bytesRead) fillBuffer(); return buffer[bufferPointer++]; } public void close() throws IOException { if (din == null) return; din.close(); } }



    
}
