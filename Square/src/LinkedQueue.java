import java.util.NoSuchElementException;

class LinkedQueue<T> implements QueueADT<T> {
   
   private QueueNode front=null, rear=null;
   
   class QueueNode {
      T data;
      QueueNode next;
      
      QueueNode(T data, QueueNode next){
         this.data = data;
         this.next = next;
      }
   }
   
   public boolean isEmpty(){
      return front == null;
   }
   
   public void enqueue(T item){
      if(front==null){
         front = rear = new QueueNode(item, null);
      }
      else {
         rear = rear.next = new QueueNode(item, null);
      }
   }
   
   public T dequeue(){
      if(front==null){
         throw new NoSuchElementException("Dequeueing from an empty queue");
      }
      else {
         T item = front.data;
         front = front.next;
         if(front==null)
            rear = null;
         return item;
      }
   }
   
   public T peek(){
      if(front==null)
         throw new NoSuchElementException("Peeking at an empty queue");
      else {
         T item = front.data;
         return item;
      }
   }
   
   public void clear(){
      front = rear = null;
   }
}
      