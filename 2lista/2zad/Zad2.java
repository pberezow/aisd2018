import java.io.PrintWriter;
import java.util.Scanner;
import java.util.Set;
import java.util.HashSet;
import java.util.Arrays;

public class Zad2 {
    private static final Set<String> types = new HashSet<String>(Arrays.asList(new String[] {"insert", "merge", "quick"}));
    private static final Set<String> comps = new HashSet<String>(Arrays.asList(new String[] {"<=", ">="}));

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
        boolean isStat = false;
        String name = "";
        int k = 0;

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
            } else if(args[i].equals("--stat")) {
                if(i+2 < args.length) {
                    isStat = true;
                    name = args[i+1];
                    try {
                        k = Integer.parseInt(args[i+2]);
                    } catch(Exception ex) { ex.printStackTrace(); }
                }
            }
        }
        if(types.contains(type) && comps.contains(comp)) {
            Stat stats = new Stat();
            long start;
            long stop;
            if(isStat) {
                int tab[];
                try {
                    PrintWriter out = new PrintWriter(name);
                    for(int n = 1; n <= 100; n++) {
                        for(int i = 0; i < k; i++) {
                            tab = Sort.generateTab(n*100, 1000);
                            start = System.nanoTime();
                            Sort.sort(tab, stats, type, comp);
                            stop = System.nanoTime();
                            // if(!Sort.isSorted(tab, comp)) {
                            //     System.out.println("Array in not sorted!");
                            //     return;
                            // }
                            stats.setTime(stop - start);
                            // System.out.println(time); //nanosec time
                            out.println(n*100 + "," + stats.getComps() + "," + stats.getSwaps() + "," + stats.getTime() + ",");
                            stats.clearStats();
                        }
                    }
                    out.close();
                } catch(Exception ex) { ex.printStackTrace(); }
            } else {
                int[] tab = getTab();
                start = System.nanoTime();
                Sort.sort(tab, stats, type, comp);
                stop = System.nanoTime();
                stats.setTime(stop - start);
                System.out.println("\nArray:");
                for(int i = 0; i < tab.length; i++) {
                    System.out.print(tab[i] + ", ");
                }
                System.out.println("\nComparisons: " + stats.getComps() + "    Swaps: " + stats.getSwaps() + "  Time: " + stats.getTime() + "ns");
            }
        }
    }
}