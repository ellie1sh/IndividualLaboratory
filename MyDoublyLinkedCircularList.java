package prelim;
import java.util.NoSuchElementException;
public class MyDoublyLinkedCircularList<E> implements MyList<E> {

    private DoublyLinkedNode<E> head;
    private int size;
    public MyDoublyLinkedCircularList() {
        head = null;
        size = 0;
    }
    @Override
    public int getSize() {
        return size;
    }
    @Override
    public void insert(E data) throws ListOverflowException {
        DoublyLinkedNode<E> newNode = new DoublyLinkedNode<>(data);
        if (head == null) {
// First node - points to itself
            head = newNode;
            head.next = head;
            head.previous = head;
        } else {
// Insert before head (at the end)
            DoublyLinkedNode<E> tail = head.previous;
            newNode.next = head;
            newNode.previous = tail;
            tail.next = newNode;
            head.previous = newNode;
        }
        size++;
    }
    @Override
    public E getElement(E data) throws NoSuchElementException {
        if (head == null) {
            throw new NoSuchElementException("List is empty");
        }
        DoublyLinkedNode<E> current = head;
        do {
            if (current.data.equals(data)) {
                return current.data;
            }
            current = current.next;
        } while (current != head);
        throw new NoSuchElementException("Element not found: " + data);
    }
    @Override
    public boolean delete(E data) {

        if (head == null) {
            return false;
        }
        DoublyLinkedNode<E> current = head;
        do {
            if (current.data.equals(data)) {
// If it's the only node
                if (current.next == current) {
                    head = null;
                } else {
// Update links
                    current.previous.next = current.next;
                    current.next.previous = current.previous;
// If we're deleting the head, update head
                    if (current == head) {
                        head = current.next;
                    }
                }
                size--;
                return true;
            }
            current = current.next;
        } while (current != head);
        return false;
    }
    @Override
    public int search(E data) {
        if (head == null) {
            return -1;
        }
        DoublyLinkedNode<E> current = head;
        int index = 0;
        do {
            if (current.data.equals(data)) {
                return index;
            }
            current = current.next;
            index++;
        } while (current != head);
        return -1;
    }
    public void display() {
        if (head == null) {
            System.out.println("List contents: []");
            return;
        }

        System.out.print("List contents: [");
        DoublyLinkedNode<E> current = head;
        do {
            System.out.print(current.data);
            current = current.next;
            if (current != head) System.out.print(", ");
        } while (current != head);
        System.out.println("]");
    }
}