package com.haroldjin.algorithms.tree;


/**
 * <p>Insertion
 *
 * <p>1. To make sure that the given tree remains AVL after every insertion, a re-balancing is required.
 * 2. BST Property value(left) < value(root) < value(right)
 *
 *
 * <p>Insertion Steps
 * <p>1. Perform standard BST insert for w.
 * 2. Starting from w, travel up and find the first unbalanced node, Let z be the first unbalanced node, y be the child of z that comes on the path from w to z and x be the grandchild of z that comes on the path from w to z can be arranged in 4 ways.
 * 3. Re-balance the tree by performing appropriate rotations on the subtree rooted with z. There can be 4 possible cases that needs to be handled as x, y and z can be arranged in 4 ways.
 *
 * <p>  a) y is the left child of z and x is the left child of y (Left Left Case)
 *      b) y is the left child of z and x is the right child of y (Left Right Case)
 *      c) y is the right child of z and x is the right child of y (Right Right Case)
 *      d) y is the right child of z and x is the left child of y (Right Left Case)
 *
 */
public class AVLTree<T extends Comparable<? super T>> {
    private Node root;

    public void insert(T val){
        if (root == null){
            root = new Node(val);
            return;
        }
        root = insert(root, val);
    }

    public int getTreeHeight(){
        return root.height;
    }

    private Node insert(Node node, T value) {
        // If the node is not created, then create it and return for linking to parent node
        if (node == null){
            return new Node(value);
        }

        // Else we haven't reached to the leaf node yet, continue on.
        int compareResult = value.compareTo(node.value);
        if (compareResult < 0 ){
            node.left = insert(node.left, value);
        } else if (compareResult >0){
            node.right = insert(node.right, value);
        } else {
            // If the node already exist, return it for parent linking
            return node;
        }

        // After insert, we check for the balance factor and rotate accordingly
        node.height = 1 + getMaxHeight(node.left, node.right);
        int balanceFactor = getBalanceFactor(node);

        // The assumption is made that the inserted node is on the heavy side
        // and bottom nodes will first get to below code as call stack starts popping.

        // Left Left AND Left Right cases
        if (balanceFactor > 1 && value.compareTo(node.left.value) < 0)
            return rightRotate(node);
        if (balanceFactor > 1 && value.compareTo(node.left.value) > 0){
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        // Right Right AND Right Left cases
        if (balanceFactor < -1 && value.compareTo(node.right.value) > 0)
            return leftRotate(node);
        if (balanceFactor < -1 && value.compareTo(node.right.value) < 0){
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        // Eventually we return the same node back
        return node;
    }

    private Node remove(Node node, T value) {
        // If the node is not created, then create it and return for linking to parent node
        if (node == null){
            return null;
        }

        // Else we haven't reached to the leaf node yet, continue on.
        int compareResult = value.compareTo(node.value);
        if (compareResult < 0 ){
            node.left = remove(node.left, value);
        } else if (compareResult >0){
            node.right = remove(node.right, value);
        } else {
            // If the node already exist, return it for parent linking
            return null;
        }
        return null;
    }


    private Node leftRotate(Node root) {
        Node right = root.right;
        Node rightLeft = right.left;

        right.left = root;
        root.right = rightLeft;

        root.height = getMaxHeight(root.left, root.right) + 1;
        right.height = getMaxHeight(right.left, right.right) + 1;

        return right;
    }

    private Node rightRotate(Node root) {
        Node left = root.left;
        Node leftRight = left.right;

        left.right = root;
        root.left = leftRight;

        root.height = getMaxHeight(root.left, root.right)+1;
        left.height = getMaxHeight(left.left, left.right)+1;

        return left;
    }

    private int getBalanceFactor(Node node){
        if (node == null)
            return 0;
        return getHeight(node.left) - getHeight(node.right);
    }

    private int getMaxHeight(Node node1, Node node2){
        int height1 = getHeight(node1);
        int height2 = getHeight(node2);
        return height1 > height2 ? height1 : height2;
    }

    private int getHeight(Node node){
        if (node == null)
            return 0;
        return node.height;
    }


    private void printPreOrder(Node node){
        if (node != null){
            System.out.println(node.value + " ");
            printPreOrder(node.left);
            printPreOrder(node.right);
        }
    }

    private void printInOrder(Node node){
        if (node != null){
            printInOrder(node.left);
            System.out.println(node.value);
            printInOrder(node.right);
        }
    }

    private void printPostOrder(Node node){
        if (node != null){
            printPostOrder(node.left);
            printPostOrder(node.right);
            System.out.println(node.value);
        }
    }

    public void printNodesPreOrder(){
        printPreOrder(root);
    }

    public void printNodesInOrder(){
        printInOrder(root);
    }

    public void printNodesPostOrder() {
        printPostOrder(root);
    }


    private class Node {
        Node left, right;
        T value;
        int height;

        Node(T value){
            this.value = value;
            this.height=1;
        }
    }

}
