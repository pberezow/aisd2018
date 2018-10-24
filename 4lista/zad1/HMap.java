public class HMap extends Structure {
    int size;
    int n;
    Structure[] data;

    HMap() {
        size = 6803;
        data = new Structure[6803];
        n = 50;
        for(int i=0; i<size; i++) {
            data[i] = new MyList();
        }
    }

    int fh(String s) {
        // int R = 3;
        // int hash = 0;
        // for(int i=0; i<s.length(); i++) {
        //     hash = (R * hash + s.charAt(i)) % this.size;
        // }
        // return hash;
        return (s.hashCode() & 0x7fffffff) % this.size;
    }

    void rebuild(int idx) {
        if(data[idx] instanceof MyList) {
            // RBTree tree = new RBTree();
            MyList list = (MyList) data[idx];
            data[idx] = new RBTree();
            int size = list.size();
            for(int i = 0; i<size; i++) {
                data[idx].insert(list.get(i));
            }
            // data[idx] = tree;
        } else {
            // MyList list = new MyList();
            RBTree tree = (RBTree) data[idx];
            data[idx] = new MyList();
            String elements = tree.inorder();
            String[] elList = elements.split(" ");
            for (String el : elList) {
                data[idx].insert(el);
            }
            // data[idx] = list;
        }
    }

    public void insert(String s) {
        int idx = fh(s);
        this.data[idx].insert(s);
        if(data[idx].size() == n) {
            rebuild(idx);
        }
    }

    public void delete(String s) {
        int idx = fh(s);
        this.data[idx].delete(s);
        if(data[idx].size() == n) {
            rebuild(idx);
        }
    }

    public boolean find(String s) {
        int idx = fh(s);
        return this.data[idx].find(s);
    }

    public String min() {
        return "";
    }

    public String max() {
        return "";
    }

    public String successor(String k) {
        return "";
    }
    public String inorder() {
        // usedBuckets();
        return "";
    }

    public String get(int idx) {
        return null;
    }

    public int size() {
        int size = 0;
        for(int i=0; i<this.size; i++) {
            size += this.data[i].size();
        }
        return size;
    }

    public int usedBuckets() {
        int count =0;
        for(int i=0; i<this.size; i++) {
            if(this.data[i].size() > 0) {
                count++;
            }
        }
        System.err.println("Used buckets: " + count);
        return count;
    }
}