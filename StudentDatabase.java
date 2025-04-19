package studentdatabase;

public class StudentDatabase {
    private Student[] students;
    private int count;
    private final int capacity;

    public StudentDatabase(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity must be positive");
        }
        this.students = new Student[capacity];
        this.capacity = capacity;
        this.count = 0;
    }

    // Factory methods for operations
    public StudentAdder getAdder() {
        return new StudentAdder(students, count, capacity);
    }

    public StudentSearcher getSearcher() {
        return new StudentSearcher(students, count);
    }

    public StudentUpdater getUpdater() {
        return new StudentUpdater(students, count);
    }

    public StudentDeleter getDeleter() {
        return new StudentDeleter(students, count);
    }

    public StudentDisplayer getDisplayer() {
        return new StudentDisplayer(students, count);
    }

    // Accessor methods
    public void setCount(int newCount) {
        if (newCount < 0 || newCount > capacity) {
            throw new IllegalArgumentException("Invalid count value");
        }
        this.count = newCount;
    }

    public int getCount() {
        return count;
    }

    public int getCapacity() {
        return capacity;
    }

    // Utility methods
    public boolean isFull() {
        return count >= capacity;
    }

    public boolean isEmpty() {
        return count == 0;
    }

    // Additional operation (not in original)
    public void clearDatabase() {
        for (int i = 0; i < count; i++) {
            students[i] = null;
        }
        count = 0;
    }
}
