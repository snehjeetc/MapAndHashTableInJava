package com.customdatastructure.java;

import org.junit.Assert;
import org.junit.Test;

import java.util.Locale;

public class HashTableTestClass {
    @Test
    public void givenParagraph_UseHashTable_ReturnTheHeighestFrequency(){
        String sentence = "Paranoids are not paranoid because they are paranoid but" +
                " because they keep putting themselves deliberately into " +
                "paranoid avoidable situations";
        CustomMap<String, Integer> myHashMap = new HashMap<>();
        String[] words = sentence.toLowerCase().split(" ");
        for(String word : words){
            Integer value = myHashMap.get(word);
            value = (value == null) ? 1 : value + 1;
            myHashMap.put(word, value);
        }
        System.out.println(myHashMap);
        EntrySet<String, Integer> entrySet = myHashMap.entrySet();
        while(entrySet.hasNext()){
            System.out.println("Key: " + entrySet.getKey() + " Value: " + entrySet.getValue());
            entrySet = entrySet.next();
        }
        int frequency = myHashMap.get("paranoid");
        Assert.assertEquals(3, frequency);
    }

    @Test
    public void givenParagraph_UseHashTable_RemoveTheGiveWord_ShouldReturnNoValueAfterRemoval(){
        String sentence = "Paranoids are not paranoid because they are paranoid but" +
                " because they keep putting themselves deliberately into " +
                "paranoid avoidable situations";
        CustomMap<String, Integer> myHashMap = new HashMap<>();
        String[] words = sentence.toLowerCase().split(" ");
        for(String word : words){
            Integer value = myHashMap.get(word);
            value = (value == null) ? 1 : value + 1;
            myHashMap.put(word, value);
        }
        System.out.println(myHashMap + " Size: " + myHashMap.size());
        myHashMap.remove("avoidable");
        System.out.println(myHashMap + " Size: " + myHashMap.size());
        Integer frequency = myHashMap.get("avoidable");

        Assert.assertEquals(null, frequency);
    }

}
