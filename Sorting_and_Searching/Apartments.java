

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Apartments {
    public static void main(String[] args) throws IOException {
        Reader fr=new Reader();
        int n,m,k;
        n=fr.nextInt();
        m=fr.nextInt();
        k=fr.nextInt();
        List<Integer> applicants=new ArrayList<>();
        for(int i=0;i<n;i++){
            int x=fr.nextInt();
            applicants.add(x);
        }
        List<Integer> apartments=new ArrayList<>();
        for(int i=0;i<m;i++){
            int x=fr.nextInt();
            apartments.add(x);
        }
        Collections.sort(applicants);
        Collections.sort(apartments);
        int i=0,j=0,ans=0;

        while(i<n&&j<m){
            int diff=Math.abs(applicants.get(i)-apartments.get(j));
            if(diff<=k){ans++;i++;j++;}
            else if(applicants.get(i)<apartments.get(j))i++;//applicants 45 60 appartment 30 60
            else j++;
        }
        System.out.println(ans);

    }



     static class Reader { final private int BUFFER_SIZE = 1 << 16; private DataInputStream din; private byte[] buffer; private int bufferPointer, bytesRead; public Reader() { din = new DataInputStream(System.in); buffer = new byte[BUFFER_SIZE]; bufferPointer = bytesRead = 0; } public Reader(String file_name) throws IOException { din = new DataInputStream( new FileInputStream(file_name)); buffer = new byte[BUFFER_SIZE]; bufferPointer = bytesRead = 0; } public String readLine() throws IOException { byte[] buf = new byte[64]; int cnt = 0, c; while ((c = read()) != -1) { if (c == '\n') { if (cnt != 0) { break; } else { continue; } } buf[cnt++] = (byte)c; } return new String(buf, 0, cnt); } public int nextInt() throws IOException { int ret = 0; byte c = read(); while (c <= ' ') { c = read(); } boolean neg = (c == '-'); if (neg) c = read(); do { ret = ret * 10 + c - '0'; } while ((c = read()) >= '0' && c <= '9'); if (neg) return -ret; return ret; } public long nextLong() throws IOException { long ret = 0; byte c = read(); while (c <= ' ') c = read(); boolean neg = (c == '-'); if (neg) c = read(); do { ret = ret * 10 + c - '0'; } while ((c = read()) >= '0' && c <= '9'); if (neg) return -ret; return ret; } public double nextDouble() throws IOException { double ret = 0, div = 1; byte c = read(); while (c <= ' ') c = read(); boolean neg = (c == '-'); if (neg) c = read(); do { ret = ret * 10 + c - '0'; } while ((c = read()) >= '0' && c <= '9'); if (c == '.') { while ((c = read()) >= '0' && c <= '9') { ret += (c - '0') / (div *= 10); } } if (neg) return -ret; return ret; } private void fillBuffer() throws IOException { bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE); if (bytesRead == -1) buffer[0] = -1; } private byte read() throws IOException { if (bufferPointer == bytesRead) fillBuffer(); return buffer[bufferPointer++]; } public void close() throws IOException { if (din == null) return; din.close(); } }
}
