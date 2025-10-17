import java.util.Scanner;

public class Utils {
    private static Scanner scanner = new Scanner(System.in);

    public static String getString(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    public static int getInt(String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextInt()) {
            System.out.print("Invalid input. Try again: ");
            scanner.next();
        }
        int val = scanner.nextInt();
        scanner.nextLine(); // consume newline
        return val;
    }
}
