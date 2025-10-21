/**
 * Singly-linked list implementation
 * Contains Node with next pointer.
 * @param <T> Type of elements
 */
public class LinkedList <T> implements List<T> {
    /**
     * Private class with a single node in the linked list.
     * Holds the data element and a reference to the next node
     * @param <T> Type of data held by the node
     */
    private static class Node<T> {
        T data;
        Node<T> next;

        /**
         * Construct a new node
         * @param data Data element to store in the node
         */
        Node(T data) {
            this.data = data;
            this.next = null;
        }
    }

    private Node<T> head;
    private int size;

    /**
     * Construct a new empty LinkedList
     */
    public LinkedList() {
        this.head = null;
        this.size = 0;
    }

    /**
     * Private helper method to get the Node at a specific index
     * @param index Index of the node to get
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
     * Insert the specified element at the specified position
     * Shift the element at current position and any following elements to the righ
     * @param index Index at which the specified element to be inserted
     * @param element Element to be inserted
     */
    public void add(int index, T element) {
        if (index < 0 || index > this.size) {
            throw new IndexOutOfBoundsException("Error");
        }

        Node<T> newNode = new Node<>(element);

        //Case 1: Adding to the front
        if (index == 0) {
            newNode.next = this.head;
            this.head = newNode;
        }
        //Case 2: Adding anywhere else
        else {
            Node<T> prev = getNode(index - 1);
            newNode.next = prev.next;
            prev.next = newNode;
        }
        this.size++;
    }

    /**
     * Append the specified element to the end of the list
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
     * Return the element at the specified position
     * @param index Index of the element to return
     * @return Element at the specified position
     */
    @Override
    public T get(int index) {
        //Use helper method to find the node
        Node<T> node = getNode(index);
        return node.data;
    }

    /**
     * Remove the element at specified position
     * Shift any following elements to the left
     * @param index Index of the element to be removed
     * @return Element that was removed from the list
     */
    @Override
    public T remove(int index) {
        if (index < 0 || index >= this.size) {
            throw new IndexOutOfBoundsException("Error");
        }
        T dataToRemove;

        //Case 1: Removing the head
        if (index == 0) {
            dataToRemove = this.head.data;
            this.head = this.head.next;
        }
        //Case 2: Removing anywhere else
        else {
            Node<T> prev = getNode(index - 1);
            dataToRemove = prev.next.data;
            prev.next = prev.next.next;
        }
        this.size--;
        return dataToRemove;
    }

    /**
     * Return the number of elements in the list
     * @return The number of elements in the list
     */
    @Override
    public int size() {
        return this.size;
    }
}