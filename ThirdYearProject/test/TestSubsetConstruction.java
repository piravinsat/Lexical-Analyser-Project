import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;

import lex.FiniteStateAutomata;
import lex.SubsetConstruction;

import org.junit.Before;
import org.junit.Test;

/**
 * 
 */

/**
 * @author Piravin Satkunarajah
 * 
 */
public class TestSubsetConstruction {

    private SubsetConstruction su;
    private FiniteStateAutomata testNfa;
    private ArrayList<ArrayList<Integer>> testAdj;
    private ArrayList<String> testAlphaSym = new ArrayList<String>(
            Arrays.asList("type", "a", "b"));

    private ArrayList<Integer> testRow1 = new ArrayList<Integer>(Arrays.asList(
            2, 1, -1, -1));
    private ArrayList<Integer> testRow2 = new ArrayList<Integer>(Arrays.asList(
            2, -1, -1, 2));
    private ArrayList<Integer> testRow3 = new ArrayList<Integer>(Arrays.asList(
            0, -1, 3, -1));
    private ArrayList<Integer> testRow4 = new ArrayList<Integer>(Arrays.asList(
            1, -1, -1, -1));

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {

        testAdj = new ArrayList<ArrayList<Integer>>(Arrays.asList(testRow1,
                testRow2, testRow3, testRow4));
        testNfa = new FiniteStateAutomata(testAdj, testAlphaSym);

        su = new SubsetConstruction(testNfa);
    }

    /**
     * Test 1: Test the move method if it returns a list of correct states
     */
    @Test
    public void testMove() {

        testAdj = new ArrayList<ArrayList<Integer>>(Arrays.asList(testRow1,
                testRow2, testRow3, testRow4));
        testNfa = new FiniteStateAutomata(testAdj, testAlphaSym);

        su = new SubsetConstruction(testNfa);
        ArrayList<Integer> testMoveList = new ArrayList<Integer>();
        ArrayList<Integer> testT = new ArrayList<Integer>(
                Arrays.asList(1, 2, 3));
        String symbol = "a";

        assertEquals("TEST1: testMove", testMoveList, su.move(testT, symbol));
    }

    /**
     * Test 2: Test the epsilonClosure method if it returns a list of correct
     * states
     */
    @Test
    public void testEpsilonClosure() {

        testAdj = new ArrayList<ArrayList<Integer>>(Arrays.asList(testRow1,
                testRow2, testRow3, testRow4));
        testNfa = new FiniteStateAutomata(testAdj, testAlphaSym);

        su = new SubsetConstruction(testNfa);
        ArrayList<Integer> testEpsilonClosureList = new ArrayList<Integer>(
                Arrays.asList(3, 2, 1));
        ArrayList<Integer> testT = new ArrayList<Integer>(
                Arrays.asList(1, 2, 3));
        assertEquals("TEST2: testEpsilonClosure", testEpsilonClosureList,
                su.epsilonClosure(testT));
    }

    /**
     * Test 3: Get the DFA from the original NFA
     */
    @Test
    public void NFAretrievalTest() {

        testAdj = new ArrayList<ArrayList<Integer>>(Arrays.asList(testRow1,
                testRow2, testRow3, testRow4));
        testNfa = new FiniteStateAutomata(testAdj, testAlphaSym);

        ArrayList<Integer> dfaRow1 = new ArrayList<Integer>(Arrays.asList(0, 1,
                -1));
        ArrayList<Integer> dfaRow2 = new ArrayList<Integer>(Arrays.asList(1,
                -1, -1));
        ArrayList<ArrayList<Integer>> dfa = new ArrayList<ArrayList<Integer>>(
                Arrays.asList(dfaRow1, dfaRow2));

        su = new SubsetConstruction(testNfa);
        su.construct();
        assertEquals("TEST3: Retrieve NFA", dfa, su.getDFA().getFsa());
    }

}
