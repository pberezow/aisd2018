import java.util.Random;

public class BinarySearch {

    public static int[] generateTab(int size, int maxVal) {
        int[] tab = new int[size];
        Random gen = new Random();
        for(int i=0; i<size; i++) {
            tab[i] = gen.nextInt(maxVal);
        }
        quick(tab, 0, size-1);
        return tab;
    }

    private static void swapValues(int[] tab, int x, int y) {
        int temp = tab[x];
        tab[x] = tab[y];
        tab[y] = temp;
    }

    private static void quick(int[] tab, int first, int last) {
        if(last - first < 2) {
            return;
        }

        int i = first;
        int j = first;

        Random gen = new Random();
        int rand = gen.nextInt(last-first);        
        swapValues(tab, last, first+rand);

        while(i < last) { 
            if(tab[i] <= tab[last]) {
                swapValues(tab, j, i);
                j++;
            }
            i++;
        }
        swapValues(tab, j, last);
        if(last - first > 2) {
            quick(tab, first, j-1);
            quick(tab, j+1, last);
        }
    }

    public static int bs(int[] tab, int val, int min, int max, Stat stats) {
        int comps = 0;
        comps++;
        if(val < tab[min] || val > tab[max]) {
            stats.addComps(comps);
            return 0;
        }
        int middle = min + (max-min)/2;
        if(min >= max) {
            comps++;
            if(tab[max] == val) {
                stats.addComps(comps);
                return 1;
            }
            return 0;
        }
        if(tab[middle] == val) {
            comps++;
            stats.addComps(comps);
            return 1;
        }
        else if(tab[middle] < val) {
            comps+=2;
            stats.addComps(comps);
            return bs(tab, val, middle+1, max, stats);
        }
        else {
            comps+=2;
            stats.addComps(comps);
            return bs(tab, val, min, middle-1, stats);
        }
    }
}