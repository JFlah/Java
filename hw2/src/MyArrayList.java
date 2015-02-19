import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class MyArrayList<AnyType> extends AbstractList<AnyType> {

    private int size;
    private AnyType[] data;

    @SuppressWarnings("unchecked")
    MyArrayList(int startLength) {
        AnyType[] data = (AnyType[]) new Object[startLength];
        int size = 0;
        this.size = 0;
        this.data = data;
    }

    MyArrayList() {
        this(5);
    }

    public AnyType get(int index) {
        if (index > data.length) {
            throw new IndexOutOfBoundsException("Index is out of bounds!");
        }
        return data[index];
    }

    @SuppressWarnings("unchecked")
    private void resize() {
        int doubled = 2*data.length;
        AnyType[] data2 = (AnyType[]) new Object[doubled];
        for (int i = 0; i < data.length; i++) {
            data2[i] = data[i];
        }
        this.data = data2;
    }

    public int size() {
        return size;
    }

    public boolean add(AnyType element) {
        add(this.size, element);
        return true;
    }

    public void add(int index, AnyType element) {
        if (index > size) {
            throw new IndexOutOfBoundsException("Index is out of bounds!");
        }
        if (this.size >= data.length){
            System.out.println(this.size + " " + data.length);
            resize();
        }
        if (data[index] == null) {
            data[index] = element;
        }
        else {
            shift(index);
            data[index] = element;
        }
        size++;
    }

    private void shift(int index) {
        if (data[data.length-1] != null) {
            resize();
        }
        for (int i = data.length-2; i >= index; i--) {
            data[i+1] = data[i];
        }
    }

    public AnyType set(int index, AnyType element) {
        if (index > size-1) {
            throw new IndexOutOfBoundsException("Index is out of bounds!");
        }
        AnyType item = get(index);
        data[index] = element;
        return item;
    }

    public boolean isEmpty() {
      return size==0;
    }

    public void clear() {
        AnyType[] data = (AnyType[]) new Object[5];
        this.data = data;
    }

    public AnyType remove(int index) {
        AnyType item = get(index);
        for (int i = index; i < size-1; i++) {
            set(i, get(i + 1));
        }
        set(size-1, null);
        return item;
    }
    public void reverse() {
        for (int i = 0; i < size/2; i++) {
           AnyType lol = get(i);
           AnyType end = set(size-1-i, lol);
           set(i, end);

        }

    }


    public String toString(){
        String s = "[";
        for (int i =0; i < this.size; i++){
            AnyType item = get(i);
            s+=item + ", ";
        }
        s+="]";
        return s;
    }

    public void testRemove() throws FileNotFoundException {
        MyArrayList<String> test = new MyArrayList<String>();
        ArrayList<String> standard = new ArrayList<String>();
        File test1 = new File("test1.txt");
        Scanner scan = new Scanner(test1);

        while (scan.hasNextLine()) {
            String s = scan.nextLine();
            test.add(0,s);
            standard.add(0,s);
        }

        System.out.println("TEST" + test.toString());
//        System.out.println("STD" + standard.toString());
        MyArrayList<String> testEvens = new MyArrayList<String>();
        ArrayList<String> standardEvens = new ArrayList<String>();

        for (int i = 0; i < test.size()/2; i++) {
            testEvens.add(test.remove(i));
            standardEvens.add(standard.remove(i));
        }
        System.out.println("TEST" + test.toString());
        System.out.println("STD" + standard.toString());
        System.out.println("TE " + testEvens.toString());
        System.out.println("STDE" + standardEvens.toString());

        }

    public static void main(String[] args) {
        MyArrayList<String> al = new MyArrayList<String>();
        try {
            al.testRemove();
        } catch (FileNotFoundException e) {e.printStackTrace();}
//        System.out.println(al.toString());
//        al.add("Hi");
//        al.add("Yo");
//        System.out.println(al.toString());
//        al.add("Yes");
//        al.add(2, "No");
//        al.add(2, "Maybe");
//        System.out.println(al.toString());
//
//        al.set(2, "Definitely");
//        System.out.println(al.remove(3));
//        System.out.println(al.toString());
//
//        al.reverse();
//        System.out.println(al.toString());
    }
}