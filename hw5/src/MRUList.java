import java.util.Iterator;
import java.util.LinkedList;

public class MRUList<T> extends DoublyLinkedList<T>  {
    public boolean add(T x){
        if (size == 0) {
            header = trailer = new ListNode(x);
            size++;
            return true;
        }
        super.add(0,x);
        return true;
    }


    public void add(int index, T x){
        super.add(0,x);
    }

    public boolean contains(Object obj){
        for (Iterator<T> it = iterator(); it.hasNext();){
            T item = it.next();
            if (obj.equals(item)){
                it.remove();
                add(item);
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        MRUList<Integer> list = new MRUList<Integer>();
        list.add(4);
        list.add(5);
        list.add(2);
        list.add(1);
        list.add(7);
        list.add(3);
        list.add(9);
        System.out.println(list);

        System.out.println(list.contains(0));
        System.out.println(list);
    }

}
