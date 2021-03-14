package com.customdatastructure.java;

public class HashMap<K, V> implements CustomMap<K, V>{
    private static final double LOAD_FACTOR = 0.7;
    private int numBuckets;
    private int size;
    private CustomLinkedList<Pair<K, V>>[] buckets;

    public HashMap(){
        numBuckets = 5;
        size = 0;
        buckets = new CustomLinkedList[numBuckets];
    }

    private int getBucketIndex(K key){
        int hashCode = key.hashCode();
        return (hashCode < 0) ? (-1*hashCode)%numBuckets : hashCode%numBuckets;
    }

    private void insertExistingValue(CustomLinkedList<Pair<K, V>> list, K key, V value){
        Node<Pair<K, V>> head = list.getHead();
        while(head != null){
            if(head.getValue().getKey().equals(key)){
                head.getValue().setValue(value);
                return;
            }
            head = head.getNext();
        }
    }

    private void rehash(){
        CustomLinkedList<Pair<K, V>>[] oldBuckets = buckets;
        int oldBucketsCount = numBuckets;
        numBuckets *= 2;
        this.size = 0;
        buckets = new CustomLinkedList[numBuckets];

        for(int i=0; i<oldBucketsCount; i++){
            if(oldBuckets[i] != null) {
                Node<Pair<K, V>> head = oldBuckets[i].getHead();
                while (head != null) {
                    put(head.getValue().getKey(), head.getValue().getValue());
                    head = head.getNext();
                }
            }
        }
    }

    @Override
    public void put(K key, V value) {
        int atIndex = getBucketIndex(key);
        if(containsKey(key)){
            CustomLinkedList<Pair<K, V>> list = buckets[atIndex];
            insertExistingValue(list, key, value);
            return;
        }
        if(buckets[atIndex] == null)
            buckets[atIndex] = new CustomLinkedList<>();
        buckets[atIndex].append(new Pair(key, value));
        this.size++;
        int list_size = buckets[atIndex].size();
        double loadFactor = (1.0 * size) / numBuckets;
        if(loadFactor > LOAD_FACTOR)
            rehash();
    }

    @Override
    public boolean containsKey(K key) {
        int atIndex = getBucketIndex(key);
        if(buckets[atIndex] != null) {
            Node<Pair<K, V>> head = buckets[atIndex].getHead();
            while (head != null) {
                if (head.getValue().getKey().equals(key))
                    return true;
                head = head.getNext();
            }
        }
        return false;
    }

    @Override
    public boolean containsValue(V value) {
        for(int i=0; i<numBuckets; i++){
            if(buckets[i] != null){
                Node<Pair<K, V>> head = buckets[i].getHead();
                while(head != null){
                    if(head.getValue().getValue().equals(value))
                        return true;
                    head = head.getNext();
                }
            }
        }
        return false;
    }

    @Override
    public V get(K key) {
        int atIndex = getBucketIndex(key);
        if(buckets[atIndex] != null){
            Node<Pair<K, V>> head = buckets[atIndex].getHead();
            while(head!=null){
                if(head.getValue().getKey().equals(key))
                    return head.getValue().getValue();
                head = head.getNext();
            }
        }
        return null;
    }

    @Override
    public void remove(K key){
        int atIndex = getBucketIndex(key);
        if(buckets[atIndex] != null){
            Node<Pair<K, V>> head = buckets[atIndex].getHead();
            int index = 0;
            while(head != null){
                if(head.getValue().getKey().equals(key)){
                    buckets[atIndex].remove(index);
                    this.size--;
                    return;
                }
                index++;
                head = head.getNext();
            }
        }
    }

    private class HashMapEntry implements EntrySet<K, V>{
        int atIndex;
        Node<Pair<K, V>> next;

        public HashMapEntry(){
            atIndex = 0;
            if(buckets[atIndex] == null) {
                for (int i = 1; i < numBuckets; i++) {
                    if (buckets[i] != null) {
                        atIndex = i;
                        next = buckets[i].getHead();
                        return;
                    }
                }
            }
        }

        public HashMapEntry(int atIndex, Node<Pair<K, V>> hashMapEntry){
            if(hashMapEntry == null){
                for(int i=atIndex + 1; i<numBuckets; i++) {
                    if (buckets[i] != null) {
                        this.atIndex = i;
                        this.next = buckets[i].getHead();
                        return;
                    }
                }
            }
            else {
                this.atIndex = atIndex;
                this.next = hashMapEntry;
                return;
            }
            this.atIndex = numBuckets + 1;
            this.next = null;
        }

        @Override
        public boolean hasNext() {
            return atIndex < numBuckets;
        }

        @Override
        public EntrySet next() {
            return new HashMapEntry(atIndex, next.getNext());
        }

        @Override
        public K getKey() {
            return next.getValue().getKey();
        }

        @Override
        public V getValue() {
            return next.getValue().getValue();
        }
    }

    @Override
    public String toString(){
        StringBuilder stringBuilder = new StringBuilder();
        for(int i=0; i<numBuckets; i++){
            if(buckets[i] != null)
                stringBuilder.append(buckets[i]);
        }
        return stringBuilder.toString();
    }

    @Override
    public EntrySet entrySet() {
        return new HashMapEntry();
    }

    @Override
    public int size(){
        int counts = 0;
        for(int i=0; i<numBuckets; i++){
            if(buckets[i] != null)
                counts += buckets[i].size();
        }
        return counts;
    }

    @Override
    public boolean isEmpty(){
        return size() == 0;
    }
}
