public class Stat {
    int insert;
    int load;
    int delete;
    int find;
    int min;
    int max;
    int successor;
    int inorder;
    int maxEl;
    int currEl;
    long start;
    long stop;

    public Stat() {
        this.insert = 0;
        this.load = 0;
        this.delete = 0;
        this.find = 0;
        this.min = 0;
        this.max = 0;
        this.successor = 0;
        this.inorder = 0;
        this.maxEl = 0;
        this.currEl = 0;
        this.start = 0;
        this.stop = 0;
    }

    public void startClock() {
        this.start = System.nanoTime();
    }

    public void stopClock() {
        this.stop = System.nanoTime();
    }

    public void insert() {
        this.insert++;
    }

    public void load() {
        this.load++;
    }

    public void delete() {
        this.delete++;
    }

    public void find() {
        this.find++;
    }

    public void min() {
        this.min++;
    }

    public void max() {
        this.max++;
    }

    public void successor() {
        this.successor++;
    }

    public void inorder() {
        this.inorder++;
    }

    public void setCurrEl(int el) {
        this.currEl = el;
        if(this.currEl > this.maxEl) {
            this.maxEl = this.currEl;
        }
    }

    public void addEl() {
        if(this.currEl == this.maxEl) {
            this.maxEl++;
        }
        this.currEl++;
    }

    public void delEl() {
        this.currEl--;
    }

    public long getTime() {
        return this.stop - this.start;
    }

    public void printStats() {
        System.err.println("----------Statistics----------");
        System.err.println("Time: " + this.getTime() + "ns");
        System.err.println("Insert operations: " + this.insert);
        System.err.println("Load operations: " + this.load);
        System.err.println("Delete operations: " + this.delete);
        System.err.println("Find operations: " + this.find);
        System.err.println("Min operations: " + this.min);
        System.err.println("Max operations: " + this.max);
        System.err.println("Successor operations: " + this.successor);
        System.err.println("Inorder operations: " + this.inorder);
        System.err.println("Max quantity of elements: " + this.maxEl);
        System.err.println("Current quantity of elements: " + this.currEl);
    }

}