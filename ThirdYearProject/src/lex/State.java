package lex;

import java.util.ArrayList;

public class State {

    private int stateNum;
    private String symbol;
    private boolean marked;
    private ArrayList<Integer> nfaStates;

    public State(int s, String y, boolean m, ArrayList<Integer> n) {
        stateNum = s;
        symbol = y;
        marked = m;
        nfaStates = n;

    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String y) {
        symbol = y;
    }

    public ArrayList<Integer> getNfaStates() {
        return nfaStates;
    }

    public void setNfaStates(ArrayList<Integer> n) {
        nfaStates = n;
    }

    public int getStateNum() {
        return stateNum;
    }

    public void setStateNum(int s) {
        stateNum = s;
    }

    public boolean isMarked() {
        return marked;
    }

    public void setMarked(boolean m) {
        marked = m;
    }

}
