import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private Node firstNode = null;
    private Node lastNode = null;
    private int[] indexArray;
    private int queueSize;

    public RandomizedQueue() {
        this.queueSize = 0;
    }

    public boolean isEmpty() {
        return this.queueSize == 0;
    }

    public int size() {
        return this.queueSize;
    }

    public void enqueue(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }

        if (this.isEmpty()) {
            firstNode = new Node(item);
            lastNode = firstNode;

            this.indexArray = new int[1];
            this.indexArray[0] = 0;
        } else {
            Node newNode = new Node(item);
            lastNode = newNode;
        }

        resizeIndexArray(this.size() + 1);

        this.queueSize += 1;
    }

    public Item dequeue() {
        if (this.isEmpty()) {
            throw new NoSuchElementException();
        }

        int index = StdRandom.uniform(this.size());
        Node nodeToRemove = this.firstNode;

        // if single node, lastNode should point to the same place as firstNode (null)
        if (lastNode == nodeToRemove) {
            firstNode = firstNode.getNext();
            lastNode = firstNode.getNext();
        } else {
            for (int i = 0; i < index - 1; i++) {
                nodeToRemove = nodeToRemove.getNext();
            }
            nodeToRemove.next = nodeToRemove.getNext().getNext();
            nodeToRemove = nodeToRemove.getNext();
        }

        resizeIndexArray(this.size() - 1);
        this.queueSize -= 1;

        return (Item) nodeToRemove.getItem();
    }

    public Item sample() {
        if (this.isEmpty()) {
            throw new NoSuchElementException();
        }

        int index = StdRandom.uniform(this.size());

        Node sampledNode = this.firstNode;

        for (int i = 0; i < index; i++) {
            sampledNode = sampledNode.getNext();
        }

        return (Item) sampledNode.getItem();
    }

    public Iterator<Item> iterator() {
        int[] tempIndexArray = generateShuffledIndexArray(indexArray);

        return new Iterator<Item>() {
            private int cursor = 0;
            private int cursorPosition = 0;

            @Override
            public Item next() {
                if (tempIndexArray == null || cursorPosition == indexArray.length) {
                    throw new NoSuchElementException();
                }

                Item item = findNode(firstNode, cursorPosition);
                if (cursorPosition < indexArray.length - 1) {
                    cursor = tempIndexArray[cursorPosition + 1];
                }
                cursorPosition += 1;

                return item;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }

            @Override
            public boolean hasNext() {
                return tempIndexArray != null && cursorPosition < indexArray.length;
            }
        };
    }

    private void resizeIndexArray(int newSize) {
        int[] newIndexArray = new int[newSize];

        if (newSize > this.size()) {
            for (int i = 0; i < this.size(); i++) {
                newIndexArray[i] = this.indexArray[i];
            }
            newIndexArray[this.size()] = this.size();
        } else {
            for (int i = 1; i < this.size(); i++) {
                newIndexArray[i - 1] = this.indexArray[i];
            }
        }

        this.indexArray = newIndexArray;
    }

    private int[] copyArray(int[] oldArray) {
        int[] newArray = new int[oldArray.length];

        for (int i = 0; i < oldArray.length; i++) {
            newArray[i] = oldArray[i];
        }

        return newArray;
    }

    private int[] generateShuffledIndexArray(int[] oldIndexArray) {
        if (oldIndexArray != null) {
            int[] tempIndexArray = copyArray(indexArray);
            StdRandom.shuffle(tempIndexArray);
            return tempIndexArray;
        }

        return null;
    }

    private Item findNode(Node firstNode, int position) {
        Node node = firstNode;

        for (int i = 0; i < position; i++) {
            node = node.getNext();
        }

        return (Item) node;
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

    // empty required method
    public static void main(String[] args) {

    }
}
