// -----------------------------------------------------
// COMP 249 Section U
// Assignment 3
// Question: Part 2
// Written by: Whi-Ming Joseph 40202164
// Due Date: March 30, 2023
// -----------------------------------------------------

public class BadIsbn13Exception extends CustomExceptions{
    public BadIsbn13Exception(int run_count, String[] errorCode, int[] error_counter, String isbn) {
        if (run_count == 0){
            error_counter[0]++;
        }
        else {
            errorCode[error_counter[0]] = "Invalid ISBN-13: " + isbn;
            error_counter[0]++;

        }
    }
    public BadIsbn13Exception(int run_count, String[] errorCode, int[] error_counter, String isbn, int[] error_entry_counter) {
        if (run_count == 0){
            error_counter[0]++;
        }
        else {
            errorCode[error_entry_counter[0]] = "Invalid ISBN-13: " + isbn;
            error_entry_counter[0]++;

        }
    }
}
