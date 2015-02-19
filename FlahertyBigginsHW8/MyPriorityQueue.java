/*
** Jack and Alyssa
* This class contains methods to manipulate the heap that we create.
 */


import java.util.*;

class MyPriorityQueue<E> implements PriorityQueueADT<E> {

    ArrayList<E> heap;
    Comparator<E> comparator;

    MyPriorityQueue(Comparator<E> comparator) {
        this.comparator = comparator;
        heap = new ArrayList<E>();
    }

    public boolean add(E item) {
        heap.add(item);
        siftUp();
        return true;
    }

    public E remove() {
        if (heap.size()==1){
            return heap.remove(0);
        }
        E returnValue = heap.get(0);
        heap.set(0, heap.remove(heap.size() - 1));
        siftDown();
        return returnValue;
    }

    public boolean isEmpty() {
        return heap.isEmpty();
    }

    public String toString() {
        return heap.toString();
    }

    private void siftUp() {
        int childIndex = heap.size() - 1;
        if (heap.size() == 1) {
            return;
        }
        while ((childIndex - 1) / 2 >= 0) {
            int parentIndex = (childIndex - 1) / 2;
            if (comparator.compare(heap.get(childIndex), heap.get(parentIndex)) > 0) {
                E childValue = heap.get(childIndex);
                heap.set(childIndex, heap.get(parentIndex));
                heap.set(parentIndex, childValue);
                childIndex = parentIndex;
            } else {
                return;
            }
        }
    }

    private void siftDown() {
        E nnode = heap.get(0);
        int parentIndex = (0);
        int leftChildIndex = 2 * heap.indexOf(nnode) + 1;
        int rightChildIndex = 2 * heap.indexOf(nnode) + 2;

        if (heap.size() == 1) {
            return;
        }
        while (heap.indexOf(nnode) < heap.size()) {
            if (rightChildIndex < heap.size()) {
                if (comparator.compare(heap.get(leftChildIndex), heap.get(rightChildIndex)) > 0) {
                    if (comparator.compare(heap.get(leftChildIndex), nnode) > 0) {
                        E leftChildValue = heap.get(leftChildIndex);
                        heap.set(leftChildIndex, heap.get(parentIndex));
                        heap.set(parentIndex, leftChildValue);
                        nnode = heap.get(leftChildIndex);
                        parentIndex = leftChildIndex;
                        leftChildIndex = 2 * leftChildIndex + 1;
                        rightChildIndex = 2 * leftChildIndex + 2;
                    }
                    else {
                        return;
                    }
                }
                else if (comparator.compare(heap.get(rightChildIndex), heap.get(leftChildIndex)) > 0) {
                    if (comparator.compare(heap.get(rightChildIndex), nnode) > 0) {
                        E rightChildValue = heap.get(rightChildIndex);
                        heap.set(rightChildIndex, heap.get(parentIndex));
                        heap.set(parentIndex, rightChildValue);
                        nnode = heap.get(rightChildIndex);
                        parentIndex = rightChildIndex;
                        leftChildIndex = 2 * rightChildIndex + 1;
                        rightChildIndex = 2 * rightChildIndex + 2;
                    }
                    else {
                        return;
                    }
                }
                else {
                    return;
                }
            }
            else if (leftChildIndex<heap.size()){
                if (comparator.compare(heap.get(leftChildIndex), nnode) > 0) {
                    E leftChildValue = heap.get(leftChildIndex);
                    heap.set(leftChildIndex, heap.get(parentIndex));
                    heap.set(parentIndex, leftChildValue);
                    nnode = heap.get(leftChildIndex);
                    parentIndex = leftChildIndex;
                    leftChildIndex = 2 * leftChildIndex + 1;
                    rightChildIndex = 2 * leftChildIndex + 2;
                }
                else {
                    return;
                }
            }
            else {
                return;
            }
        }
    }

    public int size(){
        return this.heap.size();
    }


}