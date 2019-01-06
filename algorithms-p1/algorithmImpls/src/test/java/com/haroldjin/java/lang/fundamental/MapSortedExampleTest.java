package com.haroldjin.java.lang.fundamental;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class MapSortedExampleTest {
    private static String expectedSortedByKey = "hello world\n" +
            "hello1 world\n" +
            "hello3 world\n" +
            "world hello1\n" +
            "world1 hello1\n" +
            "world2 hello1";

    private static String expectedSortedByValue = "world2 hello1\n" +
            "world hello1\n" +
            "world1 hello1\n" +
            "hello1 world\n" +
            "hello world\n" +
            "hello3 world";

    private static String expectedSortedByValueReversed= "hello1 world\n" +
            "hello world\n" +
            "hello3 world\n" +
            "world2 hello1\n" +
            "world hello1\n" +
            "world1 hello1";

    private Map<String, String> unsortedMap = new HashMap<>();

    @Before
    public void setup(){
        unsortedMap.put("hello", "world");
        unsortedMap.put("hello3", "world");
        unsortedMap.put("hello1", "world");
        unsortedMap.put("world", "hello1");
        unsortedMap.put("world2", "hello1");
        unsortedMap.put("world1", "hello1");
    }

    @Test
    public void mapSortedByKeyUsingTreeMap() {
        Map<String, String> sortedMap = MapSortedExample.sortByKeyWithTreeMap(unsortedMap);
        Assert.assertEquals(expectedSortedByKey,MapSortedExample.toString(sortedMap));
    }

    @Test
    public void mapSortedByValue(){
        Map<String, String> sortedMap = MapSortedExample.sortByValue(unsortedMap);
        Assert.assertEquals(expectedSortedByValue,MapSortedExample.toString(sortedMap));
    }

    @Test
    public void  mapSortedByValueReversedJava8(){
        Map<String, String> sortedMap = MapSortedExample.sortReversedOrderByValueJava8(unsortedMap);
        Assert.assertEquals(expectedSortedByValueReversed,MapSortedExample.toString(sortedMap));
    }

    @Test
    public void  mapSortedByValueReversedJava8V2(){
        Map<String, String> sortedMap = MapSortedExample.sortReversedOrderByValueJava8V2(unsortedMap);
        Assert.assertEquals(expectedSortedByValueReversed,MapSortedExample.toString(sortedMap));
    }

}
