package dictionaryelev;

import java.util.HashMap;
import java.util.Map;

public class DictionaryHashMap<K, V> implements Dictionary<K, V> {

    private Map<K, V>[] tabel;
    private int size;
    private static int N = 13;

    /**
     * HashingMap constructor comment.
     */

    public DictionaryHashMap() {
        tabel = new HashMap[N];
        size = 0;
        for (int i = 0; i < N; i++) {
            tabel[i] = new HashMap<K, V>();
        }
    }

    public int hashCode(K key){
        return Math.abs(key.hashCode() % tabel.length);
    }

    @Override
    public V get(K key) {
        int index = hashCode(key);
        return tabel[index].get(key);
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public V put(K key, V value) {
        if (key == null || value == null) {
            throw new IllegalArgumentException("Neither key nor value can be null");
        }
        int index = hashCode(key);
        V oldValue = tabel[index].put(key, value);
        if(oldValue == null){
            System.out.println("Spot was free, adding " + value + " to " + key + "...");
            size++;
        } else {
            System.out.println("Spot was taken, replacing " + oldValue + " with: " + value);

        }
        return oldValue;
    }

    @Override
    public V remove(K key) {
        int index = hashCode(key);
        V oldValue = tabel[index].remove(key);
        if(oldValue != null){
            size--;
        }
        return oldValue;
    }

    @Override
    public int size() {
        return size;
    }

}
