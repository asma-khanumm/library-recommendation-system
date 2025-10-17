import java.io.*;
import java.util.*;

public class Library {
    private List<Book> books;
    private List<User> users;

    private static final String BOOKS_FILE = "data/books.txt";
    private static final String USERS_FILE = "data/users.txt";

    public Library() {
        books = new ArrayList<>();
        users = new ArrayList<>();
        loadBooks();
        loadUsers();
    }

    // Book management
    public void addBook(Book book) {
        books.add(book);
        saveBooks();
    }

    public void viewBooks() {
        books.forEach(System.out::println);
    }

    public Optional<Book> getBookById(String id) {
        return books.stream().filter(b -> b.getId().equals(id)).findFirst();
    }

    // User management
    public void addUser(User user) {
        users.add(user);
        saveUsers();
    }

    public Optional<User> getUserById(String userId) {
        return users.stream().filter(u -> u.getUserId().equals(userId)).findFirst();
    }

    // Borrow & return
    public boolean borrowBook(String userId, String bookId) {
        Optional<User> userOpt = getUserById(userId);
        Optional<Book> bookOpt = getBookById(bookId);

        if (userOpt.isPresent() && bookOpt.isPresent() && bookOpt.get().isAvailable()) {
            bookOpt.get().setAvailable(false);
            userOpt.get().borrowBook(bookId);
            saveBooks();
            saveUsers();
            return true;
        }
        return false;
    }

    public boolean returnBook(String userId, String bookId) {
        Optional<User> userOpt = getUserById(userId);
        Optional<Book> bookOpt = getBookById(bookId);

        if (userOpt.isPresent() && bookOpt.isPresent() && !bookOpt.get().isAvailable()) {
            bookOpt.get().setAvailable(true);
            userOpt.get().returnBook(bookId);
            saveBooks();
            saveUsers();
            return true;
        }
        return false;
    }

    // Persistence
    private void saveBooks() { saveObject(books, BOOKS_FILE); }
    private void saveUsers() { saveObject(users, USERS_FILE); }

    @SuppressWarnings("unchecked")
    private void loadBooks() { books = (List<Book>) loadObject(BOOKS_FILE, new ArrayList<Book>()); }

    @SuppressWarnings("unchecked")
    private void loadUsers() { users = (List<User>) loadObject(USERS_FILE, new ArrayList<User>()); }

    private void saveObject(Object obj, String filename) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(obj);
        } catch (IOException e) { e.printStackTrace(); }
    }

    private Object loadObject(String filename, Object defaultObj) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            return ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return defaultObj;
        }
    }

    public List<Book> getBooks() { return books; }
}
