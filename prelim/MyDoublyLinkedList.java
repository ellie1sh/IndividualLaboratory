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
    public void insert(E data) {
        DoublyLinkedNode<E> newNode = new DoublyLinkedNode<>(data);
        if (head == null) {
            head = tail = newNode;
        } else {
            tail.setNext(newNode);
            newNode.setPrevious(tail);
            tail = newNode;
        }
        size++;
    }
    @Override

    public E getElement(E data) throws NoSuchElementException {
        DoublyLinkedNode<E> current = head;
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
        DoublyLinkedNode<E> current = head;
        while (current != null) {
            if (current.getData().equals(data)) {
// If it's the only node
                if (current == head && current == tail) {
                    head = tail = null;
                }
// If it's the head
                else if (current == head) {
                    head = head.getNext();
                    if (head != null) {
                        head.setPrevious(null);
                    }
                }
// If it's the tail
                else if (current == tail) {
                    tail = tail.getPrevious();
                    if (tail != null) {
                        tail.setNext(null);
                    }
                }
// If it's a middle node
                else {
                    current.getPrevious().setNext(current.getNext());
                    current.getNext().setPrevious(current.getPrevious());
                }
                size--;
                return true;
            }
            current = current.getNext();
        }
        return false;
    }
    @Override
    public int search(E data) {
        DoublyLinkedNode<E> current = head;
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
        DoublyLinkedNode<E> current = head;
        while (current != null) {
            sb.append(current.getData());
            if (current.getNext() != null) sb.append(", ");
            current = current.getNext();
        }
        sb.append("]");
        return sb.toString();
    }
}