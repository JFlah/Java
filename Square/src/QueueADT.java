import java.util.NoSuchElementException;

public interface QueueADT<T> {
    void enqueue(T item);
    T dequeue() throws NoSuchElementException;
    T peek() throws NoSuchElementException;
    boolean isEmpty();
    void clear();
}
