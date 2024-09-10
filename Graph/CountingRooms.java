package Graph;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class CountingRooms {
    public static void main(String[] args)throws IOException {
        //Reader fr=new Reader();
        BufferedReader fr = new BufferedReader(new InputStreamReader(System.in));
        String[] nm = fr.readLine().split(" ");
        int n = Integer.parseInt(nm[0]);
        int m = Integer.parseInt(nm[1]);


        char[][] grid=new char[n][m];

        for(int i=0;i<n;i++){  
            String row=fr.readLine();
            for(int j=0;j<m;j++){
                grid[i][j]=row.charAt(j);
            }    
                      
        }
        boolean[][] visited=new boolean[n][m];
        int [][] dir={
            {1,0},{-1,0},{0,1},{0,-1}
        };
        int ans=0;
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                
                if(visited[i][j]==false&&grid[i][j]=='.'){
                    ans++;
                    Queue<Pair<Integer,Integer>> q=new LinkedList<>();
                    q.add(new Pair<>(i, j));
                    visited[i][j]=true;

                    while(q.size()>0){
                        Pair<Integer,Integer> cur=q.peek();
                        q.poll();

                        for(int k=0;k<4;k++){
                            int newX=cur.x+dir[k][0],newY=cur.y+dir[k][1];
                            if(newX>=0&&newX<n&&newY>=0&&newY<m
                            &&visited[newX][newY]==false&&grid[newX][newY]=='.'){
                                q.add(new Pair<Integer,Integer>(newX, newY));
                                visited[newX][newY]=true;
                            }
                        }

                    }

                }

            }
        }

        System.out.println(ans);


    }


    static public class Pair<T1, T2> {
        T1 x;T2 y;
        Pair(T1 x,T2 y){
            this.x=x;this.y=y;
        }
    }


    static class Reader { final private int BUFFER_SIZE = 1 << 16; private DataInputStream din; private byte[] buffer; private int bufferPointer, bytesRead; public Reader() { din = new DataInputStream(System.in); buffer = new byte[BUFFER_SIZE]; bufferPointer = bytesRead = 0; } public Reader(String file_name) throws IOException { din = new DataInputStream( new FileInputStream(file_name)); buffer = new byte[BUFFER_SIZE]; bufferPointer = bytesRead = 0; } public String readLine() throws IOException { byte[] buf = new byte[64]; int cnt = 0, c; while ((c = read()) != -1) { if (c == '\n') { if (cnt != 0) { break; } else { continue; } } buf[cnt++] = (byte)c; } return new String(buf, 0, cnt); } 
    public char readChar() throws IOException { int charInt = read(); if (charInt == -1) { throw new IOException("End of stream reached"); } return (char) charInt; }
    public int nextInt() throws IOException { int ret = 0; byte c = read(); while (c <= ' ') { c = read(); } boolean neg = (c == '-'); if (neg) c = read(); do { ret = ret * 10 + c - '0'; } while ((c = read()) >= '0' && c <= '9'); if (neg) return -ret; return ret; } public long nextLong() throws IOException { long ret = 0; byte c = read(); while (c <= ' ') c = read(); boolean neg = (c == '-'); if (neg) c = read(); do { ret = ret * 10 + c - '0'; } while ((c = read()) >= '0' && c <= '9'); if (neg) return -ret; return ret; } public double nextDouble() throws IOException { double ret = 0, div = 1; byte c = read(); while (c <= ' ') c = read(); boolean neg = (c == '-'); if (neg) c = read(); do { ret = ret * 10 + c - '0'; } while ((c = read()) >= '0' && c <= '9'); if (c == '.') { while ((c = read()) >= '0' && c <= '9') { ret += (c - '0') / (div *= 10); } } if (neg) return -ret; return ret; } private void fillBuffer() throws IOException { bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE); if (bytesRead == -1) buffer[0] = -1; } private byte read() throws IOException { if (bufferPointer == bytesRead) fillBuffer(); return buffer[bufferPointer++]; } public void close() throws IOException { if (din == null) return; din.close(); } }



}
