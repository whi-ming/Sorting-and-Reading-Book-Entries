// -----------------------------------------------------
// COMP 249 Section U
// Assignment 3
// Question: Part 2
// Written by: Whi-Ming Joseph 40202164
// Due Date: March 30, 2023
// -----------------------------------------------------


import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class part2 extends part1{
    public part2(){}

    public static void do_part2(){
        String[] errorOriginalFileName = new String[0];
        String[] errorRecord = new String[0];
        String[] errorCode = new String[0]; //still needing to make error array
        try {
            Scanner input = new Scanner(new FileInputStream("part2/part1_genre_file_names.txt"));
            while (input.hasNextLine()){
                String fileName = input.nextLine();
                int run_count = 0;
                int[] book_count = {0};
                int[] error_count = {0};

                while (run_count < 2){ /*Same idea as previously. I am reading each file twice. First to take count
                                         of the errors and which are valid. On the second run, I can create the Book Objects
                                         based on the valid books. However, for the errors, since every file contains errors,
                                         I had to work on that differently, I will explain further down*/
                    Book[] bookArray = new Book[book_count[0]];
                    /*The idea is that inside the loop I need to be able to count each error in the file. However, i needed to save that
                    * count outside of it. On the first run the count is made. On the second run, the outside array will copy itself and
                    * add the count in length. In the second count, the errors will be written in the outside array.*/
                    int[] error_entry_counter = {errorOriginalFileName.length};
                    errorOriginalFileName = Arrays.copyOf(errorOriginalFileName,errorOriginalFileName.length + error_count[0]);
                    errorRecord = Arrays.copyOf(errorRecord,errorOriginalFileName.length);
                    errorCode = Arrays.copyOf(errorCode,errorRecord.length);
                    error_count[0] = 0;
                    book_count[0] = 0;
                    BufferedReader book = new BufferedReader((new FileReader("part1/" + fileName)));
                    String line;
                    while ((line = book.readLine()) != null){
                        String[] arrayOfBookInfo = sort(line);
                        try {
                            createBook(bookArray, arrayOfBookInfo,error_count,run_count, errorCode, book_count, error_entry_counter);
                        }catch (CustomExceptions e){
                            if (run_count == 1){
                                errorOriginalFileName[error_entry_counter[0] - 1] = fileName;
                                errorRecord[error_entry_counter[0] - 1] = arrayOfBookInfo[0];
                            }
                        }
                    }
                    if (run_count == 1){
                        printToSer(bookArray, "part2/" + fileName + ".ser");
                    }
                    book.close();
                    run_count++;
                    //repeat
                }
                //Next file
            }
            printToErrorFile(errorOriginalFileName,errorRecord, errorCode);
            input.close();
        } catch (IOException e) {
        e.printStackTrace();
    }
    }
    public static void createBook(Book[] bookArray, String[] arrayOfBookInfo, int[] error_count,int run_count, String[] errorCode, int[] book_count, int[] error_entry_counter) throws CustomExceptions{
        String title = arrayOfBookInfo[1];
        String authors = arrayOfBookInfo[2];
        double price = Double.parseDouble(arrayOfBookInfo[3]);
        String isbn = arrayOfBookInfo[4];
        String genre = arrayOfBookInfo[5];
        int year = Integer.parseInt(arrayOfBookInfo[6]);

        if (!validISBN(isbn)){
            if (isbn.length() == 10){
                throw new BadIsbn10Exception(run_count, errorCode, error_count, isbn, error_entry_counter);
            }
            else if (isbn.length() == 13){
                throw new BadIsbn13Exception(run_count,errorCode, error_count, isbn, error_entry_counter);
            }
        }
        if (price < 0){
            throw new BadPriceException(run_count, errorCode, error_count, price, error_entry_counter);
        }
        if (year < 1995 || year > 2010){
            throw new BadYearException(run_count, errorCode, error_count, year, error_entry_counter);
        }
        else{
            if (run_count != 0){
                Book book = new Book(title, authors, price, isbn, genre, year);
                bookArray[book_count[0]] = book;
            }
            book_count[0]++;
        }
    }
    public static boolean validISBN(String isbn){
        try {
            int x1 = Integer.parseInt(String.valueOf(isbn.charAt(0)));
            int x2 = Integer.parseInt(String.valueOf(isbn.charAt(1)));
            int x3 = Integer.parseInt(String.valueOf(isbn.charAt(2)));
            int x4 = Integer.parseInt(String.valueOf(isbn.charAt(3)));
            int x5 = Integer.parseInt(String.valueOf(isbn.charAt(4)));
            int x6 = Integer.parseInt(String.valueOf(isbn.charAt(5)));
            int x7 = Integer.parseInt(String.valueOf(isbn.charAt(6)));
            int x8 = Integer.parseInt(String.valueOf(isbn.charAt(7)));
            int x9 = Integer.parseInt(String.valueOf(isbn.charAt(8)));
            int x10 = Integer.parseInt(String.valueOf(isbn.charAt(9)));


            if (isbn.length() == 10){
                return (10 * x1 + 9 * x2 + 8 * x3 + 7 * x4 + 6 * x5 + 5 * x6 + 4 * x7 + 3 * x8 * 2 * x9 + x10) % 11 == 0;
            }
            else if (isbn.length() == 13){
                int x11 = Integer.parseInt(String.valueOf(isbn.charAt(10)));
                int x12 = Integer.parseInt(String.valueOf(isbn.charAt(11)));
                int x13 = Integer.parseInt(String.valueOf(isbn.charAt(12)));
                return (x1 + 3 * x2 + x3 + 3 * x4 + x5 + 3 * x6 + x7 + 3 * x8 + x9 + 3 * x10 + x11 + 3 * x12 + x13) % 10 == 0;
            }
        }
        catch (NumberFormatException e){
            return false;
        }

        return false;
    }
    public static void printToSer(Book[] Book, String fileName) throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName));
        for (Book book : Book){
            oos.writeObject(book);
        }
        oos.close();
    }
    public static void printToErrorFile(String[] ErrorOriginalFileName, String[] ErrorRecord, String[]ErrorCode) throws FileNotFoundException {
        PrintWriter pw = new PrintWriter(new FileOutputStream("part2/semantic_error_file.txt"));
        for (int i = 0; i < ErrorCode.length; i++){
            String errorOriginalFileName = ErrorOriginalFileName[i];
            String errorRecord = ErrorRecord[i];
            String errorCode = ErrorCode[i];
            pw.println("Syntax Error in file: " + errorOriginalFileName +
                    "\n--------------------------\n" +
                    "Error: " + errorCode +
                    "\nRecord: " + errorRecord +
                    "\n\n=========================================\n");
        }
        pw.close();
    }
}
