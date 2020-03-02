import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {
    
    private ArrayList<Item> queue;

    // construct an empty randomized queue
    public RandomizedQueue() {
        this.queue = new ArrayList<>();
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return this.size() == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return this.queue.size();
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        
        this.queue.add(item);
    }

    // remove and return a random item
    public Item dequeue() {
        if (this.isEmpty()) {
            throw new NoSuchElementException();
        }
        
        int index = StdRandom.uniform(this.size());
        
        return this.queue.remove(index);
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (this.isEmpty()) {
            throw new NoSuchElementException();
        }
        
        int index = StdRandom.uniform(this.size());
        
        return this.queue.get(index);
    }

    // return an independent iterator over items in random order
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
