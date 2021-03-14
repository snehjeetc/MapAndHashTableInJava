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
        int frequency = myMap.get("to");
        System.out.println(myMap);
        Assert.assertEquals(2, frequency);
    }
}
