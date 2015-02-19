import java.util.NoSuchElementException;

class LinkedStack<T> implements StackADT<T> {
   
   private StackNode top=null;
   
   class StackNode {
      T data;
      StackNode next;
      
      StackNode(T data, StackNode next){
         this.data = data;
         this.next = next;
      }
   }
   
   public boolean isEmpty(){
      return top == null;
   }
   
   public void push(T item){
      top = new StackNode(item, top);
   }
   
   public T pop(){
      if(top==null){
         throw new NoSuchElementException("Popping from an empty stack");
      }
      else {
         T item = top.data;
         top = top.next;
         return item;
      }
   }
   
   public T peek(){
      if(top==null)
         throw new NoSuchElementException("Peeking at an empty stack");
      else {
         T item = top.data;
         return item;
      }
   }
   
   public void clear(){
      top = null;
   }
}
      