package prelim;
import java.util.NoSuchElementException;

/**
 * Generic List Interface - defines the contract for all list implementations
 * @param <E> the type of elements stored in the list
 */
public interface MyList<E> {
    public int getSize();
    public void insert(E data) throws ListOverflowException;
    public E getElement(E data) throws NoSuchElementException;
    public boolean delete(E data); // returns false if the data is not deleted in the list
    public int search(E data); // returns index of data, else -1 is return
}