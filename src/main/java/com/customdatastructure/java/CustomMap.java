package com.customdatastructure.java;

public interface CustomMap<K, V> {
    void put(K key, V value);
    boolean containsKey(K key);
    boolean containsValue(V value);
    V get(K key);
    EntrySet entrySet();
    void remove(K key);
    int size();
    boolean isEmpty();
}
