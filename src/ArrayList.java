/**
 * ArrayList implementation
 * With initial capacity of 10
 * @param <T> Type of elements
 */
public class ArrayList<T> implements List<T> {
   private static int INITIAL_CAPACITY = 10;
   private Object[] data;
   private int size;

    /**
     * Constructs a new empty ArrayList
     */
    public ArrayList() {
        this.data = new Object[INITIAL_CAPACITY];
        this.size = 0;
    }

    /**
     * Private helper to double the capacity of the internal array
     */
    private void grow() {
        int newCapacity = this.data.length * 2;
        Object[] newData = new Object[newCapacity];
        //Copy all elements from the old array to the new one
        for (int i = 0; i < this.size; i++) {
            newData[i] = this.data[i];
        }
        //Replace the old array with the new, larger array
        this.data = newData;
    }

    /**
     * Insert the element at the specified position in the list.
     * Shift the element currently at that position and any following elements to the right
     * @param index Index at which the element will be inserted at
     * @param element Element to be inserted
     */
    @Override
    public void add(int index, T element) {
        if (index < 0 || index > this.size) {
            throw new IndexOutOfBoundsException("Error");
        }
        //Check if we need to grow the array
        if (this.size == this.data.length) {
            grow(); //call helper method
        }
        //Shift elements to the right with a for loop
        for (int i = this.size; i > index; i--) {
            this.data[i] = this.data[i - 1];
        }

        //Insert the new element
        this.data[index] = element;
        this.size++;
    }

    /**
     * Append the element to the end of the list
     * @param element The element to be appended
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
     * @param index The index of the element returned
     * @return The element at the specified position
     */
    @Override
    public T get(int index) {
        if (index < 0 || index >= this.size) {
            throw new IndexOutOfBoundsException("Error");
        }
        return (T) this.data[index]; //Case from object back to T
    }

    /**
     * Remove the element at the specified position and shift any following elements to the left
     * @param index The index of the element that will be removed
     * @return The element that was removed from the list
     */
    @Override
    public T remove(int index) {
        if (index < 0 || index >= this.size) {
            throw new IndexOutOfBoundsException("Error");
        }
        //Get the element to return
        T elementToRemove = (T) this.data[index];

        //Shift elements to the left with a for loop
        for (int i = index;i < this.size - 1; i++) {
            this.data[i] = this.data[i + 1];
        }
        //Decrement size and clear the last spot
        this.size--;
        this.data[this.size] = null;
        return elementToRemove;
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