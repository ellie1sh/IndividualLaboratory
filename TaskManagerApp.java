package prelim;
import java.util.Scanner;
class Task {
    private String projectName;
    private String dateAssigned;
    private String dateSubmitted;
    private String status;
    public Task(String projectName, String dateAssigned, String dateSubmitted, String status) {
        this.projectName = projectName;
        this.dateAssigned = dateAssigned;
        this.dateSubmitted = dateSubmitted;
        this.status = status;
    }
    @Override
    public String toString() {
        return projectName + " (Assigned: " + dateAssigned + ", Submitted: " + dateSubmitted + ", Status: " +
                status + ")";
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Task task = (Task) obj;

        return projectName.equals(task.projectName);
    }
}
public class TaskManagerApp {
    public static void main(String[] args) {
        MyGrowingArrayList<Task> taskList = new MyGrowingArrayList<>();
        Scanner scanner = new Scanner(System.in);
        System.out.println(ConsoleUI.title("Course Task Manager"));
        System.out.println(ConsoleUI.info("Growing array list - starts at 5, doubles when full"));
        while (true) {
            System.out.println("\n1. Add Task");
            System.out.println("2. View All Tasks");
            System.out.println("3. Search Task");
            System.out.println("4. Delete Task");
            System.out.println("5. Exit");
            int choice = InputUtils.readIntInRange(scanner, "Choose option: ", 1, 5);
            switch (choice) {
                case 1:
                    try {
                        String name = InputUtils.readNonEmptyString(scanner, "Enter project name: ");
                        String assigned = InputUtils.readMatchingPattern(
                                scanner,
                                "Enter date assigned (MM/DD/YYYY): ",
                                "^(0[1-9]|1[0-2])/(0[1-9]|[12][0-9]|3[01])/\\d{4}$",
                                "Please enter a valid date in MM/DD/YYYY format."
                        );
                        String submitted = InputUtils.readMatchingPattern(
                                scanner,
                                "Enter date submitted (MM/DD/YYYY): ",
                                "^(0[1-9]|1[0-2])/(0[1-9]|[12][0-9]|3[01])/\\d{4}$",
                                "Please enter a valid date in MM/DD/YYYY format."
                        );
                        String status = InputUtils.readChoice(
                                scanner,
                                "Enter status (Completed/Pending): ",
                                new String[]{"Completed", "Pending"},
                                true
                        );
                        Task task = new Task(name, assigned, submitted, status);
                        taskList.insert(task);
                        System.out.println(ConsoleUI.success("Task added successfully"));
                    } catch (ListOverflowException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;
                case 2:
                    System.out.println(ConsoleUI.section("Your Tasks"));
                    System.out.println(taskList.toString());
                    System.out.println(ConsoleUI.info("Total tasks: " + taskList.getSize()));
                    break;
                case 3:
                    String searchName = InputUtils.readNonEmptyString(scanner, "Enter project name to search: ");
                    Task searchTask = new Task(searchName, "", "", "");

                    int index = taskList.search(searchTask);
                    if (index != -1) {
                        System.out.println(ConsoleUI.success("Found at position " + index));
                    } else {
                        System.out.println(ConsoleUI.warn("Task not found"));
                    }
                    break;
                case 4:
                    String deleteName = InputUtils.readNonEmptyString(scanner, "Enter project name to delete: ");
                    Task deleteTask = new Task(deleteName, "", "", "");
                    if (taskList.delete(deleteTask)) {
                        System.out.println(ConsoleUI.success("Task deleted successfully"));
                    } else {
                        System.out.println(ConsoleUI.warn("Task not found"));
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