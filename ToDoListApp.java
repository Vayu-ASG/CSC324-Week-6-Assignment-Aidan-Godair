import java.util.*;

/**
 * A To-Do List application with Task Prioritization.
 * Users can:
 * 1. Add tasks with priority (High, Medium, Low)
 * 2. View tasks sorted by priority
 * 3. Remove tasks
 * 4. Exit the program
 */
public class ToDoListApp {
    private static List<Task> tasks = new ArrayList<>(); // Stores tasks as Task objects

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            // Display menu options
            System.out.println("\n--- To-Do List ---");
            System.out.println("1. Add Task");
            System.out.println("2. View Tasks");
            System.out.println("3. Remove Task");
            System.out.println("4. Exit");
            System.out.print("Enter choice: ");

            choice = scanner.nextInt();
            scanner.nextLine();

            // Handle user input choices
            switch (choice) {
                case 1 -> addTask(scanner);
                case 2 -> viewTasks();
                case 3 -> removeTask(scanner);
                case 4 -> System.out.println("Exiting...");
                default -> System.out.println("Invalid choice. Try again.");
            }
        } while (choice != 4);

        scanner.close(); // Close scanner to avoid resource leak
    }

    /**
     * Adds a new task with a priority level.
     * @param scanner Scanner object for user input.
     */
    private static void addTask(Scanner scanner) {
        System.out.print("Enter task: ");
        String description = scanner.nextLine();
        
        System.out.print("Enter priority (H for High, M for Medium, L for Low): ");
        char priorityInput = scanner.nextLine().toUpperCase().charAt(0);
        
        TaskPriority priority;
        switch (priorityInput) {
            case 'H' -> priority = TaskPriority.HIGH;
            case 'M' -> priority = TaskPriority.MEDIUM;
            case 'L' -> priority = TaskPriority.LOW;
            default -> {
                System.out.println("Invalid priority! Defaulting to Low.");
                priority = TaskPriority.LOW;
            }
        }
        
        tasks.add(new Task(description, priority));
        System.out.println("Task added!");
    }

    /**
     * Displays all tasks sorted by priority.
     */
    private static void viewTasks() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks available.");
            return;
        }
        
        // Sort tasks based on priority level
        tasks.sort(Comparator.comparing(Task::getPriority));
        System.out.println("\nYour Tasks:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
    }

    /**
     * Removes a task by its index number.
     * @param scanner Scanner object for user input.
     */
    private static void removeTask(Scanner scanner) {
        viewTasks();
        if (tasks.isEmpty()) return;

        System.out.print("Enter task number to remove: ");
        int index = scanner.nextInt();

        if (index > 0 && index <= tasks.size()) {
            tasks.remove(index - 1);
            System.out.println("Task removed.");
        } else {
            System.out.println("Invalid task number.");
        }
    }
}

/**
 * Represents a Task with a description and priority level.
 */
class Task {
    private final String description;
    private final TaskPriority priority;

    /**
     * Constructor to initialize a task.
     * @param description Task description.
     * @param priority Priority level of the task.
     */
    public Task(String description, TaskPriority priority) {
        this.description = description;
        this.priority = priority;
    }

    /**
     * Returns the priority of the task.
     * @return TaskPriority enum value.
     */
    public TaskPriority getPriority() {
        return priority;
    }

    @Override
    public String toString() {
        return "[" + priority + "] " + description;
    }
}

/**
 * Enum representing task priority levels.
 */
enum TaskPriority {
    HIGH, MEDIUM, LOW
}
