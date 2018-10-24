import java.util.LinkedList;

public class MyList extends Structure {
    LinkedList<String> list;

    public MyList() {
        this.list = new LinkedList<>();
    }

    public String get(int idx) {
        return list.get(idx);
    }

    public void insert(String s) {
        list.add(s);
    }

    public void delete(String s) {
        list.remove(s);
    }

    public boolean find(String s) {
        return list.contains(s);
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
        return "";
    }

    public int size() {
        return list.size();
    }
}