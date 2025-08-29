package prelim;

import java.util.Scanner;
import java.util.regex.Pattern;

public final class InputUtils {
    private InputUtils() {}

    public static int readIntInRange(Scanner scanner, String prompt, int minInclusive, int maxInclusive) {
        while (true) {
            System.out.print(prompt);
            String line = scanner.nextLine();
            try {
                int value = Integer.parseInt(line.trim());
                if (value < minInclusive || value > maxInclusive) {
                    System.out.println(ConsoleUI.warn("Enter a number between " + minInclusive + " and " + maxInclusive));
                    continue;
                }
                return value;
            } catch (NumberFormatException ex) {
                System.out.println(ConsoleUI.warn("Invalid number. Please try again."));
            }
        }
    }

    public static String readNonEmptyString(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String value = scanner.nextLine();
            if (value != null && !value.trim().isEmpty()) {
                return value.trim();
            }
            System.out.println(ConsoleUI.warn("This field cannot be empty."));
        }
    }

    public static String readMatchingPattern(Scanner scanner, String prompt, String regex, String errorMessage) {
        Pattern pattern = Pattern.compile(regex);
        while (true) {
            System.out.print(prompt);
            String value = scanner.nextLine().trim();
            if (pattern.matcher(value).matches()) {
                return value;
            }
            System.out.println(ConsoleUI.warn(errorMessage));
        }
    }

    public static String readChoice(Scanner scanner, String prompt, String[] allowed, boolean caseInsensitive) {
        while (true) {
            System.out.print(prompt);
            String value = scanner.nextLine().trim();
            for (String option : allowed) {
                if (caseInsensitive) {
                    if (option.equalsIgnoreCase(value)) {
                        return option; // normalize to declared option
                    }
                } else if (option.equals(value)) {
                    return value;
                }
            }
            System.out.println(ConsoleUI.warn("Allowed values: " + String.join(", ", allowed)));
        }
    }
}

