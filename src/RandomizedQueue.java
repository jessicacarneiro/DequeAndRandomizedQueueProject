import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdRandom;

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
        int[] tempIndexArray = null;

        if (indexArray != null) {
            tempIndexArray = Arrays.copyOf(indexArray, indexArray.length);
            StdRandom.shuffle(tempIndexArray);
        }

        int[] finalTempIndexArray = tempIndexArray;

        return new Iterator<Item>() {

            private int cursor = 0;
            private int cursorPosition = 0;

            @Override
            public Item next() {
                if (finalTempIndexArray == null || cursorPosition == indexArray.length) {
                    throw new NoSuchElementException();
                }

                Node node = firstNode;

                for (int i = 0; i < cursor; i++) {
                    node = node.getNext();
                }

                if (cursorPosition < indexArray.length - 1) {
                    cursor = finalTempIndexArray[cursorPosition + 1];
                }

                cursorPosition += 1;

                return (Item) node.getItem();
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }

            @Override
            public boolean hasNext() {
                return finalTempIndexArray != null && cursorPosition < indexArray.length;
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

    private void resizeIndexArray(int newSize) {
        int[] newIndexArray = new int[newSize];

        if (newSize > this.size()) {
            for (int i = 0; i < this.size(); i++) {
                newIndexArray[i] = this.indexArray[i];
            }
            newIndexArray[this.size()] = this.size();
        }
        else {
            for (int i = 1; i < this.size(); i++) {
                newIndexArray[i-1] = this.indexArray[i];
            }
        }

        this.indexArray = newIndexArray;
    }
}
