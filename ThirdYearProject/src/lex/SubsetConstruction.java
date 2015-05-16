package lex;

import java.util.ArrayList;
import java.util.Stack;

import lex.State;

/**
 * This program generates an equivalent DFA from an input NFA.
 * 
 * The DFA is written to subdfa.txt
 * 
 * @author Piravin Satkunarajah
 */
public class SubsetConstruction {

    private FiniteStateAutomata detFA;
    private ArrayList<ArrayList<Integer>> nfa = new ArrayList<ArrayList<Integer>>();
    private ArrayList<ArrayList<Integer>> dfa = new ArrayList<ArrayList<Integer>>();
    private ArrayList<State> Dstates = new ArrayList<State>();
    private ArrayList<State> moveD = new ArrayList<State>();
    private ArrayList<String> nfaAlphaSym = new ArrayList<String>();
    private ArrayList<String> dfaAlphaSym = new ArrayList<String>();

    public SubsetConstruction(FiniteStateAutomata n) {
        
        nfa = n.getFsa();
        nfaAlphaSym = n.getInputSym();
    }

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
                                moveD.add(new State(i, nfaAlphaSym.get(j), false, m));
                                u = epsilonClosure(m);

                                //if NOT U IN Dstates then
                                boolean uExists = false;
                                for (int k = 0; k < Dstates.size(); k++) {
                                    if (u.equals(Dstates.get(k).getNfaStates()) && Dstates.get(k).getSymbol().equals(nfaAlphaSym.get(j))) {
                                        uExists = true;
                                    }
                                }
                                
                                if (!uExists) {
                                Dstates.add(new State(i, nfaAlphaSym.get(j),
                                        false, u));
                                }

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
            if (nfa.get(T.get(i)).get(nfaAlphaSym.indexOf(symbol)) != -9) {
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
                if (nfa.get(poppedState).get(nfaAlphaSym.indexOf("#")) != -9) {
                    u.push(nfa.get(poppedState).get(nfaAlphaSym.indexOf("#")));
                }

            if (nfaAlphaSym.indexOf("#2") != -1) {
                if (nfa.get(poppedState).get(nfaAlphaSym.indexOf("#2")) != -9) {
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
     * Initialise and then populate the dfa ArrayList with correct values using
     * Dstates
     */
    public void populateDFA() {
        ArrayList<Integer> fill = new ArrayList<Integer>();
        //Initially set state types to normal
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
                fill.add(-9);
            }
        }

        // Setting the values of the table
        for (int k = 0; k < Dstates.size(); k++) {
            dfa.add(new ArrayList<Integer>(fill));
        }

        // Change values in DFA to correct transitions
        for (int j = 0; j < moveD.size(); j++) {

            //int state = Dstates.get(j).getStateNum();
            //String input = Dstates.get(j).getSymbol();
            int state = moveD.get(j).getStateNum();
            String input = moveD.get(j).getSymbol();
            
            int dfaState = -9;
            
            for(int k = 0; k < Dstates.size(); k++) {
                if (Dstates.get(k).getNfaStates().contains(moveD.get(j).getNfaStates().get(0))) {
                    dfaState = k;
                }
                }


                //dfa.get(state).set(dfaAlphaSym.indexOf(input), j);
                dfa.get(state).set(dfaAlphaSym.indexOf(input), dfaState);
            
        }

        // Change type column to correct values
        for (int m = 0; m < dfa.size(); m++) {
            for (int n = 0; n < Dstates.get(m).getNfaStates().size(); n++) {
                //If DFA state contains a NFA state that is a start state
                if (nfa.get(Dstates.get(m).getNfaStates().get(n)).get(0) == 0) {
                    dfa.get(m).set(0, 0);
                //If DFA state contains a NFA state that is a accepting state
                } else if (nfa.get(Dstates.get(m).getNfaStates().get(n)).get(0) == 1) {
                    dfa.get(m).set(0, 1);
                }
            }
        }
    }

    public FiniteStateAutomata getDFA() {
        populateDFA();
        detFA = new FiniteStateAutomata(dfa, dfaAlphaSym);
        return detFA;
    }

}
