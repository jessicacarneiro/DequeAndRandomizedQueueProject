import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<T> implements Iterable<T> {

    private ArrayList<T> queue;

    public RandomizedQueue() {
        this.queue = new ArrayList<>();
    }

    public boolean isEmpty() {
        return this.size() == 0;
    }

    public int size() {
        return this.queue.size();
    }

    public void enqueue(T item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }

        this.queue.add(item);
    }

    public T dequeue() {
        if (this.isEmpty()) {
            throw new NoSuchElementException();
        }

        int index = StdRandom.uniform(this.size());

        return this.queue.remove(index);
    }

    public T sample() {
        if (this.isEmpty()) {
            throw new NoSuchElementException();
        }

        int index = StdRandom.uniform(this.size());

        return this.queue.get(index);
    }

    public Iterator<T> iterator() {
        StdRandom.shuffle(queue.toArray());

        return new Iterator<T>() {

            private Iterator<T> i = queue.iterator();

            @Override
            public T next() {
                if (!i.hasNext()) {
                    throw new NoSuchElementException();
                }
                return i.next();
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }

            @Override
            public boolean hasNext() {
                return i.hasNext();
            }
        };
    }
}
