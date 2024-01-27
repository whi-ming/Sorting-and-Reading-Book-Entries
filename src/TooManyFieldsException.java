// -----------------------------------------------------
// COMP 249 Section U
// Assignment 3
// Question: Part 1
// Written by: Whi-Ming Joseph 40202164
// Due Date: March 30, 2023
// -----------------------------------------------------

public class TooManyFieldsException extends CustomExceptions{
    public TooManyFieldsException(int[] arrayOfCounts){
        arrayOfCounts[8]++;
    }
    public TooManyFieldsException(String[] errorCode, int error_counter){
        errorCode[error_counter] = "Too Many Fields";
    }
}
