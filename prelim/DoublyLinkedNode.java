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
    
    public E getData() {
        return data;
    }
    
    public void setNext(DoublyLinkedNode<E> next) {
        this.next = next;
    }
    
    public DoublyLinkedNode<E> getNext() {
        return next;
    }
    
    public void setPrevious(DoublyLinkedNode<E> previous) {
        this.previous = previous;
    }
    
    public DoublyLinkedNode<E> getPrevious() {
        return previous;
    }
}