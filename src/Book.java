// -----------------------------------------------------
// COMP 249 Section U
// Assignment 3
// Question: Part 2
// Written by: Whi-Ming Joseph 40202164
// Due Date: March 30, 2023
// -----------------------------------------------------

import java.io.Serializable;

public class Book implements Serializable {
    String title;
    String authors;
    double price;
    String isbn;
    String genre;
    int year;

    public Book(String title, String authors, double price, String isbn, String genre, int year) {
        this.title = title;
        this.authors = authors;
        this.price = price;
        this.isbn = isbn;
        this.genre = genre;
        this.year = year;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthors() {
        return authors;
    }

    public void setAuthors(String authors) {
        this.authors = authors;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public boolean equals(Object obj){
        if (obj != null){
            return true;
        }
        else if (obj instanceof Book){
            Book other = (Book)obj;
            if (this.title.equals(other.title) && this.authors.equals(other.authors) && this.price == other.price && this.isbn.equals(other.isbn) && this.genre.equals(other.genre) && this.year == other.year){
                return true;
            }
        }
        return false;
    }

    public String toString(){
        return title + ", " +
                authors + ", " +
                price + ", " +
                isbn + ", " +
                genre + ", " +
                year;
    }
}
