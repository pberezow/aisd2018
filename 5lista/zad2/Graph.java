import java.util.ArrayList;
import java.util.Scanner;

public class Graph {
    float[][] edges;
    int v;

    public Graph(int v) {
        this.v = v;
        edges = new float[v][v];
        for(int i=0; i<v; i++) {
            for(int j=0; j<v; j++) {
                if(i == j) {
                    edges[i][j] = 0;
                } else {
                    edges[i][j] = -1;
                }
            }
        }
    }

    public void addEdge(int u, int v, float w) {
        this.edges[u-1][v-1] = w;
    }

    public void shortestPath(int src) {
        PrioQueue pq = new PrioQueue(10);
        double[] dist = new double[this.v];
        int[] predecessor = new int[this.v];
        for(int i = 0; i<this.v; i++) {
            predecessor[i] = -1;
        }
        predecessor[src-1] = src-1;
        for(int i = 0; i<this.v; i++) {
            dist[i] = Double.POSITIVE_INFINITY;
        }

        dist[src-1] = 0;
        pq.insert(src, 0);

        while(pq.empty() == 1) {
            int u = pq.pop();
            for(int i = 0; i<this.v; i++) {
                if(edges[u-1][i] > 0) {
                    if(dist[i] > dist[u-1] + edges[u-1][i]) {
                        dist[i] = dist[u-1] + edges[u-1][i];
                        predecessor[i] = u-1;
                        pq.insert(i+1, (int)dist[i]);
                    }
                }
            }
        }

        for(int i = 0; i<this.v; i++) {
            System.out.println("Path to: " + (i+1) + ",   " + dist[i]);
        }

        for(int i = 0; i<this.v; i++) {
            if(predecessor[i] != -1) {
                String s = "]";
                int prev = -1;
                int curr = i;
                if(i == src-1) {
                    s = "[" + src + "] -- 0 --> [" + src + "]";
                }
                while(curr != src-1) {
                    if(prev == -1) {
                        s = "[" + (curr+1) + s;
                    } else {
                        s = "[" + (curr+1) + "] -- " + this.edges[curr][prev] + " --> " + s;
                    }
                    prev = curr;
                    curr = predecessor[prev];
                }
                if(i != src-1) {
                    s = "[" + (curr+1) + "] -- " + this.edges[curr][prev] + " --> " + s;
                }
                System.err.println(s);
            } else {
                System.err.println("There is no path from " + src + " to " + (i+1));
            }
        }
    }

    public static void main(String[] args) {

        long start = System.nanoTime();

        Scanner inStream = new Scanner(System.in);

        int vertex = Integer.parseInt(inStream.nextLine());
        int edges = Integer.parseInt(inStream.nextLine());

        Graph g = new Graph(vertex);

        for(int i=0; i<edges; i++) {
            g.addEdge(inStream.nextInt(), inStream.nextInt(), inStream.nextFloat());
        }

        int startV = inStream.nextInt();

        g.shortestPath(startV);

        inStream.close();

        long stop = System.nanoTime();
        System.err.println("Time: " + (stop - start)/1000000 + "ms");
    }
}