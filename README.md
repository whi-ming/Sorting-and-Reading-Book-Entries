# assignment 3 comp-249 winter 2023
 
Part 1
The program will read the text files and sort the books in their corresponding csv file according to their genre. The program will also be able to spot any entry that hold a syntax error. Syntax errors consist of an entry having too few/many fields or an unknown genre. At this point, an exception will be thrown depending on the error. Once the exception is caught, the error and corresponding entry will be printed to a text file.

Part 2
From this new list of genre files, the program will serialise the records into binary files. While doing so, a semantic check will be done over all book entries. Semantic errors are invalid ISBN numbers, invalid price, or invalid year. Exceptions will be thrown and written in a text file to record the error an entry holds. Those that pass the check will be converted into Book objects and put into a Book array. These objects will then be serialized.

Part 3
The program will now read from the serialized files and extract the Book objects. Here the program becomes interactive and allows the user to read explore the files. A list will display the different genres and how many records are in that file. Once opened, the user can read the details of the book entries within. 
