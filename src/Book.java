import java.io.Serializable;

public class Book implements Serializable {
    private String id;
    private String title;
    private String author;
    private String genre;
    private boolean available;

    public Book(String id, String title, String author, String genre) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.available = true;
    }

    // Getters and Setters
    public String getId() { return id; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public String getGenre() { return genre; }
    public boolean isAvailable() { return available; }
    public void setAvailable(boolean available) { this.available = available; }

    @Override
    public String toString() {
        return String.format("%s | %s | %s | %s | %s", id, title, author, genre, (available ? "Available" : "Borrowed"));
    }
}
