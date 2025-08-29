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
            System.out.println("\n1. Add Property");
            System.out.println("2. View All Properties");
            System.out.println("3. Search Property");
            System.out.println("4. Delete Property");
            System.out.println("5. Exit");
            int choice = InputValidator.getValidMenuChoice(scanner, 1, 5);
            switch (choice) {
                case 1:
                    try {
                        String name = InputValidator.getValidString(scanner, "Enter property name: ", "Property name");
                        String model = InputValidator.getValidString(scanner, "Enter model: ", "Model");
                        String color = InputValidator.getValidString(scanner, "Enter color: ", "Color");
                        String status = InputValidator.getValidPropertyStatus(scanner, "Enter status");
                        PersonalProperty property = new PersonalProperty(name, model, color, status);
                        propertyList.insert(property);
                        System.out.println(ConsoleUI.success("Property added successfully"));
                    } catch (ListOverflowException e) {
                        System.out.println(ConsoleUI.warn("Error: " + e.getMessage()));
                    }
                    break;
                case 2:
                    System.out.println(ConsoleUI.section("Your Properties"));
                    System.out.println(propertyList.toString());
                    System.out.println(ConsoleUI.info("Total items: " + propertyList.getSize()));
                    break;
                case 3:
                    String searchName = InputValidator.getValidString(scanner, "Enter property name to search: ", "Property name");
                    String searchModel = InputValidator.getValidString(scanner, "Enter model: ", "Model");
                    PersonalProperty searchProperty = new PersonalProperty(searchName, searchModel, "", "");
                    int index = propertyList.search(searchProperty);
                    if (index != -1) {
                        System.out.println(ConsoleUI.success("Found at position " + index));
                        try {
                            PersonalProperty found = propertyList.getElement(searchProperty);
                            System.out.println(ConsoleUI.info("Property: " + found));
                        } catch (Exception e) {
                            System.out.println(ConsoleUI.warn("Error retrieving property details"));
                        }
                    } else {
                        System.out.println(ConsoleUI.warn("Property not found"));
                    }
                    break;
                case 4:
                    String deleteName = InputValidator.getValidString(scanner, "Enter property name to delete: ", "Property name");
                    String deleteModel = InputValidator.getValidString(scanner, "Enter model: ", "Model");
                    PersonalProperty deleteProperty = new PersonalProperty(deleteName, deleteModel, "", "");
                    
                    // Show the property before deletion for confirmation
                    int deleteIndex = propertyList.search(deleteProperty);
                    if (deleteIndex != -1) {
                        try {
                            PersonalProperty found = propertyList.getElement(deleteProperty);
                            System.out.println(ConsoleUI.info("Found property: " + found));
                            if (InputValidator.getConfirmation(scanner, "Are you sure you want to delete this property?")) {
                                if (propertyList.delete(deleteProperty)) {
                                    System.out.println(ConsoleUI.success("Property deleted successfully"));
                                } else {
                                    System.out.println(ConsoleUI.warn("Failed to delete property"));
                                }
                            } else {
                                System.out.println(ConsoleUI.info("Deletion cancelled"));
                            }
                        } catch (Exception e) {
                            System.out.println(ConsoleUI.warn("Error retrieving property details"));
                        }
                    } else {
                        System.out.println(ConsoleUI.warn("Property not found"));
                    }
                    break;
                case 5:
                    System.out.println(ConsoleUI.info("Goodbye!"));
                    scanner.close();
                    return;
                default:
                    System.out.println(ConsoleUI.warn("Invalid option"));
            }
        }
    }
}