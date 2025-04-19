package studentdatabase;
import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static StudentDatabase database;

    public static void main(String[] args) {
        System.out.println("Student Data Entry System");
        initializeDatabase();
        runMainLoop();
    }

    private static void initializeDatabase() {
        System.out.print("Enter maximum number of students: ");
        int capacity = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        database = new StudentDatabase(capacity);
        System.out.println("Database initialized for " + capacity + " students\n");
    }

    private static void runMainLoop() {
        while (true) {
            displayMenu();
            int choice = getIntInput("Enter your choice: ");

            try {
                handleUserChoice(choice);
            } catch (Exception e) {
                System.out.println("\nError: " + e.getMessage());
            }
        }
    }

    private static void displayMenu() {
        System.out.println("\n===== MAIN MENU =====");
        System.out.println("1. Add Student");
        System.out.println("2. Display All Students");
        System.out.println("3. Search Student");
        System.out.println("4. Update Student");
        System.out.println("5. Delete Student");
        System.out.println("6. Database Statistics");
        System.out.println("7. Exit");
    }

    private static void handleUserChoice(int choice) {
        switch (choice) {
            case 1 -> addStudent();
            case 2 -> displayAllStudents();
            case 3 -> searchStudent();
            case 4 -> updateStudent();
            case 5 -> deleteStudent();
            case 6 -> showStatistics();
            case 7 -> exitProgram();
            default -> System.out.println("Invalid choice. Please try again.");
        }
    }

    private static void addStudent() {
        System.out.println("\n--- ADD NEW STUDENT ---");
        String prn = getStringInput("Enter PRN: ");
        String name = getStringInput("Enter Name: ");
        String dob = getStringInput("Enter Date of Birth (DD/MM/YYYY): ");
        double marks = getDoubleInput("Enter Marks (0-100): ");

        try {
            Student student = new Student(prn, name, dob, marks);
            int newCount = database.getAdder().addStudent(student);
            database.setCount(newCount);
            System.out.println("\nStudent added successfully!");
        } catch (Exception e) {
            System.out.println("\nFailed to add student: " + e.getMessage());
        }
    }

    private static void displayAllStudents() {
        System.out.println("\n--- ALL STUDENTS ---");
        if (database.isEmpty()) {
            System.out.println("No students in database");
            return;
        }
        database.getDisplayer().displayAllStudents();
    }

    private static void searchStudent() {
        System.out.println("\n--- SEARCH STUDENT ---");
        System.out.println("1. By PRN");
        System.out.println("2. By Name");
        System.out.println("3. By Position");
        int searchChoice = getIntInput("Enter search method: ");

        try {
            switch (searchChoice) {
                case 1 -> {
                    String prn = getStringInput("Enter PRN: ");
                    Student student = database.getSearcher().searchByPRN(prn);
                    System.out.println("\nStudent found:\n" + student);
                }
                case 2 -> {
                    String name = getStringInput("Enter Name: ");
                    Student[] students = database.getSearcher().searchByName(name);
                    System.out.println("\nFound " + students.length + " student(s):");
                    for (Student s : students) {
                        System.out.println(s);
                    }
                }
                case 3 -> {
                    int position = getIntInput("Enter position (1-" + database.getCount() + "): ");
                    Student student = database.getSearcher().searchByPosition(position);
                    System.out.println("\nStudent at position " + position + ":\n" + student);
                }
                default -> System.out.println("Invalid search option");
            }
        } catch (Exception e) {
            System.out.println("\nSearch failed: " + e.getMessage());
        }
    }

    private static void updateStudent() {
        System.out.println("\n--- UPDATE STUDENT ---");
        String prn = getStringInput("Enter PRN of student to update: ");

        try {
            Student oldStudent = database.getSearcher().searchByPRN(prn);
            System.out.println("\nCurrent details:\n" + oldStudent);

            System.out.println("\nEnter new details (leave blank to keep current):");
            String newPrn = getStringInput("New PRN [" + oldStudent.getPrn() + "]: ", true);
            String newName = getStringInput("New Name [" + oldStudent.getName() + "]: ", true);
            String newDob = getStringInput("New DoB [" + oldStudent.getDob() + "]: ", true);
            String marksInput = getStringInput("New Marks [" + oldStudent.getMarks() + "]: ", true);

            // Apply updates only if new values provided
            if (newPrn.isEmpty()) newPrn = oldStudent.getPrn();
            if (newName.isEmpty()) newName = oldStudent.getName();
            if (newDob.isEmpty()) newDob = oldStudent.getDob();
            double newMarks = marksInput.isEmpty() ? oldStudent.getMarks() : Double.parseDouble(marksInput);

            Student updatedStudent = new Student(newPrn, newName, newDob, newMarks);
            database.getUpdater().updateStudent(prn, updatedStudent);
            System.out.println("\nStudent updated successfully!");
        } catch (Exception e) {
            System.out.println("\nUpdate failed: " + e.getMessage());
        }
    }

    private static void deleteStudent() {
        System.out.println("\n--- DELETE STUDENT ---");
        String prn = getStringInput("Enter PRN of student to delete: ");

        try {
            int newCount = database.getDeleter().deleteStudent(prn);
            database.setCount(newCount);
            System.out.println("\nStudent deleted successfully!");
        } catch (Exception e) {
            System.out.println("\nDeletion failed: " + e.getMessage());
        }
    }

    private static void showStatistics() {
        System.out.println("\n--- DATABASE STATISTICS ---");
        System.out.println("Current students: " + database.getCount());
        System.out.println("Available slots: " + (database.getCapacity() - database.getCount()));
        System.out.println("Database status: " + (database.isFull() ? "FULL" : "AVAILABLE"));
    }

    private static void exitProgram() {
        System.out.println("\nExiting program...");
        System.exit(0);
    }

    // Utility methods for input handling
    private static int getIntInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }

    private static double getDoubleInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Double.parseDouble(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }

    private static String getStringInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    private static String getStringInput(String prompt, boolean allowEmpty) {
        System.out.print(prompt);
        String input = scanner.nextLine().trim();
        return allowEmpty ? input : input.isEmpty() ? getStringInput(prompt, false) : input;
    }
}
