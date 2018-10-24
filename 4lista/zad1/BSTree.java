public class BSTree extends Structure{
    BSTNode root;

    public BSTree(String key) {
        this.root = new BSTNode(null, key);
    }

    public BSTree() {
        this.root = null;
    }

    @Override
    public void insert(String s) {
        if(root == null) {
            root = new BSTNode(null, s);
        }
        else {
            root.insert(s);
        }
    }

    @Override
    public void delete(String s) {
        this.root = this.root.delete(this.root, s);
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
            return null;
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
        String list = "";
        // System.err.println("InOrder:");
        // if(root == null) {
        //     System.err.println();
        //     return "";
        // }
        // BSTNode successor = root.minNode();
        // while(successor != null) {
        //     System.err.print(successor.key + ", ");
        //     list = list + successor.key + " ";
        //     successor = successor.successor(successor);
        //     // try { Thread.sleep(200); } catch(Exception e) {}
        // }
        // System.err.println();
        list = root.inorder(list);
        return list;
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