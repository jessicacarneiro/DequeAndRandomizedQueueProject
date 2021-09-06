import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private Item[] queue;
    private int queueSize;

    public Deque() {
        this.queue = (Item[]) new Object[0];
        this.queueSize = 0;
    }

    public boolean isEmpty() {
        return this.queueSize == 0;
    }

    public int size() {
        return this.queueSize;
    }

    public void addFirst(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }

        Item[] newQueue = (Item[]) new Object[this.size() + 1];
        newQueue[0] = item;

        int j = 1;
        for (int i = 0; i < this.size(); i++) {
            newQueue[j] = this.queue[i];
            j++;
        }

        this.queueSize += 1;
        this.queue = newQueue;
    }

    public void addLast(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }

        Item[] newQueue = (Item[]) new Object[this.size() + 1];

        for (int i = 0; i < this.size(); i++) {
            newQueue[i] = this.queue[i];
        }

        newQueue[this.size()] = item;

        this.queueSize += 1;
        this.queue = newQueue;
    }

    public Item removeFirst() {
        if (this.isEmpty()) {
            throw new NoSuchElementException();
        }

        Item itemToBeRemoved = this.queue[0];
        Item[] newQueue = (Item[]) new Object[this.size() - 1];

        for (int i = 1; i < this.size(); i++) {
            newQueue[i - 1] = this.queue[i];
        }

        this.queueSize -= 1;
        this.queue = newQueue;

        return itemToBeRemoved;
    }

    public Item removeLast() {
        if (this.isEmpty()) {
            throw new NoSuchElementException();
        }

        Item itemToBeRemoved = this.queue[this.size() - 1];
        Item[] newQueue = (Item[]) new Object[this.size() - 1];

        for (int i = 0; i < this.size() - 1; i++) {
            newQueue[i] = this.queue[i];
        }

        this.queueSize -= 1;
        this.queue = newQueue;

        return itemToBeRemoved;
    }

    public Iterator<Item> iterator() {
        return new Iterator<Item>() {
            private int cursor = queueSize == 0 ? -1 : 0;

            @Override
            public Item next() {
                if (!hasNext()) { throw new NoSuchElementException(); }

                int currentIndex = cursor;
                incrementCursor();

                return queue[currentIndex];
            }

            private void incrementCursor() {
                if (++cursor == queueSize) { cursor = -1; }
            }

            @Override
            public void remove() { throw new UnsupportedOperationException(); }

            @Override
            public boolean hasNext() { return cursor != -1; }
        };
    }

    /*
     empty main - REQUIRED!
     */
    public static void main(String[] args) {

    }
}