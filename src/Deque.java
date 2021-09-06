import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private Node firstNode = null;
    private Node lastNode = null;
    private int queueSize;

    public Deque() {
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

        if (this.isEmpty()) {
            firstNode = new Node(item);
            lastNode = firstNode;
        }
        else {
            Node newNode = new Node(item, firstNode);
            firstNode = newNode;
        }

        this.queueSize += 1;
    }

    public void addLast(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }

        if (this.isEmpty()) {
            firstNode = new Node(item);
            lastNode = firstNode;
        }
        else {
            Node newNode = new Node(item);
            lastNode = newNode;
        }

        this.queueSize += 1;
    }

    public Item removeFirst() {
        if (this.isEmpty()) {
            throw new NoSuchElementException();
        }

        Item itemToBeRemoved = (Item) this.firstNode.getItem();

        // if single node, lastNode should point to the same place as firstNode (null)
        if (lastNode == firstNode) {
            lastNode = firstNode.getNext();
        }

        firstNode = firstNode.getNext();

        return itemToBeRemoved;
    }

    public Item removeLast() {
        if (this.isEmpty()) {
            throw new NoSuchElementException();
        }

        Item itemToBeRemoved = (Item) this.lastNode.getItem();

        // if single node, firstNode should point to the same place as lastNode (null)
        if (lastNode == firstNode) {
            firstNode = lastNode.getNext();
        }

        lastNode = lastNode.getNext();

        return itemToBeRemoved;
    }

    public Iterator<Item> iterator() {
        return new Iterator<Item>() {

            private Node cursor = firstNode;

            @Override
            public Item next() {
                if (cursor == null || cursor.getItem() == null) {
                    throw new NoSuchElementException();
                }

                Item currentItem = (Item) cursor.getItem();
                cursor = cursor.getNext();

                return currentItem;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }

            @Override
            public boolean hasNext() {
                return cursor != null;
            }
        };
    }

    private class Node<Item> {
        Item item;
        Node next;

        public Node(Item item) {
            this.item = item;
        }

        public Node(Item item, Node next) {
            this.item = item;
            this.next = next;
        }

        public Item getItem() {
            return item;
        }

        public Node getNext() {
            return next;
        }
    }

    public static void main(String[] args) {

    }
}