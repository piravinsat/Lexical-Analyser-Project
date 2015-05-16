import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;

import lex.FiniteStateAutomata;
import lex.Minimisation;

import org.junit.Before;
import org.junit.Test;

/**
 * @author Piravin Satkunarajah
 * 
 */
public class TestMinimisation {

    /**
     * @throws java.lang.Exception
     */

    private Minimisation min;
    private ArrayList<ArrayList<Integer>> testAdj;
    private ArrayList<String> testDfaAlphaSym = new ArrayList<String>(
            Arrays.asList("type", "a", "b"));
    private FiniteStateAutomata testDfa;
    private ArrayList<Integer> testRow1 = new ArrayList<Integer>(Arrays.asList(
            0, 1, -1));
    private ArrayList<Integer> testRow2 = new ArrayList<Integer>(Arrays.asList(
            1, -1, 2));
    private ArrayList<Integer> testRow3 = new ArrayList<Integer>(Arrays.asList(
            2, -1, -1));

    @Before
    public void setUp() throws Exception {
        testAdj = new ArrayList<ArrayList<Integer>>(Arrays.asList(testRow1,
                testRow2, testRow3));
        testDfa = new FiniteStateAutomata(testAdj, testDfaAlphaSym);
        min = new Minimisation(testDfa);

    }

    /**
     * Test 1: Testing the CheckPartitions method
     */
    @Test
    public void testCheckPartitions() {
        
        testAdj = new ArrayList<ArrayList<Integer>>(Arrays.asList(testRow1,
                testRow2, testRow3));
        testDfa = new FiniteStateAutomata(testAdj, testDfaAlphaSym);
        
        min = new Minimisation(testDfa);
        min.minimise();
        int partitionsCount = 2;
        ArrayList<Integer> testUnmatchedPartitionsList = new ArrayList<Integer>();
        testUnmatchedPartitionsList.add(-1);
        
        assertEquals("TEST1: test CheckPartitions", testUnmatchedPartitionsList,
                min.checkPartitions());
    }

    /**
     * Test 2: Testing the SplitPartitions method
     */
    @Test
    public void splitPartitions() {
        
        testAdj = new ArrayList<ArrayList<Integer>>(Arrays.asList(testRow1,
                testRow2, testRow3));
        testDfa = new FiniteStateAutomata(testAdj, testDfaAlphaSym);

        min = new Minimisation(testDfa);
        min.minimise();
        ArrayList<Integer> unMatchedPartitions = new ArrayList<Integer>();
        unMatchedPartitions.add(2);
        int partitionsCount = 2;
        int newPartitionsCount = 2;
        assertEquals("TEST2: test SplitPartitions", newPartitionsCount,
                min.splitPartitions(unMatchedPartitions));
    }

    /**
     * Test 3: Testing the getMinimalDFA method
     */
    @Test
    public void getMinimalDFA() {
        
        testAdj = new ArrayList<ArrayList<Integer>>(Arrays.asList(testRow1,
                testRow2, testRow3));
        testDfa = new FiniteStateAutomata(testAdj, testDfaAlphaSym);
        
        min = new Minimisation(testDfa);
        min.minimise();
    
        ArrayList<Integer> minRow1 = new ArrayList<Integer>(Arrays.asList(0,2,-1));
        ArrayList<Integer> minRow2 = new ArrayList<Integer>(Arrays.asList(1,-1,1));
        ArrayList<Integer> minRow3 = new ArrayList<Integer>(Arrays.asList(2,-1,-1));
        ArrayList<ArrayList<Integer>> testMinAdj = new ArrayList<ArrayList<Integer>>(Arrays.asList(minRow1,minRow2,minRow3));
        


        assertEquals("TEST3: test MinimalDFA ", testMinAdj, min.getMinimalDFA().getFsa());
    }

}
