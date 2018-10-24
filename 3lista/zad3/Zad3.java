import java.util.Scanner;

public class Zad3 {
    public static void main(String[] args) {
        long start, stop, time;
        Stat stats = new Stat();
        int[] tab;
        int value;

        if(args[0].equals("-r")) { //generuje losowa tablice o dlugosci n
            Scanner in = new Scanner(System.in);
            int n = in.nextInt();
            value = in.nextInt();
            in.close();

            tab = BinarySearch.generateTab(n, 1000);
        }
        else {
            Scanner in = new Scanner(System.in);
            String values = in.nextLine();
            String[] valuesTab = values.split(" ");
            tab = new int[valuesTab.length];
            for(int i=0; i < tab.length; i++) {
                try {
                    tab[i] = Integer.parseInt(valuesTab[i]);
                } catch(Exception ex) { ex.printStackTrace(); }
            }

            value = in.nextInt();
            in.close();
        }

        start = System.nanoTime();
        int found = BinarySearch.bs(tab, value, 0, tab.length-1, stats);
        stop = System.nanoTime();
        time = stop - start;

        System.out.println("Array:");
        for(int i=0; i<tab.length; i++) {
            if(tab[i] == value) {
                System.out.print("[" + tab[i] + "] ");
            }
            else {
                System.out.print(tab[i] + " ");
            }
        }
        System.out.println();
        if(found == 1) {
            System.out.println("Znaleziono");
        }
        else {
            System.out.println("Nie znaleziono");
        }
        System.out.println("time=" + time + " comps=" + stats.getComps());
    }
}