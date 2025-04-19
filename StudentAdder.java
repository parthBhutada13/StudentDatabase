package studentdatabase;

public class StudentAdder {
    private final studentdatabase.Student[] students;
    private final int count;
    private final int capacity;

    public StudentAdder(studentdatabase.Student[] students, int count, int capacity) {
        this.students = students;
        this.count = count;
        this.capacity = capacity;
    }

    public int addStudent(Student student) throws DatabaseFullException,
            DuplicateStudentException {
        checkCapacity();
        checkDuplicate(student.getPrn());
        students[count] = student;
        return count + 1;
    }

    private void checkCapacity() throws DatabaseFullException {
        if (count >= capacity) throw new DatabaseFullException();
    }

    private void checkDuplicate(String prn) throws DuplicateStudentException {
        for (int i = 0; i < count; i++) {
            if (students[i].getPrn().equals(prn)) {
                throw new DuplicateStudentException(prn);
            }
        }
    }
}
