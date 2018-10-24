import java.util.Scanner;
import java.io.PrintWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Zad1 {
    Structure structure;
    Scanner inStream;
    Stat statistics;

    public Zad1() {
        structure = null;
        inStream = null;
        statistics = new Stat();
    }
    
    public boolean createStruct(String type) {
        if(type.equals("bst")) {
            this.structure = new BSTree();
            return true;
        } else if(type.equals("rbt")) {
            this.structure = new RBTree();
            return true;
        } else if(type.equals("hmap")) {
            this.structure = new HMap();
            return true;
        } else {
            this.structure = null;
            return false;
        }
    }
    
    public boolean getInput() {
            Scanner reader = new Scanner(System.in);
            this.inStream = reader;
            return true;
    }

    public void make(String[] args) {
        switch(args[0]) {
            case "insert":
                if(args[1].length() <= 100) {
                    structure.insert(structure.prepareString(args[1]));
                } else {
                    System.err.println("Max length of single element is 100 characters, string " + args[1] + " too long");
                }
                // structure.insert(args[1]);
                this.statistics.insert();
                this.statistics.addEl();
                break;
            case "load":
                if(structure.load(args[1]) == -1) {
                    System.err.println("File not found");
                }
                this.statistics.load();
                this.statistics.setCurrEl(structure.size());
                // System.err.println("Size of struct: " + structure.size());
                break;
            case "delete":
                structure.delete(args[1]);
                this.statistics.delete();
                this.statistics.delEl();
                break;
            case "find":
                if(structure.find(args[1])) {
                    System.out.println(1);
                } else {
                    System.out.println(0);
                }
                this.statistics.find();
                break;
            case "min":
                System.out.println(structure.min());
                this.statistics.min();
                break;
            case "max":
                System.out.println(structure.max());
                this.statistics.max();
                break;
            case "successor":
                String succ = structure.successor(args[1]);
                if(succ == null) {
                    System.out.println();
                } else {
                    System.out.println(succ);
                }
                this.statistics.successor();
                break;
            case "inorder":
                System.out.println(structure.inorder());
                this.statistics.inorder();
                break;
            default:
        }
    }
    
    public static void main(String[] args) {
        Zad1 obj = new Zad1();
        String type = null;

        for(int i=0; i < args.length; i++) {
            if(args[i].equals("--type")) {
                if(i+1 < args.length) {
                    type = args[i+1];
                } else {
                    System.out.println("Not enougth arguments!");
                    return;
                }
            }
        }

        if(!obj.createStruct(type)) {
            System.err.println("Wrong type");
            return;
        }
        if(!obj.getInput()) {
            return;
        }

        int count = Integer.parseInt(obj.inStream.nextLine());

        obj.statistics.startClock();
        for(int i = 0; i < count; i++) {
            String line = obj.inStream.nextLine();
            String[] lines = line.split(" ");
            obj.make(lines);
        }
        obj.statistics.stopClock();
        obj.statistics.printStats();
        obj.inStream.close();
        return;
    }
}