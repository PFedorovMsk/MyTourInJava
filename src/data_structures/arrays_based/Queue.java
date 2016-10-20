package data_structures.arrays_based;

import java.util.Iterator;
import java.util.NoSuchElementException;

@SuppressWarnings("unchecked")
public class Queue<Item> implements Iterable<Item> {

    private Item[] mData;
    private int mSize;
    private int mFirst;
    private int mLast;

    public Queue() {
        mData = (Item[]) new Object[2];
        mSize = 0;
        mFirst = 0;
        mLast = 0;
    }

    public boolean isEmpty() {
        return mSize == 0;
    }

    public int size() {
        return mSize;
    }

    private void resize(int newSize) {
        assert newSize >= mSize;
        Item[] tmp = (Item[]) new Object[newSize];
        for (int i = 0; i < mSize; ++i) {
            tmp[i] = mData[(mFirst + i) % mData.length];
        }
        mData = tmp;
        mFirst = 0;
        mLast = mSize;
    }

    public void add(Item item) {
        if (mSize == mData.length) {
            resize(2 * mData.length);
        }
        mData[mLast++] = item;
        if (mLast == mData.length) {
            mLast = 0;
        }
        ++mSize;
    }

    public Item poll() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue underflow");
        }
        Item item = mData[mFirst];
        mData[mFirst++] = null;
        --mSize;
        if (mFirst == mData.length) {
            mFirst = 0;
        }
        if (mSize > 0 && mSize == mData.length / 4) {
            resize(mData.length / 2);
        }
        return item;
    }

    public Item peek() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue underflow");
        }
        return mData[mFirst];
    }

    public Iterator<Item> iterator() {
        return new ArrayIterator();
    }

    private class ArrayIterator implements Iterator<Item> {

        private int i = 0;

        public boolean hasNext() {
            return i < mSize;
        }

        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Item item = mData[(i + mFirst) % mData.length];
            ++i;
            return item;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}
