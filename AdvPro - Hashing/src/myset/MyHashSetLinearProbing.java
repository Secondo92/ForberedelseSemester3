package myset;

import java.util.Arrays;

/**
 * This class implements a hash set using separate chaining.
 */
public class MyHashSetLinearProbing<E> implements MySet<E> {
    public E[] table;
    private int size;

    private final E DELETED = (E)new Object();

    /**
     * Constructs a hash table.
     *
     * @param bucketsLength the length of the buckets array
     */
    public MyHashSetLinearProbing(int bucketsLength) {
        table = (E[]) new Object[bucketsLength];
        size = 0;
    }

    private int hash(int hashCode) {
        if (hashCode < 0) {
            hashCode = -hashCode;
        }
        return hashCode % table.length;
    }

    @Override
    /** Return true if the element is in the set */
    public boolean contains(E e) {
        if (isEmpty()) {
            return false;
        }
        int probe = 0;
        int hash = hash(e.hashCode());

        while (probe < table.length) {
            int index = (hash + probe) % table.length;
            if (table[index] == null) {
                return false;
            } else if (table[index].equals(e)) {
                return true;
            } else {
                probe++;
            }
        }
        return false;
    }

    @Override
    /** Remove all elements from this set */
    public void clear() {
        Arrays.fill(table, null);
        size = 0;
    }

    /**
     * Add an element to the set.
     *
     * @return true if e is a new object, false if e was already in the set
     */
    public boolean add(E e) {
        int probe = 0;
        int hash = hash(e.hashCode());
        System.out.println("Going to add: " + e + " with hashcode: " + hash);

        while(probe < table.length){
            int index = (hash + probe) % table.length;
            if(table[index] == null || table[index] == DELETED){
                table[index] = e;
                size++;
                System.out.println(e + " added to index " + index);
                return true;
            } else {
                probe++;
            }
        }
        return false;
    }

    /**
     * Remove the element from the set.
     *
     * @return true if e was removed from this set, false if e was not an
     * element of this set
     */
    public boolean remove(E e) {
        if(isEmpty()){
            return false;
        }
        int hash = hash(e.hashCode());
        int probe = 0;

        while(probe < table.length) {
            int index = (hash + probe) % table.length;
            if(table[index] == null){
                return false;
            } else if(table[index].equals(e)) {
                System.out.println("(Remove method) Element found and deleted");
                table[index] = DELETED;
                size--;
                return true;
            } else {
                probe++;
            }
        }
        return false;
    }

    @Override
    /** Return the number of elements in the set */
    public int size() {
        return size;
    }

    @Override
    /** Return true if the set contains no elements */
    public boolean isEmpty() {
        return size == 0;
    }

    public String toString(){
        return "DELETED";
    }


    // method only for test purpose
    public void writeOut() {
        for (int i = 0; i < table.length; i++) {
            System.out.println(i + "\t" + table[i]);
        }
    }


}
