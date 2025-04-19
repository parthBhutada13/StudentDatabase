package studentdatabase;

public class StudentUpdater {
    private final Student[] students;
    private final int count;

    public StudentUpdater(Student[] students, int count) {
        this.students = students;
        this.count = count;
    }

    public void updateStudent(String oldPRN, Student newStudent)
            throws StudentNotFoundException {
        int index = findStudentIndex(oldPRN);
        students[index] = newStudent;
    }

    private int findStudentIndex(String prn) throws StudentNotFoundException {
        for (int i = 0; i < count; i++) {
            if (students[i].getPrn().equals(prn)) {
                return i;
            }
        }
        throw new StudentNotFoundException(prn);
    }
}
