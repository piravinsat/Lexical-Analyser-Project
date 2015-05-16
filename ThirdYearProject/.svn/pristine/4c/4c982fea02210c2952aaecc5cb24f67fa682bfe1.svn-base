import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;

import lex.DfaWalker;
import lex.FiniteStateAutomata;
import lex.RdpRunner;

import org.junit.Before;
import org.junit.Test;

/**
 * 
 */

/**
 * @author piravin
 *
 */
public class TestRdpRunner {
    
    private RdpRunner rd;
    private String regex = "a b";
    private testOutput = " ";
    
    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        rd = new RdpRunner("a|b");
    }

    /**
     * Junit test
     */
    @Test
    public void testRDPrunner() {
        rd = new RdpRunner(regex);
        assertEquals("TEST1: test RDP runner", testOutput, rd.getOutput());
    }

}
