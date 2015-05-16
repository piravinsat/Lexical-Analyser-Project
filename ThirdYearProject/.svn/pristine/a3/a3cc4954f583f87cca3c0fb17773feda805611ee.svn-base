package min;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

/**
 * Proof of concept of Minimising DFA
 * 
 * @author Piravin Satkunarajah
 */
public class Minimisation {

    ArrayList<ArrayList<Integer>> dfa = new ArrayList<ArrayList<Integer>>();
    ArrayList<ArrayList<Integer>> minDfa = new ArrayList<ArrayList<Integer>>();
    ArrayList<String> dfaAlphaSym = new ArrayList<String>();
    int moveTable[][];

    public Minimisation() {
        readInDFA();
        minimise();
    }

    /**
     * Read in the DFA which we are going to convert into a minimal DFA.
     */
    public void readInDFA() {

        try {
            Scanner scan = new Scanner(new FileInputStream("subdfa.txt"));

            // Scans the alphabet symbols of the DFA
            String alphabetLine = scan.nextLine();

            Scanner scanLine = new Scanner(alphabetLine);
            while (scanLine.hasNext()) {
                dfaAlphaSym.add(scanLine.next());
            }

            // While there's a row to be read in
            while (scan.hasNextLine()) {

                // Make a new row ArrayList
                ArrayList<Integer> row = new ArrayList<Integer>();
                // Reads in the line integer by integer
                Scanner colScan = new Scanner(scan.nextLine());

                // Adds it to the row variable
                while (colScan.hasNextInt()) {
                    row.add(colScan.nextInt());
                }

                colScan.close();
                // Adds row to NFA ArrayList
                dfa.add(row);
            }
            scanLine.close();
            scan.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    /**
     * Minimisation of the DFA is done here where the initial move table built
     */
    public void minimise() {

        int partitionsCount;

        // Add a new dead state d
        ArrayList<Integer> deadState = new ArrayList<Integer>();

        // The number 3 correspond the type of dead state
        deadState.add(3);
        int deadStatePos = dfa.size();

        for (int i = 1; i < dfaAlphaSym.size(); i++) {
            // The size of the DFA corresponds to the position of the new dead
            // state
            deadState.add(deadStatePos);
        }

        dfa.add(deadState);

        // Check if dead state added
        System.out.println(dfa);

        // Adding arrows from every other state to d so until there's
        // an arrow labelled with each element of L
        for (int j = 0; j < dfa.size(); j++) {
            for (int k = 0; k < dfa.get(j).size(); k++) {
                if (dfa.get(j).get(k) == 99) {
                    dfa.get(j).set(k, deadStatePos);
                }
            }
        }

        // Check if it's correct
        System.out.println(dfa);

        int rowSize = dfaAlphaSym.size() + 1;
        moveTable = new int[dfa.size()][rowSize];

        // Divide the states into two disjoint partitions where one is the
        // dead state and normal states and other is accepting states.

        // Add the rest of the states
        for (int m = 0; m < dfa.size(); m++) {
            moveTable[0][m] = m;

            // If an accepting state place it in partition one
            // else place it in partition zero
            if (dfa.get(m).get(0) == 1) {
                moveTable[1][m] = 1;
            } else {
                moveTable[1][m] = 0;
            }
        }

        partitionsCount = 2;

        // Build a move table in which the entry states are replaced with set s0
        // or s1 to which they belong

        for (int i = 2; i <= dfaAlphaSym.size(); i++) {
            for (int j = 0; j < dfa.size(); j++) {

                moveTable[i][j] = moveTable[1][dfa.get(j).get(i - 1)];
            }
        }

        // Check the states match
        // else split partitions again
        ArrayList<Integer> unMatchedPartitions = checkPartitions(partitionsCount);

        System.out.println("WORKS AT THIS POINT 1");

        // int count = 0;
        System.out.println("TEST " + unMatchedPartitions);

        while (unMatchedPartitions.size() > 1) {
            System.out.println("WORKS AT THIS POINT 2");
            partitionsCount = splitPartitions(unMatchedPartitions,
                    partitionsCount);
            unMatchedPartitions = checkPartitions(partitionsCount);

            // System.out.println("TEST " + count + unMatchedPartitions);
            // count++;
        }

        // If it does then discard the dead state and the process stops and a
        // minidfa.txt is returned.
        // Remove the dead state
        dfa.remove(dfa.size() - 1);
        for (int i = 0; i < moveTable.length; i++) {
            for (int j = 0; j < moveTable[0].length; j++) {

                System.out.print(moveTable[i][j]);
            }
            System.out.println("");
        }
        System.out.println(dfaAlphaSym);
        writeOutMinDFA(moveTable, partitionsCount);

    }

    /**
     * Check each partition to see if the values match
     * 
     * @param partitionsCount
     * @return A list of unmatched partitions.
     */
    public ArrayList<Integer> checkPartitions(int partitionsCount) {
        int matchState;

        ArrayList<Integer> unMatchedPartitions = new ArrayList<Integer>();
        unMatchedPartitions.add(-1);

        // Check the states match
        for (int i = 0; i < partitionsCount; i++) {

            matchState = 0;

            // Set up match for partition
            for (int j = 0; j < dfa.size(); j++) {
                if (moveTable[1][j] == i) {
                    matchState = j;
                    break;
                }
            }

            // Match matchState with the rest of the states in the partition
            for (int k = 0; k < dfa.size(); k++) {
                // If the state in the same partition
                if (moveTable[1][k] == i) {
                    // Check values for each symbol are the same
                    for (int l = 2; l < moveTable.length; l++) {
                        if (moveTable[l][matchState] != moveTable[l][k]) {
                            // If they aren't then note that partition is not
                            // matched.
                            if (!unMatchedPartitions.contains(i)) {
                                unMatchedPartitions.add(i);
                            }
                        }
                    }
                }
            }

        }

        System.out.println("HOWDY!" + unMatchedPartitions);
        return unMatchedPartitions;

    }

    /**
     * Splits up unmatched partitions
     * 
     * @param unMatchedPartitions
     * @param partitionsCount
     * @return The number of partitions after splitting
     */
    public int splitPartitions(ArrayList<Integer> unMatchedPartitions,
            int partitionsCount) {

        int matchState = 0;

        System.out.println(unMatchedPartitions + " "
                + unMatchedPartitions.size());

        // Split up each unMatched Partition
        for (int j = 1; j < unMatchedPartitions.size(); j++) {

            // System.out.println("count " + j);
            // Set up match for partition
            for (int i = 0; i < dfa.size(); i++) {
                if (moveTable[1][i] == unMatchedPartitions.get(j)) {
                    // System.out.println("unMatchedPartitions " +
                    // unMatchedPartitions.get(j) );
                    matchState = i;
                    System.out.println("matchState " + matchState);
                    break;
                }
            }

            // Find states that doesn't match and set up a new partition
            for (int k = 0; k < dfa.size(); k++) {

                if (moveTable[1][k] == unMatchedPartitions.get(j)) {
                    for (int l = 2; l < moveTable.length; l++) {
                        if (moveTable[l][matchState] != moveTable[l][k]) {

                            // System.out.println("SHOULD MATCH " +
                            // unMatchedPartitions.get(j));
                            // set up a new partition
                            moveTable[1][k] = partitionsCount;
                            System.out.println(moveTable[1][k] + " = "
                                    + partitionsCount);

                            // Look for matches of that state to add to the new
                            // partition
                            for (int m = 0; m < dfa.size(); m++) {

                                boolean matched = true;

                                if (moveTable[1][k] == unMatchedPartitions
                                        .get(j)) {
                                    for (int n = 2; n < moveTable.length; n++) {
                                        if (moveTable[m][k] != moveTable[n][k]) {
                                            matched = false;
                                        }
                                    }

                                    if (matched) {
                                        moveTable[1][k] = partitionsCount;
                                    }
                                }
                            }
                            
                            //Changes the number of partitions
                            HashSet<Integer> unique = new HashSet<Integer>();
                            for(int p = 0;  p < moveTable[1].length ; p++) {
                                 unique.add(moveTable[1][p]);
                            }
                            
                            partitionsCount = unique.size();
                        }
                    }
                }
            }

        }

        // Build the move table again
        for (int i = 2; i <= dfaAlphaSym.size(); i++) {
            for (int j = 0; j < dfa.size(); j++) {

                moveTable[i][j] = moveTable[1][dfa.get(j).get(i - 1)];
            }
        }

        return partitionsCount;
    }

    /**
     * Write out the new minimum DFA into a txt file.
     * 
     * @param moveTable
     *            Move Table
     * @param partitionsCount
     */
    public void writeOutMinDFA(int[][] moveTable, int partitionsCount) {

        PrintWriter out;
        try {
            out = new PrintWriter("minimaldfa.txt");

            // Write the alphabet containing the columns as the first line
            for (String s : dfaAlphaSym) {
                out.print(s);
                out.print(" ");
            }
            out.println(" ");

            // Initalise an new ArrayList for each partition
            for (int partition = 0; partition < partitionsCount; partition++) {
                ArrayList<Integer> emptyList = new ArrayList<Integer>();
                minDfa.add(emptyList);

                Boolean partitionFound = false;
                int count = 0;

                //while (!partitionFound) {
                for (count = 0; count < moveTable[0].length && !partitionFound; count++) {
                    // Look for partition first
                    if (moveTable[1][count] == partition) {
                        partitionFound = true;

                        // When found, add the rest of values
                        for (int a = 2; a < moveTable.length; a++) {
                            if (moveTable[a][count] == 3) {
                                minDfa.get(partition).add(99);
                            } else {
                                minDfa.get(partition).add(moveTable[a][count]);
                            }
                        }
                    }
                }
                    //count++;
                }
           // }

            // // Add the all of the values from the moveTable to minDfa
            // for (int a = 2; a < moveTable.length; a++) {
            // for (int b = 0; b < moveTable[0].length; b++) {
            // if (moveTable[a][b] == 3) {
            // minDfa.get(b).add(99);
            // } else {
            // minDfa.get(b).add(moveTable[a][b]);
            // }
            // }
            // }

            // Finally add the state types
            // Initalise set the state types as 2 for normal state
            for (int j = 0; j < minDfa.size(); j++) {
                minDfa.get(j).add(0, 2);
            }

            // Find the original state which was the start state and accepting
            // state (can be more than one)

            ArrayList<Integer> acceptingStates = new ArrayList<Integer>();
            int originalState = -9;

            for (int k = 0; k < dfa.size(); k++) {
                if (dfa.get(k).get(0) == 0) {
                    originalState = k;
                } else if (dfa.get(k).get(0) == 1) {
                    acceptingStates.add(k);
                }
            }

            // Match them up with the partitions
            for (int par = 0; par < partitionsCount; par++) {
                for (int m : moveTable[0]) {
                    if (moveTable[0][m] == originalState
                            && moveTable[1][m] == par) {
                        minDfa.get(par).set(0, 0);
                    } else if (acceptingStates.contains(moveTable[0][m])
                            && moveTable[1][m] == par) {
                        minDfa.get(par).set(0, 1);
                    }
                }
            }

            // for (int m : moveTable[0]) {
            // if (moveTable[0][m] == originalState) {
            // minDfa.get(m).set(0, 0);
            // } else if (acceptingStates.contains(moveTable[0][m])) {
            // minDfa.get(m).set(0, 1);
            // }
            // }

            // Remove dead state partition
            minDfa.remove(minDfa.size() - 1);

            // // Adding the state types
            // for (int j = 0; j < dfa.size(); j++) {
            // ArrayList<Integer> state = new ArrayList<Integer>();
            // state.add(dfa.get(j).get(0));
            // minDfa.add(state);
            // }
            //
            // //Filling the rest of the ArrayList with empty transition
            // initally
            // //to stop ArrayIndexOutOfBoundsExceptions
            // // for (int l = 0; l < dfa.size(); l++) {
            // // for (int k = 1; k < dfa.get(0).size(); k++) {
            // // minDfa.get(l).add(99);
            // // }
            // // }
            //
            // // System.out.println("BEFORE " + minDfa);
            //
            // // Add the rest of the new values in the minimum DFA.
            // for (int a = 2; a < moveTable.length; a++) {
            // for (int b = 0; b < moveTable[0].length - 1; b++) {
            // if (moveTable[a][b] == 3) {
            // minDfa.get(b).add(a - 1, 99);
            // } else {
            // // minDfa.get(b).add(a - 1, moveTable[a][b]);
            //
            // // Need to get partition and find the state in that
            // // partition and then add the state
            // int partition = moveTable[a][b];
            //
            // for (int state : moveTable[0]) {
            // if (moveTable[1][state] == partition) {
            // minDfa.get(state).add(a - 1, partition);
            // }
            // }
            // }
            // }
            // }

            // System.out.println("AFTER " + minDfa);

            // Write out the rest of minimal dfa transition table
            for (int row = 0; row < minDfa.size(); row++) {
                for (int col = 0; col < minDfa.get(0).size(); col++) {
                    out.print(minDfa.get(row).get(col));
                    out.print(" ");
                }
                out.println(" ");
            }
            out.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        new Minimisation();

    }

}
