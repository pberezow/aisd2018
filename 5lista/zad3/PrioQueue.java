import java.util.Arrays;

public class PrioQueue {
    final static int EMPTY_QUEUE = -2147483647;
    Element[] heap;
    int size;

    class Element {
        int val;
        float prio;
    
        public Element(int val, float prio) {
            this.val = val;
            this.prio = prio;
        }
    }

    public PrioQueue(int size) {
        this.heap = new Element[size];
        this.size = 0;
    }

    public int empty() {
        if(heap[0] == null) {
            return 0;
        } else {
            return 1;
        }
    }

    private void resizeHeap() {
        Element[] newHeap = Arrays.copyOf(this.heap, this.size*2);
        this.heap = newHeap;
    }

    public void insert(int x, float p) {
        if(size >= this.heap.length) {
            resizeHeap();
        }
        int i = this.size;
        int j = (i-1) / 2;
        Element newElement = new Element(x, p);
        this.size++;

        while(i > 0 && this.heap[j].prio > p) {
            this.heap[i] = this.heap[j];
            i = j;
            j = (i-1) / 2;
        }
        this.heap[i] = newElement;
    }

    public int pop() {
        if(this.size == 0) {
            return EMPTY_QUEUE;
        } else {
            int val = this.heap[0].val;
            this.size--;
            if(this.size == 0) {
                this.heap[0] = null;
                return val;
            }
            Element last = this.heap[this.size];
            this.heap[this.size] = null;

            int i = 0;
            int j = 1;

            while(j < this.size) {
                if(j+1 < this.size && this.heap[j+1].prio < this.heap[j].prio) {
                    j++;
                }
                if(last.prio <= this.heap[j].prio) {
                    break;
                }
                this.heap[i] = this.heap[j];
                i = j;
                j = 2 * j + 1;
            }
            this.heap[i] = last;
            return val;
        }
    }

    public String top() {
        if(this.size > 0) {
            return "" + this.heap[0].val;
        } else {
            return "";
        }
    }

    public void priority(int x, float p) {
        findElements(x, p, 0);
    }

    private void findElements(int x, float p, int idx) {
        if(this.size > idx && this.heap[idx] != null) {
            if(this.heap[idx].val == x && this.heap[idx].prio > p) {
                this.heap[idx].prio = p;
                fixElement(idx);
            }
            int left = 2 * idx + 1;
            findElements(x, p, left);
            int right = 2 * idx + 2;
            findElements(x, p, right);
        }
    }

    private void fixElement(int idx) {
        Element toFix = this.heap[idx];

        int i = idx;
        int j = (idx - 1) / 2;

        while(j >= 0 && this.heap[j].prio > toFix.prio ) {
            this.heap[i] = this.heap[j];
            i = j;
            j = (i - 1) / 2;
            if(j == 0) {
                break;
            }
        }
        this.heap[i] = toFix;
    }

    public String print() {
        String s = "";
        Element[] arr = Arrays.copyOf(this.heap, this.size);
        int size= this.size-1;

        while(size >= 0) {
            s += "(" + arr[0].val + ", " + arr[0].prio + ") ";
            Element last = arr[size];
            arr[size] = null;

            int i = 0;
            int j = 1;

            while(j < size) {
                if(j+1 < size && arr[j+1].prio < arr[j].prio) {
                    j++;
                }
                if(last.prio <= arr[j].prio) {
                    break;
                }
                arr[i] = arr[j];
                i = j;
                j = 2 * j + 1;
            }
            arr[i] = last;
            size--;
        }
        return s;
    }
}