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
            tail.next = tail;
        } else {
// Insert after tail (at the end)
            newNode.next = tail.next; // Point to head
            tail.next = newNode; // Connect tail to new node
            tail = newNode; // Update tail to new node
        }
        size++;
    }

    @Override
    public E getElement(E data) throws NoSuchElementException {
        if (tail == null) {
            throw new NoSuchElementException("List is empty");
        }
        Node<E> current = tail.next; // Start from head
        do {
            if (current.data.equals(data)) {
                return current.data;
            }
            current = current.next;
        } while (current != tail.next);
        throw new NoSuchElementException("Element not found: " + data);
    }
    @Override
    public boolean delete(E data) {
        if (tail == null) {
            return false;
        }
// If there's only one node
        if (tail.next == tail && tail.data.equals(data)) {
            tail = null;
            size--;
            return true;
        }
        Node<E> head = tail.next;
        Node<E> current = head;
        Node<E> previous = tail;
// Traverse the circular list
        do {
            if (current.data.equals(data)) {
                previous.next = current.next;
// If we're deleting the head
                if (current == head) {
// If head is also tail (2 nodes)
                    if (current == tail) {
                        tail = previous;
                    }
// tail.next now points to new head
                }
// If we're deleting the tail
                else if (current == tail) {
                    tail = previous;
                }

                size--;
                return true;
            }
            previous = current;
            current = current.next;
        } while (current != head);
        return false;
    }
    @Override
    public int search(E data) {
        if (tail == null) {
            return -1;
        }
        Node<E> current = tail.next; // Start from head
        int index = 0;
        do {
            if (current.data.equals(data)) {
                return index;
            }
            current = current.next;
            index++;
        } while (current != tail.next);
        return -1;
    }
    public void display() {
        if (tail == null) {
            System.out.println("List contents: []");
            return;
        }
        System.out.print("List contents: [");
        Node<E> current = tail.next; // Start from head
        do {
            System.out.print(current.data);
            current = current.next;
            if (current != tail.next) System.out.print(", ");
        } while (current != tail.next);
        System.out.println("]");
    }
}