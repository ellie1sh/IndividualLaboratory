package prelim;

import java.util.Scanner;

/**
 * Demonstration class to showcase the input validation features
 */
public class ValidationDemo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println(ConsoleUI.title("Input Validation Demonstration"));
        System.out.println(ConsoleUI.info("This demo showcases various input validation features"));
        
        while (true) {
            System.out.println("\n" + ConsoleUI.section("Validation Demo Menu"));
            System.out.println("1. Test Integer Validation");
            System.out.println("2. Test String Validation");
            System.out.println("3. Test Date Validation");
            System.out.println("4. Test Status Validation");
            System.out.println("5. Test Yes/No Confirmation");
            System.out.println("6. Run PersonalPropertyApp");
            System.out.println("7. Run TaskManagerApp");
            System.out.println("8. Exit");
            
            int choice = InputValidator.getValidatedIntChoice(scanner, "Choose option (1-8): ", 1, 8);
            
            switch (choice) {
                case 1:
                    testIntegerValidation(scanner);
                    break;
                case 2:
                    testStringValidation(scanner);
                    break;
                case 3:
                    testDateValidation(scanner);
                    break;
                case 4:
                    testStatusValidation(scanner);
                    break;
                case 5:
                    testYesNoValidation(scanner);
                    break;
                case 6:
                    System.out.println(ConsoleUI.info("Launching PersonalPropertyApp..."));
                    PersonalPropertyApp.main(new String[]{});
                    return;
                case 7:
                    System.out.println(ConsoleUI.info("Launching TaskManagerApp..."));
                    TaskManagerApp.main(new String[]{});
                    return;
                case 8:
                    if (InputValidator.getYesNoConfirmation(scanner, "\nAre you sure you want to exit?")) {
                        System.out.println(ConsoleUI.info("Thank you for testing the validation demo!"));
                        scanner.close();
                        return;
                    }
                    break;
            }
        }
    }
    
    private static void testIntegerValidation(Scanner scanner) {
        System.out.println("\n" + ConsoleUI.section("Integer Validation Test"));
        System.out.println("Try entering invalid values (letters, out of range, etc.)");
        
        int value = InputValidator.getValidatedIntChoice(scanner, "Enter a number between 1 and 10: ", 1, 10);
        System.out.println(ConsoleUI.success("You entered: " + value));
    }
    
    private static void testStringValidation(Scanner scanner) {
        System.out.println("\n" + ConsoleUI.section("String Validation Test"));
        
        System.out.println("\n1. Testing non-empty string:");
        String str1 = InputValidator.getValidatedString(scanner, "Enter any non-empty text: ");
        System.out.println(ConsoleUI.success("You entered: " + str1));
        
        System.out.println("\n2. Testing string with max length (10 chars):");
        String str2 = InputValidator.getValidatedString(scanner, "Enter text (max 10 chars): ", 10);
        System.out.println(ConsoleUI.success("You entered: " + str2));
        
        System.out.println("\n3. Testing alphabetic-only string:");
        String str3 = InputValidator.getValidatedAlphabeticString(scanner, "Enter letters only: ");
        System.out.println(ConsoleUI.success("You entered: " + str3));
        
        System.out.println("\n4. Testing alphanumeric string:");
        String str4 = InputValidator.getValidatedAlphanumericString(scanner, "Enter letters and numbers: ");
        System.out.println(ConsoleUI.success("You entered: " + str4));
    }
    
    private static void testDateValidation(Scanner scanner) {
        System.out.println("\n" + ConsoleUI.section("Date Validation Test"));
        
        System.out.println("\n1. Testing standard date (MM/DD/YYYY):");
        String date1 = InputValidator.getValidatedDate(scanner, "Enter a date (MM/DD/YYYY): ");
        System.out.println(ConsoleUI.success("You entered: " + date1));
        
        System.out.println("\n2. Testing date with 'Not yet' option:");
        String date2 = InputValidator.getValidatedDateOrPending(scanner, "Enter date or 'Not yet' (MM/DD/YYYY): ", true);
        System.out.println(ConsoleUI.success("You entered: " + date2));
    }
    
    private static void testStatusValidation(Scanner scanner) {
        System.out.println("\n" + ConsoleUI.section("Status Validation Test"));
        System.out.println("Only specific values will be accepted");
        
        String[] options = {"Active", "Inactive", "Pending", "Archived"};
        String status = InputValidator.getValidatedStatus(scanner, "Enter status", options);
        System.out.println(ConsoleUI.success("You entered: " + status));
    }
    
    private static void testYesNoValidation(Scanner scanner) {
        System.out.println("\n" + ConsoleUI.section("Yes/No Validation Test"));
        
        boolean result = InputValidator.getYesNoConfirmation(scanner, "Do you like validation?");
        if (result) {
            System.out.println(ConsoleUI.success("You answered: YES"));
        } else {
            System.out.println(ConsoleUI.info("You answered: NO"));
        }
    }
}