package data_structures.links_based;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Queue<Item> implements Iterable<Item> {

    private Node<Item> mFirst;
    private Node<Item> mLast;
    private int mSize;

    public Queue() {
        mFirst = null;
        mLast = null;
        mSize = 0;
    }

    public boolean isEmpty() {
        return mSize == 0;
    }

    public int size() {
        return mSize;
    }

    public void add(Item item) {
        Node<Item> oldLast = mLast;
        mLast = new Node<Item>();
        mLast.item = item;
        mLast.next = null;
        if (isEmpty()) {
            mFirst = mLast;
        } else {
            oldLast.next = mLast;
        }
        ++mSize;
    }

    public Item poll() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue underflow");
        }
        Item item = mFirst.item;
        mFirst = mFirst.next;
        --mSize;
        if (isEmpty()) {
            mLast = null;
        }
        return item;
    }

    public Item peek() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue underflow");
        }
        return mFirst.item;
    }

    public Iterator<Item> iterator() {
        return new ListIterator<Item>(mFirst);
    }

    private class Node<Type> {
        Type item;
        Node<Type> next;
    }

    private class ListIterator<Type> implements Iterator<Type> {
        private Node<Type> mCurrent;

        public ListIterator(Node<Type> first) {
            mCurrent = first;
        }

        public boolean hasNext() {
            return mCurrent != null;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Type next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Type item = mCurrent.item;
            mCurrent = mCurrent.next;
            return item;
        }
    }
}

