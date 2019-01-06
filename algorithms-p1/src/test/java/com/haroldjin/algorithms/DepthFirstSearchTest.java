package com.haroldjin.algorithms;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

import com.haroldjin.algorithms.graph.DepthFirstSearch;
import com.haroldjin.algorithms.model.InvalidArgumentsException;
import com.haroldjin.algorithms.utils.TimeIt;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class DepthFirstSearchTest {
    private DepthFirstSearch depthFirstSearch = new DepthFirstSearch<String>();

    @Before
    public void setUp() throws Exception {
        depthFirstSearch.addEdge("hello", "world");
        depthFirstSearch.addEdge("hello", "world");
        depthFirstSearch.addEdge("hello", "test");
    }

    @Test
    public void addEdge_shouldHaveInternal_correctStructure() throws NoSuchFieldException, IllegalAccessException {
        Field field = depthFirstSearch.getClass().getDeclaredField("nodeLinks");
        field.setAccessible(true);
        Map<String, ArrayList<String>> nodeLists = (Map<String, ArrayList<String>>)field.get(depthFirstSearch);
        for(Map.Entry<String, ArrayList<String>> entry : nodeLists.entrySet()){
            Assert.assertEquals(entry.getKey(), "hello");
            Iterator<String> iterator = entry.getValue().iterator();
            Assert.assertEquals("world", iterator.next());
            Assert.assertEquals("world", iterator.next());
            Assert.assertEquals("test", iterator.next());
        }
    }

    @Test(expected = InvalidArgumentsException.class)
    public void doSearch_invalidNodeName_shouldThrow_InvalidArgumentsException() throws InvalidArgumentsException {
        depthFirstSearch.doSearch("test");
    }

    @Test
    public void doSearch_shouldReturn_expectedList() throws InvalidArgumentsException {
        DepthFirstSearch<String> depthFirstSearch = new DepthFirstSearch<>();
        depthFirstSearch.addEdge("0", "1");
        depthFirstSearch.addEdge("0", "2");
        depthFirstSearch.addEdge("1", "2");
        depthFirstSearch.addEdge("2", "0");
        depthFirstSearch.addEdge("2", "3");
        depthFirstSearch.addEdge("3", "3");
        List<String> result = depthFirstSearch.doSearch("2");
        String strResult = String.join(" ", result);
        Assert.assertEquals("2 0 1 3", strResult);
    }

    @Test
    public void doSearchNonRecursive_shouldReturn_expectedList() throws InvalidArgumentsException {
        DepthFirstSearch<String> depthFirstSearch = new DepthFirstSearch<>();
        depthFirstSearch.addEdge("0", "1");
        depthFirstSearch.addEdge("0", "2");
        depthFirstSearch.addEdge("1", "2");
        depthFirstSearch.addEdge("2", "0");
        depthFirstSearch.addEdge("2", "3");
        depthFirstSearch.addEdge("3", "3");
        List<String> result = depthFirstSearch.doSearchNonRecursive("2");
        String strResult = String.join(" ", result);
        Assert.assertEquals("2 0 1 3", strResult);
    }


    // in case we want to test the performance
//    @Test
    public void performanceTest() throws InvalidArgumentsException, InterruptedException {
        int min=0, max = 1000000;
        DepthFirstSearch<String> depthFirstSearch = new DepthFirstSearch<>();

        int start = ThreadLocalRandom.current().nextInt(min,max+1);
        int end = ThreadLocalRandom.current().nextInt(min,max+1);
        depthFirstSearch.addEdge(Integer.toString(start), Integer.toString(end));

        System.out.println("Started adding edges");

        TimeIt timeAddEdge = new TimeIt();
        for (int i = 0 ; i < max ; i++){
            int randomFirst = ThreadLocalRandom.current().nextInt(min,max+1);
            int randomLast = ThreadLocalRandom.current().nextInt(min,max+1);
            depthFirstSearch.addEdge(Integer.toString(randomFirst), Integer.toString(randomLast));
        }
        timeAddEdge.printTimeElapsed("timeAddEdge");

        TimeIt timeIt = new TimeIt();
        List<String> result1 = depthFirstSearch.doSearch(Integer.toString(start));
        timeIt.printTimeElapsed("Recursive");
        System.out.println("Non Recursive search");
        TimeIt timeIt2 = new TimeIt();
        List<String> result2 = depthFirstSearch.doSearchNonRecursive(Integer.toString(start));
        timeIt2.printTimeElapsed("Non-Recursive");

//        Assert.assertEquals(result1, result2);
    }
}
