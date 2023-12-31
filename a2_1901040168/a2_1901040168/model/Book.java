package model;

import common.Genre;

public class Book {
    private String isbn;
    private String title;
    private String author;
    private Genre genre;
    private int publicationYear;
    private int numberOfCopiesAvailable;

    public Book(){}
    public Book(String title, String author, Genre genre, int publicationYear, int numberOfCopiesAvailable) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.publicationYear = publicationYear;
        this.numberOfCopiesAvailable = numberOfCopiesAvailable;
        this.setIsbn(this.generateISBN());
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
    }

    public int getNumberOfCopiesAvailable() {
        return numberOfCopiesAvailable;
    }

    public void setNumberOfCopiesAvailable(int numberOfCopiesAvailable) {
        this.numberOfCopiesAvailable = numberOfCopiesAvailable;
    }

    @Override
    public String toString() {
        return "Books[" +
                "isbn = '" + isbn + '\'' +
                ", title = '" + title + '\'' +
                ", author = '" + author + '\'' +
                ", genre = " + genre +
                ", publicationYear = " + publicationYear +
                ", numberOfCopiesAvailable = " + numberOfCopiesAvailable +
                ']';
    }

    public String generateISBN(){
        String genreCode = ""; 
        int genreIndex = this.getGenre().ordinal() + 1;
        if(genreIndex < 10){
            genreCode = '0' + String.valueOf(genreIndex);
        }else{
            genreCode = String.valueOf(genreIndex);
        }
        String[] words = this.getAuthor().split(" ");
        String initials = "";
        for(String word : words){
            if(!word.isEmpty()){
                initials += word.charAt(0);
            }
        }
        return initials + "-" + genreCode + "-" + this.getPublicationYear();
    }

    //  Keep track of the number of copies available
    public int getNumCopiesAvailable(){
        return this.getNumberOfCopiesAvailable();
    }

}
