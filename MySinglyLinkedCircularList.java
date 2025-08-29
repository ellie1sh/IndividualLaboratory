package prelim;
import java.util.NoSuchElementException;
public class MySinglyLinkedCircularList<E> implements MyList<E> {
    private Node<E> tail; // Points to the last node, which points to head
    private int size;
    public MySinglyLinkedCircularList() {
        tail = null;
        size = 0;
    }
    @Override
    public int getSize() {
        return size;
    }
    @Override
    public void insert(E data) throws ListOverflowException {
        Node<E> newNode = new Node<>(data);
        if (tail == null) {
// First node - points to itself
            tail = newNode;
            tail.setNext(tail);
        } else {
// Insert after tail (at the end)
            newNode.setNext(tail.getNext()); // Point to head
            tail.setNext(newNode); // Connect tail to new node
            tail = newNode; // Update tail to new node
        }
        size++;
    }

    @Override
    public E getElement(E data) throws NoSuchElementException {
        if (tail == null) {
            throw new NoSuchElementException("List is empty");
        }
        Node<E> current = tail.getNext(); // Start from head
        do {
            if (current.getData().equals(data)) {
                return current.getData();
            }
            current = current.getNext();
        } while (current != tail.getNext());
        throw new NoSuchElementException("Element not found: " + data);
    }
    @Override
    public boolean delete(E data) {
        if (tail == null) {
            return false;
        }
// If there's only one node
        if (tail.getNext() == tail && tail.getData().equals(data)) {
            tail = null;
            size--;
            return true;
        }
        Node<E> head = tail.getNext();
        Node<E> current = head;
        Node<E> previous = tail;
// Traverse the circular list
        do {
            if (current.getData().equals(data)) {
                previous.setNext(current.getNext());
                // If deleting head, update head reference via tail.next
                if (current == head) {
                    tail.setNext(current.getNext());
                }
                // If deleting tail, move tail back
                if (current == tail) {
                    tail = previous;
                }
                size--;
                return true;
            }
            previous = current;
            current = current.getNext();
        } while (current != head);
        return false;
    }
    @Override
    public int search(E data) {
        if (tail == null) {
            return -1;
        }
        Node<E> current = tail.getNext(); // Start from head
        int index = 0;
        do {
            if (current.getData().equals(data)) {
                return index;
            }
            current = current.getNext();
            index++;
        } while (current != tail.getNext());
        return -1;
    }
    public void display() {
        if (tail == null) {
            System.out.println("List contents: []");
            return;
        }
        System.out.print("List contents: [");
        Node<E> current = tail.getNext(); // Start from head
        do {
            System.out.print(current.getData());
            current = current.getNext();
            if (current != tail.getNext()) System.out.print(", ");
        } while (current != tail.getNext());
        System.out.println("]");
    }
}