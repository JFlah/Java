/*
 * DoublyLinkedList
 *
 * This class implements the Java List interface using a doubly-linked list.
 *
 * Two nested classes are included:  ListNode and DoublyLinkedListIterator.
 *
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class DoublyLinkedList<T> extends AbstractList<T> {
    int size;  // number of data items in the list
    ListNode header, trailer;  // sentinel nodes

    /*
     * ListNode
     *
     * This class represents one node in a doubly linked list.
     */
    protected class ListNode {
        T datum;
        ListNode prior, next;

        ListNode(){
            this(null);
        }

        ListNode(T data){
            this(data,null,null);
        }

        ListNode(T data, ListNode prior, ListNode next){
            this.datum = data;
            this.prior = prior;
            this.next = next;
        }
    } // end of class ListNode

    /*
     * Constructs an empty list.
     */
    DoublyLinkedList(){
        size = 0;
        header = new ListNode(null);
        trailer = new ListNode(null);
        header.next = trailer;
        trailer.prior = header;
    }

    /*
     * Returns a reference to the nth node in the list.
     */
    private ListNode getNthNode(int n) throws IndexOutOfBoundsException {
        if (n > size-1 || n < 0){
            throw new IndexOutOfBoundsException("List not that long.");
        }
        ListNode current = header;
        for (int i = 0; i < n; i++) {
            current = current.next;
        }
        return current;
    }

    /*
     * Returns a count of the number of elements in the list.
     */
    public int size() {
        return size;
    }

    /*
     * Returns the data item at the given position in the list.
     */
    public T get(int position) throws IndexOutOfBoundsException {
        if (position > size-1 || position < 0) {
            throw new IndexOutOfBoundsException("List not that long.");
        }

        ListNode pos = getNthNode(position);
        return pos.datum;
    }

    /*
     * Replaces the item at the given position with the
     * given data item.  The return value is the item
     * that is replaced.
     */
    public T set(int position, T data) throws IndexOutOfBoundsException, NullPointerException {
        if (position > size-1 || position < 0) {
            throw new IndexOutOfBoundsException("List not that long.");
        }
        if (data == null) {
            throw new NullPointerException("You need to add some data.");
        }

        ListNode current = header;
        for (int i = 0; i < position; i++) {
            current = current.next;
        }
        T oldData = current.datum;
        current.datum = data;
        return oldData;
    }

    /*
     * Inserts the given data item at the end of the list.
     */
    public boolean add(T data) {
        ListNode newNode = new ListNode(data);
        if (size == 0) {
            header = trailer = newNode;
            size++;
            return true;
        }
        if(size == 1){
            newNode.prior = header;
            header.next = newNode;
            trailer = newNode;
            size++;
            return true;
        }

        ListNode end = trailer;
        end.next = newNode;
        newNode.prior = end;
        trailer = newNode;

        size++;
        return true;
    }

    /*
     * Inserts the given data item at the given position in the list.
     */
    public void add(int position, T data) throws NullPointerException, IndexOutOfBoundsException {
        if (data == null) {
            throw new NullPointerException("Your node has no data.");
        }
        if (position > size || position < 0) {
            throw new IndexOutOfBoundsException("That position is too small/big.");
        }
        if (position == size || size == 0) {
            add(data);
            return;
        }
        ListNode newNode = new ListNode(data);
        if (position == 0) {
            newNode.next = header;
            header.prior = newNode;
            header = newNode;
            size++;
            return;
        }

        ListNode current = getNthNode(position);

        ListNode nodeBefore = current.prior;
        nodeBefore.next = newNode;
        newNode.prior = nodeBefore;
        newNode.next = current;
        current.prior = newNode;


        size++;
    }

    /*
     * Removes the element at a given index in the list.
     */
    public T remove(int index) throws IndexOutOfBoundsException {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException("There's nothing here.");
        }
        ListNode toBeRemoved = getNthNode(index);


        if (index == 0 && size > 1) {
            header = header.next;
            header.prior=null;
            toBeRemoved.next=null;
            size--;
            return toBeRemoved.datum;
        }
        else if (index == 0){
            clear();
            return toBeRemoved.datum;
        }
        if (index == size-1) {
            trailer = trailer.prior;
            toBeRemoved.prior=null;
            trailer.next=null;
            size--;
            return toBeRemoved.datum;
        }

        T returnData = toBeRemoved.datum;

        ListNode priorNode = toBeRemoved.prior;
        ListNode nextNode = toBeRemoved.next;

        priorNode.next = nextNode;
        nextNode.prior = priorNode;

        toBeRemoved.prior = toBeRemoved.next = null;

        size--;

        return returnData;
    }

    /*
     * Searches the list for a given object.  Returns true if found,
     * false otherwise.
     */
    @SuppressWarnings("unchecked")
    public boolean contains(Object o){
        T current;
        T oData = (T) o;
        for (int i = 0; i < size; i++) {
            current = get(i);
            if (current.equals(oData)) {
                return true;
            }
        }
        return false;
    }

    /*
     * Deletes all elements from the list.
     */
    public void clear() {
        header = trailer = null;
        size = 0;
    }

    /*
     * Determines if the list is empty.
     */
    public boolean isEmpty() {
        return size == 0;
    }
    /*
     * Returns an iterator for this list.  The iterator is written
     * as an anonymous class.
     */
    public Iterator<T> iterator(){
        return new Iterator<T>(){
            ListNode current = header;
            boolean hasNext=true;

            public boolean hasNext(){

                return current!=trailer;
            }
            public T next(){
                T item = current.datum;
                current = current.next;
                return item;
            }
            public void remove(){
                ListNode prior = current.prior.prior;
                prior.next = current;
                current.prior = prior;
                size--;
            }
        };
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size; i++) {
            sb.append(get(i) + ", ");

        }
        return sb.toString();
    }


    public static boolean isPrime(int number) {
        int squareRoot = (int) Math.sqrt(number);
        for (int i = 2; i < squareRoot; i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }
    public static DoublyLinkedList<Integer> Sieve(int beg, int end) {
        if (beg < 2) {
            return new DoublyLinkedList<Integer>();
        }
        DoublyLinkedList<Integer> workList = new DoublyLinkedList<Integer>();
        DoublyLinkedList<Integer> primeList = new DoublyLinkedList<Integer>();

        for(int i =2; i<= end; i++){
            workList.add(i);
        } workList.add(0);
        double endNumber = Math.sqrt(end);
        int current = workList.get(0);
        while(current < endNumber){
            current = workList.remove(0);
            if(isPrime(current) && current >= beg){
                primeList.add(current);
            }
            System.out.println("WORKLIST: " + workList.toString());
            for (Iterator<Integer> it = workList.iterator(); it.hasNext();){
                Integer num = it.next();
                System.out.println("CURRENT NUM: " + num);
                if (num%current == 0){
                    it.remove();
                }
            }
        } workList.remove(workList.size-1);

        while (!workList.isEmpty()){
            Integer num2 = workList.remove(0);
            if (num2 >= beg)
                primeList.add(num2);
        }

        return primeList;
    }

    public static void main(String[] args) throws FileNotFoundException {
        DoublyLinkedList<Integer> test = new DoublyLinkedList<Integer>();
        System.out.println(DoublyLinkedList.Sieve(900, 1000));
    }

}











