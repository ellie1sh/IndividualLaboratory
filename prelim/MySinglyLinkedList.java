package prelim;
import java.util.NoSuchElementException;
public class MySinglyLinkedList<E> implements MyList<E> {
    private Node<E> head;
    private int size;
    public MySinglyLinkedList() {
        head = null;
        size = 0;
    }
    @Override
    public int getSize() {
        return size;
    }
    @Override
    public void insert(E data) throws ListOverflowException {
        Node<E> newNode = new Node<>(data);
        if (head == null) {
            head = newNode;
        } else {
// Insert at the end
            Node<E> current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        size++;
    }
    @Override
    public E getElement(E data) throws NoSuchElementException {
        Node<E> current = head;
        while (current != null) {
            if (current.data.equals(data)) {
                return current.data;
            }
            current = current.next;
        }
        throw new NoSuchElementException("Element not found: " + data);
    }

    @Override
    public boolean delete(E data) {
        if (head == null) {
            return false;
        }
// If head contains the data to delete
        if (head.data.equals(data)) {
            head = head.next;
            size--;
            return true;
        }
// Search for the node to delete
        Node<E> current = head;
        while (current.next != null) {
            if (current.next.data.equals(data)) {
                current.next = current.next.next;
                size--;
                return true;
            }
            current = current.next;
        }
        return false;
    }
    @Override
    public int search(E data) {
        Node<E> current = head;
        int index = 0;
        while (current != null) {
            if (current.data.equals(data)) {
                return index;
            }
            current = current.next;
            index++;
        }
        return -1;
    }
    public void display() {
        System.out.print("List contents: [");
        Node<E> current = head;
        while (current != null) {
            System.out.print(current.data);
            if (current.next != null) System.out.print(", ");
            current = current.next;
        }
        System.out.println("]");
    }
}