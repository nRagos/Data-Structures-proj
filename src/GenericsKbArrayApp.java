package src;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class GenericsKbArrayApp {
    public static String fname;

    /**
     * main method
     * @param arguments required for main method
     */
    public static void main(String[] args) {
        Scanner sc= new Scanner(System.in);
        int n=0;
        String [] state = new String[100000];

        while (n !=5){
            try {
                System.out.println("Choose an action from the menu:");
            System.out.println("1. Load a knowledge base from a file");
            System.out.println("2. Add a new statement to the knowledge base");
            System.out.println("3. Search for an item in the knowledge base by term");
            System.out.println("4. Search for a item in the knowledge base by term and sentence");
            System.out.println("5. Quit");
            System.out.println("Enter your choice: ");

            n = sc.nextInt();
            if (n==5){
                System.out.println("Goodbye");
            }
            }catch (InputMismatchException i){
                System.out.println("Please enter an integer from 1-5");
                n=0;
            }
            sc.nextLine();

            switch(n){
                case 1:
                    try{
                        System.out.println("Enter file name");
                        fname= sc.next();
                        File f= new File(fname);
                        Scanner sf= new Scanner(f);
                        int j= 0;
                        while(sf.hasNextLine()){
                            state[j]= sf.nextLine();
                            j+=1;
                        }
                        System.out.println("Knowledge base loaded successfully.");
                        sf.close();

                    }catch(FileNotFoundException e){
                        System.out.println("Error 404 file not found");
                }break;
                case 2:
                    System.out.println("Enter the term: ");
                    String term = sc.nextLine();
                    GenericsSearch gs1= new GenericsSearch(term, state);
                    String search1= gs1.search();
                    System.out.println("Enter the statement: ");
                    String statem= sc.nextLine();
                    System.out.println("Enter the confidence score: ");
                    String conf= sc.nextLine();
                    if (Float.parseFloat(conf)>=Float.parseFloat(gs1.getC())) {
                        state[gs1.getIndex()]= String.format("%s\t%s\t%s", term, statem, conf);

                        //System.out.println(state[gs1.getIndex()]);
                        System.out.println("Statement for term " + term + " has been updated.");
                    }else {
                        System.out.println("Confidence is too low");
                    }
                    break;
                case 3:
                    System.out.println("Enter the term to search: ");
                    String term_s = sc.nextLine();
                    GenericsSearch gs2= new GenericsSearch(term_s, state);
                    String search2= gs2.search();
                    System.out.println(search2);
                    System.out.println("Would you like to see similar results? y/n");
                    String yn= sc.nextLine();
                    String[] sp= search2.split("\t");
                    String[] options= new String[state.length];
                    if (yn.equals("y")){
                        for (int ii=0;ii< state.length;ii++){
                            if (state[ii]!=null){
                            String[] terr=state[ii].split("\t");
                                if (terr[0].contains(sp[0])){
                                    options[ii]=terr[0];
                            }}
                            if (options[ii]!=null){
                                System.out.println(options[ii]);
                            }
                        }
                    }

                    break;
                case 4:
                    System.out.println("Enter the term: ");
                    String term_fs = sc.nextLine();
                    System.out.println("Enter the statement to search for: ");
                    String st=sc.nextLine();
                    GenericsSearch gs3= new GenericsSearch(term_fs, state);
                    String s3= gs3.fullSearch(st);
                    if (s3!=null){
                        System.out.printf("The statement was found and has a confidence score of %s.\n", s3);
                    }else{
                        System.out.println("Statement not found");
                    }
                    break;
            }
        }
    }
    }

