package datastructures;

import queue.EmptyQueueException;

import student.TestCase;

/**
 * This is the test class for the QueueGolfADT, so this class 
 * (using CS 2114's special JUnit testing classes standard for the
 * course workflow) tests whether a student's implemented Queue is
 * functionally complete.
 * 
 * Although I can't know the underlying structure of the student's queue,
 * as they are allowed to use linked or array implementations at their
 * own discretion, I do know many of the common pitfalls students make
 * after three semesters of grading student projects. Inline comments
 * explain some of the reasons for testing specific sequences of calls.
 * For example, some students fumble the linked structure updates in dequeues,
 * and sometimes their clear method doesn't reset the size.
 * 
 * Students will have this class in order to run against their queue as 
 * they develop for the queue gold activity.
 * 
 * @author Gracie
 */
public class QueueTest extends TestCase
{
    ExampleQueue<String> smallQueue;
    ExampleQueue<String> largeQueue;

    public void setUp()
    {
        // Make queues using both of the constructors for testing
        smallQueue = new ExampleQueue<String>();
        largeQueue = new ExampleQueue<String>();
    }

    // ----------------------------------------------------------
    // Testing enqueues and dequeues

    /**
     * This method tests basic enqueue behavior
     * 
     * This test relies on getFront, clear, and size
     */
    public void testEnqueue()
    {
        assertEquals(0, smallQueue.size(), .01);
        assertEquals(0, largeQueue.size(), .01);

        // enqueueing a first entry
        smallQueue.enqueue("A is for Array");
        assertEquals(1, smallQueue.size(), .01);
        assertTrue(smallQueue.getFront().equals("A is for Array"));

        // enqueueing a second entry
        largeQueue.enqueue("A is for Array");
        largeQueue.enqueue("B is for Boolean");
        assertEquals(2, largeQueue.size(), .01);
        assertTrue(largeQueue.getFront().equals("A is for Array"));

        largeQueue.clear();

        // test for very large sizes in case an array was used and expanded
        for (int i = 0; i < 50; i++)
        {
            // This pushes the binary equivalent of the decimal i
            largeQueue.enqueue(Integer.toBinaryString(i) + " is for " + i);
        }
        assertEquals(50, largeQueue.size(), 01);
        assertFalse(largeQueue.isEmpty());
        // Pop them all to be sure the order is correct
        for (int i = 0; i < 50; i++)
        {
            assertTrue(largeQueue.dequeue()
                    .equals(Integer.toBinaryString(i) + " is for " + i));
        }
        assertTrue(largeQueue.isEmpty());
    }

    /**
     * This method tests whether enqueue throws a proper exception for null
     * params
     */
    public void testEnqueueException()
    {
        String nullString = null;
        Exception enqueueException = null;

        // Test it with an empty queue
        try
        {
            smallQueue.enqueue(nullString);
        }
        catch (Exception caughtException)
        {
            enqueueException = caughtException;
        }
        assertNotNull(enqueueException);
        assertTrue(enqueueException instanceof IllegalArgumentException);

        // Reusing the same exception, so null it
        enqueueException = null;
        // Test it with a queue that already has a value
        largeQueue.enqueue("A is for Array");
        try
        {
            largeQueue.enqueue(nullString);
        }
        catch (Exception caughtException)
        {
            enqueueException = caughtException;
        }
        assertNotNull(enqueueException);
        assertTrue(enqueueException instanceof IllegalArgumentException);
    }

    /**
     * This method tests whether dequeue works for a queue with just one element
     * and for a queue that has more than one.
     * 
     * This test relies on size, getFront and toString
     */
    public void testDequeue()
    {
        // dequeue one entry
        smallQueue.enqueue("A is for Array");
        assertEquals(1, smallQueue.size(), .01);
        assertTrue(smallQueue.dequeue().equals("A is for Array"));
        assertEquals(0, smallQueue.size(), .01);

        // dequeueing a second entry
        largeQueue.enqueue("A is for Array");
        largeQueue.enqueue("B is for Boolean");
        assertEquals(2, largeQueue.size(), .01);
        assertTrue(largeQueue.dequeue().equals("A is for Array"));
        assertFalse(largeQueue.getFront().equals("A is for Array"));
        assertTrue(largeQueue.getFront().equals("B is for Boolean"));

        // test that enqueue works after dequeue
        largeQueue.dequeue();
        largeQueue.enqueue("A is for Array");
        largeQueue.enqueue("B is for Boolean");
        largeQueue.enqueue("C is for Constructor");
        largeQueue.enqueue("D is for Dynamic Memory");
        largeQueue.dequeue();
        largeQueue.dequeue();
        assertTrue(largeQueue.getFront().equals("C is for Constructor"));
        largeQueue.enqueue("E is for Exception");
        // Testing the whole thing using the toString
        assertTrue(largeQueue.toString().equals("[C is for Constructor, "
                + "D is for Dynamic Memory, E is for Exception]"));
    }

    /**
     * This method tests whether dequeue throws a proper exception when empty
     * 
     * This test relies on enqueue and dequeue
     */
    public void testDequeueException()
    {
        Exception dequeueException = null;

        // Test it with an empty queue
        try
        {
            smallQueue.dequeue();
        }
        catch (Exception caughtException)
        {
            dequeueException = caughtException;
        }
        assertNotNull(dequeueException);
        assertTrue(dequeueException instanceof EmptyQueueException);

        // Reusing the same exception, so null it
        dequeueException = null;

        // Test it with a queue that has had values added then removed
        largeQueue.enqueue("A is for Array");
        largeQueue.dequeue();
        try
        {
            largeQueue.dequeue();
        }
        catch (Exception caughtException)
        {
            dequeueException = caughtException;
        }
        assertNotNull(dequeueException);
        assertTrue(dequeueException instanceof EmptyQueueException);
    }

    // ----------------------------------------------------------
    // Testing simpler methods

    /**
     * This method tests whether getFront works for a queue with just one
     * element or for a queue with more than one. It also tests whether getFront
     * works after dequeueing.
     * 
     * This test relies on enqueue and dequeue
     */
    public void testGetFront()
    {
        // Test it for one item
        smallQueue.enqueue("A is for Array");
        assertTrue(smallQueue.getFront().equals("A is for Array"));
        // Test to be sure it doesn't change after one call
        assertTrue(smallQueue.getFront().equals("A is for Array"));

        // Test it for multiple items
        largeQueue.enqueue("A is for Array");
        assertTrue(largeQueue.getFront().equals("A is for Array"));
        largeQueue.enqueue("B is for Boolean");
        assertTrue(largeQueue.getFront().equals("A is for Array"));
        largeQueue.enqueue("C is for Constructor");
        assertTrue(largeQueue.getFront().equals("A is for Array"));

        // Test it after dequeueing
        largeQueue.dequeue();
        assertTrue(largeQueue.getFront().equals("B is for Boolean"));
        largeQueue.dequeue();
        assertTrue(largeQueue.getFront().equals("C is for Constructor"));
    }

    /**
     * This method tests whether getFront throws a proper exception when empty
     * 
     * This test relies on enqueue and dequeue
     */
    public void testGetFrontException()
    {
        Exception getFrontException = null;

        // Test it with an empty queue
        try
        {
            smallQueue.getFront();
        }
        catch (Exception caughtException)
        {
            getFrontException = caughtException;
        }
        assertNotNull(getFrontException);
        assertTrue(getFrontException instanceof EmptyQueueException);

        // Reusing the same exception, so null it
        getFrontException = null;

        // Test it with a queue that has had values added then removed
        largeQueue.enqueue("A is for Array");
        largeQueue.dequeue();
        try
        {
            largeQueue.getFront();
        }
        catch (Exception caughtException)
        {
            getFrontException = caughtException;
        }
        assertNotNull(getFrontException);
        assertTrue(getFrontException instanceof EmptyQueueException);
    }

    /**
     * This method tests whether the clear method properly returns the queue to
     * an empty queue state.
     * 
     * This test relies on isEmpty, size, and enqueue
     */
    public void testClear()
    {
        // Clear a just-made queue
        smallQueue.clear();
        assertTrue(smallQueue.isEmpty());
        assertEquals(0, smallQueue.size(), .01);

        // Clear a queue with some entries
        smallQueue.enqueue("A is for Array");
        smallQueue.enqueue("B is for Boolean");
        smallQueue.clear();
        assertTrue(smallQueue.isEmpty());
        assertEquals(0, smallQueue.size(), .01);

        // Clear a queue which has had its size expanded
        // For loop for speed
        for (int i = 0; i < 10; i++)
        {
            // This enqueues the binary equivalent of the decimal i
            largeQueue.enqueue(Integer.toBinaryString(i) + " is for " + i);
        }

        // This enqueue should expand the array
        largeQueue.enqueue(Integer.toBinaryString(10) + " is for 10");
        largeQueue.clear();
        assertTrue(largeQueue.isEmpty());
        assertEquals(0, largeQueue.size(), .01);

        // Be sure we can enqueue onto a previously cleared queue
        largeQueue.enqueue("A is for Array");
        assertTrue(largeQueue.getFront().equals("A is for Array"));
        largeQueue.enqueue("B is for Boolean");
        assertTrue(largeQueue.getFront().equals("A is for Array"));
    }

    // size() has been tested extensively in other tests

    // isEmpty() has been tested extensively in other tests

    // ----------------------------------------------------------
    // Testing toString, toArray, and equals

    /**
     * This method tests whether the toString of the queue returns the properly
     * formatted string. The formatting should be square brackets around a list
     * of each item with commas between them.
     * 
     * This test relies on enqueue
     */
    public void testToString()
    {
        // Empty queue
        assertTrue(smallQueue.toString().equals("[]"));
        assertTrue(smallQueue.toString().equals("[]"));

        // Some entries
        smallQueue.enqueue("A is for Array");
        smallQueue.enqueue("B is for Boolean");
        assertTrue(smallQueue.toString()
                .equals("[A is for Array, B is for Boolean]"));

        // Test that toString works on queues that have had dequeues happen
        largeQueue.enqueue("A is for Array");
        largeQueue.enqueue("B is for Boolean");
        largeQueue.enqueue("C is for Constructor");
        largeQueue.enqueue("D is for Dynamic Memory");
        largeQueue.dequeue();
        assertTrue(largeQueue.toString().equals("[B is for Boolean, "
                + "C is for Constructor, D is for Dynamic Memory]"));
    }

    /**
     * This method test whether the toArray method of the queue returns a proper
     * array of its contents. The array should be exactly as big as however many
     * elements it has. The top of the queue should be the last index in the
     * array.
     * 
     * This test relies on enqueue, dequeue, and size
     */
    public void testToArray()
    {
        // Temp variable to reuse for each toArray call
        Object[] toArrayValues;

        // Test a queue with one element
        smallQueue.enqueue("A is for Array");
        toArrayValues = smallQueue.toArray();
        assertEquals(1, toArrayValues.length, .01);
        assertTrue(toArrayValues[0].equals("A is for Array"));

        // Test a queue which has had some elements enqueueed and dequeueped
        largeQueue.enqueue("A is for Array");
        largeQueue.enqueue("B is for Boolean");
        largeQueue.enqueue("C is for Constructor");
        largeQueue.dequeue();
        toArrayValues = largeQueue.toArray();
        assertEquals(2, toArrayValues.length, .01);
        assertTrue(toArrayValues[0].equals("B is for Boolean"));
        assertTrue(toArrayValues[1].equals("C is for Constructor"));

        // Test a queue which is the full size of the queue capacity
        smallQueue.enqueue("B is for Boolean"); // smallqueue is only 2 elements
                                                // big
        toArrayValues = smallQueue.toArray();
        assertEquals(2, toArrayValues.length, .01);
        assertTrue(toArrayValues[0].equals("A is for Array"));
        assertTrue(toArrayValues[1].equals("B is for Boolean"));

        // Test a queue which has expanded
        smallQueue.enqueue("C is for Constructor"); // smallqueue is only 2
                                                    // elements big
        toArrayValues = smallQueue.toArray();
        assertEquals(3, toArrayValues.length, .01);
        assertTrue(toArrayValues[0].equals("A is for Array"));
        assertTrue(toArrayValues[1].equals("B is for Boolean"));
        assertTrue(toArrayValues[2].equals("C is for Constructor"));
    }

    /**
     * This method tests whether toArray throws a proper exception when empty
     * 
     * This test relies on enqueue and dequeue
     */
    public void testToArrayException()
    {
        Exception toArrayException = null;

        // Test it with an empty queue
        try
        {
            smallQueue.toArray();
        }
        catch (Exception caughtException)
        {
            toArrayException = caughtException;
        }
        assertNotNull(toArrayException);
        assertTrue(toArrayException instanceof EmptyQueueException);

        // Reusing the same exception, so null it
        toArrayException = null;

        // Test it with a queue that has had values added then removed
        largeQueue.enqueue("A is for Array");
        largeQueue.dequeue();
        try
        {
            largeQueue.toArray();
        }
        catch (Exception caughtException)
        {
            toArrayException = caughtException;
        }
        assertNotNull(toArrayException);
        assertTrue(toArrayException instanceof EmptyQueueException);
    }

    /**
     * This method tests whether the equals method for the queue properly
     * returns whether two queues are equal or not. It tests for giving null
     * values, the wrong class, queues of different sizes, and queues that are
     * or are not identical of the same size.
     * 
     * This test relies on enqueue and dequeue
     */
    public void testEquals()
    {
        // Test null value and non-queue class
        assertFalse(smallQueue.equals(null));
        assertFalse(smallQueue.equals("[]"));

        // Test empty queues
        assertTrue(smallQueue.equals(largeQueue));

        // Test empty queue which has had one removed
        largeQueue.enqueue("A is for Array");
        assertFalse(largeQueue.equals(smallQueue));
        largeQueue.dequeue();
        assertTrue(largeQueue.equals(smallQueue));

        // Test same queue
        assertTrue(smallQueue.equals(smallQueue));
        smallQueue.enqueue("A is for Array");
        assertTrue(smallQueue.equals(smallQueue));

        // Test same elements in different order
        largeQueue.enqueue("B is for Boolean");
        largeQueue.enqueue("A is for Array");
        smallQueue.enqueue("B is for Boolean");
        assertFalse(largeQueue.equals(smallQueue));
    }
}
