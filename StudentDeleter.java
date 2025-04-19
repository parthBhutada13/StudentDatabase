package studentdatabase;

public class StudentDeleter {
    private final Student[] students;
    private final int count;

    public StudentDeleter(Student[] students, int count) {
        this.students = students;
        this.count = count;
    }

    public int deleteStudent(String prn) throws StudentNotFoundException {
        int index = findStudentIndex(prn);
        System.arraycopy(students, index+1, students, index, count-index-1);
        students[count-1] = null;
        return count - 1;
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
