import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;

public class Graph {
    float[][] edges;
    int v;

    class Edge {
        int u;
        int v;
        float w;
    
        public Edge(int u, int v, float weight) {
            this.u = u;
            this.v = v;
            this.w = weight;
        }
    
        public String toString() {
            return u + " - " + v + " (" + w + ")";
        }
    }


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

    public Graph(ArrayList<Edge> mst) {
        this.v = mst.size() + 1;
        // System.out.println(this.v);
        this.edges = new float[v][v];
        for(int i=0; i<v; i++) {
            for(int j=0; j<v; j++) {
                if(i == j) {
                    edges[i][j] = 0;
                } else {
                    edges[i][j] = -1;
                }
            }
        }
        for(Edge e: mst) {
            addEdge(e.u, e.v, e.w);
        }
    }

    public void addEdge(int u, int v, float w) {
        this.edges[u-1][v-1] = w;
        this.edges[v-1][u-1] = w;
    }

    public ArrayList<Edge> prim() {
        PrioQueue pq = new PrioQueue(10);
        boolean[] visited = new boolean[this.v];
        for(int i=0; i<visited.length; i++) {
            visited[i] = false;
        }

        float[] key = new float[this.v];
        for(int i=0; i<key.length; i++) {
            key[i] = Float.MAX_VALUE;
        }

        int[] parent = new int[this.v];
        for(int i=0; i<parent.length; i++) {
            parent[i] = -1;
        }

        ArrayList<Edge> list = new ArrayList<>();

        // int v = 1;
        pq.insert(1, 0);
        int u;

        visited[0] = true;
        key[0] = 0;

        while(pq.empty() == 1) {
            u = pq.pop();
            visited[u-1] = true;
            for(int i=0; i<this.v; i++) {
                if(this.edges[u-1][i] > 0) {
                    int v = i+1;
                    float weight = this.edges[u-1][v-1];

                    if(visited[v-1] == false && key[v-1] > weight) {
                        key[v-1] = weight;
                        pq.insert(v, key[v-1]);
                        parent[v-1] = u;
                    }
                }
            }
        }
        for(int i=1; i<this.v; i++) {
            list.add(new Edge(i+1, parent[i], key[i]));
        }

        // for(Edge e : list) {
        //     System.out.println(e.toString());
        // }
        return list;
    }

    //kruskal ------------------------------------

    class Subset {
        int parent, rank;

        public Subset(int parent, int rank) {
            this.parent = parent;
            this.rank = rank;
        }
    }

    private int find(Subset subsets[], int i) {
        if(subsets[i].parent != i) {
            subsets[i].parent = find(subsets, subsets[i].parent);
        }
        return subsets[i].parent;
    }

    private void union(Subset subsets[], int x, int y) {
        int xroot = find(subsets, x);
        int yroot = find(subsets, y);

        if(subsets[xroot].rank < subsets[yroot].rank) {
            subsets[yroot].parent = xroot;
        } else if(subsets[xroot].rank > subsets[yroot].rank) {
            subsets[xroot].parent = yroot;
        } else {
            subsets[yroot].parent = xroot;
            subsets[xroot].rank++;
        }
    }

    public ArrayList<Edge> kruskal() {
        ArrayList<Edge> edges = new ArrayList<>();
        PrioQueue pq = new PrioQueue(10);
        int counter = 0;
        for(int i=0; i<this.v; i++) {
            for(int j=i+1; j<this.v; j++) {
                if(this.edges[i][j] > 0) {
                    edges.add(new Edge(i+1, j+1, this.edges[i][j]));
                    pq.insert(counter, this.edges[i][j]);
                    counter++;
                }
            }
        }

        Subset[] subsets = new Subset[this.v];
        for(int i=0; i<subsets.length; i++) {
            subsets[i] = new Subset(i, 0);
        }

        ArrayList<Edge> list = new ArrayList<>();

        while(pq.empty() == 1) {
            int idx = pq.pop();
            int u = edges.get(idx).u;
            int v = edges.get(idx).v;

            int x = find(subsets, u-1);
            int y = find(subsets, v-1);

            if(x != y) {
                list.add(edges.get(idx));
                union(subsets, x, y);
            }
        }

        // for(Edge e : list) {
            // System.out.println(e.toString());
        // }

        return list;
    }

    public void randTraverse() {
        long m = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        long start = System.nanoTime();

        boolean[] visited = new boolean[this.v];
        for(int i=0; i<visited.length; i++) {
            visited[i] = false;
        }
        int visits = 1;

        int steps = 0;
        float w = 0;
        Random gen = new Random();

        int v = gen.nextInt(this.v) + 1;
        visited[v-1] = true;
        while(visits < this.v) {
            int u = gen.nextInt(this.v) + 1;
            if(visited[u-1] == false) {
                visited[u-1] = true;
                visits++;
                // System.out.println("visits: " + visits);
            }
            w += this.edges[v-1][u-1];
            v = u;
            steps++;
        }
        long stop = System.nanoTime();
        long m2 = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory() - m;

        System.out.println("Koszt trasy: " + w);
        System.out.println("Kroki: " + steps);
        System.out.println("Czas: " + (stop-start)/1000000);
        System.out.println("Pamiec: " + m);
    }

    public void costTraverse() {
        long m = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        long start = System.nanoTime();

        boolean[] visited = new boolean[this.v];
        for(int i=0; i<visited.length; i++) {
            visited[i] = false;
        }
        int visits = 1;

        int steps = 0;
        float w = 0;
        // int m = 0;
        Random gen = new Random();

        int v = gen.nextInt(this.v) + 1;
        visited[v-1] = true;
        while(visits < this.v) {
            PrioQueue pq = new PrioQueue(this.v);
            for(int i=0; i<this.v; i++) {
                if(visited[i] == false) {
                    pq.insert(i+1, this.edges[v-1][i]);
                }
            }
            int u = pq.pop();
            visited[u-1] = true;
            visits++;
            // System.out.println("visits: " + visits);
            w += this.edges[v-1][u-1];
            v = u;
            steps++;
        }
        long stop = System.nanoTime();
        long m2  = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory() - m;
        
        System.out.println("Koszt trasy: " + w);
        System.out.println("Kroki: " + steps);
        System.out.println("Czas: " + (stop-start)/1000000);
        System.out.println("Pamiec: " + m2);
    }

    public void removeEdge(int u, int v) {
        this.edges[u-1][v-1] = -1;
        this.edges[v-1][u-1] = -1;
    }

    public void eulerTraverse() {
        long m = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        long start = System.nanoTime();

        ArrayList<Edge> mst = this.prim();
        Graph g = new Graph(mst);

        boolean[] visited = new boolean[g.v];
        for(int i=0; i<visited.length; i++) {
            visited[i] = false;
        }
        int visits = 1;

        int steps = 0;
        float w = 0;
        // int m = 0;
        Random gen = new Random();

        int v = gen.nextInt(g.v) + 1;
        int u = 0;
        // while(u != 0) {
        do {
            u = 0;
            for(int i=0; i<g.v; i++) {
                if(g.edges[v-1][i] > 0) {
                    if(visited[i] == false) {
                        u = i+1;
                    }
                }
            }
            if(u == 0) {
                for(int i=0; i<g.v; i++) {
                    if(g.edges[v-1][i] > 0) {
                        u = i+1;
                        w += g.edges[v-1][u-1];
                        g.removeEdge(u, v);
                        // System.out.println(w);
                    }
                }
            } else {
                visited[u-1] = true;
                w += g.edges[v-1][u-1];
                // System.out.println(w);
            }
            visits++;
            // System.out.println(u + " visits: " + visits);
            v = u;
            steps++;
        } while(u != 0);

        long stop = System.nanoTime();
        long m2  = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory() - m;

        System.out.println("Koszt trasy: " + w);
        System.out.println("Kroki: " + steps);
        System.out.println("Czas: " + (stop - start)/1000000);
        System.out.println("Pamiec: " + m2);
    }

    public static void main(String[] args) {

        // long start = System.nanoTime();

        Scanner inStream = new Scanner(System.in);

        int vertex = inStream.nextInt();

        Graph g = new Graph(vertex);

        int edges = g.v*(g.v-1)/2;
        for(int i=0; i<edges; i++) {
            g.addEdge(inStream.nextInt(), inStream.nextInt(), inStream.nextFloat());
        }
        inStream.close();

        g.randTraverse();

        g.costTraverse();

        g.eulerTraverse();


        // long stop = System.nanoTime();
        // System.err.println("Time: " + (stop - start)/1000000 + "ms");
    }
}