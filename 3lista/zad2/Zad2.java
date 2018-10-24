import java.util.Random;
import java.util.Scanner;
import java.util.Arrays;

public class Zad2 {

    private static int getParam() {
        int param = 0;
        Scanner in = new Scanner(System.in);
        try {
            param = Integer.parseInt(in.nextLine());
        } catch(Exception e) { e.printStackTrace(); }
        // in.close();
        return param;
    }

    private static int[] genRandomTab(int size, int maxVal) {
        int[] tab = new int[size];
        Random gen = new Random();
        for(int i=0; i<tab.length; i++) {
            tab[i] = gen.nextInt(maxVal);
        }
        return tab;
    }

    private static int[] genPermTab(int n) {
        int[] tab = new int[n];
        for(int i=0; i<n; i++) {
            tab[i] = i+1;
        }
        shuffleTab(tab);
        return tab;
    }

    private static void shuffleTab(int[] tab) {
        Random gen = new Random();
        for(int i = tab.length - 1; i > 0; i--) {
            int index = gen.nextInt(i + 1);
            int tmp = tab[index];
            tab[index] = tab[i];
            tab[i] = tmp;
        }
    }

    private static void testSelect(int[] tab, int n, int k) {
        Stat stats = new Stat();
        long start, stop, time;
        System.out.println("N: " + n + " K: " + k);
        System.out.println("Input Array:");
        for(int i=0; i<tab.length; i++) {
            System.out.print(tab[i] + " ");
        }
        System.out.println();
        
        int[] tab2 = Arrays.copyOf(tab, tab.length);
        start = System.nanoTime();
        int val = Select.randomizedSelect(tab2, 0, tab2.length-1, k, stats);
        stop = System.nanoTime();
        time = stop - start;
        System.out.println("\nRandomized Select:");
        System.out.println("N=" + n + " K=" + k + " time=" + time + "ns k-value=" + val + " swaps=" + stats.getSwaps() + " comps=" + stats.getComps() + "\n");
        System.out.println("Array:");
        for(int i=0; i<tab2.length; i++) {
            if(tab2[i] == val) {
                System.out.print("[" + tab2[i] + "] ");
            }
            else {
                System.out.print(tab2[i] + " ");
            }
        }
        System.out.println();

        stats = new Stat();
        tab2 = Arrays.copyOf(tab, tab.length);
        start = System.nanoTime();
        val = Select.select(tab2, 0, tab2.length-1, k, stats);
        stop = System.nanoTime();
        time = stop - start;
        System.out.println("\nSelect:");
        System.out.println("N=" + n + " K=" + k + " time=" + time + "ns k-value=" + val + " swaps=" + stats.getSwaps() + " comps=" + stats.getComps() + "\n");
        System.out.println("Array:");
        for(int i=0; i<tab2.length; i++) {
            if(tab2[i] == val) {
                System.out.print("[" + tab2[i] + "] ");
            }
            else {
                System.out.print(tab2[i] + " ");
            }
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int n, k;
        int[] tab;
        if(args[0].equals("-r") || args[0].equals("-p")) {
            n = getParam();
            k = getParam();
            if(n < k) {
                System.out.println("k can't be greater than n!");
                return;
            }
        }
        else {
            return;
        }
        switch(args[0]) {
            case "-r":
                tab = genRandomTab(n, 1000);
                testSelect(tab, n, k);
                break;
            case "-p":
                tab = genPermTab(n);
                testSelect(tab, n, k);
                break;
            default:
                System.out.println("Bad parameter!");
        }
    }
}