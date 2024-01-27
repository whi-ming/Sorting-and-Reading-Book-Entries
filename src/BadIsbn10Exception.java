// -----------------------------------------------------
// COMP 249 Section U
// Assignment 3
// Question: Part 2
// Written by: Whi-Ming Joseph 40202164
// Due Date: March 30, 2023
// -----------------------------------------------------

public class BadIsbn10Exception extends CustomExceptions{
    public BadIsbn10Exception(int run_count, String[] errorCode, int[] error_counter, String isbn) {
        if (run_count == 0){
            error_counter[0]++;
        }
        else{
            errorCode[error_counter[0]] = "Invalid ISBN-10 : " + isbn;
            error_counter[0]++;
        }
    }
    public BadIsbn10Exception(int run_count, String[] errorCode, int[] error_counter, String isbn, int[] error_entry_counter) {
        if (run_count == 0){
            error_counter[0]++;
        }
        else{
            errorCode[error_entry_counter[0]] = "Invalid ISBN-10 : " + isbn;
            error_entry_counter[0]++;
        }

    }
}
