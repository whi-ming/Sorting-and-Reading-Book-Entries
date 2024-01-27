// -----------------------------------------------------
// COMP 249 Section U
// Assignment 3
// Question: Part 1
// Written by: Whi-Ming Joseph 40202164
// Due Date: March 30, 2023
// -----------------------------------------------------

public class UnknownGenreException extends CustomExceptions{
    public UnknownGenreException(int[] arrayOfCounts){
        arrayOfCounts[8]++;
    }
    public UnknownGenreException(String[] errorCode, int error_counter, String unknownGenre){
        errorCode[error_counter] = "Invalid Genre: " + unknownGenre;
    }
}
