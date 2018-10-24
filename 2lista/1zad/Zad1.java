import java.util.Scanner;

public class Zad1 {

    private static int[] getTab() {
        Scanner in = new Scanner(System.in);
        int size = 0;
        try {
            size = Integer.parseInt(in.nextLine());
        } catch(Exception ex) { ex.printStackTrace(); }
        int[] tab = new int[size];
        String values = in.nextLine();
        String[] valuesTab = values.split(" ");
        for(int i=0; i < size; i++) {
            try {
                tab[i] = Integer.parseInt(valuesTab[i]);
            } catch(Exception ex) { ex.printStackTrace(); }
        }
        in.close();
        return tab;
    }

    public static void main(String[] args) {

        String type = "";
        String comp = "";
        Stat stats = new Stat();

        for(int i=0; i < args.length; i++) {
            if(args[i].equals("--type")) {
                if(i+1 < args.length) {
                    type = args[i+1];
                } else {
                    System.out.println("Not enougth arguments!");
                    return;
                }
            } else if(args[i].equals("--comp")) {
                if(i+1 < args.length) {
                    comp = args[i+1];
                } else {
                    System.out.println("Not enougth arguments!");
                    return;
                }
            }
        }

        int[] tab = getTab();

        long start = System.nanoTime();
        Sort.sort(tab, stats, type, comp);
        long stop = System.nanoTime();
        stats.setTime(stop - start);
        if(!Sort.isSorted(tab, comp)) {
            System.out.println("Array in not sorted!");
            return;
        }
        System.out.println("\nArray:");
        for(int i = 0; i < tab.length; i++) {
            System.out.print(tab[i] + ", ");
        }
        System.out.println("\nComparisons: " + stats.getComps() + "    Swaps: " + stats.getSwaps() + "  Time: " + stats.getTime() + "ns");
    }
}