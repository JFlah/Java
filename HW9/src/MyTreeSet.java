import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.AbstractSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

class MyTreeSet<E extends Comparable<? super E>> extends AbstractSet<E> {
    // instance variables
    private MyTreeMap<E, Boolean> mtm = new MyTreeMap<E, Boolean>();

    public boolean add(E item) {
        return mtm.put(item, true) == null;
    }

    @SuppressWarnings("unchecked")
    public boolean contains(Object object) {
        try {
            return mtm.get((E) object) != null;
        }
        catch (Exception e) {
            return false;
        }
    }

    public int size() {
        return mtm.size();
    }

    public void clear() {
        mtm = new MyTreeMap<E, Boolean>();
    }

    public Iterator<E> iterator() {
        return mtm.keys();
    }

    public String toString() {
        Iterator<E> iterator = iterator();
        List<E> list = new ArrayList<E>();
        while (iterator.hasNext()) {
            list.add(iterator.next());
        }
        return list.toString();
    }

    public static void main(String[] args) {
        MyTreeSet<String> mts = new MyTreeSet<String>();
        mts.add("hi");
        mts.add("hey");
        mts.add("yo");

        System.out.println(mts.toString());
        System.out.println(mts);
        System.out.println(mts.size());
        System.out.println(mts.contains("yo"));
        System.out.println(mts.contains("yos"));
        mts.clear();
        System.out.println(mts.toString());
        System.out.println(mts.toString());
        System.out.println(mts.contains("yo"));

    }
}