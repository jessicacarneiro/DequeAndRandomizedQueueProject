import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<T> implements Iterable<T> {

    private ArrayList<T> queue;

    public Deque() {
        this.queue = new ArrayList<T>();
    }

    public boolean isEmpty() {
        return this.queue.size() == 0;
    }

    public int size() {
        return this.queue.size();
    }

    public void addFirst(T item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }

        this.queue.add(0, item);
    }

    public void addLast(T item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }

        this.queue.add(item);
    }

    public T removeFirst() {
        if (this.isEmpty()) {
            throw new NoSuchElementException();
        }

        return this.queue.remove(0);
    }

    public T removeLast() {
        if (this.isEmpty()) {
            throw new NoSuchElementException();
        }

        int last = this.queue.size() - 1;
        return this.queue.remove(last);
    }

    public Iterator<T> iterator() {
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