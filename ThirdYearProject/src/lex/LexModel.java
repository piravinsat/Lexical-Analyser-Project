package lex;

/**
 * LexModel class calls each process in the Lexical Analyser.
 * @author Piravin
 */
public class LexModel {

    /**
     * Instance of the Thompson's Construction program.
     */
    private ThompsonsConstruction th;
    
    /**
     * Instance of the Subset Construction program.
     */
    private SubsetConstruction su;
    
    /**
     * Instance of the Minimisation program.
     */
    private Minimisation mi;
    
    /**
     * Instance of DfaWalker program.
     */
    private DfaWalker df;

    /**
     * 
     * @param input
     * @param location
     * @return
     */
    public final boolean evaluate(final String input, final String location) {
        
        th = new ThompsonsConstruction(location);
        th.startAlgorithm();
        su = new SubsetConstruction(th.getNFA());
        su.construct();
        mi = new Minimisation(su.getDFA());
        mi.minimise();
        df = new DfaWalker(mi.getMinimalDFA(), input);
        
        return df.traversal();
    }


}