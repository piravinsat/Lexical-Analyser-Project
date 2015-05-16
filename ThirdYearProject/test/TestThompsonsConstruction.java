import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;

import lex.FiniteStateAutomata;
import lex.Machine;
import lex.ThompsonsConstruction;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

/**
 * The test class for ThompsonsConstruction
 * 
 * @author Piravin Satkunarajah
 */
public class TestThompsonsConstruction {

    /**
     * A object of the Thompson's Construction class.
     */
    private ThompsonsConstruction th; // Create Thompson's Construction class

    private String devTree[][] = { { "alt", "0", null }, { "con", "1", null },
            { "kle", "2", null }, { "reg", "3", null }, { "a", "4", null },
            { "|", "1", null }, { "con", "1", null }, { "kle", "2", null },
            { "reg", "3", null }, { "b", "4", null } };

    private Machine testAltMachine = new Machine(0, 3);
    private Machine testConMachine = new Machine(0, 3);
    private Machine testKleMachine = new Machine(0, 3);
    private Machine testRegMachine = new Machine(0, 3);

    private ArrayList<ArrayList<Integer>> testAdj;

    private ArrayList<String> testAlphaSym = new ArrayList<String>(
            Arrays.asList("type", "a", "b"));

    private FiniteStateAutomata testNfa;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        th = new ThompsonsConstruction("example.txt"); // Created constructor
                                                       // with string
                                                       // parameter which
                                                       // points to txt file
    }

    /**
     * Test 1: Get the data from the derivation tree .txt file
     */
    @Test
    public void retrievalTest() {
        th = new ThompsonsConstruction("altdevtree.txt");

        assertArrayEquals("TEST1: Get devtree", devTree, th.devTreeRetrieval());
        // Created devTreeRetrieval method
        // Complies but gives the wrong array length
        // Test succeeded by adding scanner code.

    }

    /**
     * Test 2: Return a alternation of two machines r,s
     */
    @Ignore
    public void testBuildAlternation() {
        th = new ThompsonsConstruction("altdevtree.txt");

        Machine r = new Machine(0, 1);
        Machine s = new Machine(2, 3);

        assertEquals("TEST2: Return alt", testAltMachine.getStartState(), th
                .buildAlternation(r, s).getStartState());
    }

    /**
     * Test 3: Return a concatenation of two machines r,s
     */
    @Ignore
    public void testBuildConcatenation() {
        th = new ThompsonsConstruction("condevtree.txt");

        Machine r = new Machine(0, 1);
        Machine s = new Machine(2, 3);

        assertEquals("TEST3: Return con", testConMachine.getStartState(), th
                .buildConcatenation(r, s).getStartState());
        // assertEquals("TEST3: Return con", testConMachine.getEndState(),
        // th.buildConcatenation(r, s).getEndState());
    }

    /**
     * Test 4: Return a kleene closure of a machine r
     */
    @Test
    public void testBuildKleeneClosure() {
        th = new ThompsonsConstruction("kledevtree.txt");

        Machine r = new Machine(0, 1);

        assertEquals("TEST4.1: Return kle", testKleMachine.getStartState(), th
                .buildKleeneClosure(r).getStartState());
        assertEquals("TEST4.2: Return kle", testKleMachine.getEndState(), th
                .buildKleeneClosure(r).getEndState());
    }

    /**
     * Test 5: Return a nfa machine of a input symbol
     */
    @Test
    public void testBuildReg() {
        th = new ThompsonsConstruction("regdevtree.txt");

        String r = "a";

        assertEquals("TEST5.1: Return reg", testRegMachine.getStartState(), th
                .buildReg(r).getStartState());
        assertEquals("TEST5.2: Return reg", testRegMachine.getEndState(), th
                .buildReg(r).getEndState());
    }

    /**
     * Test 6: Test returning of the NFA ArrayList
     */
    @Test
    public void ReturnNFATest() {

        ArrayList<Integer> testRow1 = new ArrayList<Integer>(Arrays.asList(2,
                1, -1));
        ArrayList<Integer> testRow2 = new ArrayList<Integer>(Arrays.asList(2,
                -1, -1));
        ArrayList<Integer> testRow3 = new ArrayList<Integer>(Arrays.asList(0,
                -1, 3));
        ArrayList<Integer> testRow4 = new ArrayList<Integer>(Arrays.asList(1,
                -1, -1));

        testAdj = new ArrayList<ArrayList<Integer>>(Arrays.asList(testRow1,
                testRow2, testRow3, testRow4));

        testNfa = new FiniteStateAutomata(testAdj, testAlphaSym);

        th = new ThompsonsConstruction("altdevtree.txt");
        th.startAlgorithm();

        assertEquals("TEST6: Return NFA ArrayList", testAdj, th.getNFA().getFsa());
    }

}
