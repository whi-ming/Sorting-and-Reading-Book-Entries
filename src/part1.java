// -----------------------------------------------------
// COMP 249 Section U
// Assignment 3
// Question: Part 1
// Written by: Whi-Ming Joseph 40202164
// Due Date: March 30, 2023
// -----------------------------------------------------


import java.io.*;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Stream;

public class part1 {
    public part1(){}
    public static void do_part1() {
        int run_count = 0;
        int[] error_counter = {0};
        int[] CCB_counter = {0};
        int[] HCB_counter = {0};
        int[] MTV_counter = {0};
        int[] MRB_counter = {0};
        int[] NEB_counter = {0};
        int[] OTR_counter = {0};
        int[] SSM_counter = {0};
        int[] TPA_counter = {0};
        int[] arrayOfCounts = new int[9];
        while (run_count < 2) { //the idea is that I will run through my program twice. the first round
                                //is to count each error and genre, so as to make an array of that size.
                                //The second time I can insert the book information in their respective array.
                                //Once everything is in an array, I can write the whole array into the proper file,
                                //allowing me to only open that file once and closing it right away.
            int CCB_count = arrayOfCounts[0];
            int HCB_count = arrayOfCounts[1];
            int MTV_count = arrayOfCounts[2];
            int MRB_count = arrayOfCounts[3];
            int NEB_count = arrayOfCounts[4];
            int OTR_count = arrayOfCounts[5];
            int SSM_count = arrayOfCounts[6];
            int TPA_count = arrayOfCounts[7];
            int error_count = arrayOfCounts[8];

            String[] CCB = new String[CCB_count];
            String[] HCB = new String[HCB_count];
            String[] MTV = new String[MTV_count];
            String[] MRB = new String[MRB_count];
            String[] NEB = new String[NEB_count];
            String[] OTR = new String[OTR_count];
            String[] SSM = new String[SSM_count];
            String[] TPA = new String[TPA_count];

            String[] errorOriginalFileName = new String[error_count];
            String[] errorRecord = new String[error_count];
            String[] errorCode = new String[error_count];
            try {
                //searching for file names
                Scanner input = new Scanner(new FileInputStream("books/part1_input_file_names.txt"));
                input.nextLine();//skipping number count, since reading will end when file has reached the end anyways
                while (input.hasNextLine()) {
                    String fileName = input.nextLine();
                    BufferedReader book = new BufferedReader(new FileReader("books/" + fileName)); //opening the file based on line read in txt file
                    String line;
                    while ((line = book.readLine()) != null) {//loop will repeat as long as theres text. Once the end is reached, loop ends and move onto the next file
                        String[] arrayOfBookInfo = sort(line);
                            try {
                                if (run_count == 0){//in first run, simply taking a count of everything
                                    sortGenre(arrayOfCounts, arrayOfBookInfo);
                                }
                                else {//on second run, i can actually put the info in their proper array since the array has been made.
                                    sortGenre(error_counter, CCB_counter, HCB_counter, MTV_counter, MRB_counter, NEB_counter, OTR_counter, SSM_counter, TPA_counter,
                                            errorCode,arrayOfBookInfo, CCB, HCB, MTV, MRB, NEB, OTR, SSM, TPA);
                                }
                            }
                            catch (CustomExceptions e) {//made a class that will be the parent of all errors, catching them all in one catch clause
                                if (run_count == 1){//only when the array size has been set can I enter the errors in the array on the second run
                                    errorOriginalFileName[error_counter[0] - 1] = fileName;
                                    errorRecord[error_counter[0] - 1] = arrayOfBookInfo[0];
                                }
                            }
                        }
                    book.close();
                    if (run_count == 1){
                        //print to files
                        printToGenreFile(CCB, "Cartoons_Comics_Books.csv");
                        printToGenreFile(HCB, "Hobbies_Collectibles_Books.csv");
                        printToGenreFile(MTV, "Movies_TV.csv");
                        printToGenreFile(MRB, "Music_Radio_Books.csv");
                        printToGenreFile(NEB, "Nostalgia_Eclectic_Books.csv");
                        printToGenreFile(OTR, "Old_Time_Radio.csv");
                        printToGenreFile(SSM, "Sports_Sports_Memorabilia.csv");
                        printToGenreFile(TPA, "Trains_Planes_Automobiles.csv");

                        printToErrorFile(errorOriginalFileName,errorRecord,errorCode);
                    }
                }
                input.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            run_count++;//marking the end of one run.
        }
    }
    public static String[] sort(String line){
        if (line.charAt(0) != '\"') {
            String[] arrayOfBookInfo = line.split(",");
            String[] holder = arrayOfBookInfo;
            arrayOfBookInfo = new String[holder.length + 1];
            for (int i = 1; i < holder.length + 1; i++) {
                arrayOfBookInfo[i] = holder[i - 1];
            }
            arrayOfBookInfo[0] = line; //saving the full record in book info
            if (line.charAt(line.length() - 1) == ',') {//taking into consideration that sometimes year is missing
                arrayOfBookInfo = Arrays.copyOf(arrayOfBookInfo, arrayOfBookInfo.length + 1);
                arrayOfBookInfo[arrayOfBookInfo.length - 1] = "";
            }
            return arrayOfBookInfo;
        }
        else {
            String[] arrayOfBookInfo = new String[1];
            arrayOfBookInfo[0] = line;
            String title = line.substring(1, line.indexOf("\","));
            String rest = line.substring(line.indexOf("\",") + 1).replace(",", "@");
            line = title + rest;
            String[] lineArray = line.split("@");
            if (line.charAt(line.length() - 1) == '@') {//taking into consideration that sometimes year is missing
                lineArray = Arrays.copyOf(lineArray, lineArray.length + 1);
                lineArray[lineArray.length - 1] = "";
            }
            arrayOfBookInfo = Arrays.copyOf(arrayOfBookInfo, lineArray.length + 1);
            for (int i = 1; i < arrayOfBookInfo.length; i++) {
                arrayOfBookInfo[i] = lineArray[i - 1];
            }
            return arrayOfBookInfo;
        }
    }
    public static void sortGenre(int[] arrayOfCounts, String[] arrayOfBookInfo)throws CustomExceptions{
        if (arrayOfBookInfo.length > 7){
            throw new TooManyFieldsException(arrayOfCounts);
        }
        else if (arrayOfBookInfo.length < 7){
            throw new TooFewFieldsException(arrayOfCounts);
        }
        else {
            String genre = arrayOfBookInfo[5];
            String[] Genres = {"CCB", "HCB", "MTV", "MRB", "NEB", "OTR", "SSM", "TPA", ""};
            boolean match = false;
            for (String code : Genres){
                if (genre.equals(code)){
                    match = true;
                    break;
                }
            }
            if (!match){
                throw new UnknownGenreException(arrayOfCounts);
            }
            else if (Stream.of(arrayOfBookInfo).anyMatch(""::equals)){
                throw new MissingFieldException(arrayOfCounts);
            }
            else {
                switch (genre){
                    case "CCB" -> arrayOfCounts[0]++;
                    case "HCB" -> arrayOfCounts[1]++;
                    case "MTV" -> arrayOfCounts[2]++;
                    case "MRB" -> arrayOfCounts[3]++;
                    case "NEB" -> arrayOfCounts[4]++;
                    case "OTR" -> arrayOfCounts[5]++;
                    case "SSM" -> arrayOfCounts[6]++;
                    case "TPA" -> arrayOfCounts[7]++;
                }
            }
        }
    }
    public static void sortGenre(int[] error_counter, int[] CCB_counter, int[] HCB_counter, int[] MTV_counter, int[] MRB_counter, int[] NEB_counter, int[] OTR_counter, int[] SSM_counter, int[] TPA_counter,
                                 String[] errorCode,String[]arrayOfBookInfo, String[] CCB, String[] HCB, String[] MTV, String[] MRB, String[] NEB, String[] OTR, String[] SSM, String[] TPA)throws CustomExceptions{
        if (arrayOfBookInfo.length > 7){
            error_counter[0]++;
            throw new TooManyFieldsException(errorCode,error_counter[0] - 1);
        }
        else if (arrayOfBookInfo.length < 7){
            error_counter[0]++;
            throw new TooFewFieldsException(errorCode,error_counter[0] - 1);
        }
        else {
            String genre = arrayOfBookInfo[5];
            String[] Genres = {"CCB", "HCB", "MTV", "MRB", "NEB", "OTR", "SSM", "TPA", ""};
            boolean match = false;
            for (String code : Genres){
                if (genre.equals(code)){
                    match = true;
                    break;
                }
            }
            if (!match){
                error_counter[0]++;
                throw new UnknownGenreException(errorCode,error_counter[0] - 1, arrayOfBookInfo[5]);
            }
            else if (Stream.of(arrayOfBookInfo).anyMatch(""::equals)){
                int emptyFieldCount = 0;
                for (String element : arrayOfBookInfo){
                    if (element.equals("")){
                        emptyFieldCount++;
                    }
                }
                int[] indexOfMissingField = new int[emptyFieldCount];
                int count = 0;
                for (int i = 1; i < arrayOfBookInfo.length; i++){
                    if (arrayOfBookInfo[i].equals("")){
                        indexOfMissingField[count] = i;
                    }
                }
                error_counter[0]++;
                throw new MissingFieldException(errorCode,error_counter[0] - 1, indexOfMissingField);
            }
            else {
                String record = arrayOfBookInfo[0];
                switch (genre){
                    case "CCB" -> {CCB_counter[0]++; CCB[CCB_counter[0]-1] = record;}
                    case "HCB" -> {HCB_counter[0]++; HCB[HCB_counter[0]-1] = record;}
                    case "MTV" -> {MTV_counter[0]++; MTV[MTV_counter[0]-1] = record;}
                    case "MRB" -> {MRB_counter[0]++; MRB[MRB_counter[0]-1] = record;}
                    case "NEB" -> {NEB_counter[0]++; NEB[NEB_counter[0]-1] = record;}
                    case "OTR" -> {OTR_counter[0]++; OTR[OTR_counter[0]-1] = record;}
                    case "SSM" -> {SSM_counter[0]++; SSM[SSM_counter[0]-1] = record;}
                    case "TPA" -> {TPA_counter[0]++; TPA[TPA_counter[0]-1] = record;}
                }
            }
        }

    }
    public static void printToGenreFile(String[] genre, String fileName) throws FileNotFoundException{
        PrintWriter pw = new PrintWriter(new FileOutputStream("part1/" + fileName));
        for (String element : genre){
            pw.println(element);
        }
        pw.close();
    }
    public static void printToErrorFile(String[] ErrorOriginalFileName, String[] ErrorRecord, String[] ErrorCode) throws FileNotFoundException{
        PrintWriter pw = new PrintWriter(new FileOutputStream("part1/syntax_error_file.txt"));
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
