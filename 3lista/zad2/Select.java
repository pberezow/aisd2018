import java.util.Random;

public class Select {

    private static void swapValues(int[] tab, int x, int y) {
        int temp = tab[x];
        tab[x] = tab[y];
        tab[y] = temp;
    }

    //Random Select
    public static int randomizedSelect(int[] tab, int first, int last, int i, Stat stats) {
        if(first == last) {
            return tab[first];
        }
        int q = partition(tab, first, last, stats);
        int k = q + 1;
        
        if(i == k) {
            return tab[q];
        }
        else if(i < k) {
            return randomizedSelect(tab, first, q-1, i, stats);
        }
        else {
            return randomizedSelect(tab, q+1, last, i, stats);
        }
    }

    private static int partition(int[] tab, int first, int last, Stat stats) {
        int swaps = 0;
        int comps = 0;
        int i = first;
        int j = first;

        Random gen = new Random();
        int rand = gen.nextInt(last-first);
        swapValues(tab, last, first+rand);
        swaps++;

        while(i < last) {
            comps++;
            if(tab[i] <= tab[last]) {
                swapValues(tab, j, i);
                j++;
                swaps++;
            }
            i++;
        }
        swapValues(tab, j, last);
        swaps++;

        stats.addComps(comps);
        stats.addSwaps(swaps);
        return j;
    }


    //Select
    private static int getMedian(int[] tab, int first, int last, Stat stats) {
        int comps = 0;
        int swaps = 0;
        int curr;

        for(int i=first+1; i<=last; i++) {
            comps++;
            if(tab[i] < tab[i-1]) {
                curr = tab[i];
                int j = i-1;
                while(j >= first && tab[j] > curr) {
                    tab[j+1] = tab[j];
                    j--;
                    swaps++;
                }
                tab[j+1] = curr;
            }
        }
        stats.addComps(comps);
        stats.addSwaps(swaps);
        return tab[first+(last-first)/2];
    }

    public static int select(int[]tab, int first, int last, int i, Stat stats) {
        if(first == last) {
            return tab[first];
        }
        int q = medianPartition(tab, first, last, stats);
        int k = q + 1;
        
        if(i == k) {
            return tab[q];
        }
        else if(i < k) {
            return select(tab, first, q-1, i, stats);
        }
        else {
            return select(tab, q+1, last, i, stats);
        }
    }

    private static int pivot(int[] tab, int first, int last, Stat stats) {
        int swaps = 0;
        if(last - first < 1) {
            return tab[0];
        }
        int[] medians;
        if((last-first+1) % 5 != 0) {
            medians = new int[(last-first+1) / 5 + 1];
        }
        else {
            medians = new int[(last-first+1) / 5];
        }
        for(int j=0; j<medians.length; j++) {
            if(j == medians.length-1) {
                medians[j] = getMedian(tab, first+j*5, last, stats);
            }
            else {
                medians[j] = getMedian(tab, first+j*5, first+j*5+4, stats);
            }
            swaps++;
        }
        stats.addSwaps(swaps);
        return pivot(medians, 0, medians.length-1, stats);
    }

    private static int getPivot(int[] tab, int first, int last, int val, Stat stats) {
        int comps = 0;
        for(int i=first; i<=last; i++) {
            comps++;
            if(tab[i] == val) {
                stats.addComps(comps);
                return i;
            }
        }
        return 0;
    }

    private static int medianPartition(int[] tab, int first, int last, Stat stats) {
        int comps = 0;
        int swaps = 0;
        int i = first;
        int j = first;

        int pivot = pivot(tab, first, last, stats);
        int q = getPivot(tab, first, last, pivot, stats);
        swapValues(tab, q, last);
        swaps++;

        while(i < last) {
            comps++;
            if(tab[i] <= tab[last]) {
                swapValues(tab, j, i);
                j++;
                swaps++;
            }
            i++;
        }
        swapValues(tab, j, last);
        swaps++;
        stats.addComps(comps);
        stats.addSwaps(swaps);
        return j;
    }
}