import java.util.NoSuchElementException;

public interface StackADT<T> {

   void push(T item);
   T pop() throws NoSuchElementException;
   T peek() throws NoSuchElementException;
   boolean isEmpty();
   void clear();
}
