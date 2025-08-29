package prelim;
public class ListOverflowException extends Exception {
    public ListOverflowException() {
        super("List is full! Cannot insert more elements.");
    }
    public ListOverflowException(String message) {
        super(message);
    }
}

