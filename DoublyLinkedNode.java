package prelim;
class DoublyLinkedNode<E> {
    private E data;
    private DoublyLinkedNode<E> next;
    private DoublyLinkedNode<E> previous;
    public DoublyLinkedNode(E data) {
        this.data = data;
        this.next = null;
        this.previous = null;
    }

    /** Returns the data payload stored in this node. */
    public E getData() {
        return data;
    }

    /** Sets the node that follows this one. */
    public void setNext(DoublyLinkedNode<E> next) {
        this.next = next;
    }

    /** Returns the node that follows this one, or null. */
    public DoublyLinkedNode<E> getNext() {
        return next;
    }

    /** Sets the node that precedes this one. */
    public void setPrevious(DoublyLinkedNode<E> previous) {
        this.previous = previous;
    }

    /** Returns the node that precedes this one, or null. */
    public DoublyLinkedNode<E> getPrevious() {
        return previous;
    }
}