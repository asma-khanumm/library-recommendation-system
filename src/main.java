import java.util.List;

public class main {
    public static void Main(String[] args) {
        Library library = new Library();

        // Add sample user
        if (library.getUserById("U001").isEmpty())
            library.addUser(new User("U001", "Asma Khan"));

        // Add sample books (only first time)
        if (library.getBooks().isEmpty()) {
            library.addBook(new Book("B001", "Clean Code", "Robert C. Martin", "Programming"));
            library.addBook(new Book("B002", "Effective Java", "Joshua Bloch", "Programming"));
            library.addBook(new Book("B003", "Harry Potter", "J.K. Rowling", "Fantasy"));
            library.addBook(new Book("B004", "The Hobbit", "J.R.R. Tolkien", "Fantasy"));
            library.addBook(new Book("B005", "Atomic Habits", "James Clear", "Self-Help"));
        }

        String userId = "U001";
        int choice;
        do {
            System.out.println("\n--- Library Menu ---");
            System.out.println("1. View all books");
            System.out.println("2. Borrow a book");
            System.out.println("3. Return a book");
            System.out.println("4. Get recommendations");
            System.out.println("0. Exit");
            choice = Utils.getInt("Enter choice: ");

            switch (choice) {
                case 1 -> library.viewBooks();
                case 2 -> {
                    String bookId = Utils.getString("Enter Book ID to borrow: ");
                    if (library.borrowBook(userId, bookId)) System.out.println("Book borrowed successfully!");
                    else System.out.println("Cannot borrow book.");
                }
                case 3 -> {
                    String bookId = Utils.getString("Enter Book ID to return: ");
                    if (library.returnBook(userId, bookId)) System.out.println("Book returned successfully!");
                    else System.out.println("Cannot return book.");
                }
                case 4 -> {
                    List<Book> recommendations = RecommendationEngine.recommendBooks(library.getUserById(userId).get(), library.getBooks());
                    System.out.println("--- Recommended Books ---");
                    recommendations.forEach(System.out::println);
                }
                case 0 -> System.out.println("Exiting...");
                default -> System.out.println("Invalid choice!");
            }

        } while (choice != 0);
    }
}
