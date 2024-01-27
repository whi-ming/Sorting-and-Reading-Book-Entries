// -----------------------------------------------------
// COMP 249 Section U
// Assignment 3
// Question: Part 1
// Written by: Whi-Ming Joseph 40202164
// Due Date: March 30, 2023
// -----------------------------------------------------

public class TooFewFieldsException extends CustomExceptions{
    public TooFewFieldsException(int[] arrayOfCounts){
        arrayOfCounts[8]++;
    }
    public TooFewFieldsException(String[] errorCode, int error_counter){
        errorCode[error_counter] = "Too Few Fields";
    }
}
