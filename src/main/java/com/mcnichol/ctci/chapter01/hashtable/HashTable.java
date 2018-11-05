package com.mcnichol.ctci.chapter01.hashtable;

import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicReference;

public class HashTable<K, V> {
    private static final int RADIX = 10;
    private int size;
    private LinkedList [] table;

    HashTable() {
        this.table = new LinkedList[RADIX];

        for (int i = 0; i < RADIX; i++) {
            table[i] = new LinkedList<Node<K,V>>();
        }
    }

    public int size() {
        return size;
    }

    @SuppressWarnings("unchecked")
    void put(K key, V value) {
        size++;
        table[getHash(key) % RADIX].add(new Node(key, value));

    }

    @SuppressWarnings("unchecked")
    V get(K key) {

        AtomicReference<V> response = new AtomicReference<>();

        LinkedList<Node<K,V>> linkedList = table[getHash(key) % RADIX];
        linkedList.forEach((node) ->{
            if (node.getKey().equals(key)) {
                response.set(node.getValue());
            }
        });
        return response.get();
    }

    private int getHash(K key) {
        return System.identityHashCode(key);
    }

}