/**
 * Double linked-list implementation
 * Contains node with next and prev pointers
 * @param <T> Type of elements held in the list
 */
public class DoublyLinkedList<T> implements List<T> {
    /**
     * Private class with a single node in the list
     * Holds the data element, a reference to the next node,
     * and a reference to the previous node
     * @param <T> Type od data held by the node
     */
    private static class Node<T> {
        T data;
        Node<T> next;
        Node<T> prev;

        /**
         * Construct a new node with the given data
         * @param data Data element to store in node
         */
        Node(T data) {
            this.data = data;
            this.next = null;
            this.prev = null;
        }
    }

    private Node<T> head;
    private Node<T> tail;
    private int size;

    /**
     * Construct a new empty DoublyLinkedList
     */
    public DoublyLinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    /**
     * Private helper method to get the Node at a specific index
     * @param index Index of the Node to retrieve
     * @return Node at the specified index
     */
    private Node<T> getNode(int index) {
        if (index < 0 || index >= this.size) {
            throw new IndexOutOfBoundsException("Error");
        }

        Node<T> current = this.head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current;
    }

    /**
     * Insert specified element at specified position in the list
     * Re-link the previous and next nodes
     * @param index Index at which the specified element that will be inserted
     * @param element Element to be inserted
     */
    @Override
    public void add(int index, T element) {
        if (index < 0 || index > this.size) {
            throw new IndexOutOfBoundsException("Error");
        }

        Node<T> newNode = new Node<>(element);

        //Case 1: Adding to an empty list
        if (this.size == 0) {
            this.head = newNode;
            this.tail = newNode;
        }
        //Case 2: Adding to the front
        else if (index == 0) {
            newNode.next = this.head;
            this.head.prev = newNode;
            this.head = newNode;
        }
        //Case 3: Adding to the end
        else if (index == this.size) {
            this.tail.next = newNode;
            newNode.prev = this.tail;
            this.tail = newNode;
        }
        //Case 4: Adding in the middle
        else {
            //Find the node at the index to insert at
            Node<T> nodeAfter = getNode(index);
            Node<T> nodeBefore = nodeAfter.prev;

            //Link newNode in between
            newNode.next = nodeAfter;
            newNode.prev = nodeBefore;

            //Update the old nodes to point to the new one
            nodeBefore.next = newNode;
            nodeAfter.prev = newNode;
        }
        this.size++;
    }

    /**
     * Append the specified element to the end
     * @param element Element to be appended to the list
     * @return true
     */
    @Override
    public boolean add(T element) {
        //Call the other add method to add at the end
        add(this.size, element);
        return true;
    }

    /**
     * Return the element at the specified position in the list
     * @param index Index of the element to return
     * @return Element at the specified position in the list
     */
    @Override
    public T get(int index) {
        //Use helper to find the node
        return getNode(index).data;
    }

    /**
     * Remove the element at the specified position in the list
     * Relink surrounding nodes
     * @param index Index of the element to be removed
     * @return Element that was removed from the list
     */
    @Override
    public T remove(int index) {
        if (index < 0 || index >= this.size) {
            throw new IndexOutOfBoundsException("Error");
        }

        Node<T> nodeToRemove = getNode(index);
        T dataToRemove = nodeToRemove.data;

        //Case 1: Removing the only element
        if (this.size == 1) {
            this.head = null;
            this.tail = null;
        }
        //Case 2: Removing from the front
        else if (index == 0) {
            this.head = this.head.next;
            this.head.prev = null;
        }
        //Case 3: Removing from the end
        else if (index == this.size - 1) {
            this.tail = this.tail.prev;
            this.tail.next = null;
        }
        //Case 4: Removing from the middle
        else {
            Node<T> nodeBefore = nodeToRemove.prev;
            Node<T> nodeAfter = nodeToRemove.next;

            //Skip the removed node
            nodeBefore.next = nodeAfter;
            nodeAfter.prev = nodeBefore;
        }
        this.size--;
        return dataToRemove;
    }

    /**
     * Return the number of elements in the list
     * @return Number of elements in the list
     */
    @Override
    public int size() {
        return this.size;
    }
}