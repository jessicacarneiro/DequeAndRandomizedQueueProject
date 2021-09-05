import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private ArrayList<Item> queue;

    public Deque() {
        this.queue = new ArrayList<Item>();
    }

    public boolean isEmpty() {
        return this.queue.size() == 0;
    }

    public int size() {
        return this.queue.size();
    }

    public void addFirst(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }

        this.queue.add(0, item);
    }

    public void addLast(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }

        this.queue.add(item);
    }

    public Item removeFirst() {
        if (this.isEmpty()) {
            throw new NoSuchElementException();
        }

        return this.queue.remove(0);
    }

    public Item removeLast() {
        if (this.isEmpty()) {
            throw new NoSuchElementException();
        }

        int last = this.queue.size() - 1;
        return this.queue.remove(last);
    }

    public Iterator<Item> iterator() {
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
                throw new UnsupportedOperationException();
            }

            @Override
            public boolean hasNext() {
                return i.hasNext();
            }
        };
    }
}