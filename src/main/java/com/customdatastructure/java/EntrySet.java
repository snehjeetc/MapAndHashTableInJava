package com.customdatastructure.java;

public interface EntrySet<K, V> {
    boolean hasNext();
    EntrySet next();
    K getKey();
    V getValue();
}
