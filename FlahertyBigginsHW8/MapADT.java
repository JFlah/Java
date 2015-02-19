import java.util.*;

interface MapADT<K,V> {
   
   public V get(K key);
   public V put(K key, V value);
   public Iterator<K> keys();
   public Iterator<Map.Entry<K,V>> entries();
   
}