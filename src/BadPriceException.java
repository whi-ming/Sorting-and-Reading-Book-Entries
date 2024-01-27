// -----------------------------------------------------
// COMP 249 Section U
// Assignment 3
// Question: Part 2
// Written by: Whi-Ming Joseph 40202164
// Due Date: March 30, 2023
// -----------------------------------------------------

public class BadPriceException extends CustomExceptions{
    public BadPriceException(int run_count, String[] errorCode, int[] error_counter, double price) {
        if (run_count == 0){
            error_counter[0]++;
        }
        else {
            errorCode[error_counter[0]] = "Invalid Price: " + price;
            error_counter[0]++;
        }

    }
    public BadPriceException(int run_count, String[] errorCode, int[] error_counter, double price, int[] error_entry_counter) {
        if (run_count == 0){
            error_counter[0]++;
        }
        else {
            errorCode[error_entry_counter[0]] = "Invalid Price: " + price;
            error_entry_counter[0]++;
        }

    }
}
