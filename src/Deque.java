import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private ArrayList<Item> queue;

    // construct an empty deque
    public Deque() {
        this.queue = new ArrayList<Item>();
    }

    // is the deque empty?
    public boolean isEmpty() {
        return this.queue.size() == 0;
    }

    // return the number of items on the deque
    public int size() {
        return this.queue.size();
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }

        this.queue.add(0, item);
    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }

        this.queue.add(item);
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (this.isEmpty()) {
            throw new NoSuchElementException();
        }

        return this.queue.remove(0);
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (this.isEmpty()) {
            throw new NoSuchElementException();
        }

        int last = this.queue.size() - 1;
        return this.queue.remove(last);
    }

    // return an iterator over items in order from front to back
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