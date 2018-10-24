public class RBTree extends Structure {
    RBTNode root;

    public RBTree() {
        this.root = null;
    }

    public RBTree(String key) {
        this.root = new RBTNode(key);
    }

    public void setRoot(RBTNode newRoot) {
        this.root = newRoot;
    }

    @Override
    public void insert(String s) {
        if(root == null) {
            root = new RBTNode(s);
        } else {
            root.insert(this, s);
        }
    }

    @Override
    public void delete(String s) {
        if(root != null) {
            root.delete(this, s);
        }
    }

    @Override
    public boolean find(String s) {
        if(root.find(s) != null) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String min() {
        if(root != null) {
            return root.min();
        } else {
            return "";
        }
    }

    @Override
    public String max() {
        if(root != null) {
            return root.max();
        } else {
            return "";
        }
    }

    @Override
    public String successor(String k) {
        return root.successor(k);
    }

    @Override
    public String inorder() {
        // if(root.checkColors()) {
        //     System.err.println("Colors OK");
        // } else {
        //     System.err.println("Wrong colors");
        // }
        checkNodes();
        return root.inorder("");
    }

    public int size() {
        if(root != null) {
            return root.size();
        } else {
            return 0;
        }
    }

    public String get(int idx) {
        return null;
    }
}