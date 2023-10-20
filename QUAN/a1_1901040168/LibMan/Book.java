package LibMan;

import common.Genre;

/*
    Books in the library can be classified into various genres, such as programming, fiction,
    non-fiction, science fiction, etc. You’ll need to use the provided common.Genre enum class.
    There are two types of patrons in the library system: regular patrons and premium patrons.
    Regular patrons can check out a maximum of 3 books at a time, while premium patrons can
    check out up to 5 books. Patrons are responsible for returning books on time to avoid fines.
    You are provided with the common.PatronType enum class. You should not implement or
    modify it.
    Your program should allow patrons to check out and return books, librarians to add new
    books to the collection, update book information, and calculate fines for overdue books based
    on the defined criteria.
*/
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
                "isbn='" + isbn + '\'' +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", genre=" + genre +
                ", publicationYear=" + publicationYear +
                ", numberOfCopiesAvailable=" + numberOfCopiesAvailable +
                ']';
    }

    //    Generate a unique ISBN for each book.
    //    ISBN is a unique book identifier generated automatically by the system. It combines
    //    elements like the author's initials, a numerical code for the genre, and the publication year. For
    //    example, a book by John Doe in the year 2023 with the genre code for programming (e.g., 02)
    //    would have an ISBN like JD-02-2023.
    public String generateISBN(){
        String genreCode = ""; 
        int genreIndex = this.getGenre().ordinal() + 1;
        if(genreIndex < 10){
            genreCode = '0' + String.valueOf(genreIndex);
        }else{
            genreCode = String.valueOf(genreIndex);
        }
        String initials = getInitials(this.getAuthor());
        return initials + "-" + genreCode + "-" + this.getPublicationYear();
    }

    private String getInitials(String name) {  
        String[] words = name.split(" ");  
        String initials = "";  
        for (String word : words) {  
            if (!word.isEmpty()) {  
                initials += word.charAt(0);  
            }  
        }  
        return initials.toUpperCase(); 
    }

    //  Keep track of the number of copies available
    public int getNumCopiesAvailable(){
        return this.getNumberOfCopiesAvailable();
    }

}
