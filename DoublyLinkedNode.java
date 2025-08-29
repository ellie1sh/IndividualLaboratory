package prelim;
class DoublyLinkedNode<E> {
    E data;
    DoublyLinkedNode<E> next;
    DoublyLinkedNode<E> previous;
    public DoublyLinkedNode(E data) {
        this.data = data;
        this.next = null;
        this.previous = null;
    }
}