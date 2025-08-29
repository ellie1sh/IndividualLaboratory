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
            int choice = InputUtils.readIntInRange(scanner, "Choose option: ", 1, 5);
            switch (choice) {
                case 1:
                    try {
                        String name = InputUtils.readNonEmptyString(scanner, "Enter property name: ");
                        String model = InputUtils.readNonEmptyString(scanner, "Enter model: ");
                        String color = InputUtils.readNonEmptyString(scanner, "Enter color: ");
                        String status = InputUtils.readNonEmptyString(scanner, "Enter status: ");
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
                    String searchName = InputUtils.readNonEmptyString(scanner, "Enter property name to search: ");
                    String searchModel = InputUtils.readNonEmptyString(scanner, "Enter model: ");
                    PersonalProperty searchProperty = new PersonalProperty(searchName, searchModel, "", "");
                    int index = propertyList.search(searchProperty);
                    if (index != -1) {
                        System.out.println(ConsoleUI.success("Found at position " + index));
                    } else {
                        System.out.println(ConsoleUI.warn("Property not found"));
                    }
                    break;
                case 4:

                    String deleteName = InputUtils.readNonEmptyString(scanner, "Enter property name to delete: ");
                    String deleteModel = InputUtils.readNonEmptyString(scanner, "Enter model: ");
                    PersonalProperty deleteProperty = new PersonalProperty(deleteName, deleteModel, "", "");
                    if (propertyList.delete(deleteProperty)) {
                        System.out.println(ConsoleUI.success("Property deleted successfully"));
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