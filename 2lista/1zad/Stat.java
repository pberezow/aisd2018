public class Stat {
    int comps;
    int swaps;
    long time;

    Stat() {
        this.comps = 0;
        this.swaps = 0;
        this.time = 0;
    }

    public void clearStats() {
        this.comps = 0;
        this.swaps = 0;
        this.time = 0;        
    }

    public int getComps() {
        return this.comps;
    }

    public int getSwaps() {
        return this.swaps;
    }

    public long getTime() {
        return this.time;
    }

    public void setComps(int comps) {
        this.comps = comps;
    }

    public void setSwaps(int swaps) {
        this.swaps = swaps;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public void addComps(int comps) {
        this.comps += comps;
    }

    public void addSwaps(int swaps) {
        this.swaps += swaps;
    }
}