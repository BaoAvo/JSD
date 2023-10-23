package a1_190140180;

import common.Genre;

import java.util.Arrays;
import java.util.stream.Collectors;

public class Book {
    private String isbn;
    private String title;
    private String author;
    private Genre genre;
    private int publicationYear;
    private int numberOfCopiesAvailable;

    public Book(String title, String author, Genre genre, int publicationYear, int numberOfCopiesAvailable) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.publicationYear = publicationYear;
        this.numberOfCopiesAvailable = numberOfCopiesAvailable;
        String isbnCode = generateISBN();
        this.setIsbn(isbnCode);
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
        String bookObject = String.format("Books[isbn = %s,title = %s,author = %s,genre = %s,publicationYear = %d,numberOfCopiesAvailable = %d]", isbn, title, author, genre, publicationYear, numberOfCopiesAvailable);
        return bookObject;
    }

    public String generateISBN() {
        int indexGenre = getIndexGenre(this.getGenre()) + 1;
        String indexGenreString = String.valueOf(indexGenre);
        String genreCode = indexGenre < 10 ? '0' + indexGenreString : indexGenreString;
        String[] words = this.getAuthor().split(" ");
        String authorInit = Arrays.stream(words)
                .filter(word -> !word.isEmpty())
                .map(word -> String.valueOf(word.charAt(0)))
                .collect(Collectors.joining(""));
        String isbnCode = authorInit + "-" + genreCode + "-" + this.getPublicationYear();
        return isbnCode;
    }

    public int getIndexGenre(Genre genre) {
        Genre[] values = Genre.values();
        for (int i = 0; i < values.length; i++) {
            if (values[i] == genre) {
                return i;
            }
        }
        return -1;
    }

    public int getNumCopiesAvailable() {
        return this.getNumberOfCopiesAvailable();
    }

}
