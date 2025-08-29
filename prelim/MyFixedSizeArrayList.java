package prelim;
import java.util.NoSuchElementException;
public class MyFixedSizeArrayList<E> implements MyList<E> {
    private E[] array;
    private int size;
    private static final int CAPACITY = 5;
    @SuppressWarnings("unchecked")
    public MyFixedSizeArrayList() {
        array = (E[]) new Object[CAPACITY];
        size = 0;
    }
    @Override
    public int getSize() {
        return size;
    }

    @Override
    public void insert(E data) throws ListOverflowException {
        if (size >= CAPACITY) {
            throw new ListOverflowException("Array is full! Cannot add more elements.");
        }
        array[size] = data;
        size++;
    }
    @Override
    public E getElement(E data) throws NoSuchElementException {
        for (int i = 0; i < size; i++) {
            if (array[i].equals(data)) {
                return array[i];
            }
        }
        throw new NoSuchElementException("Element not found: " + data);
    }
    @Override
    public boolean delete(E data) {
        int index = search(data);
        if (index == -1) {
            return false;
        }
// Shift elements to the left
        for (int i = index; i < size - 1; i++) {
            array[i] = array[i + 1];
        }
        size--;
        array[size] = null; // Clear the last element
        return true;
    }
    @Override
    public int search(E data) {
        for (int i = 0; i < size; i++) {
            if (array[i].equals(data)) {
                return i;
            }
        }
        return -1;
    }
    // Helper method to display all elements
    public void display() {
        System.out.println(toString());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("List contents: [");
        for (int i = 0; i < size; i++) {
            sb.append(array[i]);
            if (i < size - 1) sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }
}