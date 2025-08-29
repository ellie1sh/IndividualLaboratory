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
            System.out.println("\n" + ConsoleUI.section("Main Menu"));
            System.out.println("1. Add Task");
            System.out.println("2. View All Tasks");
            System.out.println("3. Search Task");
            System.out.println("4. Delete Task");
            System.out.println("5. Exit");
            
            // Validated menu choice input
            int choice = InputValidator.getValidatedIntChoice(scanner, "Choose option (1-5): ", 1, 5);
            switch (choice) {
                case 1:
                    try {
                        System.out.println("\n" + ConsoleUI.info("Adding New Task"));
                        
                        // Validate project name (max 100 chars)
                        String name = InputValidator.getValidatedString(scanner, "Enter project name: ", 100);
                        
                        // Validate date assigned with proper format
                        String assigned = InputValidator.getValidatedDate(scanner, "Enter date assigned (MM/DD/YYYY): ");
                        
                        // Validate date submitted with proper format (allow "Not yet" for pending tasks)
                        String submitted = InputValidator.getValidatedDateOrPending(scanner, "Enter date submitted (MM/DD/YYYY or 'Not yet' for pending): ", true);
                        
                        // Validate status with predefined options
                        String[] statusOptions = {"Completed", "Pending", "In Progress", "Overdue"};
                        String status = InputValidator.getValidatedStatus(scanner, "Enter status", statusOptions);
                        
                        // Display task summary before confirmation
                        System.out.println("\n" + ConsoleUI.info("Task Details:"));
                        System.out.println("  Project: " + name);
                        System.out.println("  Date Assigned: " + assigned);
                        System.out.println("  Date Submitted: " + submitted);
                        System.out.println("  Status: " + status);
                        
                        if (InputValidator.getYesNoConfirmation(scanner, "\nConfirm adding this task?")) {
                            Task task = new Task(name, assigned, submitted, status);
                            taskList.insert(task);
                            System.out.println(ConsoleUI.success("Task added successfully"));
                        } else {
                            System.out.println(ConsoleUI.info("Task addition cancelled"));
                        }
                    } catch (ListOverflowException e) {
                        System.out.println(ConsoleUI.warn("Error: " + e.getMessage()));
                    }
                    break;
                case 2:
                    System.out.println("\n" + ConsoleUI.section("Your Tasks"));
                    if (taskList.getSize() == 0) {
                        System.out.println(ConsoleUI.info("No tasks in the list"));
                    } else {
                        System.out.println(taskList.toString());
                        System.out.println(ConsoleUI.info("Total tasks: " + taskList.getSize()));
                    }
                    break;
                case 3:
                    if (taskList.getSize() == 0) {
                        System.out.println(ConsoleUI.warn("No tasks to search. List is empty."));
                    } else {
                        System.out.println("\n" + ConsoleUI.info("Search Task"));
                        
                        // Validate search input
                        String searchName = InputValidator.getValidatedString(scanner, "Enter project name to search: ");
                        
                        Task searchTask = new Task(searchName, "", "", "");
                        int index = taskList.search(searchTask);
                        
                        if (index != -1) {
                            System.out.println(ConsoleUI.success("Task found at position " + (index + 1)));
                        } else {
                            System.out.println(ConsoleUI.warn("Task '" + searchName + "' not found"));
                        }
                    }
                    break;
                case 4:
                    if (taskList.getSize() == 0) {
                        System.out.println(ConsoleUI.warn("No tasks to delete. List is empty."));
                    } else {
                        System.out.println("\n" + ConsoleUI.info("Delete Task"));
                        
                        // Validate delete input
                        String deleteName = InputValidator.getValidatedString(scanner, "Enter project name to delete: ");
                        
                        // Confirmation before deletion
                        System.out.println("\n" + ConsoleUI.warn("Warning: This action cannot be undone!"));
                        if (InputValidator.getYesNoConfirmation(scanner, "Are you sure you want to delete task '" + deleteName + "'?")) {
                            Task deleteTask = new Task(deleteName, "", "", "");
                            if (taskList.delete(deleteTask)) {
                                System.out.println(ConsoleUI.success("Task deleted successfully"));
                            } else {
                                System.out.println(ConsoleUI.warn("Task '" + deleteName + "' not found"));
                            }
                        } else {
                            System.out.println(ConsoleUI.info("Deletion cancelled"));
                        }
                    }
                    break;
                case 5:
                    if (InputValidator.getYesNoConfirmation(scanner, "\nAre you sure you want to exit?")) {
                        System.out.println(ConsoleUI.info("Thank you for using Course Task Manager. Goodbye!"));
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