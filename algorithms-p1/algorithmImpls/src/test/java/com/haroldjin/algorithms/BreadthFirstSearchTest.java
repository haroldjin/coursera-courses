package com.haroldjin.algorithms;

import com.haroldjin.algorithms.graph.BreadthFirstSearch;
import com.haroldjin.algorithms.model.InvalidArgumentsException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.*;

public class BreadthFirstSearchTest {
    private BreadthFirstSearch<String> breadthFirstSearch;

    @Before
    public void setUp() throws Exception {
        breadthFirstSearch = new BreadthFirstSearch();

        breadthFirstSearch.addEdge("hello", "world");
        breadthFirstSearch.addEdge("hello", "world");
        breadthFirstSearch.addEdge("hello", "test");
    }

    @Test
    public void addEdge_shouldHaveInternal_correctStructure() throws NoSuchFieldException, IllegalAccessException {
        Field field = breadthFirstSearch.getClass().getDeclaredField("nodeLinks");
        field.setAccessible(true);
        Map<String, HashSet<String>> nodeSets = (Map<String, HashSet<String>>)field.get(breadthFirstSearch);
        for(Map.Entry<String, HashSet<String>> entry : nodeSets.entrySet()){
            Assert.assertEquals(entry.getKey(), "hello");
            Iterator<String> iterator = entry.getValue().iterator();
            Assert.assertEquals("world", iterator.next());
            Assert.assertEquals("test", iterator.next());
        }
    }

    @Test(expected = InvalidArgumentsException.class)
    public void doSearch_invalidNodeName_shouldThrow_InvalidArgumentsException() throws InvalidArgumentsException {
        breadthFirstSearch.doSearch("test");
    }

    @Test
    public void doSearch_shouldReturn_expectedList() throws InvalidArgumentsException {
        BreadthFirstSearch<String> breadthFirstSearch = new BreadthFirstSearch<>();
        breadthFirstSearch.addEdge("0", "1");
        breadthFirstSearch.addEdge("0", "2");
        breadthFirstSearch.addEdge("1", "2");
        breadthFirstSearch.addEdge("2", "0");
        breadthFirstSearch.addEdge("2", "3");
        breadthFirstSearch.addEdge("3", "3");
        List<String> result = breadthFirstSearch.doSearch("2");
        String strResult = String.join(" ", result);
        Assert.assertEquals("2 0 3 1", strResult);
    }
}
