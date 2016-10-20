package data_structures.arrays_based;

import java.util.Iterator;
import java.util.NoSuchElementException;

@SuppressWarnings("unchecked")
public class Stack<Item> implements Iterable<Item> {

    private int mSize;
    private Item[] mData;

    public Stack() {
        mSize = 0;
        mData = (Item[]) new Object[2];
    }

    private void resize(int newSize) {
        assert newSize >= mSize;
        Item[] tmp = (Item[]) new Object[newSize];
        System.arraycopy(mData, 0, tmp, 0, mSize);
        mData = tmp;
    }

    public boolean isEmpty() {
        return mSize == 0;
    }

    public int size() {
        return mSize;
    }

    public void push(Item item) {
        if (mSize == mData.length) {
            resize(2 * mData.length);
        }
        mData[mSize++] = item;
    }

    public Item pop() {
        if (isEmpty()) {
            throw new NoSuchElementException("Stack underflow");
        }
        Item item = mData[mSize - 1];
        mData[mSize - 1] = null;
        --mSize;
        if (mSize > 0 && mSize == mData.length / 4) {
            resize(mData.length / 2);
        }
        return item;
    }

    //pop without removing:
    public Item peek() {
        if (isEmpty()) {
            throw new NoSuchElementException("Stack underflow");
        }
        return mData[mSize - 1];
    }

    public Iterator<Item> iterator() {
        return new ArrayIterator();
    }

    private class ArrayIterator implements Iterator<Item> {

        private int i = mSize - 1;

        public boolean hasNext() {
            return i >= 0;
        }

        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return mData[i--];
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}
