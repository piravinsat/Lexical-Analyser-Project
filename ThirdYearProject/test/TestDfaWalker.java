import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

import lex.DfaWalker;
import lex.FiniteStateAutomata;

/**
 * 
 */

/**
 * @author Piravin Satkunarajah
 * 
 */
public class TestDfaWalker {

    private DfaWalker dw;
    private FiniteStateAutomata testDfa;
    
    private ArrayList<ArrayList<Integer>> testAdj;
    private ArrayList<String> testDfaAlphaSym = new ArrayList<String>(
            Arrays.asList("type", "a", "b"));
    private ArrayList<Integer> testRow1 = new ArrayList<Integer>(Arrays.asList(
            0, 1, -1));
    private ArrayList<Integer> testRow2 = new ArrayList<Integer>(Arrays.asList(
            1, -1, 2));
    private ArrayList<Integer> testRow3 = new ArrayList<Integer>(Arrays.asList(
            2, -1, -1));

    
    private String testString = "ab";
    private String testBadString = "xyz";

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        testAdj = new ArrayList<ArrayList<Integer>>(Arrays.asList(testRow1,
                testRow2, testRow3));
        testDfa = new FiniteStateAutomata(testAdj, testDfaAlphaSym);
        dw = new DfaWalker(testDfa, testString);
    }

    /**
     * Test 1: Testing traversal to give correct acceptance
     */
    @Test
    public void testTraversal() {
        testAdj = new ArrayList<ArrayList<Integer>>(Arrays.asList(testRow1,
                testRow2, testRow3));
        testDfa = new FiniteStateAutomata(testAdj, testDfaAlphaSym);
        dw = new DfaWalker(testDfa, testString);
        assertEquals("TEST1: test Traversal method", true, dw.traversal());
    }

    /**
     * Test 2: Testing traversal to give correct acceptance
     */
    @Test
    public void testTraversalBadString() {
        testAdj = new ArrayList<ArrayList<Integer>>(Arrays.asList(testRow1,
                testRow2, testRow3));
        testDfa = new FiniteStateAutomata(testAdj, testDfaAlphaSym);
        dw = new DfaWalker(testDfa, testBadString);
        assertEquals("TEST2: test Traversal method", false, dw.traversal());
    }

}
