package lex;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.SystemColor;


/**
 * This was created mostly by WindowBuilder.
 * @author Piravin Satkunarajah
 */
public class LexView extends JFrame {


    /**
     * Serial id of LexView.
     */
    private static final long serialVersionUID = -4073714931153620805L;
    /**
     * The JPanel which holds the rest below.
     */
    private JPanel contentPane;
    /**
     * This is where the user inputs the string.
     */
    private JTextField txtInputString = new JTextField();
    /**
     * This is clicked to enter the expression to get the result.
     */
    private JButton btnOkButton = new JButton("OK");
    /**
     * This is where the answer is shown.
     */
    private JLabel lblAnswerIsHere = new JLabel("");

    private final JTextField txtLocation = new JTextField();
    private final JLabel lblDevTreeLocation = new JLabel("Dev Tree Location");

//    /**
//     * Launch the application.
//     */
//    public static void main(String[] args) {
//        EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                try {
//                    CalcView frame = new CalcView();
//                    frame.setVisible(true);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//    }

    /**
     * Create the frame.
     */
    public LexView() {
        setTitle("T-Lex - Lexical Analyser");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 400, 268);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(51, 153, 255));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new MigLayout("", "[][][][grow][][]", "[][][][][][][][][]"));
        
                JLabel lblTitle = new JLabel(
                        "Lexical Analyser");
                lblTitle.setForeground(Color.WHITE);
                lblTitle.setFont(new Font("Gill Sans MT", Font.PLAIN, 16));
                contentPane.add(lblTitle, "cell 2 0");
        lblDevTreeLocation.setForeground(Color.WHITE);
        
        contentPane.add(lblDevTreeLocation, "cell 0 2");
        txtLocation.setForeground(Color.WHITE);
        txtLocation.setBackground(Color.BLUE);
        txtLocation.setColumns(10);
        
        contentPane.add(txtLocation, "cell 2 2 2 1,growx");

        JLabel lblInputString = new JLabel("Input String");
        lblInputString.setForeground(Color.WHITE);
        contentPane.add(lblInputString, "cell 0 4 2 1");
        txtInputString.setForeground(Color.WHITE);
        txtInputString.setBackground(Color.BLUE);
        contentPane.add(txtInputString, "cell 2 4 2 1,growx");
        txtInputString.setColumns(10);
        btnOkButton.setBackground(SystemColor.textHighlight);

        contentPane.add(btnOkButton, "cell 2 6 2 1,alignx center");

        JLabel lblResult = new JLabel("Result:");
        lblResult.setForeground(Color.WHITE);
        contentPane.add(lblResult, "cell 0 8");

        contentPane.add(lblAnswerIsHere, "cell 3 8");
    }

    /**
     * Adds the Action Listener to the OK button.
     * @param mal Action Listener
     */
    public final void addOKListener(final ActionListener mal) {
        btnOkButton.addActionListener(mal);
    }

    /**
     * Gets the user input from the user.
     * @return the expression.
     */
    public final String getUserInput() {
        return txtInputString.getText();
    }
    
    /**
     * Gets the dev tree location from the user.
     */
    public final String getDevTreeLocation() {
        return txtLocation.getText();
    }

    /**
     * Sets the answer to the GUI.
     * @param answer The answer from the expression.
     */
    public final void setAnswer(final String answer) {
        lblAnswerIsHere.setText(answer);
    }
}
