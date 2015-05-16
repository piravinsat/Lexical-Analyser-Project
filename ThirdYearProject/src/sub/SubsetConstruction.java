package sub;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

/**
 * This program generates an equivalent DFA from an input NFA.
 * 
 * The DFA is written to subdfa.txt
 * 
 * @author Piravin Satkunarajah
 */
public class SubsetConstruction {

    ArrayList<ArrayList<Integer>> nfa = new ArrayList<ArrayList<Integer>>();
    ArrayList<ArrayList<Integer>> dfa = new ArrayList<ArrayList<Integer>>();
    ArrayList<State> Dstates = new ArrayList<State>();
    ArrayList<String> nfaAlphaSym = new ArrayList<String>();
    ArrayList<String> dfaAlphaSym = new ArrayList<String>();

    /**
     * The constructor is acting as the main method for this program.
     */
    public SubsetConstruction() {
        readInNFA();
        System.out.println("Input NFA:");
        System.out.println(" Hello " + nfaAlphaSym);
        for (int row = 0; row < nfa.size(); row++) {

            System.out.println(row + " " + nfa.get(row));
        }
        construct();
        populateDFA();

        System.out.println("\nOutput DFA:");
        System.out.println(" " + dfaAlphaSym);
        for (int row = 0; row < dfa.size(); row++) {

            System.out.println(row + " " + dfa.get(row));
        }

        writeToDFAtxt();
    }

    /**
     * Subset Construction algorithm where DFA states are being generated
     */
    public void construct() {

        ArrayList<Integer> s0 = new ArrayList<Integer>();
        ArrayList<Integer> u = new ArrayList<Integer>();
        ArrayList<Integer> m = new ArrayList<Integer>();

        // Look for the start state in NFA
        for (int i = 0; i < nfa.size(); i++) {
            if (nfa.get(i).get(0) == 0) {
                s0.add(i);
            }
        }

        boolean statesAllMarked = false;

        // s0 just contain the number of start state
        Dstates.add(new State(0, "null", false, epsilonClosure(s0)));

        // while there's an umarked state T in Dstates do
        while (!statesAllMarked) {
            statesAllMarked = true;
            for (int i = 0; i < Dstates.size(); i++) {
                if (Dstates.get(i).isMarked() == false) {
                    statesAllMarked = false;
                    // mark T
                    Dstates.get(i).setMarked(true);
                    // for each input symbol a do
                    for (int j = 1; j < nfaAlphaSym.size(); j++) {
                        if (!nfaAlphaSym.get(j).equals("#")
                                && !nfaAlphaSym.get(j).equals("#2")) {
                            // U = epsilon-closure(move(T,a))
                            m = move(Dstates.get(i).getNfaStates(),
                                    nfaAlphaSym.get(j));

                            if (!m.isEmpty()) {
                                u = epsilonClosure(m);

                                Dstates.add(new State(i, nfaAlphaSym.get(j),
                                        false, u));

                            }
                        }
                    }
                }
            }

        }
    }

    /**
     * Calculates T0a using T and an input symbol where it gets the set of
     * states from N can be reached using the input symbol from a state in T.
     * 
     * @param T
     *            A state in the new DFA
     * @param symbol
     *            input symbol
     * @return move Ta = moveN(T,a)
     */
    public ArrayList<Integer> move(ArrayList<Integer> T, String symbol) {

        ArrayList<Integer> move = new ArrayList<Integer>();

        // For each state in T
        for (int i = 0; i < T.size(); i++) {
            // Find a state from N that can be reached from the state in T using
            // symbol
            if (nfa.get(T.get(i)).get(nfaAlphaSym.indexOf(symbol)) != 99) {
                move.add(nfa.get(T.get(i)).get(nfaAlphaSym.indexOf(symbol)));
            }
        }

        return move;
    }

    /**
     * Calculates epsilon-closure of T which is the set of NFA states reachable
     * using epsilon transitions from T
     * 
     * @param T
     *            A state in the new DFA
     * @return epsilon-closure(T)
     */
    public ArrayList<Integer> epsilonClosure(ArrayList<Integer> T) {

        Stack<Integer> u = new Stack<Integer>();

        // push all states in T e.g. s0 = {0} to the stack
        for (int i = 0; i < T.size(); i++) {
            u.push(T.get(i));
        }

        // initialise epsilon-closure(T) of T
        ArrayList<Integer> epsilonClosure = new ArrayList<Integer>();

        // while NOT stack empty do
        while (!u.isEmpty()) {

            int poppedState = u.pop();

            // Get states from where there's an epsilon transition from the
            // poppedState and push them on the stack
            // System.out.println("poppedState " + poppedState);
            if (nfaAlphaSym.indexOf("#") != -1)
                if (nfa.get(poppedState).get(nfaAlphaSym.indexOf("#")) != 99) {
                    u.push(nfa.get(poppedState).get(nfaAlphaSym.indexOf("#")));
                }

            if (nfaAlphaSym.indexOf("#2") != -1) {
                if (nfa.get(poppedState).get(nfaAlphaSym.indexOf("#2")) != 99) {
                    u.push(nfa.get(poppedState).get(nfaAlphaSym.indexOf("#2")));
                }
            }

            // If poppedState isn't contained in epsilon Closure
            if (!epsilonClosure.contains(poppedState)) {
                epsilonClosure.add(poppedState);
            }
        }

        return epsilonClosure;
    }

    /**
     * Reads in the NFA txt file (nfa.txt) and adds it to nfa ArrayList
     */
    public void readInNFA() {

        try {
            Scanner scan = new Scanner(new FileInputStream("nfa.txt"));

            // Scans the alphabet symbols of the DFA
            String alphabetLine = scan.nextLine();

            Scanner scanLine = new Scanner(alphabetLine);
            while (scanLine.hasNext()) {
                nfaAlphaSym.add(scanLine.next());
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
                // Adds row to NFA ArrayList
                nfa.add(row);
            }
            scanLine.close();
            scan.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    /**
     * Initialise and then populate the dfa ArrayList with correct values using
     * Dstates
     */
    public void populateDFA() {
        ArrayList<Integer> fill = new ArrayList<Integer>();
        fill.add(2);

        // Adding to dfa alphabet
        for (int i = 0; i < nfaAlphaSym.size(); i++) {
            if (!nfaAlphaSym.get(i).equals("#")
                    && !nfaAlphaSym.get(i).equals("#2")) {

                dfaAlphaSym.add(nfaAlphaSym.get(i));

            }
        }

        // Adding columns depending on the number of columns
        for (String s : dfaAlphaSym) {
            if (!s.equals("type")) {
                fill.add(99);
            }
        }

        // Setting the values of the table
        for (int k = 0; k < Dstates.size(); k++) {
            dfa.add(new ArrayList<Integer>(fill));
        }

        // Change values in DFA to correct transitions
        for (int j = 0; j < Dstates.size(); j++) {

            int state = Dstates.get(j).getStateNum();
            String input = Dstates.get(j).getSymbol();

            if (!input.equals("null")) {
                dfa.get(state).set(dfaAlphaSym.indexOf(input), j);
            }
        }

        // Change type column to correct values
        for (int m = 0; m < dfa.size(); m++) {
            for (int n = 0; n < Dstates.get(m).getNfaStates().size(); n++) {
                if (nfa.get(Dstates.get(m).getNfaStates().get(n)).get(0) == 0) {
                    dfa.get(m).set(0, 0);
                } else if (nfa.get(Dstates.get(m).getNfaStates().get(n)).get(0) == 1) {
                    dfa.get(m).set(0, 1);
                }
            }
        }
    }

    /**
     * Write to DFA file using dfa ArrayList.
     */
    public void writeToDFAtxt() {

        PrintWriter out;
        try {
            out = new PrintWriter("subdfa.txt");

            // Write the alphabet containing the columns as the first line
            for (String i : dfaAlphaSym) {
                out.print(i);
                out.print(" ");
            }
            out.println(" ");

            // Write out the rest of dfa transition table
            for (int row = 0; row < dfa.size(); row++) {
                for (int col = 0; col < dfa.get(row).size(); col++) {
                    out.print(dfa.get(row).get(col));
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
     * The main class which calls the constructor
     * 
     * @param args
     */
    public static void main(String[] args) {
        new SubsetConstruction();

    }

}
