package lex;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Based on How To Execute Shell Command From Java by mkyong
 * http://www.mkyong.com/java/how-to-execute-shell-command-from-java/
 * 
 * This version currently doesn't work with make or cd commands so will be
 * scrapped.
 * 
 * @author Piravin Satkunarajah
 * 
 */
public class RdpRunner {

    String regex = "(a|b)";
    String output = " ";

    public RdpRunner(String input) {
        regex = input;
        createStr(regex);

        // String command1 = "cd Documents";
        // String command2 = "cd rdp16";
        String command1 = "./runrdp"; // A bash script which does a make=GRAMMAR
                                      // command
        String command2 = "./rdparser -l -v regex.str";

		executeCommand(command1);
        output = executeCommand(command2);
        // executeCommand(command3);
        // output = executeCommand(command4);
        System.out.println(output);
        writeOutDevTree(output);
    }

    // public static void main(String[] args) {
    //
    // // RdpRunner obj = new RdpRunner();
    //
    // // String domainName = "google.com";
    //
    // // in windows
    // // String command = "ping -n 3 " + domainName;
    //
    // // String output = obj.executeCommand(command);
    //
    // // System.out.println(output);
    //
    // }

    /**
     * The derivation tree created from regular expression.
     * 
     * @return deviration tree.
     */
    public String getOutput() {
        return output;
    }

    /**
     * Create the regex.str file for rdp which contains regular expression
     * 
     * @param regexInput
     *            e.g. a*
     */
    private void createStr(String regexInput) {
        PrintWriter out;
        try {
            out = new PrintWriter("regex.str");
            out.println(regexInput);
            out.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Executes commands from command prompt
     * 
     * @param command
     * @return Output
     */
    private String executeCommand(String command) {

        StringBuffer output = new StringBuffer();

        Process p;
        try {
            String loc = "/LexerProject/rdp16";
            File location = new File(loc);
            // p = Runtime.getRuntime().exec(command);
            p = Runtime.getRuntime().exec(command, null, location);
            p.waitFor();
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    p.getInputStream()));

            String line = "";
            while ((line = reader.readLine()) != null) {
                output.append(line + "\n");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return output.toString();

    }

    /**
     * Write out the derivation tree
     * 
     * @param output
     */
    private void writeOutDevTree(String output) {
        int lineNum = 0;
        PrintWriter out;
        Scanner scan = new Scanner(output);
        try {
            out = new PrintWriter("regexdevtree.txt");
            
            while (scan.hasNextLine()) {
                lineNum++;
                String line = scan.nextLine();
                
                //Leaves out everything that isn't part of the derivation tree
                if (lineNum > 6 && !line.contains("******:")) {
                out.println(line);
                }
            }

            // out.println(output);
            scan.close();
            out.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}