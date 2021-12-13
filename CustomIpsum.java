import java.util.*;

    public class IpsumMachine {

        public static void main(String[] args) throws Exception {

            ArrayList<String> latinWords = new ArrayList<>();


            java.io.File file = new java.io.File("file.txt");

            Scanner fileSc = new Scanner(file);
            Scanner sc = new Scanner(System.in);

//        int i = 0;
            while (fileSc.hasNext()) {


                latinWords.add(fileSc.next());
            }

//        System.out.println(latinWords);

            int seed;
            ArrayList<String> userWords = new ArrayList<>();

            char activateProgram = welcomeUser(sc);



            if (activateProgram == 'y') {

                seed = chooseSeed(sc);
                Random rand = new Random(seed);
                int choice = mainMenu(sc);
                sc.nextLine();

                while (choice != 4) {

                    switch (choice) {

                        case 1:
                           if (takeWords(sc, userWords) != -1) {
                                if (userWords.size() <= 0.5 * latinWords.size()) {
                                    for (int i = 0; i < (0.25 * latinWords.size()); i++) {
                                    latinWords.add(latinWords.size() - rand.nextInt(latinWords.size() - 2), userWords.get(rand.nextInt(userWords.size())));
                                    }
                                }
                            }
                            choice = mainMenu(sc);
                            break;
                        case 2:
                            System.out.println(userWords.size());
                            wordList(userWords);
                            choice = mainMenu(sc);
                            break;
                        case 3:
                            generateText(latinWords, sc);
                            choice = mainMenu(sc);
                            break;
                        default:
                            System.out.println("Please enter a valid response.");
                            choice = sc.nextInt();
                            break;
                    }
                }



                //choice = sc.nextInt();


                System.out.println("Have a nice day!");
            } else {
                System.out.println("That's too bad :(\nHave a nice day!");
            }


        }

        public static char welcomeUser(Scanner sc) {

            System.out.println("""

                                        Welcome to Custom Ipsum!

                    This program will help you generate you own custom Lorem Ipsum text.
                    If you would like to proceed, please type yes. Otherwise, the program will terminate""");
            return sc.nextLine().toLowerCase().charAt(0);
        }

        public static int chooseSeed(Scanner sc) {
            Random tempRand = new Random();
            System.out.println("\nwould you like to enter a seed?\nIf no, a seed will be randomly generated for you");
            if (sc.nextLine().toLowerCase().charAt(0) == 'y') {
                System.out.println("Enter seed:");
                return sc.nextInt();
            } else {
                int seed = tempRand.nextInt();
                System.out.println("your seed is " + seed);
                System.out.println();
                return seed;
            }

        }

        public static int mainMenu(Scanner sc) {


            System.out.println("Please make a selection:");
            System.out.println("""
                    1.) Enter your words
                    2.) See your current word list.
                    3.) Generate your text
                    4.) Exit program""");

            return sc.nextInt();
        }


        public static int takeWords(Scanner sc, ArrayList<String> userWords) {
            System.out.println("\nHow many custom words would you like to include?");
            int numOfWords = sc.nextInt();
            if (numOfWords <= 0) {
                System.out.println("\nReturning to the main menu.\n");
                return -1;
            } else
            sc.nextLine();
            for (int i = 0; i < numOfWords; i++) {
                System.out.println("\nWord(s) left: " + (numOfWords - i));
                System.out.println("Enter a word:");
                userWords.add(sc.nextLine());

            }
            return numOfWords;
        }

        public static void wordList(ArrayList<String> userWords) {
            System.out.println("\nHere is a list of your current words:");
            for (int i = 0; i < userWords.size() - 1; i++) {
                System.out.print(userWords.get(i) + ", ");
            }
            System.out.println(userWords.get(userWords.size() - 1) + "\n");
        }

        public static int numberOfParagraphs(Scanner sc) {
            System.out.println("how many paragraphs would you like to add?");
            return sc.nextInt();
        }

        public static void generateText(ArrayList<String> latinWords, Scanner sc) {
            int numOfParagraphs = numberOfParagraphs(sc);
            System.out.print("" + latinWords.get(0) + " " + latinWords.get(1) + ", ");
            for(int h = 0;h < numOfParagraphs; h++) {
                System.out.print("\n\t");
                for (int i = 2; i < latinWords.size() - 1; i++) {
                    System.out.print(latinWords.get(i) + " ");
                    for (int j = i; j % 15 == 0; j++) {
                        System.out.println("");
                    }
                }
            }
            System.out.println("\n");


        }

    }

/*
1.) Randomize the paragraph content after the first
2.) Adjust lengths of paragraphs to be pseudo-random
3.) Do not allow excessive repeat numbers
*/
