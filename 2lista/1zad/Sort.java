import java.util.Random;

public class Sort {

    public static boolean isSorted(int[] tab, String ord) {
        if(ord.equals("<=")) {
            for(int i = 1; i < tab.length; i++) {
                if(tab[i-1] < tab[i]) {
                    return false;
                }
            }
            return true;
        }
        else if(ord.equals(">=")) {
            for(int i = 1; i < tab.length; i++) {
                if(tab[i-1] > tab[i]) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    private static void swapValues(int[] tab, int x, int y) {
        int temp = tab[x];
        tab[x] = tab[y];
        tab[y] = temp;
    }

    //----Insertion Sort----
    public static void insertion(int[] tab, String ord, Stat stats) {
        int curr;
        int comps = 0;
        int swaps = 0;

        if(ord.equals(">=")) {

            for(int i=1; i<tab.length; i++) {
                comps++;
                if(tab[i] < tab[i-1]) {
                    curr = tab[i];
                    int j = i-1;
                    while(j > -1 && tab[j] > curr) {
                        comps++;
                        tab[j+1] = tab[j];
                        j--;
                    }
                    tab[j+1] = curr;
                    swaps++;
                }
            }
        } 
        else if(ord.equals("<=")) {

            for(int i=1; i<tab.length; i++) {
                comps++;
                if(tab[i] > tab[i-1]) {
                    curr = tab[i];
                    int j = i-1;
                    while(j > -1 && tab[j] < curr) {
                        comps++;
                        tab[j+1] = tab[j];
                        j--;
                    }
                    tab[j+1] = curr;
                    swaps++;
                }
            }
        }
        stats.setComps(comps);
        stats.setSwaps(swaps);
    }

    //----Quick Sort----
    public static void quick(int[] tab, int first, int last, String ord, Stat stats) {
        if(last - first < 2) {
            return;
        }

        int comps = 0;
        int swaps = 0;
        int i = first;
        int j = first;

        Random gen = new Random();
        int rand = gen.nextInt(last-first);
        swapValues(tab, last, first+rand);
        swaps++;

        if(ord.equals(">=")) {

            while(i < last) {
                comps++; 
                if(tab[i] <= tab[last]) {
                    swaps++; 
                    swapValues(tab, j, i);
                    j++;
                }
                i++;
            }
        }
        else if(ord.equals("<=")) {

            while(i < last) {
                comps++;
                if(tab[i] >= tab[last]) {
                    swaps++;
                    swapValues(tab, j, i);
                    j++;
                }
                i++;
            }
        }
        swapValues(tab, j, last);
        swaps++;
        if(last - first > 2) {
            quick(tab, first, j-1, ord, stats);
            quick(tab, j+1, last, ord, stats);
        }
        stats.addComps(comps);
        stats.addSwaps(swaps);
    }

    //----Merge Sort----
    public static void merge(int[] tab, int first, int last, String ord, Stat stats) {

        int middle = first + (last - first) / 2;
        int[] temp = new int[tab.length];
        doMergeSort(tab, temp, 0, last, ord, stats);

    }

    private static void doMergeSort(int[] tab, int[] temp, int first, int last, String ord, Stat stats) {
        if(first < last) {
            int middle = first + (last - first) / 2;
            doMergeSort(tab, temp, first, middle, ord, stats);
            doMergeSort(tab, temp, middle+1, last, ord, stats);
            mergeParts(tab, temp, first, middle, last, ord, stats);
        }
    }

    private static void mergeParts(int[] tab, int[] temp, int first, int middle, int last, String ord, Stat stats) {
        int swaps = 0;
        int comps = 0;

        for(int i=first; i <= last; i++) {
            temp[i] = tab[i];
            swaps++;
        }
        int i = first;
        int j = middle + 1;
        int k = first;
        if(ord.equals(">=")) {
            while(i <= middle || j <= last) {
                if(i > middle) {
                    tab[k] = temp[j];
                    j++;
                } else if(j > last) {
                    tab[k] = temp[i];
                    i++;
                } else {
                    comps++; 
                    swaps++; 
                    if(temp[i] < temp[j]) {
                        tab[k] = temp[i];
                        i++;
                    } else {
                        tab[k] = temp[j];
                        j++;
                    }
                }
                k++;
            }
        }
        else if(ord.equals("<=")) {
            while(i <= middle || j <= last) {
                if(i > middle) {
                    tab[k] = temp[j];
                    j++;
                } else if(j > last) {
                    tab[k] = temp[i];
                    i++;
                } else {
                    comps++;
                    if(temp[i] > temp[j]) {
                        tab[k] = temp[i];
                        i++;
                    } else {
                        tab[k] = temp[j];
                        j++;
                    }
                }
                swaps++;
                k++;
            }
        }
        stats.addComps(comps);
        stats.addSwaps(swaps);
    }

    public static void sort(int[] tab, Stat stats, String type, String ord) {
        if(ord.equals("<=") || ord.equals(">=")) {
            if(type.equals("insert")) {
                insertion(tab, ord, stats);
            } else if(type.equals("merge")) {
                merge(tab, 0, tab.length-1, ord, stats);
            } else if(type.equals("quick")) {
                quick(tab, 0, tab.length-1, ord, stats);
            }
        }
        else {
            System.out.println("Wrong comp!");
            return;
        }
    }
}
