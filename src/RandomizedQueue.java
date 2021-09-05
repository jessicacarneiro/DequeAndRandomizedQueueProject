import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private ArrayList<Item> queue;

    public RandomizedQueue() {
        this.queue = new ArrayList<>();
    }

    public boolean isEmpty() {
        return this.size() == 0;
    }

    public int size() {
        return this.queue.size();
    }

    public void enqueue(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }

        this.queue.add(item);
    }

    public Item dequeue() {
        if (this.isEmpty()) {
            throw new NoSuchElementException();
        }

        int index = StdRandom.uniform(this.size());

        return this.queue.remove(index);
    }

    public Item sample() {
        if (this.isEmpty()) {
            throw new NoSuchElementException();
        }

        int index = StdRandom.uniform(this.size());

        return this.queue.get(index);
    }

    public Iterator<Item> iterator() {
        StdRandom.shuffle(queue.toArray());

        return new Iterator<Item>() {

            private Iterator<Item> i = queue.iterator();

            @Override
            public Item next() {
                if (!i.hasNext()) {
                    throw new NoSuchElementException();
                }
                return i.next();
            }

            @Override
            public void remove() {
                if (!i.hasNext()) {
                    throw new NoSuchElementException();
                }

                i.next();
                i.remove();
            }

            @Override
            public boolean hasNext() {
                return i.hasNext();
            }
        };
    }
}
