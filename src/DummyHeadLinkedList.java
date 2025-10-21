/**
 * DummyHead LinkedList Implementation
 * LinkedList with null head node
 * @param <T> Type of elements
 */
public class DummyHeadLinkedList<T> implements List<T>{
    /**
     * Represents a single node in the linked list
     * @param <T> Type of data stored in the node
     */
    private static class Node<T> {
        T data;
        Node<T> next;

        /**
         * Construct a new node with the specified data
         * @param data Data to store in the node
         */
        Node(T data) {
            this.data = data;
            this.next = null;
        }
    }

    private Node<T> dummyHead;
    private int size;

    /**
     * Construct an empty DummyHeadLinkedList
     * Initializes the dummy head node and sets the size to zero
     */
    public DummyHeadLinkedList() {
        this.dummyHead = new Node<>(null);
        this.size = 0;
    }

    /**
     * Insert the specified element at the specified position in the list
     * Shift the element currently at that position and any following elements to the right
     * @param index Index at whcih the specified element is to be inserted
     * @param element Element to be inserted
     */
    @Override
    public void add(int index, T element) {
        if (index < 0 || index > this.size) {
            throw new IndexOutOfBoundsException("Error");
        }

        Node<T> prev = this.dummyHead;

        //Find the node before the insertion point
        for (int i = 0; i < index; i++) {
            prev = prev.next;
        }

        //Link the new node in
        Node<T> newNode = new Node<>(element);
        newNode.next = prev.next;
        prev.next = newNode;

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
     * Return the element at the specified position in the list
     * @param index Index of the element to return
     * @return Element at the specified position in the list
     */
    @Override
    public T get(int index) {
        if (index < 0 || index >= this.size) {
            throw new IndexOutOfBoundsException("Error");
        }

        //Start at the first real node
        Node<T> current = this.dummyHead.next;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.data;
    }

    /**
     * Remove the element at the specified postion in the list
     *Shift any following elements to the left
     * @param index Index of the element to be removed
     * @return Element previously at the specified position
     */
    @Override
    public T remove(int index) {
        if (index < 0 || index >= this.size) {
            throw new IndexOutOfBoundsException("Error");
        }

        Node<T> prev = this.dummyHead;

        //Find the node before the one to remove
        for (int i = 0; i < index; i++) {
            prev = prev.next;
        }

        //Get the node to remove and its data
        Node<T> nodeToRemove = prev.next;
        T dataToRemove = nodeToRemove.data;

        //Skip the node to remove it
        prev.next = nodeToRemove.next;

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
