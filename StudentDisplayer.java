package studentdatabase;

public class StudentDisplayer {
    private final Student[] students;
    private final int count;

    public StudentDisplayer(Student[] students, int count) {
        this.students = students;
        this.count = count;
    }

    /**
     * Displays all students in the database in a formatted table
     */
    public void displayAllStudents() {
        if (count == 0) {
            System.out.println("No students in database");
            return;
        }

        // Print table header
        System.out.println("+--------+----------------------+------------+-------+-------+");
        System.out.println("|  PRN   |        Name          |    DoB     | Marks | Grade |");
        System.out.println("+--------+----------------------+------------+-------+-------+");

        // Print each student's information
        for (int i = 0; i < count; i++) {
            Student student = students[i];
            System.out.printf("| %-6s | %-20s | %-10s | %5.1f |   %-3s |\n",
                    student.getPrn(),
                    student.getName(),
                    student.getDob(),
                    student.getMarks(),
                    student.getGrade());
        }

        System.out.println("+--------+----------------------+------------+-------+-------+");
        System.out.printf("Total students: %d\n", count);
    }

    /**
     * Displays a single student's details in verbose format
     * @param student The student to display
     */
    public void displayStudentDetails(Student student) {
        if (student == null) {
            System.out.println("Student not found");
            return;
        }

        System.out.println("\n=== STUDENT DETAILS ===");
        System.out.println("PRN:       " + student.getPrn());
        System.out.println("Name:      " + student.getName());
        System.out.println("DoB:       " + student.getDob());
        System.out.println("Age:       " + student.getAge() + " years");
        System.out.println("Marks:     " + student.getMarks());
        System.out.println("Grade:     " + student.getGrade());
        System.out.println("========================\n");
    }

    /**
     * Displays students in a compact list format
     */
    public void displayStudentList() {
        if (count == 0) {
            System.out.println("No students available");
            return;
        }

        System.out.println("\nID  PRN     Name                Marks");
        System.out.println("------------------------------------");
        for (int i = 0; i < count; i++) {
            Student student = students[i];
            System.out.printf("%-3d %-7s %-20s %.1f\n",
                    i + 1,
                    student.getPrn(),
                    student.getName(),
                    student.getMarks());
        }
    }
}
