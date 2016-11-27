package datastructures;

import bag.Node;
import queue.EmptyQueueException;

public class ExampleQueue<T> implements QueueGolfADT<T>
{
    Node<T> front, back = null;
    int size = 0;

    public ExampleQueue()
    {
        front = back = new Node<T>(null);
    }

    @Override
    public boolean isEmpty()
    {
        return front == back;
    }

    @Override
    public T getFront()
    {
        if (size == 0)
        {
            throw new EmptyQueueException();
        }
        return front.next().data();
    }

    @Override
    public T dequeue()
    {
        T data = getFront();
        if (size == 1)
        {
            back = front;
        }
        front.setNext(front.next().next());
        size--;
        return data;
    }

    @Override
    public void enqueue(T item)
    {
        if (item == null)
        {
            throw new IllegalArgumentException();
        }
        back.setNext(new Node<T>(item, back.next()));
        back = back.next();
        size++;
    }

    @Override
    public int size()
    {
        return size;
    }

    @Override
    public void clear()
    {
        front.setNext(null);
        back = front;
        size = 0;
    }

    @Override
    public Object[] toArray()
    {
        getFront();
        T[] queueArray = (T[]) new Object[size];
        Node<T> current = front.next();
        for (int i = 0; i < size; i++)
        {
            queueArray[i] = current.data();
            current = current.next();
        }
        return queueArray;
    }

    @Override
    public String toString()
    {
        if (front.next() == null)
        {
            return "[]";
        }
        String returnString = front.next().data().toString();
        Node<T> current = front.next();
        while (current.next() != null)
        {
            current = current.next();
            returnString += ", " + current.data();
        }
        return "[" + returnString + "]";
    }

    @Override
    public boolean equals(Object other)
    {
        if (other == null || other.getClass() != this.getClass())
        {
            return false;
        }
        return this.toString().equals(other.toString());
    }
}