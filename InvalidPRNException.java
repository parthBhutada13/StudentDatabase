package studentdatabase;

public class InvalidPRNException extends Exception {
    public InvalidPRNException() {
        super("PRN cannot be empty or null");
    }

    public InvalidPRNException(String prn) {
        super("Invalid PRN format: " + prn);
    }
}
