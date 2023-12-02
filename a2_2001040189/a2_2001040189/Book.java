import java.util.Arrays;

public class Book {
     int id;
     String ISBN;
    String title;
    String author;
     Genre genre;
     String publicationYear;
    int numCopiesAvailable;

    public Book(String title, String author, Genre genre, String publicationYear, int numCopiesAvailable) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.publicationYear = publicationYear;
        this.numCopiesAvailable = numCopiesAvailable;
        this.ISBN = generateISBN();
    }

    public Book() {

    }

    private String generateISBN() {
        StringBuilder builder = new StringBuilder();
        String[] names = this.author.split(" ");
        Arrays.stream(names).forEachOrdered(name -> {
            char firstLetter = Character.toUpperCase(name.charAt(0));
            builder.append(firstLetter);
        });
        builder.append('-');

        int genreCode = genre.ordinal() + 1;
        builder.append(String.format("%02d-", genreCode));

        builder.append(publicationYear);

        return builder.toString();
    }

    public int getNumCopiesAvailable() {
        return this.numCopiesAvailable;
    }


    public String getISBN() {
        return ISBN;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public Genre getGenre() {
        return genre;
    }

    public String getPublicationYear() {
        return publicationYear;
    }

    public void setNumCopiesAvailable(int numCopiesAvailable) {
        this.numCopiesAvailable = numCopiesAvailable;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public void setGenre(String genre) {
        this.genre = Genre.valueOf(genre);
    }

    public void setPublicationYear(String publicationYear) {
        this.publicationYear = publicationYear;
    }
}
