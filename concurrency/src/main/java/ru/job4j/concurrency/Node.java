package ru.job4j.concurrency;

public class Node<T> {
    private final Node next;
    private final T value;

    Node(Node next, T value) {
        this.next = next;
        this.value = value;
    }
    public Node getNext() {
        return next;
    }

    public T getValue() {
        return value;
    }
}
