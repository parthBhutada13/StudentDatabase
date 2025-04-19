package studentdatabase;
import java.util.Arrays;

public class StudentSearcher {
    private final Student[] students;
    private final int count;

    public StudentSearcher(Student[] students, int count) {
        this.students = students;
        this.count = count;
    }

    public Student searchByPRN(String prn) throws StudentNotFoundException {
        for (int i = 0; i < count; i++) {
            if (students[i].getPrn().equals(prn)) {
                return students[i];
            }
        }
        throw new StudentNotFoundException(prn);
    }

    public Student[] searchByName(String name) {
        return Arrays.stream(students, 0, count)
                .filter(s -> s.getName().equalsIgnoreCase(name))
                .toArray(Student[]::new);
    }

    public Student searchByPosition(int position) throws InvalidPositionException {
        // Validate position is within bounds (1-based index)
        if (position < 1 || position > count) {
            throw new InvalidPositionException(String.format("Position must be between 1 and %d (got %d)", count, position));
        }

        // Return student at position-1 (converting to 0-based index)
        return students[position - 1];
    }
}
