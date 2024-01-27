// -----------------------------------------------------
// COMP 249 Section U
// Assignment 3
// Written by: Whi-Ming Joseph 40202164
// Due Date: March 30, 2023
// -----------------------------------------------------


import java.util.concurrent.TimeUnit;

public class Main extends part3 {

    public static void main(String[] args) {
        do_part1(); // validating syntax, partition book records based on genre.
        System.out.println("Syntax Validated");

        do_part2(); // validating semantics, read the genre files each into arrays of Book objects,
        // then serialize the arrays of Book objects each into binary files.
        try{
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        System.out.println("Semantics Validated and Serialized in array of Books into Binary File");
        try{
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        do_part3(); // reading the binary files, deserialize the array objects in each file, and
        // then provide an interactive program to allow the user to navigate the arrays
    }
}
