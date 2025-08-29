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
        System.out.println("=== Personal Property Manager ===");
        System.out.println("(Fixed size list - maximum 5 items)");

        while (true) {
            System.out.println("\n1. Add Property");
            System.out.println("2. View All Properties");
            System.out.println("3. Search Property");
            System.out.println("4. Delete Property");
            System.out.println("5. Exit");
            System.out.print("Choose option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline
            switch (choice) {
                case 1:
                    try {
                        System.out.print("Enter property name: ");
                        String name = scanner.nextLine();
                        System.out.print("Enter model: ");
                        String model = scanner.nextLine();
                        System.out.print("Enter color: ");
                        String color = scanner.nextLine();
                        System.out.print("Enter status: ");
                        String status = scanner.nextLine();
                        PersonalProperty property = new PersonalProperty(name, model, color, status);
                        propertyList.insert(property);
                        System.out.println("Property added successfully!");
                    } catch (ListOverflowException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;
                case 2:
                    System.out.println("\nYour Properties:");
                    propertyList.display();
                    System.out.println("Total items: " + propertyList.getSize());
                    break;
                case 3:
                    System.out.print("Enter property name to search: ");
                    String searchName = scanner.nextLine();
                    System.out.print("Enter model: ");
                    String searchModel = scanner.nextLine();
                    PersonalProperty searchProperty = new PersonalProperty(searchName, searchModel, "", "");
                    int index = propertyList.search(searchProperty);
                    if (index != -1) {
                        System.out.println("Found at position " + index);
                    } else {
                        System.out.println("Property not found.");
                    }
                    break;
                case 4:

                    System.out.print("Enter property name to delete: ");
                    String deleteName = scanner.nextLine();
                    System.out.print("Enter model: ");
                    String deleteModel = scanner.nextLine();
                    PersonalProperty deleteProperty = new PersonalProperty(deleteName, deleteModel, "", "");
                    if (propertyList.delete(deleteProperty)) {
                        System.out.println("Property deleted successfully!");
                    } else {
                        System.out.println("Property not found.");
                    }
                    break;
                case 5:
                    System.out.println("Goodbye!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option!");
            }
        }
    }
}