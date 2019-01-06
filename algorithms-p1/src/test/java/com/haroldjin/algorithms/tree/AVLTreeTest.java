package com.haroldjin.algorithms.tree;

import org.junit.Assert;
import org.junit.Test;

import java.util.Random;
import java.util.stream.IntStream;

import static org.junit.Assert.*;

public class AVLTreeTest {

    @Test
    public void insert_visual_test() {
        AVLTree avlTree = getAVLTree("5 10 15 2 3 4") ;
        avlTree.printNodesPreOrder();
        System.out.println();
        avlTree.printNodesInOrder();
        System.out.println();
        avlTree.printNodesPostOrder();
    }

    @Test
    public void insert_height_test(){
        // 2^20
        AVLTree<Integer> avlTree = new AVLTree<>();
        IntStream.range(0, 1048576).forEach(
                e -> avlTree.insert(e)
        );
        Assert.assertEquals(avlTree.getTreeHeight(),21);
    }

    @Test
    public void printRightLeftRotateCase(){
        AVLTree avlTree = getAVLTree("10 5 20 25 15 17");
        avlTree.printNodesPreOrder();
    }

    public AVLTree<Integer> getAVLTree(String nodes){

        AVLTree<Integer> avlTree = new AVLTree<>();
        String[] nodeStr = nodes.split(" ");
        for (String node : nodeStr){
            avlTree.insert(Integer.parseInt(node));
        }
        return avlTree;
    }
}
