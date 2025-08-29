package prelim;
import java.util.NoSuchElementException;
public class MyDoublyLinkedList<E> implements MyList<E> {
    private DoublyLinkedNode<E> head;
    private DoublyLinkedNode<E> tail;
    private int size;
    public MyDoublyLinkedList() {
        head = null;
        tail = null;
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
            head = tail = newNode;
        } else {
            tail.next = newNode;
            newNode.previous = tail;
            tail = newNode;
        }
        size++;
    }
    @Override

    public E getElement(E data) throws NoSuchElementException {
        DoublyLinkedNode<E> current = head;
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
        DoublyLinkedNode<E> current = head;
        while (current != null) {
            if (current.data.equals(data)) {
// If it's the only node
                if (current == head && current == tail) {
                    head = tail = null;
                }
// If it's the head
                else if (current == head) {
                    head = head.next;
                    head.previous = null;
                }
// If it's the tail
                else if (current == tail) {
                    tail = tail.previous;
                    tail.next = null;
                }
// If it's a middle node
                else {
                    current.previous.next = current.next;
                    current.next.previous = current.previous;
                }
                size--;
                return true;
            }
            current = current.next;
        }
        return false;
    }
    @Override
    public int search(E data) {
        DoublyLinkedNode<E> current = head;
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
        DoublyLinkedNode<E> current = head;
        while (current != null) {
            System.out.print(current.data);
            if (current.next != null) System.out.print(", ");
            current = current.next;
        }
        System.out.println("]");
    }
}