import java.io.*;
import java.util.*;
public class Main {
    public static void main(String[] args) {
        int userSelection = 0;
        String input;
        Scanner s = new Scanner(System.in);
        String filepath = "";
        File file;
        while(userSelection != 9) {
            System.out.print("Welcome to Flashcard Manager\n" +
                    "Please make a selection from the menu below:\n" +
                    "0 - Select a directory to work in\n" +
                    "1 - Create a new flashcard stack\n" +
                    "2 - Edit flashcard stack\n" +
                    "3 - Study a flashcard stack\n" +
                    "4 - Quiz with a flashcard stack\n" +
                    "5 - Style guide\n" +
                    "6 - README\n" +
                    "8 - About\n" +
                    "9 - Quit\n");
            userSelection = s.nextInt();
            s.nextLine().strip();
            switch(userSelection) {
                case 0:
                    System.out.print("Type filepath: ");
                    input = s.nextLine().strip();
                    System.out.print("Confirm that " + input + " is the filepath you wish to create card stacks in (y/n): ");
                    if (s.nextLine().strip().toLowerCase().charAt(0) == 'y') {
                        filepath = input.strip();
                        System.out.print("Filepath now saved as " + filepath + "\n");
                    } else
                        System.out.print("Filepath will remain as " + filepath + "\n");
                    break;
                case 1:
                    System.out.print("Card stack title (no spaces or special characters; filename material): ");
                    String title = s.nextLine().strip();
                    System.out.print("Confirm the creation of " + title + " at " + filepath + "\\" + title + ".txt (y/n): ");
                    if (s.nextLine().strip().toLowerCase().charAt(0) == 'y')
                        try {
                            file = new File(filepath + "\\" + title + ".txt");
                            if (file.createNewFile()) {
                                System.out.print("Card stack " + file.getName() + " successfully created at " + file.getPath() + "\n");
                            } else {
                                System.out.print("Card stack " + title + ".txt already exists at " + file.getPath() + "\n");
                            }
                        } catch (IOException e) {
                            System.out.print("An error occurred, sorry for the inconvenience\n");
                            e.printStackTrace();
                        }
                    break;
                case 2:
                    System.out.print("Access file at " + filepath + "\\");
                    try {
                        input = s.nextLine().strip();
                        file = new File(filepath + "\\" + input);
                        Scanner readFile = new Scanner(file);
                        ArrayList<String> fileContents = new ArrayList<String>();
                        while (readFile.hasNextLine())
                            fileContents.add(readFile.nextLine());
                        FileWriter myWriter = new FileWriter(filepath + "\\" + input);
                        System.out.print("File at " + file.getPath() + " successfully accessed\n");
                        for (String str : fileContents)
                            System.out.print(str + "\n");
                        System.out.print("Add a card to stack " + file.getName() + "? (y/n): ");
                        input = s.nextLine().strip();
                        while (input.toLowerCase().charAt(0) == 'y') {
                            input = s.nextLine().strip();
                            if (input.split(":").length == 2) {
                                fileContents.add(input);
                                System.out.print("The card " + input + " successfully added to stack " + file.getName() + ". Add another card? (y/n): ");
                            } else
                                System.out.print("The input " + input + " has a syntax error. Please consult style guide.\nAdd another card? (y/n): ");
                            input = s.nextLine().strip();
                        }
                        String toWrite = "";
                        for (String str : fileContents)
                            toWrite += str + "\n";
                        myWriter.write(toWrite);
                        myWriter.close();
                    } catch (IOException e) {
                        System.out.print("An error occurred, sorry for the inconvenience\n");
                        e.printStackTrace();
                    }
                    break;
                case 3:
                    System.out.print("Access file at " + filepath + "\\");
                    input = s.nextLine().strip();
                    ArrayList<Flashcard> cardStack = new ArrayList<Flashcard>();
                    try {
                        file = new File(filepath + "\\" + input);
                        Scanner readFile = new Scanner(file);
                        ArrayList<String> fileContents = new ArrayList<String>();
                        while (readFile.hasNextLine())
                            fileContents.add(readFile.nextLine());
                        System.out.print("File at " + file.getPath() + " successfully accessed\n");
                        for (String str : fileContents)
                            cardStack.add(new Flashcard(str));
                        for (Flashcard f : cardStack)
                            System.out.print(f + "\n");
                        for (Flashcard f : cardStack) {
                            boolean correct = false;
                            int clue = 0;
                            while (!correct) {
                                for (int i = 0; i <= clue; i++) {
                                    if (i < f.getClues().size())
                                        System.out.print("Clue " + (i + 1) + ": " + f.getClue(i) + "\n");
                                }
                                System.out.print("Enter your guess or (!) to get another clue: ");
                                input = s.nextLine().strip();
                                if (!input.equals("!")) {
                                    for (String str : f.getAnswers())
                                        if (input.toLowerCase().contains(str.strip().toLowerCase()))
                                            correct = true;
                                    if (correct) {
                                        System.out.print("Correct answer. All answers are");
                                        for (String str : f.getAnswers())
                                            System.out.print(" " + str);
                                        System.out.print("\n");
                                    } else
                                        System.out.print("Incorrect answer\n");
                                } else
                                    if (clue < f.getClues().size()) {
                                        clue++;
                                        System.out.print("No more clues. Your next clue request will result in this card being skipped\n");
                                    } else {
                                        correct = true;
                                        System.out.print("You failed to guess this one. All answers are");
                                        for (String str : f.getAnswers())
                                            System.out.print(" " + str);
                                        System.out.print("\n");
                                    }
                            }
                        }
                    } catch (IOException e) {
                        System.out.print("An error occurred, sorry for the inconvenience\n");
                        e.printStackTrace();
                    }
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    break;
                case 8:
                    System.out.print("Copyright 2024 MTGmonkey\n" +
                            "Creative Commons Attributed-NonCommercial-ShareAlike 4.0 International\n" +
                            "Created for people who want a clean, light, and intuitive study method\n");
                    break;
                default:
                    break;
            }

        }

    }
}
