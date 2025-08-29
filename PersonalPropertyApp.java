package prelim;
import java.util.Scanner;

class PersonalProperty {
    private String name;
    private String model;
    private String color;
    private String status;
    public PersonalProperty(String name, String model, String color, String status) {
        this.name = name;
        this.model = model;
        this.color = color;
        this.status = status;
    }
    @Override
    public String toString() {
        return name + " (" + model + ", " + color + ", " + status + ")";
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        PersonalProperty that = (PersonalProperty) obj;
        return name.equals(that.name) && model.equals(that.model);
    }
}
public class PersonalPropertyApp {
    public static void main(String[] args) {
        MyFixedSizeArrayList<PersonalProperty> propertyList = new MyFixedSizeArrayList<>();
        Scanner scanner = new Scanner(System.in);
        System.out.println(ConsoleUI.title("Personal Property Manager"));
        System.out.println(ConsoleUI.info("Fixed size list - maximum 5 items"));

        while (true) {
            System.out.println("\n" + ConsoleUI.section("Main Menu"));
            System.out.println("1. Add Property");
            System.out.println("2. View All Properties");
            System.out.println("3. Search Property");
            System.out.println("4. Delete Property");
            System.out.println("5. Exit");
            
            // Validated menu choice input
            int choice = InputValidator.getValidatedIntChoice(scanner, "Choose option (1-5): ", 1, 5);
            switch (choice) {
                case 1:
                    try {
                        System.out.println("\n" + ConsoleUI.info("Adding New Property"));
                        
                        // Validate property name (alphanumeric with spaces, max 50 chars)
                        String name = InputValidator.getValidatedString(scanner, "Enter property name: ", 50);
                        
                        // Validate model (alphanumeric, max 30 chars)
                        String model = InputValidator.getValidatedAlphanumericString(scanner, "Enter model: ");
                        if (model.length() > 30) {
                            model = model.substring(0, 30);
                            System.out.println(ConsoleUI.info("Model truncated to 30 characters"));
                        }
                        
                        // Validate color (alphabetic only)
                        String color = InputValidator.getValidatedAlphabeticString(scanner, "Enter color: ");
                        
                        // Validate status with predefined options
                        String[] statusOptions = {"Available", "In Use", "Under Repair", "Lost", "Damaged"};
                        String status = InputValidator.getValidatedStatus(scanner, "Enter status", statusOptions);
                        
                        // Confirmation before adding
                        System.out.println("\n" + ConsoleUI.info("Property Details:"));
                        System.out.println("  Name: " + name);
                        System.out.println("  Model: " + model);
                        System.out.println("  Color: " + color);
                        System.out.println("  Status: " + status);
                        
                        if (InputValidator.getYesNoConfirmation(scanner, "\nConfirm adding this property?")) {
                            PersonalProperty property = new PersonalProperty(name, model, color, status);
                            propertyList.insert(property);
                            System.out.println(ConsoleUI.success("Property added successfully"));
                        } else {
                            System.out.println(ConsoleUI.info("Property addition cancelled"));
                        }
                    } catch (ListOverflowException e) {
                        System.out.println(ConsoleUI.warn("Error: " + e.getMessage()));
                    }
                    break;
                case 2:
                    System.out.println("\n" + ConsoleUI.section("Your Properties"));
                    if (propertyList.getSize() == 0) {
                        System.out.println(ConsoleUI.info("No properties in the list"));
                    } else {
                        System.out.println(propertyList.toString());
                        System.out.println(ConsoleUI.info("Total items: " + propertyList.getSize()));
                    }
                    break;
                case 3:
                    if (propertyList.getSize() == 0) {
                        System.out.println(ConsoleUI.warn("No properties to search. List is empty."));
                    } else {
                        System.out.println("\n" + ConsoleUI.info("Search Property"));
                        
                        // Validate search inputs
                        String searchName = InputValidator.getValidatedString(scanner, "Enter property name to search: ");
                        String searchModel = InputValidator.getValidatedString(scanner, "Enter model: ");
                        
                        PersonalProperty searchProperty = new PersonalProperty(searchName, searchModel, "", "");
                        int index = propertyList.search(searchProperty);
                        
                        if (index != -1) {
                            System.out.println(ConsoleUI.success("Property found at position " + (index + 1)));
                        } else {
                            System.out.println(ConsoleUI.warn("Property not found with name '" + searchName + "' and model '" + searchModel + "'"));
                        }
                    }
                    break;
                case 4:
                    if (propertyList.getSize() == 0) {
                        System.out.println(ConsoleUI.warn("No properties to delete. List is empty."));
                    } else {
                        System.out.println("\n" + ConsoleUI.info("Delete Property"));
                        
                        // Validate delete inputs
                        String deleteName = InputValidator.getValidatedString(scanner, "Enter property name to delete: ");
                        String deleteModel = InputValidator.getValidatedString(scanner, "Enter model: ");
                        
                        // Confirmation before deletion
                        System.out.println("\n" + ConsoleUI.warn("Warning: This action cannot be undone!"));
                        if (InputValidator.getYesNoConfirmation(scanner, "Are you sure you want to delete '" + deleteName + "' (" + deleteModel + ")?")) {
                            PersonalProperty deleteProperty = new PersonalProperty(deleteName, deleteModel, "", "");
                            if (propertyList.delete(deleteProperty)) {
                                System.out.println(ConsoleUI.success("Property deleted successfully"));
                            } else {
                                System.out.println(ConsoleUI.warn("Property not found with name '" + deleteName + "' and model '" + deleteModel + "'"));
                            }
                        } else {
                            System.out.println(ConsoleUI.info("Deletion cancelled"));
                        }
                    }
                    break;
                case 5:
                    if (InputValidator.getYesNoConfirmation(scanner, "\nAre you sure you want to exit?")) {
                        System.out.println(ConsoleUI.info("Thank you for using Personal Property Manager. Goodbye!"));
                        scanner.close();
                        return;
                    }
                    break;
                default:
                    System.out.println(ConsoleUI.warn("Invalid option. Please choose between 1 and 5."));
            }
        }
    }
}