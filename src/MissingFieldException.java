// -----------------------------------------------------
// COMP 249 Section U
// Assignment 3
// Question: Part 1
// Written by: Whi-Ming Joseph 40202164
// Due Date: March 30, 2023
// -----------------------------------------------------

public class MissingFieldException extends CustomExceptions{
    public MissingFieldException(int[] arrayOfCounts){
        arrayOfCounts[8]++;
    }
    public MissingFieldException(String[] errorCode, int error_counter, int[] indexOfMissingField){
        String entry = "";
        String errorMessage = "";
            for (int index : indexOfMissingField){
                if (index == 1){
                    entry = "Title";
                }
                else if (index == 2){
                    entry = "Author";
                }
                else if (index == 3){
                    entry = "Price";
                }
                else if (index == 4){
                    entry = "ISBN";
                }
                else if (index == 5){
                    entry = "Genre";
                }
                else if (index == 6){
                    entry = "Year";
                }

                errorMessage = "Missing " + entry + "\n" + errorMessage;
            }
            if (indexOfMissingField.length == 1){
                errorCode[error_counter] = "Missing " + entry;
            }
            else {
                errorCode[error_counter] = errorMessage;
            }
    }
}
