/**
 * 
 */
package lex;

import java.util.ArrayList;

/**
 * @author Piravin
 * 
 */
public class FiniteStateAutomata {

    private ArrayList<ArrayList<Integer>> fsa = new ArrayList<ArrayList<Integer>>();
    private ArrayList<String> inputSym = new ArrayList<String>();

    public FiniteStateAutomata(ArrayList<ArrayList<Integer>> adjMatrix,
            ArrayList<String> alphabet) {

        fsa = adjMatrix;
        inputSym = alphabet;
    }

    public ArrayList<ArrayList<Integer>> getFsa() {
        return fsa;
    }

    public void setFsa(ArrayList<ArrayList<Integer>> f) {
        fsa = f;
    }

    public ArrayList<String> getInputSym() {
        return inputSym;
    }

    public void setInputSym(ArrayList<String> i) {
        inputSym = i;
    }

}
