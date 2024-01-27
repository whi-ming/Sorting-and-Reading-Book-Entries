// -----------------------------------------------------
// COMP 249 Section U
// Assignment 3
// Question: Part 2
// Written by: Whi-Ming Joseph 40202164
// Due Date: March 30, 2023
// -----------------------------------------------------

public class BadYearException extends CustomExceptions{
    public BadYearException(int run_count, String[] errorCode, int[] error_counter, int year) {
        if (run_count == 0){
            error_counter[0]++;
        }
        else {
            errorCode[error_counter[0]] = "Invalid Year: " + year;
            error_counter[0]++;
        }
    }
    public BadYearException(int run_count, String[] errorCode, int[] error_counter, int year, int[] error_entry_counter) {
        if (run_count == 0){
            error_counter[0]++;
        }
        else {
            errorCode[error_entry_counter[0]] = "Invalid Year: " + year;
            error_entry_counter[0]++;
        }
    }
}
