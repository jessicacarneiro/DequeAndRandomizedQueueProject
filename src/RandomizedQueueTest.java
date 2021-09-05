import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class RandomizedQueueTest {

    @Test
    void shouldReturnTrueIfRandomizedQueueIsEmpty() {
        RandomizedQueue<Item> randomizedQueue = new RandomizedQueue<>();

        assertTrue(randomizedQueue.isEmpty());
    }

    @Test
    void shouldReturnFalseIfRandomizedQueueIsNotEmpty() {
        RandomizedQueue<Item> randomizedQueue = new RandomizedQueue<>();
        randomizedQueue.enqueue(new Item());

        assertFalse(randomizedQueue.isEmpty());
    }

    @Test
    void shouldReturnSizeZeroIfRandomizedQueueIsEmpty() {
        RandomizedQueue<Item> randomizedQueue = new RandomizedQueue<>();

        assertEquals(0, randomizedQueue.size());
    }

    @Test
    void shouldReturnSizeTwoWhenDequeHasTwoElements() {
        RandomizedQueue<Item> randomizedQueue = new RandomizedQueue<>();
        randomizedQueue.enqueue(new Item());
        randomizedQueue.enqueue(new Item());

        assertEquals(2, randomizedQueue.size());
    }

    @Test
    void shouldThrowIllegalArgumentExceptionIfEnqueueCalledWithNullElement() {
        RandomizedQueue<Item> randomizedQueue = new RandomizedQueue<>();

        assertThrows(IllegalArgumentException.class, () -> {
            randomizedQueue.enqueue(null);
        });
    }

    @Test
    void shouldThrowNoSuchElementExceptionIfDequeueCalledWhenRandomizedQueueIsEmpty() {
        RandomizedQueue<Item> randomizedQueue = new RandomizedQueue<>();

        assertThrows(NoSuchElementException.class, () -> {
            randomizedQueue.dequeue();
        });
    }

    @Test
    void shouldThrowNoSuchElementExceptionIfSampleCalledWhenRandomizedQueueIsEmpty() {
        RandomizedQueue<Item> randomizedQueue = new RandomizedQueue<>();

        assertThrows(NoSuchElementException.class, () -> {
            randomizedQueue.sample();
        });
    }

    @Test
    void shouldThrowNoSuchElementExceptionIfCallingRemoveInIteratorWhenRandomizedQueueIsEmpty() {
        RandomizedQueue<Item> randomizedQueue = new RandomizedQueue<>();
        Iterator<Item> iterator = randomizedQueue.iterator();

        assertThrows(NoSuchElementException.class, () -> { iterator.next(); });
    }

    @Test
    void shouldReturnTrueIfRandomizedQueueHasNextElementWhenCallingNextMethodFromIterator() {
        RandomizedQueue<Item> randomizedQueue = new RandomizedQueue<>();
        randomizedQueue.enqueue(new Item());
        Iterator<Item> iterator = randomizedQueue.iterator();

        assertTrue(iterator.hasNext());
    }

    @Test
    void shouldReturnFalseIfRandomizedQueueIsEmptyWhenCallingNextMethodFromIterator() {
        RandomizedQueue<Item> randomizedQueue = new RandomizedQueue<>();
        Iterator<Item> iterator = randomizedQueue.iterator();

        assertFalse(iterator.hasNext());
    }

    @Test
    void shouldGetNextElementWithNextMethodFromIterator() {
        RandomizedQueue<Item> randomizedQueue = new RandomizedQueue<>();
        Item item = new Item();
        randomizedQueue.enqueue(item);

        Iterator<Item> iterator = randomizedQueue.iterator();

        assertEquals(item, iterator.next());
    }

    @Test
    void shouldThrowUnsupportedOperationExceptionWhenCallingRemoveMethodFromIterator() {
        RandomizedQueue<Item> randomizedQueue = new RandomizedQueue<>();
        randomizedQueue.enqueue(new Item());
        Iterator<Item> iterator = randomizedQueue.iterator();

        assertThrows(UnsupportedOperationException.class, () -> { iterator.remove(); });
    }
}