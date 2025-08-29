package prelim;

import java.util.Scanner;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Utility class for validating and sanitizing user inputs
 */
public class InputValidator {
    
    /**
     * Validates and gets an integer input within a specified range
     * @param scanner Scanner object for input
     * @param prompt Message to display to user
     * @param min Minimum valid value (inclusive)
     * @param max Maximum valid value (inclusive)
     * @return Valid integer within the specified range
     */
    public static int getValidatedIntChoice(Scanner scanner, String prompt, int min, int max) {
        int choice;
        while (true) {
            System.out.print(prompt);
            try {
                String input = scanner.nextLine().trim();
                choice = Integer.parseInt(input);
                
                if (choice >= min && choice <= max) {
                    return choice;
                } else {
                    System.out.println(ConsoleUI.warn("Invalid choice! Please enter a number between " + min + " and " + max));
                }
            } catch (NumberFormatException e) {
                System.out.println(ConsoleUI.warn("Invalid input! Please enter a valid number."));
            }
        }
    }
    
    /**
     * Validates and gets a non-empty string input
     * @param scanner Scanner object for input
     * @param prompt Message to display to user
     * @return Non-empty trimmed string
     */
    public static String getValidatedString(Scanner scanner, String prompt) {
        String input;
        while (true) {
            System.out.print(prompt);
            input = scanner.nextLine().trim();
            
            if (!input.isEmpty()) {
                return input;
            } else {
                System.out.println(ConsoleUI.warn("This field cannot be empty. Please enter a valid value."));
            }
        }
    }
    
    /**
     * Validates and gets a string input with maximum length
     * @param scanner Scanner object for input
     * @param prompt Message to display to user
     * @param maxLength Maximum allowed length
     * @return Valid string within length limit
     */
    public static String getValidatedString(Scanner scanner, String prompt, int maxLength) {
        String input;
        while (true) {
            System.out.print(prompt);
            input = scanner.nextLine().trim();
            
            if (input.isEmpty()) {
                System.out.println(ConsoleUI.warn("This field cannot be empty. Please enter a valid value."));
            } else if (input.length() > maxLength) {
                System.out.println(ConsoleUI.warn("Input too long! Maximum " + maxLength + " characters allowed."));
            } else {
                return input;
            }
        }
    }
    
    /**
     * Validates and gets a date in MM/DD/YYYY format
     * @param scanner Scanner object for input
     * @param prompt Message to display to user
     * @return Valid date string in MM/DD/YYYY format
     */
    public static String getValidatedDate(Scanner scanner, String prompt) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        dateFormat.setLenient(false);
        String input;
        
        while (true) {
            System.out.print(prompt);
            input = scanner.nextLine().trim();
            
            if (input.isEmpty()) {
                System.out.println(ConsoleUI.warn("Date cannot be empty. Please enter a date in MM/DD/YYYY format."));
                continue;
            }
            
            // Check format pattern
            if (!input.matches("\\d{2}/\\d{2}/\\d{4}")) {
                System.out.println(ConsoleUI.warn("Invalid format! Please use MM/DD/YYYY format (e.g., 01/15/2024)."));
                continue;
            }
            
            try {
                Date date = dateFormat.parse(input);
                return input;
            } catch (ParseException e) {
                System.out.println(ConsoleUI.warn("Invalid date! Please enter a valid date in MM/DD/YYYY format."));
            }
        }
    }
    
    /**
     * Validates and gets a status input from allowed values
     * @param scanner Scanner object for input
     * @param prompt Message to display to user
     * @param allowedValues Array of allowed status values
     * @return Valid status string
     */
    public static String getValidatedStatus(Scanner scanner, String prompt, String[] allowedValues) {
        String input;
        StringBuilder allowedStr = new StringBuilder();
        for (int i = 0; i < allowedValues.length; i++) {
            allowedStr.append(allowedValues[i]);
            if (i < allowedValues.length - 1) {
                allowedStr.append("/");
            }
        }
        
        while (true) {
            System.out.print(prompt + " (" + allowedStr + "): ");
            input = scanner.nextLine().trim();
            
            if (input.isEmpty()) {
                System.out.println(ConsoleUI.warn("Status cannot be empty. Please enter one of: " + allowedStr));
                continue;
            }
            
            // Case-insensitive comparison
            for (String allowed : allowedValues) {
                if (input.equalsIgnoreCase(allowed)) {
                    // Return with proper case
                    return allowed;
                }
            }
            
            System.out.println(ConsoleUI.warn("Invalid status! Please enter one of: " + allowedStr));
        }
    }
    
    /**
     * Validates and gets a yes/no confirmation
     * @param scanner Scanner object for input
     * @param prompt Message to display to user
     * @return true for yes, false for no
     */
    public static boolean getYesNoConfirmation(Scanner scanner, String prompt) {
        String input;
        while (true) {
            System.out.print(prompt + " (yes/no): ");
            input = scanner.nextLine().trim().toLowerCase();
            
            if (input.equals("yes") || input.equals("y")) {
                return true;
            } else if (input.equals("no") || input.equals("n")) {
                return false;
            } else {
                System.out.println(ConsoleUI.warn("Please enter 'yes' or 'no'."));
            }
        }
    }
    
    /**
     * Validates that a string contains only alphabetic characters and spaces
     * @param scanner Scanner object for input
     * @param prompt Message to display to user
     * @return Valid alphabetic string
     */
    public static String getValidatedAlphabeticString(Scanner scanner, String prompt) {
        String input;
        while (true) {
            System.out.print(prompt);
            input = scanner.nextLine().trim();
            
            if (input.isEmpty()) {
                System.out.println(ConsoleUI.warn("This field cannot be empty. Please enter a valid value."));
            } else if (!input.matches("[a-zA-Z\\s]+")) {
                System.out.println(ConsoleUI.warn("Invalid input! Please use only letters and spaces."));
            } else {
                return input;
            }
        }
    }
    
    /**
     * Validates and gets an alphanumeric string (letters, numbers, spaces)
     * @param scanner Scanner object for input
     * @param prompt Message to display to user
     * @return Valid alphanumeric string
     */
    public static String getValidatedAlphanumericString(Scanner scanner, String prompt) {
        String input;
        while (true) {
            System.out.print(prompt);
            input = scanner.nextLine().trim();
            
            if (input.isEmpty()) {
                System.out.println(ConsoleUI.warn("This field cannot be empty. Please enter a valid value."));
            } else if (!input.matches("[a-zA-Z0-9\\s]+")) {
                System.out.println(ConsoleUI.warn("Invalid input! Please use only letters, numbers, and spaces."));
            } else {
                return input;
            }
        }
    }
    
    /**
     * Validates and gets a date in MM/DD/YYYY format or allows "Not yet" for pending dates
     * @param scanner Scanner object for input
     * @param prompt Message to display to user
     * @param allowNotYet If true, accepts "Not yet" as a valid input
     * @return Valid date string in MM/DD/YYYY format or "Not yet"
     */
    public static String getValidatedDateOrPending(Scanner scanner, String prompt, boolean allowNotYet) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        dateFormat.setLenient(false);
        String input;
        
        while (true) {
            System.out.print(prompt);
            input = scanner.nextLine().trim();
            
            if (input.isEmpty()) {
                System.out.println(ConsoleUI.warn("Date cannot be empty. Please enter a date in MM/DD/YYYY format" + 
                    (allowNotYet ? " or 'Not yet'" : "") + "."));
                continue;
            }
            
            // Check if user entered "Not yet" when allowed
            if (allowNotYet && input.equalsIgnoreCase("Not yet")) {
                return "Not yet";
            }
            
            // Check format pattern
            if (!input.matches("\\d{2}/\\d{2}/\\d{4}")) {
                System.out.println(ConsoleUI.warn("Invalid format! Please use MM/DD/YYYY format (e.g., 01/15/2024)" + 
                    (allowNotYet ? " or enter 'Not yet'" : "") + "."));
                continue;
            }
            
            try {
                Date date = dateFormat.parse(input);
                return input;
            } catch (ParseException e) {
                System.out.println(ConsoleUI.warn("Invalid date! Please enter a valid date in MM/DD/YYYY format" + 
                    (allowNotYet ? " or 'Not yet'" : "") + "."));
            }
        }
    }
}