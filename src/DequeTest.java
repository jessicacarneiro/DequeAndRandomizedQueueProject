import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class DequeTest {

    @Test
    void shouldReturnTrueIfDequeIsEmpty() {
        Deque<Item> deque = new Deque<>();

        assertTrue(deque.isEmpty());
    }

    @Test
    void shouldReturnFalseIfDequeIsNotEmpty() {
        Deque<Item> deque = new Deque<>();
        deque.addFirst(new Item());

        assertFalse(deque.isEmpty());
    }

    @Test
    void shouldReturnSizeZeroIfDequeIsEmpty() {
        Deque<Item> deque = new Deque<>();

        assertEquals(0, deque.size());
    }

    @Test
    void shouldThrowIllegalArgumentExceptionIfAddFirstWithNullElement() {
        Deque<Item> deque = new Deque<>();

        assertThrows(IllegalArgumentException.class, () -> {
            deque.addFirst(null);
        });
    }

    @Test
    void shouldThrowIllegalArgumentExceptionIfAddLastWithNullElement() {
        Deque<Item> deque = new Deque<>();

        assertThrows(IllegalArgumentException.class, () -> {
            deque.addLast(null);
        });
    }

    @Test
    void shouldAddElementToFirstPosition() {
        Deque<Item> deque = new Deque<>();
        Item firstItem = new Item();
        Item secondItem = new Item();

        deque.addFirst(firstItem);
        deque.addFirst(secondItem);

        assertEquals(secondItem, deque.removeFirst());
    }

    @Test
    void shouldAddElementToLastPosition() {
        Deque<Item> deque = new Deque<>();
        Item firstItem = new Item();
        Item secondItem = new Item();

        deque.addLast(firstItem);
        deque.addLast(secondItem);

        assertEquals(secondItem, deque.removeLast());
    }

    @Test
    void shouldThrowNoSuchElementExceptionIfRemoveFirstWhenQueueIsEmpty() {
        Deque<Item> deque = new Deque<>();

        assertThrows(NoSuchElementException.class, () -> {
            deque.removeFirst();
        });
    }

    @Test
    void shouldThrowNoSuchElementExceptionIfRemoveLastWhenQueueIsEmpty() {
        Deque<Item> deque = new Deque<>();

        assertThrows(NoSuchElementException.class, () -> {
            deque.removeLast();
        });
    }

    @Test
    void shouldRemoveLastElementWhenCallRemoveLast() {
        Deque<Item> deque = new Deque<>();
        Item firstItem = new Item();
        Item secondItem = new Item();

        deque.addLast(firstItem);
        deque.addFirst(secondItem);

        assertEquals(firstItem, deque.removeLast());
    }

    @Test
    void shouldRemoveFirstElementWhenCallRemoveFirst() {
        Deque<Item> deque = new Deque<>();
        Item firstItem = new Item();
        Item secondItem = new Item();

        deque.addLast(firstItem);
        deque.addFirst(secondItem);

        assertEquals(secondItem, deque.removeFirst());
    }

    @Test
    void shouldNoSuchElementExceptionIfCallingRemoveInIteratorWhenDequeIsEmpty() {
        Deque<Item> deque = new Deque<>();
        Iterator<Item> iterator = deque.iterator();

        assertThrows(NoSuchElementException.class, () -> { iterator.next(); });
    }

    @Test
    void shouldReturnTrueIfDequeHasNextElementWhenCallingNextMethodFromIterator() {
        Deque<Item> deque = new Deque<>();
        deque.addFirst(new Item());
        Iterator<Item> iterator = deque.iterator();

        assertTrue(iterator.hasNext());
    }

    @Test
    void shouldReturnFalseIfDequeIsEmptyWhenCallingNextMethodFromIterator() {
        Deque<Item> deque = new Deque<>();
        Iterator<Item> iterator = deque.iterator();

        assertFalse(iterator.hasNext());
    }

    @Test
    void shouldGetNextElementWithNextMethodFromIterator() {
        Deque<Item> deque = new Deque<>();
        Item item = new Item();
        deque.addFirst(item);

        Iterator<Item> iterator = deque.iterator();

        assertEquals(item, iterator.next());
    }

    @Test
    void shouldThrowUnsupportedOperationExceptionWhenCallingRemoveMethodFromIterator() {
        Deque<Item> deque = new Deque<>();
        deque.addFirst(new Item());
        Iterator<Item> iterator = deque.iterator();

        assertThrows(UnsupportedOperationException.class, () -> { iterator.remove(); });
    }
}
