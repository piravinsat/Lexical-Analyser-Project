package dfa;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class traverses an example DFA.
 * 
 * @author Piravin Satkunarajah Traversal algorithm based on Machine
 *         Fundamentals notes.
 */
public class DfaWalker {

    /**
     * The main method reads in the input string and DFA from txt files then
     * calls the traversal method.
     * 
     * @param args
     *            Arguments
     */
    public static void main(final String[] args) {
        new DfaWalker();

    }

    DfaWalker() {
        String input = "ba";
        String alphaSym = "a b";

        // NOTE: "99" represents no transition

        // The last element represents if the state is a start or accepting
        // state
        // Start State = 0
        // Accepting State = 1
        // Normal State = 2

        @SuppressWarnings("unused")
        int[][] exampleDFA = {
                // a - b - c - state type
                { 1, 2, 1, 0 }, // State 0
                { 4, 2, 4, 2 }, // State 1
                { 5, 6, 7, 1 }, // State 2
                { 99, 3, 99, 1 }, // State 3
                { 99, 3, 99, 1 }, // State 4
                { 5, 6, 99, 1 }, // State 5
                { 99, 6, 99, 1 }, // State 6
                { 99, 99, 99, 2 } // State 7
        };

        // 2D ArrayList for the DFA
        ArrayList<ArrayList<Integer>> dfa = new ArrayList<ArrayList<Integer>>();

        try {
            // Reads in the inputString.txt file
            Scanner scan = new Scanner(new FileInputStream("inputString.txt"));

            // Checks if there's a string in the file
            // and then assigns to the input variable
            if (scan.hasNext()) {
                input = scan.nextLine();
            }

            // Now scans in the dfa txt file
            scan = new Scanner(new FileInputStream("dfa.txt"));

            // Scans the alphabet symbols of the DFA
            if (scan.hasNext()) {
                alphaSym = scan.nextLine().replace(" ", "");
            }

            // While there's a row to be read in
            while (scan.hasNextLine()) {

                // Make a new row ArrayList
                ArrayList<Integer> row = new ArrayList<Integer>();
                // Reads in the line integer by integer
                Scanner colScan = new Scanner(scan.nextLine());

                // Adds it to the row variable
                while (colScan.hasNextInt()) {
                    row.add(colScan.nextInt());
                }

                colScan.close();
                // Adds row to DFA ArrayList
                dfa.add(row);
            }
            scan.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println("Input string: " + input);

        if (traversal(dfa, input, alphaSym)) {
            System.out.println("ACCEPTED");
        } else {
            System.out.println("REJECTED");
        }
    }

    /**
     * This method does the traversal using the given DFA and input string.
     * 
     * @param dfa
     *            DFA as a 2D ArrayList
     * @param input
     *            Input string
     * @param alphaSym
     *            Alphabet symbols
     * @return accept or reject
     */
    public boolean traversal(final ArrayList<ArrayList<Integer>> dfa,
            final String input, final String alphaSym) {

        int currentState = 0;
        int nextState = 0;
        int symbolPointer = 0;
        char currentSymbol = ' ';
        int step = 1;
        boolean foundTransition = false;

        // Set current and next state at the start state
        for (int row = 0; row < dfa.size(); row++) {
            if (dfa.get(row).get(3) == 0) {
                currentState = row;
                nextState = row;
            }
        }

        // Traverses through the DFA
        // until it reaches the end of the input string
        while (symbolPointer < input.length()) {

            currentSymbol = input.charAt(symbolPointer);

            // If the current input symbol matches then transition to next state
            for (int i = 0; i < alphaSym.length(); i++) {

                if (currentSymbol == alphaSym.charAt(i)) {
                    nextState = dfa.get(currentState).get(i);
                    foundTransition = true;
                }
            }

            if (!foundTransition) {
                // If the current input symbol is not a label in the DFA
                // then reject the input string
                System.out.println("Step " + step + ": No transition labelled "
                        + currentSymbol + " from state " + currentState);
                return false;
            }

            // If there's no transitions for the current input symbol then
            // reject
            // the input string
            if (nextState == 99) {
                System.out.println("Step " + step + ": No transition labelled "
                        + currentSymbol + " from state " + currentState);
                return false;
            }
            System.out.println("Step " + step + ": A transition labelled "
                    + currentSymbol + " from state " + currentState
                    + " to state " + nextState);
            step++;
            symbolPointer++;
            currentState = nextState;
        }

        // Returns if last state is an accepting state
        return dfa.get(currentState).get(3) == 1;
    }
}
