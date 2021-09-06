import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<T> implements Iterable<T> {

    private T[] queue;
    private int queueSize;

    public Deque() {
        this.queue = (T[]) new Object[0];
        this.queueSize = 0;
    }

    public boolean isEmpty() {
        return this.queueSize == 0;
    }

    public int size() {
        return this.queueSize;
    }

    public void addFirst(T item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }

        T[] newQueue = (T[]) new Object[this.size() + 1];
        newQueue[0] = item;

        int j = 1;
        for (int i = 0; i < this.size(); i++) {
            newQueue[j] = this.queue[i];
            j++;
        }

        this.queueSize += 1;
        this.queue = newQueue;
    }

    public void addLast(T item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }

        T[] newQueue = (T[]) new Object[this.size() + 1];

        for (int i = 0; i < this.size(); i++) {
            newQueue[i] = this.queue[i];
        }

        newQueue[this.size()] = item;

        this.queueSize += 1;
        this.queue = newQueue;
    }

    public T removeFirst() {
        if (this.isEmpty()) {
            throw new NoSuchElementException();
        }

        T itemToBeRemoved = this.queue[0];
        T[] newQueue = (T[]) new Object[this.size() - 1];

        int j = 0;
        for (int i = 1; i < this.size(); i++) {
            newQueue[j] = this.queue[i];
            j++;
        }

        this.queueSize -= 1;
        this.queue = newQueue;

        return itemToBeRemoved;
    }

    public T removeLast() {
        if (this.isEmpty()) {
            throw new NoSuchElementException();
        }

        T itemToBeRemoved = this.queue[this.size() - 1];
        T[] newQueue = (T[]) new Object[this.size() - 1];

        for (int i = 0; i < this.size() - 1; i++) {
            newQueue[i] = this.queue[i];
        }

        this.queueSize -= 1;
        this.queue = newQueue;

        return itemToBeRemoved;
    }

    public Iterator<T> iterator() {
        return new Iterator<T>() {

            private Iterator<T> i = Arrays.stream(queue).iterator();

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

    public static void main(String[] args) {

    }
}