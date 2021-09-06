import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private Item[] queue;
    private int queueSize;

    public RandomizedQueue() {
        this.queueSize = 0;
    }

    public boolean isEmpty() {
        return this.size() == 0;
    }

    public int size() {
        return this.queueSize;
    }

    public void enqueue(Item item) {
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

    public Item dequeue() {
        if (this.isEmpty()) {
            throw new NoSuchElementException();
        }

        int index = StdRandom.uniform(this.size());

        Item itemToBeRemoved = this.queue[index];
        Item[] newQueue = (Item[]) new Object[this.size() - 1];

        int j = 0;
        for (int i = 0; i < this.size(); i++) {
            if (i != index) {
                newQueue[j] = this.queue[i];
                j++;
            }
        }

        this.queueSize -= 1;
        this.queue = newQueue;

        return itemToBeRemoved;
    }

    public Item sample() {
        if (this.isEmpty()) {
            throw new NoSuchElementException();
        }

        int index = StdRandom.uniform(this.size());

        return this.queue[index];
    }

    public Iterator<Item> iterator() {
        Item[] shuffledQueue = createShuffledCopy(queue);

        return new Iterator<Item>() {
            private int cursor = queueSize == 0 ? -1 : 0;

            @Override
            public Item next() {
                if (!hasNext()) { throw new NoSuchElementException(); }

                int currentIndex = cursor;
                incrementCursor();

                return shuffledQueue[currentIndex];
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

    private Item[] createShuffledCopy(Item[] queue) {
        Item[] queueCopy = null;

        if (queueSize > 0) {
            queueCopy = (Item[]) new Object[queue.length];

            for (int i = 0; i < queue.length; i++) {
                queueCopy[i] = queue[i];
            }

            StdRandom.shuffle(queueCopy);
        }

        return queueCopy;
    }

    /*
     empty main - REQUIRED!
     */
    public static void main(String[] args) {

    }
}
