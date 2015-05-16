package lex;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * The controller for the Lexical Analyser GUI which links the LexView and
 * LexModel.
 * 
 * @author Piravin Satkunarajah
 */
final class LexController {

    /**
     * The instance variable of where LexModel class is used.
     */
    private LexModel model = new LexModel();
    /**
     * The instance variable of where LexView class is used.
     */
    private LexView view = new LexView();


    /**
     * The constructor for CalcController class which adds Listeners.
     */
    private LexController() {
        view.addOKListener(new OKListener());
        view.setVisible(true);
    }

    /**
     * The main class which calls the constructor.
     * 
     * @param args
     *            Arguments
     */
    public static void main(final String[] args) {
        new LexController();
    }

    /**
     * CalculateListener which uses ActionListener.
     * 
     * @author Piravin
     */
    private class OKListener implements ActionListener {
        /**
         * This calls the evaluate method and then sets the answer on the GUI.
         * 
         * @param e
         *            Action Event
         */
        public void actionPerformed(final ActionEvent e) {
            
            String answer = " ";
            
            if (model.evaluate(view.getUserInput(), view.getDevTreeLocation())){
                answer = "ACCEPTED";
            } else {
                answer = "REJECTED";
            }
            
            view.setAnswer(answer);
        }
    }
}
