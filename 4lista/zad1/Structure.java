import java.io.*;
import java.util.Scanner;

public abstract class Structure {

    public abstract void insert(String s);

    public abstract void delete(String s);

    public abstract boolean find(String s);

    public abstract String min();

    public abstract String max();

    public abstract String successor(String k);

    public abstract String inorder();

    public abstract int size();

    public abstract String get(int idx);

    public int load(String f) {
        try {
            Scanner reader = new Scanner(new File(f));
            String key;
            while(reader.hasNextLine()) {
                key = reader.nextLine();
                String[] keys = key.trim().split("\\s");
                // System.out.println(keys);
                for(String s : keys) {
                    if(s.length() > 0) {
                        String str = prepareString(s);
                        if(str.length() <= 100) {
                            insert(str);
                        } else {
                            System.err.println("Max length of single element is 100 characters, string " + s + " too long");
                        }
                    }
                }
            }
            reader.close();
        } catch(FileNotFoundException ex) {
            System.err.println("Nie ma takiego pliku");
            return -1;
        }
        return 0;
    }

    public String prepareString(String s) {
        int first = 0;
        int last = s.length();
        while(first < last && !Character.isLetter(s.charAt(first))) {
            first++;
        }
        while(last > first && !Character.isLetter(s.charAt(last-1))) {
            last--;
        }
        return s.substring(first, last);
    }
}