package com.customdatastructure.java;

public class LinkedMap<K, V> implements CustomMap<K, V> {
    private CustomLinkedList<Pair<K,V>> list;

    public LinkedMap(){
        list = new CustomLinkedList<>();
    }

    @Override
    public void put(K key, V value){
        if(containsKey(key)) {
            Node<Pair<K, V>> head = list.getHead();
            while (head != null) {
                if (head.getValue().getKey().equals(key)) {
                    head.getValue().setValue(value);
                    return;
                }
                head = head.getNext();
            }
        }
        list.add(new Pair(key, value));
    }

    @Override
    public boolean containsKey(K key){
        EntrySet entryPoint = entrySet();
        while(entryPoint.hasNext()){
            if(key.equals(entryPoint.getKey()))
                return true;
            entryPoint = entryPoint.next();
        }
        return false;
    }

    @Override
    public boolean containsValue(V value){
        EntrySet entryPoint = entrySet();
        while(entryPoint.hasNext()){
            if(entryPoint.getValue().equals(value))
                return true;
            entryPoint = entryPoint.next();
        }
        return false;
    }

    @Override
    public V get(K key){
        if(containsKey(key)) {
            Node<Pair<K, V>> head = list.getHead();
            while(head != null){
                if(head.getValue().getKey().equals(key))
                    return head.getValue().getValue();
                head = head.getNext();
            }
        }
        return null;
    }

    private class LinkedMapEntry implements EntrySet<K,V>{
        Node<Pair<K, V>> next;
        public LinkedMapEntry(Node<Pair<K, V>> next){
            this.next = next;
        }
        public LinkedMapEntry(){
             this.next = list.getHead();
        }
        @Override
        public boolean hasNext(){
            return next != null;
        }
        @Override
        public EntrySet next(){
            return new LinkedMapEntry(next.getNext());
        }
        @Override
        public K getKey(){
            return next.getValue().getKey();
        }
        @Override
        public V getValue(){
            return next.getValue().getValue();
        }
    }

    public EntrySet entrySet(){
        return new LinkedMapEntry();
    }

    @Override
    public String toString(){
        return "{" + list.toString() + "}";
    }
}
