import javax.swing.text.html.HTMLDocument;
import java.util.*;

/**
 * Created by Jack on 11/23/2014.
 */
public class MyHashMap<K, V> implements MapADT<K, V> {
    //Instance Variables
    private LinkedList<Map.Entry<K, V>>[] table;
    private int size;
    private double loadFactor;
    private static final int CAPACITY = 11;
    private static final float DEFAULT_LOAD = 0.75f;
    private List<Integer> sizeMap;

    @SuppressWarnings("unchecked")
    public MyHashMap(int capacity, float loadFactor) {
        this.loadFactor = loadFactor;
        table = (LinkedList<Map.Entry<K, V>>[]) new LinkedList[capacity];

        populateTable();
        populateCapacity();
    }

    public MyHashMap() {
        this(CAPACITY, DEFAULT_LOAD);
    }

    private void populateTable() {
        for (int i = 0; i < table.length; i++) {
            table[i] = new LinkedList<Map.Entry<K, V>>();
        }
    }

    private void populateCapacity() {
        List<Integer> capacities = new ArrayList<Integer>();
        capacities.add(23);
        capacities.add(47);
        capacities.add(97);
        capacities.add(197);
        capacities.add(397);
        capacities.add(797);
        capacities.add(1597);
        capacities.add(3203);
        capacities.add(6421);
        capacities.add(12853);
        capacities.add(25717);
        capacities.add(51437);
        capacities.add(102877);
        capacities.add(205759);
        capacities.add(411527);
        capacities.add(823117);
        capacities.add(1646237);
        capacities.add(3922489);
        capacities.add(6584983);
        capacities.add(13169977);
        capacities.add(26339969);
        capacities.add(52679969);
        capacities.add(105359939);
        capacities.add(210719881);
        capacities.add(421439783);
        capacities.add(842879579);
        capacities.add(1685759167);
        sizeMap = capacities;
    }

    @SuppressWarnings("unchecked")
    private void resize() {
        int newSize = 0;
        // New array 2x capacity
        while (newSize < this.table.length) {
            newSize = sizeMap.remove(0);
        }
        // Get all entries
        Iterator<Map.Entry<K, V>> entries = entries();
        // Put into new array
        table = (LinkedList<Map.Entry<K, V>>[]) new LinkedList[newSize];
        populateTable();
        this.size = 0;
        while (entries.hasNext()) {
            Map.Entry<K, V> current = entries.next();
            put(current.getKey(), current.getValue());

        }
    }

    public int hash(K key) {
        return Math.abs(key.hashCode() % table.length);
    }

    /**
     * method updateList searches through LinkedList for instance of the key
     * returns associated value if found and updates the entry
     * else: returns null
     * Author: Jack Flaherty
     */
    public V updateList(LinkedList<Map.Entry<K, V>> list, Map.Entry<K, V> currentEntry) {
        K key = currentEntry.getKey();
        for (Map.Entry<K, V> entry : list) {
            if (entry.getKey().equals(key)) {
                return entry.setValue(currentEntry.getValue());
            }
        }
        list.add(currentEntry);
        size++;
        return null;
    }


    public V get(K key) {
        int hash = hash(key);
        LinkedList<Map.Entry<K, V>> ourList = table[hash];
        for (Map.Entry<K, V> entry : ourList) {
            if (key.equals(entry.getKey())) {
                return entry.getValue();
            }
        }
        return null;
    }

    public V put(K key, V value) throws NullPointerException {
        if (key == null || value == null) {
            throw new NullPointerException("Key or Value was null");
        }
        Map.Entry<K, V> newEntry = new AbstractMap.SimpleEntry<K, V>(key, value);
        int hash = hash(key);
        System.out.println(this.size + "\t" + this.table.length + "\t" + (float) this.size / this.table.length + " " + this.loadFactor);
        if ((float) this.size / table.length > this.loadFactor) {
            resize();
        }
        return updateList(table[hash], newEntry);
    }

    public Iterator<K> keys() {
        List<K> keyList = new ArrayList<K>();
        for (int i = 0; i < table.length; i++) {
            //loop through all entries (foreach) and add all keys to mapList ^^
            // i increments through the keys, for each on the linkedlist.
            for (Map.Entry<K, V> entry : table[i]) {
                keyList.add(entry.getKey());
            }
        }
        return keyList.iterator();
    }

    public Iterator<Map.Entry<K, V>> entries() {
        List<Map.Entry<K, V>> listEntries = new LinkedList<Map.Entry<K, V>>();
        for (int i = 0; i < table.length; i++) {
            listEntries.addAll(table[i]);
        }
        return listEntries.iterator();
    }


    public String toString() {
        List<Map.Entry<K, V>> listEntries = new LinkedList<Map.Entry<K, V>>();
        for (int i = 0; i < table.length; i++) {
            listEntries.addAll(table[i]);
        }
        return listEntries.toString();
    }

//    public static void main(String[] args) {
//        MyHashMap<String, Integer> mhm = new MyHashMap<String, Integer>();
//        mhm.put("Hi", 23);
//        mhm.put("Hello", 24);
//        mhm.put("Ha", 28);
//        mhm.put("Ho", 28);
//        mhm.put("AH", 28);
//        mhm.put("LOL", 28);
//        mhm.put("Hie", 28);
//        mhm.put("Heldlo", 28);
//        mhm.put("LJF", 28);
//        System.out.println(mhm.put("LOJ", 28));
//        mhm.put("FEF", 28);
//        mhm.put("DP", 28);
//        mhm.put("POO", 28);
//        mhm.put("AS", 28);
//        mhm.put("ASAS", 28);
//        mhm.put("DICKBUTT", 28);
//        mhm.put("FF", 28);
//        mhm.put("lol", 28);
//        mhm.put("EF", 28);
//        mhm.put("EF", 28);
//        mhm.toString();
//        mhm.get("Hi");
//        mhm.get("Hie");
//        mhm.get("Hello");
//
//        Iterator<String> iterator = mhm.keys();
//        Iterator<Map.Entry<String, Integer>> iteratorEntries = mhm.entries();
//
//        while (iterator.hasNext()) {
//            System.out.println(iterator.next());
//        }
//        while (iteratorEntries.hasNext()) {
//            System.out.println(iteratorEntries.next());
//        }
//    }
}
