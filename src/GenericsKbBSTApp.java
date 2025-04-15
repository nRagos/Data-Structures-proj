package src;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class GenericsKbBSTApp {
    static String[] tsc; //term-statement-confidence

    /**
     * main method for BST
     *
     * @param args Strings
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = 0;
        GenericBinaryST bst = new GenericBinaryST();

        while (n != 5) {
            try {
                System.out.println("Choose an action from the menu:");
                System.out.println("1. Load a knowledge base from a file");
                System.out.println("2. Add a new statement to the knowledge base");
                System.out.println("3. Search for an item in the knowledge base by term");
                System.out.println("4. Search for a item in the knowledge base by term and sentence");
                System.out.println("5. Quit");
                System.out.println("Enter your choice: ");

                n = sc.nextInt();
                if (n == 5) {
                    System.out.println("Goodbye");
                }
            } catch (InputMismatchException i) {
                System.out.println("Please enter an integer from 1-5");
                n = 0;
            }
            sc.nextLine();

            switch (n) {
                case 1:
                    try {
                        System.out.println("Enter file name");
                        String fname = sc.next();
                        File f = new File(fname);
                        Scanner sf = new Scanner(f);
                        int j = 0;
                        while (sf.hasNextLine()) {
                            bst.insert(sf.nextLine());
                            j += 1;
                        }
                        System.out.println("Knowledge base loaded successfully.");
                        sf.close();
                    } catch (FileNotFoundException e) {
                        System.out.println("Error 404 file not found");
                    }
                    break;
                case 2:
                    System.out.println("Enter the term: ");
                    String term = sc.nextLine();
                    String search1 = bst.search(term);
                    System.out.println("Enter the statement: ");
                    String statem = sc.nextLine();
                    System.out.println("Enter the confidence score: ");
                    String conf = sc.nextLine();
                    String s = String.format("%s\t%s\t%s", term, statem, conf);
                    if (search1 != null) {
                        tsc = search1.split("\t");
                        if (Float.parseFloat(conf) >= Float.parseFloat(tsc[2])) {
                            bst.delete(term);
                            System.out.println(s);
                            bst.insert(s);
                            //System.out.println(state[gs1.getIndex()]);
                            System.out.println("Statement for term " + term + " has been updated.");
                        } else {
                            System.out.println("Confidence is too low");
                        }
                    } else {
                        System.out.println("Term not found, inserting new line");
                        bst.insert(s);
                    }
                    break;
                case 3:
                    System.out.println("Enter the term to search: ");
                    String term_s = sc.nextLine();
                    String search2 = bst.search(term_s);

                    if (search2 != null) {
                        tsc = search2.split("\t");

                        System.out.printf("Statement found: %s (Confidence score: %s)\n", tsc[1], tsc[2]);

                        System.out.println("would you like to see similar results? y/n");
                        String yn = sc.nextLine();
                        if (yn.equals("y")) {
                            ArrayList<String> simi = bst.sim(term_s);
                            for (String str: simi) {
                                if (str != null) {
                                    System.out.println(str);
                                }
                            }
                        }
                    } else System.out.println("Term not found");
                    break;
                case 4:
                    System.out.println("Enter the term: ");
                    String term_fs = sc.nextLine();
                    System.out.println("Enter the statement to search for: ");
                    String st = sc.nextLine();
                    String s3 = bst.searchFull(term_fs, st);

                    if (s3 != null) {
                        tsc = s3.split("\t");
                        System.out.printf("The statement was found and has a confidence score of %s.\n", tsc[2]);
                    } else {
                        System.out.println("Statement not found");
                    }
            }

        }
    }


}
