package prelim;

import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * Utility class for validating user inputs in console applications
 */
public final class InputValidator {
    private static final Pattern DATE_PATTERN = Pattern.compile("^(0[1-9]|1[0-2])/(0[1-9]|[12][0-9]|3[01])/\\d{4}$");
    private static final Pattern STATUS_PATTERN = Pattern.compile("^(completed|pending|in progress|cancelled)$", Pattern.CASE_INSENSITIVE);
    
    private InputValidator() {}
    
    /**
     * Validates and gets a menu choice from user input
     * @param scanner Scanner object for reading input
     * @param minChoice Minimum valid choice
     * @param maxChoice Maximum valid choice
     * @return Valid menu choice
     */
    public static int getValidMenuChoice(Scanner scanner, int minChoice, int maxChoice) {
        while (true) {
            try {
                System.out.print("Choose option (" + minChoice + "-" + maxChoice + "): ");
                String input = scanner.nextLine().trim();
                
                if (input.isEmpty()) {
                    System.out.println(ConsoleUI.warn("Input cannot be empty. Please enter a number."));
                    continue;
                }
                
                int choice = Integer.parseInt(input);
                if (choice >= minChoice && choice <= maxChoice) {
                    return choice;
                } else {
                    System.out.println(ConsoleUI.warn("Invalid option. Please choose between " + minChoice + " and " + maxChoice + "."));
                }
            } catch (NumberFormatException e) {
                System.out.println(ConsoleUI.warn("Invalid input. Please enter a valid number."));
            }
        }
    }
    
    /**
     * Validates and gets a non-empty string from user input
     * @param scanner Scanner object for reading input
     * @param prompt Prompt message to display
     * @param fieldName Name of the field being validated (for error messages)
     * @return Valid non-empty string
     */
    public static String getValidString(Scanner scanner, String prompt, String fieldName) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            
            if (input.isEmpty()) {
                System.out.println(ConsoleUI.warn(fieldName + " cannot be empty. Please enter a valid value."));
                continue;
            }
            
            if (input.length() > 100) {
                System.out.println(ConsoleUI.warn(fieldName + " is too long. Please enter a value with 100 characters or less."));
                continue;
            }
            
            return input;
        }
    }
    
    /**
     * Validates and gets a date in MM/DD/YYYY format from user input
     * @param scanner Scanner object for reading input
     * @param prompt Prompt message to display
     * @return Valid date string in MM/DD/YYYY format
     */
    public static String getValidDate(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            
            if (input.isEmpty()) {
                System.out.println(ConsoleUI.warn("Date cannot be empty. Please enter a date in MM/DD/YYYY format."));
                continue;
            }
            
            if (!DATE_PATTERN.matcher(input).matches()) {
                System.out.println(ConsoleUI.warn("Invalid date format. Please use MM/DD/YYYY format (e.g., 12/25/2024)."));
                continue;
            }
            
            // Additional validation for valid date ranges
            String[] parts = input.split("/");
            int month = Integer.parseInt(parts[0]);
            int day = Integer.parseInt(parts[1]);
            int year = Integer.parseInt(parts[2]);
            
            if (month < 1 || month > 12) {
                System.out.println(ConsoleUI.warn("Invalid month. Please enter a month between 01 and 12."));
                continue;
            }
            
            if (day < 1 || day > 31) {
                System.out.println(ConsoleUI.warn("Invalid day. Please enter a day between 01 and 31."));
                continue;
            }
            
            if (year < 1900 || year > 2100) {
                System.out.println(ConsoleUI.warn("Invalid year. Please enter a year between 1900 and 2100."));
                continue;
            }
            
            // Basic validation for days in month (simplified)
            if ((month == 4 || month == 6 || month == 9 || month == 11) && day > 30) {
                System.out.println(ConsoleUI.warn("Invalid day for the given month. This month has only 30 days."));
                continue;
            }
            
            if (month == 2 && day > 29) {
                System.out.println(ConsoleUI.warn("Invalid day for February. February has at most 29 days."));
                continue;
            }
            
            return input;
        }
    }
    
    /**
     * Validates and gets a status value from user input
     * @param scanner Scanner object for reading input
     * @param prompt Prompt message to display
     * @return Valid status string
     */
    public static String getValidStatus(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt + " (Completed/Pending/In Progress/Cancelled): ");
            String input = scanner.nextLine().trim();
            
            if (input.isEmpty()) {
                System.out.println(ConsoleUI.warn("Status cannot be empty. Please enter: Completed, Pending, In Progress, or Cancelled."));
                continue;
            }
            
            if (!STATUS_PATTERN.matcher(input).matches()) {
                System.out.println(ConsoleUI.warn("Invalid status. Please enter: Completed, Pending, In Progress, or Cancelled."));
                continue;
            }
            
            // Normalize the status to proper case
            return normalizeStatus(input);
        }
    }
    
    /**
     * Validates and gets a property status (for PersonalProperty)
     * @param scanner Scanner object for reading input
     * @param prompt Prompt message to display
     * @return Valid property status string
     */
    public static String getValidPropertyStatus(Scanner scanner, String prompt) {
        String[] validStatuses = {"Working", "Broken", "Lost", "Sold", "In Repair"};
        
        while (true) {
            System.out.print(prompt + " (Working/Broken/Lost/Sold/In Repair): ");
            String input = scanner.nextLine().trim();
            
            if (input.isEmpty()) {
                System.out.println(ConsoleUI.warn("Status cannot be empty. Please enter: Working, Broken, Lost, Sold, or In Repair."));
                continue;
            }
            
            for (String validStatus : validStatuses) {
                if (validStatus.equalsIgnoreCase(input)) {
                    return validStatus; // Return the properly capitalized version
                }
            }
            
            System.out.println(ConsoleUI.warn("Invalid status. Please enter: Working, Broken, Lost, Sold, or In Repair."));
        }
    }
    
    /**
     * Gets confirmation from user (y/n)
     * @param scanner Scanner object for reading input
     * @param prompt Prompt message to display
     * @return true if user confirms, false otherwise
     */
    public static boolean getConfirmation(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt + " (y/n): ");
            String input = scanner.nextLine().trim().toLowerCase();
            
            if (input.equals("y") || input.equals("yes")) {
                return true;
            } else if (input.equals("n") || input.equals("no")) {
                return false;
            } else {
                System.out.println(ConsoleUI.warn("Invalid input. Please enter 'y' for yes or 'n' for no."));
            }
        }
    }
    
    /**
     * Normalizes status string to proper case
     * @param status Status string to normalize
     * @return Normalized status string
     */
    private static String normalizeStatus(String status) {
        String lower = status.toLowerCase();
        switch (lower) {
            case "completed":
                return "Completed";
            case "pending":
                return "Pending";
            case "in progress":
                return "In Progress";
            case "cancelled":
                return "Cancelled";
            default:
                return status; // Should not reach here due to validation
        }
    }
    
    /**
     * Validates that a string is not null, empty, or whitespace only
     * @param input String to validate
     * @param fieldName Name of the field being validated
     * @return true if valid, false otherwise
     */
    public static boolean isValidString(String input, String fieldName) {
        if (input == null || input.trim().isEmpty()) {
            System.out.println(ConsoleUI.warn(fieldName + " cannot be empty."));
            return false;
        }
        if (input.trim().length() > 100) {
            System.out.println(ConsoleUI.warn(fieldName + " is too long (max 100 characters)."));
            return false;
        }
        return true;
    }
    
    /**
     * Validates date format
     * @param date Date string to validate
     * @return true if valid, false otherwise
     */
    public static boolean isValidDate(String date) {
        return date != null && DATE_PATTERN.matcher(date).matches();
    }
}