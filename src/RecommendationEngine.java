import java.util.*;
import java.util.stream.Collectors;

public class RecommendationEngine {
    public static List<Book> recommendBooks(User user, List<Book> allBooks) {
        // Recommend books by genre of previously borrowed books
        Set<String> genres = user.getBorrowedBooks().stream()
                .map(bookId -> allBooks.stream()
                        .filter(b -> b.getId().equals(bookId))
                        .map(Book::getGenre)
                        .findFirst().orElse(""))
                .collect(Collectors.toSet());

        List<Book> recommendations = allBooks.stream()
                .filter(Book::isAvailable)
                .filter(b -> genres.contains(b.getGenre()) && !user.getBorrowedBooks().contains(b.getId()))
                .collect(Collectors.toList());

        // If no recommendations by genre, return random available books
        if (recommendations.isEmpty()) {
            recommendations = allBooks.stream()
                    .filter(Book::isAvailable)
                    .filter(b -> !user.getBorrowedBooks().contains(b.getId()))
                    .limit(5)
                    .collect(Collectors.toList());
        }

        return recommendations;
    }
}
