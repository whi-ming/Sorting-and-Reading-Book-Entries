// -----------------------------------------------------
// COMP 249 Section U
// Assignment 3
// Question: Part 3
// Written by: Whi-Ming Joseph 40202164
// Due Date: March 30, 2023
// -----------------------------------------------------

import java.io.*;
import java.util.Locale;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class part3 extends part2 {
    static Book[] CCB = makeBookArray("Cartoons_Comics_Books.csv.ser");
    static Book[] HCB = makeBookArray("Hobbies_Collectibles_Books.csv.ser");
    static Book[] MTV = makeBookArray("Movies_TV.csv.ser");
    static Book[] MRB = makeBookArray("Music_Radio_Books.csv.ser");
    static Book[] NEB = makeBookArray("Nostalgia_Eclectic_Books.csv.ser");
    static Book[] OTR = makeBookArray("Old_Time_Radio.csv.ser");
    static Book[] SSM = makeBookArray("Sports_Sports_Memorabilia.csv.ser");
    static Book[] TPA = makeBookArray("Trains_Planes_Automobiles.csv.ser");
    public part3(){}
    public static void do_part3(){
        Scanner input = new Scanner(System.in);
        int fileNumber = 1;
        String letter;
        do{
            printMainMenu(fileNumber);
            letter = input.nextLine().toLowerCase(Locale.ROOT);
            if (letter.equals("s")){
                printSubMenu();
                try{
                    fileNumber = Integer.parseInt(input.nextLine());
                    String selection = "";
                    switch (fileNumber){
                        case 1 -> selection = "Cartoons_Comics_Books.csv.ser";
                        case 2 -> selection = "Hobbies_Collectibles_Books.csv.ser";
                        case 3 -> selection = "Movies_TV.csv.ser";
                        case 4 -> selection = "Music_Radio_Books.csv.ser";
                        case 5 -> selection = "Nostalgia_Eclectic_Books.csv.ser";
                        case 6 -> selection = "Old_Time_Radio.csv.ser";
                        case 7 -> selection = "Sports_Sports_Memorabilia.csv.ser";
                        case 8 -> selection = "Trains_Planes_Automobiles.csv.ser";
                    }
                    System.out.println("You have selected " + fileNumber + ": " + selection +
                            "\nReturning to Main Menu...");
                    System.out.println(".\n");
                    try{
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                    System.out.println(".\n");
                    try{
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }catch (NumberFormatException e){
                    System.out.println("Not an integer, returning to main menu...\n");
                    try{
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                    System.out.println(".\n");
                    try{
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                    System.out.println(".\n");
                    try{
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                    System.out.println(".\n");
                    try{
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    };
                }


            }
            else if (letter.equals("v")){
                String selection = "";
                switch (fileNumber){
                    case 1 -> selection = "Cartoons_Comics_Books.csv.ser";
                    case 2 -> selection = "Hobbies_Collectibles_Books.csv.ser";
                    case 3 -> selection = "Movies_TV.csv.ser";
                    case 4 -> selection = "Music_Radio_Books.csv.ser";
                    case 5 -> selection = "Nostalgia_Eclectic_Books.csv.ser";
                    case 6 -> selection = "Old_Time_Radio.csv.ser";
                    case 7 -> selection = "Sports_Sports_Memorabilia.csv.ser";
                    case 8 -> selection = "Trains_Planes_Automobiles.csv.ser";
                }
                System.out.println("Opening " + selection + "...");
                System.out.println(".\n");
                try{
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
                System.out.println(".\n");
                try{
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
                switch (fileNumber){
                    case 1 -> printView(CCB, "Cartoons_Comics_Books.csv.ser");
                    case 2 -> printView(HCB, "Hobbies_Collectibles_Books.csv.ser");
                    case 3 -> printView(MTV, "Movies_TV.csv.ser");
                    case 4 -> printView(MRB, "Music_Radio_Books.csv.ser");
                    case 5 -> printView(NEB, "Nostalgia_Eclectic_Books.csv.ser");
                    case 6 -> printView(OTR, "Old_Time_Radio.csv.ser");
                    case 7 -> printView(SSM, "Sports_Sports_Memorabilia.csv.ser");
                    case 8 -> printView(TPA, "Trains_Planes_Automobiles.csv.ser");
                }
            }
            else if (!letter.equals("x")){
                System.out.println("Invalid input, please try again");
                try{
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        }
        while (!letter.equals("x"));
    }
    public static Book[] makeBookArray(String fileName){
        int[] number = {0};
        try {
            number[0] = countBooks("part2/" + fileName);
        } catch (IOException e) {e.printStackTrace();}
        Book[] bookArray = new Book[number[0]];
        try {
            booksIntoArray("part2/" + fileName, bookArray);

        } catch (IOException e) {e.printStackTrace();}
        return bookArray;
    }
    public static int countBooks(String fileName) throws IOException {
       ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName));
       int i = 0;
       try{
           while (true){
              ois.readObject();
              i++;
           }
       } catch (ClassNotFoundException e) {e.printStackTrace();}
       catch(EOFException e) {}
        ois.close();
       return i;
    }
    public static void booksIntoArray(String fileName, Book[] bookArray) throws IOException{
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName));
        int i = 0;
        try{
            while (true){
                bookArray[i] = (Book)ois.readObject();
                i++;
            }
        } catch (ClassNotFoundException e) {e.printStackTrace();}
        catch(EOFException e) {}
        ois.close();
    }
    public static void printMainMenu(int fileNumber){

        String[] fileName = {""};
        int[] length = {0};
        switch (fileNumber){
            case 1 -> {fileName[0] = "Cartoons_Comics_Books.csv.ser"; length[0] = CCB.length;}
            case 2 -> {fileName[0] = "Hobbies_Collectibles_Books.csv.ser"; length[0] = HCB.length;}
            case 3 -> {fileName[0] = "Movies_TV.csv.ser"; length[0] = MTV.length;}
            case 4 -> {fileName[0] = "Music_Radio_Books.csv.ser"; length[0] = MRB.length;}
            case 5 -> {fileName[0] = "Nostalgia_Eclectic_Books.csv.ser"; length[0] = NEB.length;}
            case 6 -> {fileName[0] = "Old_Time_Radio.csv.ser"; length[0] = OTR.length;}
            case 7 -> {fileName[0] = "Sports_Sports_Memorabilia.csv.ser"; length[0] = SSM.length;}
            case 8 -> {fileName[0] = "Trains_Planes_Automobiles.csv.ser"; length[0] = TPA.length;}
        }
        String plural = "";
        if (length[0] != 1){
            plural = "s";
        }
        System.out.printf("" +
                "-----------------------------------\n" +
                "              Main Menu            \n" +
                "-----------------------------------\n" +
                "  v    View the selected file: " + fileName[0] + " (" + length[0] + " record%s)\n" +
                "  s    Select a file to view\n" +
                "  x    Exit\n" +
                "-----------------------------------\n" +
                "\n" +
                "Enter Your Choice: ", plural);
    }
    public static void printSubMenu(){
        System.out.printf("" +
                "\n----------------------------------\n" +
                "           File Sub-Menu\n" +
                "----------------------------------\n" +
                "1 %-40s%-5s\n" +
                "2 %-40s%-5s\n" +
                "3 %-40s%-5s\n" +
                "4 %-40s%-5s\n" +
                "5 %-40s%-5s\n" +
                "6 %-40s%-5s\n" +
                "7 %-40s%-5s\n" +
                "8 %-40s%-5s\n" +
                "9 %-40s\n" +
                "----------------------------------\n",
                "Cartoons_Comics_Books.csv.ser", "(" + CCB.length + " records)",
                "Hobbies_Collectibles_Books.csv.ser", "(" + HCB.length + " records)",
                "Movies_TV.csv.ser", "(" + MTV.length + " records)",
                "Music_Radio_Books.csv.ser", "(" + MRB.length + " records)",
                "Nostalgia_Eclectic_Books.csv.ser", "(" + NEB.length + " records)",
                "Old_Time_Radio.csv.ser", "(" + OTR.length + " records)",
                "Sports_Sports_Memorabilia.csv.ser", "(" + SSM.length + " records)",
                "Trains_Planes_Automobiles.csv.ser", "(" + TPA.length + " records)",
                "Exit");
        System.out.print("\nEnter Your Choice: ");
    }
    public static void printView(Book[] bookArray, String fileName){
        String plural = "";
        if (bookArray.length != 1){
            plural = "s";
        }
        System.out.println("Use the numbers to display a book.\n" +
                "A positive number n will display the the current book and the n-1 book(s) after it.\n" +
                "A negative number will show the current book and the |n|-1 book(s) before it.\n" +
                "The last book displayed becomes the current book.\n" +
                "-----------------------------------------\n" +
                "Currently Viewing: " + fileName + " (" + bookArray.length + " record" + plural + ")");
        Scanner input = new Scanner(System.in);
        int number;
        int index = 0;
        try{
            do{
                System.out.print("========================================\n" +
                        "Enter a digit: ");
                number = Integer.parseInt(input.nextLine());
                System.out.println("\nCurrently Viewing: " + fileName + " (" + bookArray.length + " record" + plural + ")\n" +
                        "------------------------------------------");
                if (number > 0){
                    try{
                        for (int i = 0; i < number; i++){
                            System.out.println((index + i) + ": " + bookArray[index + i]);
                        }
                        index = index + number - 1;
                    }
                    catch (ArrayIndexOutOfBoundsException e){
                        index = bookArray.length - 1;
                        System.out.println("EOF has been reached");
                    }
                }
                else if (number < 0){
                    number = Math.abs(number);
                    try{
                        for (int i = 0; i < number; i++){
                            System.out.println((index - i) + ": " + bookArray[index - i]);
                        }
                        index = index - number + 1;
                    }
                    catch (ArrayIndexOutOfBoundsException e){
                        index = 0;
                        System.out.println("BOF has been reached");
                    }
                }
            }while (number !=0);
        }

        catch(NumberFormatException e){
            System.out.println("Not an integer, returning to main menu...\n");
            try{
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            System.out.println(".\n");
            try{
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            System.out.println(".\n");
            try{
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }
}
