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
            int choice = InputValidator.getValidMenuChoice(scanner, 1, 5);
            switch (choice) {
                case 1:
                    try {
                        String name = InputValidator.getValidString(scanner, "Enter project name: ", "Project name");
                        String assigned = InputValidator.getValidDate(scanner, "Enter date assigned (MM/DD/YYYY): ");
                        String submitted = InputValidator.getValidDate(scanner, "Enter date submitted (MM/DD/YYYY): ");
                        String status = InputValidator.getValidStatus(scanner, "Enter status");
                        Task task = new Task(name, assigned, submitted, status);
                        taskList.insert(task);
                        System.out.println(ConsoleUI.success("Task added successfully"));
                    } catch (ListOverflowException e) {
                        System.out.println(ConsoleUI.warn("Error: " + e.getMessage()));
                    }
                    break;
                case 2:
                    System.out.println(ConsoleUI.section("Your Tasks"));
                    System.out.println(taskList.toString());
                    System.out.println(ConsoleUI.info("Total tasks: " + taskList.getSize()));
                    break;
                case 3:
                    String searchName = InputValidator.getValidString(scanner, "Enter project name to search: ", "Project name");
                    Task searchTask = new Task(searchName, "", "", "");

                    int index = taskList.search(searchTask);
                    if (index != -1) {
                        System.out.println(ConsoleUI.success("Found at position " + index));
                        try {
                            Task found = taskList.getElement(searchTask);
                            System.out.println(ConsoleUI.info("Task: " + found));
                        } catch (Exception e) {
                            System.out.println(ConsoleUI.warn("Error retrieving task details"));
                        }
                    } else {
                        System.out.println(ConsoleUI.warn("Task not found"));
                    }
                    break;
                case 4:
                    String deleteName = InputValidator.getValidString(scanner, "Enter project name to delete: ", "Project name");
                    Task deleteTask = new Task(deleteName, "", "", "");
                    
                    // Show the task before deletion for confirmation
                    int deleteIndex = taskList.search(deleteTask);
                    if (deleteIndex != -1) {
                        try {
                            Task found = taskList.getElement(deleteTask);
                            System.out.println(ConsoleUI.info("Found task: " + found));
                            if (InputValidator.getConfirmation(scanner, "Are you sure you want to delete this task?")) {
                                if (taskList.delete(deleteTask)) {
                                    System.out.println(ConsoleUI.success("Task deleted successfully"));
                                } else {
                                    System.out.println(ConsoleUI.warn("Failed to delete task"));
                                }
                            } else {
                                System.out.println(ConsoleUI.info("Deletion cancelled"));
                            }
                        } catch (Exception e) {
                            System.out.println(ConsoleUI.warn("Error retrieving task details"));
                        }
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