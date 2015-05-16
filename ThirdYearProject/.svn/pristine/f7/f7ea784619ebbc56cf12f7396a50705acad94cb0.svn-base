/**
 * 
 */
package lex;

import java.util.ArrayList;

/**
 * @author Piravin Satkunarajah Traversal algorithm based on Machine
 *         Fundamentals notes.
 * 
 */
public class DfaWalker {

    private FiniteStateAutomata dfa;
    private String input;
    ArrayList<String> alphaSym = new ArrayList<String>();

    /**
     * 
     */
    public DfaWalker(FiniteStateAutomata d, String i) {
        dfa = d;
        input = i;
        alphaSym = dfa.getInputSym();
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
    public boolean traversal() {

        ArrayList<ArrayList<Integer>> adjMatrix = dfa.getFsa();

        int currentState = 0;
        int nextState = 0;
        int symbolPointer = 0;
        String currentSymbol = " ";
        int step = 1;
        boolean foundTransition = false;

        // Set current and next state at the start state
        for (int row = 0; row < adjMatrix.size(); row++) {

            if (adjMatrix.get(row).get(0) == 0) {
                currentState = row;
                nextState = row;
            }
        }

        if (adjMatrix.size() == 1) {
            currentState = adjMatrix.get(0).get(1);
            nextState = currentState;
        }

        // Traverses through the DFA
        // until it reaches the end of the input string
        while (symbolPointer < input.length()) {

            foundTransition = false;

            currentSymbol = Character.toString(input.charAt(symbolPointer));

            // If the current input symbol matches then transition to next state
            for (int i = 1; i < alphaSym.size(); i++) {

                if (currentSymbol.equals(alphaSym.get(i))) {
                    nextState = adjMatrix.get(currentState).get(i);
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
            if (nextState == -9) {
                System.out.println("Step " + step + ": No transition labelled "
                        + currentSymbol + " from state " + currentState);
                return false;
            }

            // if (nextState >= dfa.getFsa().size()) {
            // return false;
            // }

            System.out.println("Step " + step + ": A transition labelled "
                    + currentSymbol + " from state " + currentState
                    + " to state " + nextState);
            step++;
            symbolPointer++;
            currentState = nextState;
        }

        // Returns if last state is an accepting state
        return adjMatrix.get(currentState).get(0) == 1;
    }

}
