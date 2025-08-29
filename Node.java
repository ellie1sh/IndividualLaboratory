package prelim;
class Node<E> {
    private E data;
    private Node<E> next;

    public Node(E data) {
        this.data = data;
        this.next = null;
    }

    /**
     * Returns the data payload stored in this node.
     */
    public E getData() {
        return data;
    }

    /**
     * Updates the next reference for this node.
     */
    public void setNext(Node<E> next) {
        this.next = next;
    }

    /**
     * Returns the next node reference or null if none.
     */
    public Node<E> getNext() {
        return next;
    }
}