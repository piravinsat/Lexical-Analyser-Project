package lex;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import lex.Machine;

public class ThompsonsConstruction {

    private String txtFile; // Holds the location of the derivation tree
    private int currentNodePos = 0;
    private ArrayList<String> alphaSym = new ArrayList<String>();
    private ArrayList<ArrayList<Integer>> adjMatrix = new ArrayList<ArrayList<Integer>>();
    private FiniteStateAutomata nfa;

    public ThompsonsConstruction(String location) {
        txtFile = location;
    }

    /**
     * Retrieves the derivation tree from the text file
     * 
     * @return 2D array containing the derivation tree
     */
    public String[][] devTreeRetrieval() {

        String devTree[][] = null;
        int nodesNum = 0;

        try {
            Scanner scan = new Scanner(new FileInputStream(txtFile));

            // Used to count the number of nodes which is need to initialise the
            // devTree
            while (scan.hasNextLine()) {
                nodesNum++;
                scan.nextLine();
            }

            System.out.println(nodesNum);
            // Position 0 = Node, position 1 = tree level, position 2 = checked
            devTree = new String[nodesNum][3];

            // Current node
            int count = 0;

            scan = new Scanner(new FileInputStream(txtFile));

            // Sets the dev tree array
            while (scan.hasNextLine()) {

                String nextNode = scan.nextLine();

                //if (nextNode.equals("")) {
                //    break;
                //}

                int level = nextNode.length()
                        - nextNode.replaceAll(" ", "").length();
                nextNode = nextNode.replaceAll(" ", "");

                devTree[count][0] = nextNode;
                devTree[count][1] = Integer.toString(level);

                count++;
            }

            scan.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < devTree.length; i++) {
            for (int j = 0; j < devTree[0].length; j++) {
                System.out.print(devTree[i][j]);
                System.out.print(" ");
            }
            System.out.println("");
        }
        // System.out.println(devTree);
        return devTree;
    }

    /**
     * The method is used to start the Thompson's Construction process
     */
    public void startAlgorithm() {
        System.out.println("HERE!");
        alphaSym.add("type");
        String devTree[][] = devTreeRetrieval();
        String currentNode = devTree[currentNodePos][0];

        if (currentNode.equals("alt")) {
            alt(devTree);
        } else {
            System.out.println("INCOMPATIBLE DERIVATION TREE");
        }
    }

    /**
     * The process starts here and corresponds to "alt" in the derivation tree
     * 
     * @param devTree
     * @return An alternation or null which makes it do nothing.
     */
    private Machine alt(String[][] devTree) {

        // If the current node isn't the last node then move to next node
        if (currentNodePos != devTree.length) {
            currentNodePos++;

        } else {
            // Return nothing instead of an adjMatrix.
            return null;
        }

        // Create adjMatrix machine for the left side of the alternation
        Machine r = con(devTree);

        // If current node is alternation symbol then move to next node
        if (currentNodePos != devTree.length) {
            if (devTree[currentNodePos][0].equals("|")) {
                currentNodePos++;
            }
        } else {
            return null;
        }

        // If current node is con
        if (currentNodePos != devTree.length) {
            if (devTree[currentNodePos][0].equals("con")) {

                // Create adjMatrix machine for the right side of the
                // alternation
                Machine s = con(devTree);

                // If r and s are both adjMatrix machine then build an
                // alternation.
                if (s != null) {
                    return buildAlternation(r, s);
                } else {
                    return null;
                }
            } else {
                // Return only the one adjMatrix machine
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

        // If the current node isn't the last node then move to next one
        if (currentNodePos != devTree.length) {
            currentNodePos++;
        } else {
            return null;
        }

        // Create adjMatrix machine for the left side of the concatenation
        Machine r = kle(devTree);

        // If the current node is a kle then build a concatenation of r and s
        if (currentNodePos != devTree.length) {
            if (devTree[currentNodePos][0].equals("kle")) {
                // Create adjMatrix for the right side of the concatenation
                Machine s = kle(devTree);
                return buildConcatenation(r, s);
            } else {
                // Return only the one adjMatrix machine
                return r;
            }
        } else {
            // Return nothing
            return null;
        }
    }

    /**
     * Corresponds to "kle" in the derivation tree May need to test again
     * 
     * @param devTree
     * @return kleene closure or null
     */
    private Machine kle(String[][] devTree) {

        // Not last node then move to next node which is reg or left bracket
        if (currentNodePos != devTree.length) {
            currentNodePos++;
        } else {
            return null;
        }

        // Create adjMatrix for the input symbol
        Machine r = reg(devTree);

        // Move from current node which is input symbol
        if (currentNodePos != devTree.length) {
            currentNodePos++;
        } else {
            return null;
        }

        // Move to the next one
        if (currentNodePos != devTree.length) {
            if (devTree[currentNodePos][0].equals(")")) {
                currentNodePos++;
            }
        } else {
            return null;
        }

        // Build Kleene closure
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
     * Builds the alternation of two machines and adds to the adjMatrix table
     * 
     * @param r
     *            Machine
     * @param s
     *            Machine
     * @return The start and end state of the alternation machine
     */
    public Machine buildAlternation(Machine r, Machine s) {

        System.out.println("ALTERNATION!");

        // Set state types for every state to 2 as a normal state
        // in preparation for setting new start and accepting state
        for (int i = 0; i < adjMatrix.size(); i++) {
            adjMatrix.get(i).set(0, 2);
        }

        // Add new start state
        ArrayList<Integer> startState = new ArrayList<Integer>();
        startState.add(0, 0);
        adjMatrix.add(startState);
        int startNum = adjMatrix.indexOf(startState);

        // Filling the arraylist to stop the ArrayIndexOutOfBoundsException
        // after new states added
        for (int row = 0; row < adjMatrix.size(); row++) {
            if (adjMatrix.get(row).size() < alphaSym.size()) {
                for (int i = adjMatrix.get(row).size(); i < alphaSym.size(); i++) {
                    adjMatrix.get(row).add(i, -9);
                }
            }
        }

        // Add epsilon(#) label to alphaSym if it doesn't already exist then
        // Add edge from the new start state to start of r with epsilon label
        if (alphaSym.indexOf("#") == -1) {
            System.out.println("HELLOOOOOOOOOOOO!");
            alphaSym.add("#");

            // Set all values in # column to -9
            for (int i = 0; i < adjMatrix.size(); i++) {
                adjMatrix.get(i).add(alphaSym.indexOf("#"), -9);
            }

        }
        adjMatrix.get(startNum).set(alphaSym.indexOf("#"), r.getStartState());

        // Add epsilon(#2) label to alphaSym if it doesn't already exist then
        // Add edge from new start state to start of s with epsilon label
        if (alphaSym.indexOf("#2") == -1) {
            alphaSym.add("#2");

            // Set all values in #2 column to -9
            for (int i = 0; i < adjMatrix.size(); i++) {
                adjMatrix.get(i).add(alphaSym.indexOf("#2"), -9);
            }

        }
        adjMatrix.get(startNum).set(alphaSym.indexOf("#2"), s.getStartState());

        // Add new end state as the new accepting state
        ArrayList<Integer> endState = new ArrayList<Integer>();
        endState.add(0, 1);
        adjMatrix.add(endState);
        int endNum = adjMatrix.indexOf(endState);

        // Filling the arraylist to stop the ArrayIndexOutOfBoundsException
        // after new states added
        for (int row = 0; row < adjMatrix.size(); row++) {
            if (adjMatrix.get(row).size() < alphaSym.size()) {
                for (int i = adjMatrix.get(row).size(); i < alphaSym.size(); i++) {
                    adjMatrix.get(row).add(i, -9);
                }
            }
        }

        // Add edge from the end state of r to new end state with epsilon label
        adjMatrix.get(r.getEndState()).set(alphaSym.indexOf("#"), endNum);

        // Add edge from end state of s to new end state with epsilon label
        adjMatrix.get(s.getEndState()).set(alphaSym.indexOf("#2"), endNum);

        // Returns where the start and end state of this adjMatrix is in the
        // adjMatrix ArrayList
        Machine altNfa = new Machine(startNum, endNum);
        return altNfa;

    }

    /**
     * Builds the concatenation of two machines and adds to the adjMatrix table
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
        for (int i = 0; i < adjMatrix.size(); i++) {
            adjMatrix.get(i).set(0, 2);
        }
        
        //Set the start state of r as the start state of the new machine
        adjMatrix.get(r.getStartState()).set(0, 0);
        
        //Set the end state of s as the accepting state of the new machine
        adjMatrix.get(s.getEndState()).set(0, 1);

        // Add epsilon(#) label to alphaSym if doesn't already exist then
        // Add edge from end state of r to start state of s with epsilon label

        if (alphaSym.indexOf("#") == -1) {
            alphaSym.add("#");

            // Set all values in # column to -9
            for (int i = 0; i < adjMatrix.size(); i++) {
                adjMatrix.get(i).add(alphaSym.indexOf("#"), -9);
            }
        }
        adjMatrix.get(r.getEndState()).set(alphaSym.indexOf("#"),
                s.getStartState());

        // Returns where the start and end state of this adjMatrix is in the
        // adjMatrix ArrayList
        Machine conNfa = new Machine(r.getStartState(), s.getEndState());
        return conNfa;
    }

    /**
     * Builds the kleene closure of a machine and adds to adjMatrix
     * 
     * @param r
     *            Machine
     * @return Start and end state of the kleene closure machine
     */
    public Machine buildKleeneClosure(Machine r) {

        // Set state types for every state to 2 as a normal state
        // in preparation for setting new start and accepting state
        for (int i = 0; i < adjMatrix.size(); i++) {
            adjMatrix.get(i).set(0, 2);
        }

        // Add new start state as the new start state
        ArrayList<Integer> startState = new ArrayList<Integer>();
        startState.add(0, 0);
        adjMatrix.add(startState);
        int startNum = adjMatrix.indexOf(startState);

        // Add new end state as the new accepting state
        ArrayList<Integer> endState = new ArrayList<Integer>();
        endState.add(0, 1);
        adjMatrix.add(endState);
        int endNum = adjMatrix.indexOf(endState);

        // Filling the arraylist to stop the ArrayIndexOutOfBoundsException
        // after new states added
        for (int row = 0; row < adjMatrix.size(); row++) {
            if (adjMatrix.get(row).size() < alphaSym.size()) {
                for (int i = adjMatrix.get(row).size(); i < alphaSym.size(); i++) {
                    adjMatrix.get(row).add(i, -9);
                }
            }
        }

        // Add epsilon(#) label to alphaSym if it doesn't already exist then
        // Add edge from the new start state to new end state with epsilon label
        if (alphaSym.indexOf("#") == -1) {
            alphaSym.add("#");

            // Set all values in # column to -9
            for (int i = 0; i < adjMatrix.size(); i++) {
                adjMatrix.get(i).add(alphaSym.indexOf("#"), -9);
            }
        }
        adjMatrix.get(startNum).set(alphaSym.indexOf("#"), endNum);

        // Add epsilon(#2) label to alphaSym if it doesn't already exist then
        // Add edge from new start state to start state of r with epsilon label
        if (alphaSym.indexOf("#2") == -1) {
            alphaSym.add("#2");

            // Set all values in # column to -9
            for (int i = 0; i < adjMatrix.size(); i++) {
                adjMatrix.get(i).add(alphaSym.indexOf("#2"), -9);
            }

        }
        adjMatrix.get(startNum).set(alphaSym.indexOf("#2"), r.getStartState());

        // Add edge from end state of r to start state of r with epsilon label
        adjMatrix.get(r.getEndState()).set(alphaSym.indexOf("#"),
                r.getStartState());

        // Add edge from end state of r to new end state with epsilon label
        // System.out.println(adjMatrix);
        adjMatrix.get(r.getEndState()).set(alphaSym.indexOf("#2"), endNum);

        // Returns where the start and end state of this adjMatrix is in the
        // adjMatrix ArrayList
        Machine kleNfa = new Machine(startNum, endNum);
        return kleNfa;
    }

    /**
     * Builds a regular expression for a symbol r
     * 
     * @param r
     *            Symbol
     * @return Start and end state of regular expression machine
     */
    public Machine buildReg(String r) {

        // Set state types for every state to 2 as a normal state
        // in preparation for setting new start and accepting state
        for (int i = 0; i < adjMatrix.size(); i++) {
            adjMatrix.get(i).set(0, 2);
        }

        // Add new start state as the new start state
        ArrayList<Integer> startState = new ArrayList<Integer>();
        startState.add(0, 0);
        adjMatrix.add(startState);
        int startNum = adjMatrix.indexOf(startState);

        // Add new end state as the new accepting state
        ArrayList<Integer> endState = new ArrayList<Integer>();
        endState.add(0, 1);
        adjMatrix.add(endState);
        int endNum = adjMatrix.indexOf(endState);

        // Filling the arraylist to stop the ArrayIndexOutOfBoundsException
        // after new states added
        for (int row = 0; row < adjMatrix.size(); row++) {
            if (adjMatrix.get(row).size() < alphaSym.size()) {
                for (int i = adjMatrix.get(row).size(); i < alphaSym.size(); i++) {
                    adjMatrix.get(row).add(i, -9);
                }
            }
        }

        // Add label to alphaSym if doesn't already exist then
        // Add edge from start to end state with id label

        if (alphaSym.indexOf(r) == -1) {
            alphaSym.add(r);

            // Set all values in r column to -9
            for (int i = 0; i < adjMatrix.size(); i++) {
                adjMatrix.get(i).add(alphaSym.indexOf(r), -9);
            }

        }
        adjMatrix.get(startNum).set(alphaSym.indexOf(r), endNum);

        // Returns where the start and end state of this adjMatrix is in the
        // adjMatrix ArrayList
        Machine regNfa = new Machine(startNum, endNum);
        return regNfa;
    }

    public FiniteStateAutomata getNFA() {

        System.out.println("HERE!");
        System.out.println("\n  " + alphaSym);
        for (int row = 0; row < adjMatrix.size(); row++) {

            System.out.println(row + " " + adjMatrix.get(row));
        }

        nfa = new FiniteStateAutomata(adjMatrix, alphaSym);
        return nfa;
    }
}
