package myset;

import java.util.Arrays;

public class MyHashSetChaining<E> implements MySet<E> {
    // The number of elements in the set
    private int size = 0;

    // Hash table is an array with each cell that is a linked list
    private Node<E>[] table;

    public MyHashSetChaining(int bucketsLength) {
        table = (Node<E>[])new Node[bucketsLength];
        size = 0;
    }

    /** Hash function */
    public int hash(int hashCode) {
        if (hashCode < 0) {
            hashCode = -hashCode;
        }
        return hashCode % table.length;
    }

    @Override /** Remove all elements from this set */
    public void clear() {
        Arrays.fill(table, null);
        size = 0;
    }

    @Override /** Return true if the element is in the set */
    public boolean contains(E e) {
        int bucketIndex = hash(e.hashCode());
        Node<E> current = table[bucketIndex];
        boolean found = false;
        while (!found && current != null) {
            if (current.data.equals(e)) {
                found = true;
            } else {
                current = current.next;
            }
        }
        return found;
    }

    @Override /** Add an element to the set */
    public boolean add(E e) {

        int bucketIndex = hash(e.hashCode());
        Node<E> current = table[bucketIndex];
        boolean found = false;
        while (!found && current != null) {
            if (current.data.equals(e)) {
                found = true;
                // Already in the set
            } else {
                current = current.next;
            }
        }
        if (!found) {
            Node newNode = new Node();
            newNode.data = e;
            newNode.next = table[bucketIndex];
            table[bucketIndex] = newNode;
            size++;
        }
        rehashIfNeeded();
        return !found;
    }

    public void rehashIfNeeded(){
        double loadFactor = (1.0 * size) / table.length;
        if(loadFactor > 0.75){
            System.out.println("Rehashing necessary...");
            rehash();
        }
    }

    public void rehash(){
        Node<E>[] oldTable = table;
        System.out.println("Next prime: " + nextPrime(oldTable.length * 2));
        table = (Node<E>[])new Node[nextPrime(oldTable.length * 2)];
        for(int i = 0; i < oldTable.length; i++){
            table[i] = oldTable[i];
        }
    }

    public int nextPrime(int currentPrime){
        int prime = -1;
        int number = currentPrime + 1;
        int counter = 2;
        boolean found = false;
        while(!found){
            if(number % counter == 0){
                number++;
                counter = 2;
            } else {
                counter++;
            }
            if(number == counter){
                found = true;
                prime = number;
            }
        }
        return prime;
    }


    @Override /** Remove the element from the set */
    public boolean remove(E e) {
        int bucketIndex = hash(e.hashCode());
        Node<E> current = table[bucketIndex];
        Node<E> prev = null;

        while(current != null){
            if(current.data.equals(e)){
                if(prev == null){
                    table[bucketIndex] = current.next;
                } else {
                    prev.next = current.next;
                }
                return true;
            }
            prev = current;
            current = current.next;
        }
        return false;
    }

    @Override /** Return true if the set contains no elements */
    public boolean isEmpty() {
        return size == 0;
    }

    @Override /** Return the number of elements in the set */
    public int size() {
        return size;
    }

    // method only for test purpose
    void writeOut() {
        for (int i = 0; i < table.length; i++) {
            Node<E> temp = table[i];
            if (temp != null) {
                System.out.print(i + "\t");
                while (temp != null) {
                    System.out.print(temp.data + "\t");
                    temp = temp.next;
                }
                System.out.println();
            }
        }
    }
   private class Node<E>{
        public E data;
        public Node<E> next;
    }

}
