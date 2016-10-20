package data_structures.links_based;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Stack<Item> implements Iterable<Item> {

    private Node<Item> mFirst;
    private int mSize;

    public Stack() {
        mFirst = null;
        mSize = 0;
    }

    public boolean isEmpty() {
        return mSize == 0;
    }

    public int size() {
        return mSize;
    }

    public void push(Item item) {
        Node<Item> oldFirst = mFirst;
        mFirst = new Node<Item>();
        mFirst.item = item;
        mFirst.next = oldFirst;
        ++mSize;
    }

    public Item pop() {
        if (isEmpty()) {
            throw new NoSuchElementException("Stack underflow");
        }
        Item item = mFirst.item;
        mFirst = mFirst.next;
        --mSize;
        return item;
    }

    //pop without removing:
    public Item peek() {
        if (isEmpty()) {
            throw new NoSuchElementException("Stack underflow");
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