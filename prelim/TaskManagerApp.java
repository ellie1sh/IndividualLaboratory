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
        System.out.println("=== Course Task Manager ===");
        System.out.println("(Growing array list - starts with 5, doubles when full)");
        while (true) {
            System.out.println("\n1. Add Task");
            System.out.println("2. View All Tasks");
            System.out.println("3. Search Task");
            System.out.println("4. Delete Task");
            System.out.println("5. Exit");
            System.out.print("Choose option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline
            switch (choice) {
                case 1:
                    try {
                        System.out.print("Enter project name: ");
                        String name = scanner.nextLine();
                        System.out.print("Enter date assigned (MM/DD/YYYY): ");
                        String assigned = scanner.nextLine();
                        System.out.print("Enter date submitted (MM/DD/YYYY): ");
                        String submitted = scanner.nextLine();
                        System.out.print("Enter status (Completed/Pending): ");
                        String status = scanner.nextLine();
                        Task task = new Task(name, assigned, submitted, status);
                        taskList.insert(task);
                        System.out.println("Task added successfully!");
                    } catch (ListOverflowException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;
                case 2:
                    System.out.println("\nYour Tasks:");
                    taskList.display();
                    System.out.println("Total tasks: " + taskList.getSize());
                    break;
                case 3:
                    System.out.print("Enter project name to search: ");
                    String searchName = scanner.nextLine();
                    Task searchTask = new Task(searchName, "", "", "");

                    int index = taskList.search(searchTask);
                    if (index != -1) {
                        System.out.println("Found at position " + index);
                    } else {
                        System.out.println("Task not found.");
                    }
                    break;
                case 4:
                    System.out.print("Enter project name to delete: ");
                    String deleteName = scanner.nextLine();
                    Task deleteTask = new Task(deleteName, "", "", "");
                    if (taskList.delete(deleteTask)) {
                        System.out.println("Task deleted successfully!");
                    } else {
                        System.out.println("Task not found.");
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