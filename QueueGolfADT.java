package datastructures;

/**
 * Blueprints for a queue data structure, including helpful data structure
 * methods like toArray. This ADT was created primarily for the purposes of
 * playing code golf with my CS 2114 taught the summer of 2016.
 * 
 * Students should implement the interface in their own version of a queue
 * (my ExampleQueue.java code is my version, which students will not have
 * access to). The QueueTest.java code should be able to use the student's 
 * queue an pass all tests for the student's queue to count as functionally
 * complete.
 * 
 * @param <T> The type of objects that the stack will hold.
 * 
 * @author Sarah Grace Fields (sgrace)
 * @version 2016.08.02
 */
public interface QueueGolfADT<T>
{
    /**
     * Checks if the queue is empty.
     * 
     * @return Returns true if the stack is empty.
     */
    public boolean isEmpty();

    /**
     * Checks the item at the front of the queue without removing it.
     * 
     * @throws EmptyQueueException when queue is empty
     * @return Item at the front of the queue.
     */
    public T getFront();

    /**
     * Removes the item at the front of the queue.
     * 
     * @throws EmptyQueueException when queue is empty
     * @return The item that was removed.
     */
    public T dequeue();

    /**
     * Enqueues an item into the back of the queue.
     * 
     * @throws IllegalArgumentException when param is null
     * @param item Item to be added to the queue.
     */
    public void enqueue(T item);

    /**
     * Number of items in the queue.
     * 
     * @return The number of items in the queue.
     */
    public int size();

    /**
     * Clears the queue (removes all of the items in the queue).
     */
    public void clear();

    /**
     * Returns an array with a copy of each element in the queue with the front
     * of the queue being the first element.
     *
     * @throws EmptyQueueException when queue is empty
     * @return the array representation of the queue
     */
    public Object[] toArray();

    /**
     * Returns a String representation of the queue by calling the toString()
     * method of each data item in the queue. The format will be [first, second,
     * ..., n]
     * 
     * @return The String representation of the queue
     */
    public String toString();

    /**
     * An equals method to determine if two queues have the same contents in the
     * same order.
     * 
     * @return A boolean for whether or not the parameter is equal to this queue
     */
    public boolean equals(Object other);
}
