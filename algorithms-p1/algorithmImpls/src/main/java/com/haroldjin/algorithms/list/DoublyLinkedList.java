package com.haroldjin.algorithms.list;

public class DoublyLinkedList<E extends Object> {
    private Node<E> head = null;
    private Node<E> tail = null;
    private class Node<E extends Object> {
        E element;
        Node<E> next;
        Node<E> prev;
        public Node(E element, Node next, Node prev){
            this.element = element;
            this.next = next;
            this.prev = prev;
        }

        public String toString(){
            return this.element.toString();
        }
    }

    public void addFirst(E e){
        // Case empty head, initialize both with a new node
        if (head == null){
            this.tail = this.head = new Node<E>(e, null, null);
            return;
        }
        // Case head next null, create new node as head, and change tail prev
        if (head.next == null){
            this.tail.prev = this.head = new Node<E>(e, this.head, null);
            return;
        }

        // Case different head and tail
        Node<E> head = new Node<E>(e, this.head, null);
        this.head.prev = head;
        this.head = head;
        return;
    }

    public void addLast(E e){
        // Case empty head, initialize both with a new node
        if (this.tail == null){
            this.tail = this.head = new Node<E>(e, null, null);
            return;
        }
        // Case head next null, create new node as head, and change tail prev
        if (this.tail.prev == null){
            this.tail = this.head.next = new Node<E>(e, null, this.tail);
            return;
        }

        // Case different head and tail
        Node<E> tail = new Node<E>(e, null, this.tail);
        this.tail.next = tail;
        this.tail = tail;
        return;
    }

    public void removeFirst(){
        // Case head null
        if (this.head == null)
            return;
        // Case head next null
        if (this.head.next == null){
            this.head = this.tail = null;
            return;
        }
        // Case head next non-null
        this.head.next.prev = null;
        this.head = this.head.next;
        return;
    }

    public void removeLast(){
        // Case tail null
        if (this.tail == null)
            return;
        // case tail prev null
        if (this.tail.prev == null){
            this.head = this.tail = null;
            return;
        }

        // Case tail prev non-null
        this.tail.prev.next = null;
        this.tail = this.tail.prev;
        return;
    }

    public void printAll(){
        if (this.head == null){
            return;
        }
        Node<E> head = this.head;
        do {
            System.out.println(head);
            head = head.next;
        } while (head != null);
    }

}
