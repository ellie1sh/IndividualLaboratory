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
    public void insert(E data) {
        Node<E> newNode = new Node<>(data);
        if (head == null) {
            head = newNode;
        } else {
// Insert at the end
            Node<E> current = head;
            while (current.getNext() != null) {
                current = current.getNext();
            }
            current.setNext(newNode);
        }
        size++;
    }
    @Override
    public E getElement(E data) throws NoSuchElementException {
        Node<E> current = head;
        while (current != null) {
            if (current.getData().equals(data)) {
                return current.getData();
            }
            current = current.getNext();
        }
        throw new NoSuchElementException("Element not found: " + data);
    }

    @Override
    public boolean delete(E data) {
        if (head == null) {
            return false;
        }
// If head contains the data to delete
        if (head.getData().equals(data)) {
            head = head.getNext();
            size--;
            return true;
        }
// Search for the node to delete
        Node<E> current = head;
        while (current.getNext() != null) {
            if (current.getNext().getData().equals(data)) {
                current.setNext(current.getNext().getNext());
                size--;
                return true;
            }
            current = current.getNext();
        }
        return false;
    }
    @Override
    public int search(E data) {
        Node<E> current = head;
        int index = 0;
        while (current != null) {
            if (current.getData().equals(data)) {
                return index;
            }
            current = current.getNext();
            index++;
        }
        return -1;
    }
    public void display() {
        System.out.println(toString());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("List contents: [");
        Node<E> current = head;
        while (current != null) {
            sb.append(current.getData());
            if (current.getNext() != null) sb.append(", ");
            current = current.getNext();
        }
        sb.append("]");
        return sb.toString();
    }
}