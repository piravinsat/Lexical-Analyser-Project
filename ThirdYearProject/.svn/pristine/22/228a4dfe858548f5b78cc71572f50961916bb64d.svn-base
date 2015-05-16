package tho;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This program creates a NFA using Thompson's Construction from a derivation
 * tree made by RDP of a regular expression.
 * 
 * The NFA is written to a nfa.txt file
 * 
 * @author Piravin Satkunarajah
 * 
 */
public class ThompsonsConstruction {

    ArrayList<ArrayList<Integer>> nfa = new ArrayList<ArrayList<Integer>>();
    ArrayList<String> alphaSym = new ArrayList<String>();
    private int currentNodePos = 0;

    /**
     * This constructor is being used as a main method at the moment as it is a
     * standalone program
     */
    public ThompsonsConstruction() {

        alphaSym.add("type");
        String currentNode = "";
        currentNodePos = 0;
        String txtFile = "devtree.txt";

        Scanner scan = new Scanner(System.in);
        System.out
                .println("Name the txt file of the derivation tree (Example: altdevtree.txt): ");
        txtFile = scan.nextLine();
        String devTree[][] = devTreeInArray(txtFile);

        currentNode = devTree[currentNodePos][0];

        if (currentNode.equals("alt")) {
            alt(devTree);
        } else {
            System.out.println("INCOMPATIBLE DERIVATION TREE");
        }

        writeNFAintoFile();

        System.out.println("NFA constructed!");
        System.out.println("\n  " + alphaSym);
        for (int row = 0; row < nfa.size(); row++) {

            System.out.println(row + " " + nfa.get(row));
        }
        scan.close();
    }

    /**
     * The process starts here and correspond to "alt" in the derivation tree
     * 
     * @param devTree
     * @return an alternation or null
     */
    private Machine alt(String[][] devTree) {

        if (currentNodePos != devTree.length) {

            currentNodePos++;
        } else {
            return null;
        }
        Machine r = con(devTree);

        if (currentNodePos != devTree.length) {
            if (devTree[currentNodePos][0].equals("|")) {
                currentNodePos++;
            }
        } else {
            return null;
        }

        if (currentNodePos != devTree.length) {
            if (devTree[currentNodePos][0].equals("con")) {

                Machine s = con(devTree);

                if (s != null) {
                    return buildAlternation(r, s);
                } else {
                    return null;
                }

            } else {
                return r;
            }
        } else {
            return null;
        }

    }

    /**
     * Corresponds to "con" in the derivation tree
     * 
     * @param devTree
     * @return concatenation or null
     */
    private Machine con(String[][] devTree) {

        if (currentNodePos != devTree.length) {
            currentNodePos++;
        } else {
            return null;
        }

        Machine r = kle(devTree);

        if (currentNodePos != devTree.length) {
            if (devTree[currentNodePos][0].equals("kle")) {
                Machine s = kle(devTree);
                return buildConcatenation(r, s);

            } else {
                return r;
            }
        } else {
            return null;
        }
    }

    /**
     * Corresponds to "kle" in the derivation tree
     * 
     * @param devTree
     * @return kleene closure or null
     */
    private Machine kle(String[][] devTree) {

        if (currentNodePos != devTree.length) {
            System.out.println("HERE " + currentNodePos + " "
                    + devTree[currentNodePos][0]);
            currentNodePos++;
        } else {
            return null;
        }

        Machine r = reg(devTree);

        if (currentNodePos != devTree.length) {
            System.out.println("HERE2 " + currentNodePos + " "
                    + devTree[currentNodePos][0]);
            currentNodePos++;
        } else {
            return null;
        }

        if (currentNodePos != devTree.length) {
            if (devTree[currentNodePos][0].equals(")")) {
                currentNodePos++;
            }
        } else {
            return null;
        }

        if (currentNodePos != devTree.length) {
            if (devTree[currentNodePos][0].equals("*")
                    && currentNodePos != devTree.length) {
                currentNodePos++;
                return buildKleeneClosure(r);
            } else {
                return r;
            }
        } else {
            return null;
        }
    }

    /**
     * Corresponds to "reg" in the derivation tree
     * 
     * @param devTree
     * @return regular expression or null
     */
    private Machine reg(String[][] devTree) {

        if (currentNodePos != devTree.length) {
            if (devTree[currentNodePos][0].equals("reg")) {
                currentNodePos++;

            }
        } else {
            return null;
        }

        if (currentNodePos != devTree.length) {
            if (devTree[currentNodePos][0].equals("(")) {
                currentNodePos++;

            }
        } else {
            return null;
        }

        if (currentNodePos != devTree.length) {
            if (devTree[currentNodePos][0].equals("alt")) {
                alt(devTree);
            }

            if (currentNodePos != devTree.length && !devTree[currentNodePos][0].equals("")) {
                Machine t = buildReg(devTree[currentNodePos][0]);
                return t;
            } else {
                return null;
            }
        } else {
            return null;
        }

    }

    /**
     * Builds the concatenation of two machines and adds to the NFA table
     * 
     * @param r
     *            Machine
     * @param s
     *            Machine
     * @return Start and End state of the concatenation machine
     */
    public Machine buildConcatenation(Machine r, Machine s) {
        
        // Set state types for every state to 2 as a normal state
        // in preparation for changing the state types
        for (int i = 0; i < nfa.size(); i++) {
            nfa.get(i).set(0, 2);
        }
        
        //Set the start state of r as the start state of the new machine
        nfa.get(r.getStartState()).set(0, 0);
        
        //Set the end state of s as the accepting state of the new machine
        nfa.get(s.getEndState()).set(0, 1);

        // Add epsilon(#) label to alphaSym if doesn't already exist then
        // Add edge from end state of r to start state of s with epsilon label

        if (alphaSym.indexOf("#") == -1) {
            alphaSym.add("#");

            // Set all values in # column to 99
            for (int i = 0; i < nfa.size(); i++) {
                nfa.get(i).add(alphaSym.indexOf("#"), 99);
            }

        }
        nfa.get(r.getEndState()).set(alphaSym.indexOf("#"), s.getStartState());

        // Returns where the start and end state of this NFA is in the
        // NFA ArrayList
        Machine conNFA = new Machine(r.getStartState(), s.getEndState());
        return conNFA;

    }

    /**
     * Builds the alternation of two machines and adds to the NFA table
     * 
     * @param r
     *            Machine
     * @param s
     *            Machine
     * @return The start and end state of the alternation machine
     */
    public Machine buildAlternation(Machine r, Machine s) {

        // Set state types for every state to 2 as a normal state
        // in preparation for setting new start and accepting state
        for (int i = 0; i < nfa.size(); i++) {
            nfa.get(i).set(0, 2);
        }

        // Add new start state
        ArrayList<Integer> startState = new ArrayList<Integer>();
        startState.add(0, 0);
        nfa.add(startState);
        int startNum = nfa.indexOf(startState);

        // Filling the arraylist to stop the ArrayIndexOutOfBoundsException
        // after new states added
        for (int row = 0; row < nfa.size(); row++) {
            if (nfa.get(row).size() < alphaSym.size()) {
                for (int i = nfa.get(row).size(); i < alphaSym.size(); i++) {
                    nfa.get(row).add(i, 99);
                }
            }
        }

        // Add epsilon(#) label to alphaSym if it doesn't already exist then
        // Add edge from the new start state to start of r with epsilon label
        if (alphaSym.indexOf("#") == -1) {
            alphaSym.add("#");

            // Set all values in # column to 99
            for (int i = 0; i < nfa.size(); i++) {
                nfa.get(i).add(alphaSym.indexOf("#"), 99);
            }

        }
        nfa.get(startNum).set(alphaSym.indexOf("#"), r.getStartState());

        // Add epsilon(#2) label to alphaSym if it doesn't already exist then
        // Add edge from new start state to start of s with epsilon label
        if (alphaSym.indexOf("#2") == -1) {
            alphaSym.add("#2");

            // Set all values in #2 column to 99
            for (int i = 0; i < nfa.size(); i++) {
                nfa.get(i).add(alphaSym.indexOf("#2"), 99);
            }

        }
        nfa.get(startNum).set(alphaSym.indexOf("#2"), s.getStartState());

        // Add new end state as the new accepting state
        ArrayList<Integer> endState = new ArrayList<Integer>();
        endState.add(0, 1);
        nfa.add(endState);
        int endNum = nfa.indexOf(endState);

        // Filling the arraylist to stop the ArrayIndexOutOfBoundsException
        // after new states added
        for (int row = 0; row < nfa.size(); row++) {
            if (nfa.get(row).size() < alphaSym.size()) {
                for (int i = nfa.get(row).size(); i < alphaSym.size(); i++) {
                    nfa.get(row).add(i, 99);
                }
            }
        }

        // Add edge from the end state of r to new end state with epsilon label
        nfa.get(r.getEndState()).set(alphaSym.indexOf("#"), endNum);

        // Add edge from end state of s to new end state with epsilon label
        nfa.get(s.getEndState()).set(alphaSym.indexOf("#2"), endNum);

        // Returns where the start and end state of this NFA is in the
        // NFA ArrayList
        Machine altNFA = new Machine(startNum, endNum);
        return altNFA;

    }

    /**
     * Builds the kleene closure of a machine and adds to NFA
     * 
     * @param r
     *            Machine
     * @return Start and end state of the kleene closure machine
     */
    public Machine buildKleeneClosure(Machine r) {

        // Set state types for every state to 2 as a normal state
        // in preparation for setting new start and accepting state
        for (int i = 0; i < nfa.size(); i++) {
            nfa.get(i).set(0, 2);
        }

        // Add new start state as the new start state
        ArrayList<Integer> startState = new ArrayList<Integer>();
        startState.add(0, 0);
        nfa.add(startState);
        int startNum = nfa.indexOf(startState);

        // Add new end state as the new accepting state
        ArrayList<Integer> endState = new ArrayList<Integer>();
        endState.add(0, 1);
        nfa.add(endState);
        int endNum = nfa.indexOf(endState);

        // Filling the arraylist to stop the ArrayIndexOutOfBoundsException
        // after new states added
        for (int row = 0; row < nfa.size(); row++) {
            if (nfa.get(row).size() < alphaSym.size()) {
                for (int i = nfa.get(row).size(); i < alphaSym.size(); i++) {
                    nfa.get(row).add(i, 99);
                }
            }
        }

        // Add epsilon(#) label to alphaSym if it doesn't already exist then
        // Add edge from the new start state to new end state with epsilon label
        if (alphaSym.indexOf("#") == -1) {
            alphaSym.add("#");

            // Set all values in # column to 99
            for (int i = 0; i < nfa.size(); i++) {
                nfa.get(i).add(alphaSym.indexOf("#"), 99);
            }
        }
        nfa.get(startNum).set(alphaSym.indexOf("#"), endNum);

        // Add epsilon(#2) label to alphaSym if it doesn't already exist then
        // Add edge from new start state to start state of r with epsilon label
        if (alphaSym.indexOf("#2") == -1) {
            alphaSym.add("#2");

            // Set all values in # column to 99
            for (int i = 0; i < nfa.size(); i++) {
                nfa.get(i).add(alphaSym.indexOf("#2"), 99);
            }

        }
        nfa.get(startNum).set(alphaSym.indexOf("#2"), r.getStartState());

        // Add edge from end state of r to start state of r with epsilon label
        nfa.get(r.getEndState()).set(alphaSym.indexOf("#"), r.getStartState());

        // Add edge from end state of r to new end state with epsilon label
        // System.out.println(nfa);
        nfa.get(r.getEndState()).set(alphaSym.indexOf("#2"), endNum);

        // Returns where the start and end state of this NFA is in the
        // NFA ArrayList
        Machine kleNFA = new Machine(startNum, endNum);
        return kleNFA;
    }

    /**
     * Builds a regular expression for a symbol r
     * 
     * @param r
     *            Symbol
     * @return Start and end state of regular expression machine
     */
    public Machine buildReg(String r) {

//        // Set state types for every state to 2 as a normal state
//        // in preparation for setting new start and accepting state
//        for (int i = 0; i < nfa.size(); i++) {
//            nfa.get(i).set(0, 2);
//        }

        // Add new start state as the new start state
        ArrayList<Integer> startState = new ArrayList<Integer>();
        startState.add(0, 0);
        nfa.add(startState);
        int startNum = nfa.indexOf(startState);

        // Add new end state as the new accepting state
        ArrayList<Integer> endState = new ArrayList<Integer>();
        endState.add(0, 1);
        nfa.add(endState);
        int endNum = nfa.indexOf(endState);

        // Filling the arraylist to stop the ArrayIndexOutOfBoundsException
        // after new states added
        for (int row = 0; row < nfa.size(); row++) {
            if (nfa.get(row).size() < alphaSym.size()) {
                for (int i = nfa.get(row).size(); i < alphaSym.size(); i++) {
                    nfa.get(row).add(i, 99);
                }
            }
        }

        // Add label to alphaSym if doesn't already exist then
        // Add edge from start to end state with id label

        if (alphaSym.indexOf(r) == -1) {
            alphaSym.add(r);

            // Set all values in r column to 99
            for (int i = 0; i < nfa.size(); i++) {
                nfa.get(i).add(alphaSym.indexOf(r), 99);
            }

        }
        nfa.get(startNum).set(alphaSym.indexOf(r), endNum);

        // Returns where the start and end state of this NFA is in the
        // NFA ArrayList
        Machine regNFA = new Machine(startNum, endNum);
        return regNFA;
    }

    /**
     * Gets the derivation tree and puts into an devTree array
     * 
     * @param txtFile
     *            text file of derivation tree
     * @return devTree Array
     */
    public String[][] devTreeInArray(String txtFile) {

        String devTree[][] = null;
        int i = 0;

        try {
            Scanner scan = new Scanner(new FileInputStream(txtFile));

            while (scan.hasNextLine()) {
                i++;
                scan.nextLine();
            }

            // Position 0 = Node, position 1 = tree level, position 2 = checked
            devTree = new String[i][3];

            int j = 0;

            scan = new Scanner(new FileInputStream(txtFile));

            while (scan.hasNextLine()) {

                String nextNode = scan.nextLine();
                int level = nextNode.length()
                        - nextNode.replaceAll(" ", "").length();
                nextNode = nextNode.replaceAll(" ", "");

                devTree[j][0] = nextNode;
                devTree[j][1] = Integer.toString(level);

                j++;
            }

            scan.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return devTree;
    }

    /**
     * This class was used to understand the derivation tree given by RDP. It
     * isn't used in the final program.
     */
    public void printDevTreeWithLevels(String txtFile) {

        try {
            Scanner scan = new Scanner(new FileInputStream(txtFile));

            while (scan.hasNextLine()) {

                String nextNode = scan.nextLine();
                int spaces = nextNode.length()
                        - nextNode.replaceAll(" ", "").length();

                System.out.println(nextNode + " " + spaces);
            }

            scan.close();

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * Writes the NFA ArrayList into the nfa.txt file
     */
    public void writeNFAintoFile() {

        PrintWriter out;
        try {
            out = new PrintWriter("nfa.txt");

            // Write the alphabet containing the columns as the first line
            for (String i : alphaSym) {
                out.print(i);
                out.print(" ");
            }
            out.println(" ");

            // Write out the rest of nfa transition table
            for (int row = 0; row < nfa.size(); row++) {
                for (int col = 0; col < nfa.get(row).size(); col++) {
                    out.print(nfa.get(row).get(col));
                    out.print(" ");
                }
                out.println(" ");
            }
            out.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    /**
     * The main method which calls the constructor
     * 
     * @param args
     */
    public static void main(String[] args) {
        new ThompsonsConstruction();
    }

}