import java.util.ArrayList;
import java.util.Scanner;

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


        // for(int i=1; i<this.v; i++) {
        //     for(int j=0; j<this.v; j++) {
        //         if(this.edges[v-1][j] > 0) {
        //             if(visited[j] == false) {
        //                 pq.insert(j+1, this.edges[v-1][j]);
        //                 System.out.println("Koszt " + (j+1) + ", " + v + "; " + this.edges[v-1][j]);
        //             }
        //         }
        //     }
        //     u = pq.pop();
        //     System.out.println(u);
        //     while(visited[u-1] == true) {
        //         u = pq.pop();
        //         System.out.println(u);
        //     }
        //     visited[u-1] = true;
        //     list.add(new Edge(v, u, this.edges[v-1][u-1]));
        //     v = u;
        // }
        float weight = 0;
        for(Edge e : list) {
            System.out.println(e.toString());
            weight += e.w;
        }
        System.out.println("Koszt: " + weight);
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

        float weight = 0;
        for(Edge e : list) {
            System.out.println(e.toString());
            weight += e.w;
        }
        System.out.println("Koszt: " + weight);

        return list;
    }

    public static void main(String[] args) {
        // Graph g = new Graph(8);
        // g.addEdge(1, 2, 13);
        String type = "";

        try {
            type = args[0];
        } catch(ArrayIndexOutOfBoundsException ex) {
            System.err.println("Type incorrect. Try '-p' for Prim's Algorithm or '-k' for Kruskal's Algorithm");
            return;
        }
        // System.out.println(type);

        long start = System.nanoTime();

        Scanner inStream = new Scanner(System.in);

        int vertex = Integer.parseInt(inStream.nextLine());
        int edges = Integer.parseInt(inStream.nextLine());

        Graph g = new Graph(vertex);

        for(int i=0; i<edges; i++) {
            g.addEdge(inStream.nextInt(), inStream.nextInt(), inStream.nextFloat());
        }

        if(type.equals("-p")) {
            g.prim();
        } else if(type.equals("-k")) {
            g.kruskal();
        } else {
            System.err.println("Type incorrect. Try '-p' for Prim's Algorithm or '-k' for Kruskal's Algorithm");
            return;
        }

        inStream.close();

        long stop = System.nanoTime();
        System.err.println("Time: " + (stop - start)/1000000 + "ms");
    }
}