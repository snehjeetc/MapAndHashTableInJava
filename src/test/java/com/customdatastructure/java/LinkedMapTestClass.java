package com.customdatastructure.java;

import org.junit.Assert;
import org.junit.Test;

import java.util.Locale;

public class LinkedMapTestClass {
    @Test
    public void givenASentence_WhenWordsAreAddedToList_ShouldReturnWordFrequency(){
        String sentence = "To be or not to be";
        CustomMap<String, Integer> myMap = new LinkedMap<>();
        String[] words = sentence.toLowerCase().split(" ");
        for(String word : words){
            Integer value = myMap.get(word);
            value = (value == null) ? 1 : value+1;
            myMap.put(word, value);
        }
        EntrySet<String, Integer> entryset = myMap.entrySet();
        while(entryset.hasNext()){
            System.out.println("Key: " + entryset.getKey() + " Value: " + entryset.getValue());
            entryset = entryset.next();
        }
        int frequency = myMap.get("to");
        System.out.println(myMap);
        Assert.assertEquals(2, frequency);
    }

    @Test
    public void givenSentence_UseLinkedMap_RemoveTheGivenWord_ShouldReturnNoValueAfterRemoval(){
        String sentence = "To be or not to be";
        CustomMap<String, Integer> myMap = new LinkedMap<>();
        String[] words = sentence.toLowerCase().split(" ");
        for(String word : words){
            Integer value = myMap.get(word);
            value = (value == null) ? 1 : value+1;
            myMap.put(word, value);
        }
        System.out.println(myMap + " Size: " + myMap.size());
        myMap.remove("be");
        System.out.println(myMap + " Size: " + myMap.size());
        Integer frequency = myMap.get("be");
        Assert.assertEquals(null, frequency);
    }
}
