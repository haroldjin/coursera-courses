package com.haroldjin.algorithms.list;

import org.junit.Test;

import static org.junit.Assert.*;

public class DoublyLinkedListTest {
    @Test
    public void test_addFirstLast_shouldReturn_CorrectValue(){
            DoublyLinkedList<String> linkedList = new DoublyLinkedList();
            linkedList.addFirst("hello");
            linkedList.addFirst("hello1");
            linkedList.addFirst("hello2");
            linkedList.addFirst("hello3");
            linkedList.addLast("hello4");
            linkedList.addLast("hello5");
            linkedList.removeFirst();
            linkedList.removeLast();
            linkedList.printAll();
    }

}
