package org.mvnsearch;

import org.jetbrains.annotations.Nullable;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SieveCache<T> {
    private int capacity = 1000;
    private Node<T> head = null;
    private Node<T> tail = null;
    private Node<T> hand = null;
    private int size = 0;
    private final Map<String, Node<T>> store = new ConcurrentHashMap<>();


    public SieveCache() {

    }

    public SieveCache(int capacity) {
        this.capacity = capacity;
    }

    private void addToHead(Node<T> node) {
        node.setNext(this.head);
        node.setPrev(null);
        if (this.head != null) {
            this.head.setPrev(node);
        }
        this.head = node;
        if (this.tail == null) {
            this.tail = node;
        }
    }

    private void removeNode(Node<T> node) {
        if (node.getPrev() != null) {
            node.getPrev().setNext(node.getNext());
        } else {
            this.head = node.getNext();
        }
        if (node.getNext() != null) {
            node.getNext().setPrev(node.getPrev());
        } else {
            this.tail = node.getPrev();
        }
    }

    private void evict() {
        Node<T> obj = this.hand != null ? this.hand : this.tail;
        while (obj != null && obj.isVisited()) {
            obj.setVisited(false);
            obj = obj.getPrev() != null ? obj.getPrev() : this.tail;
        }
        if (obj != null) {
            this.hand = obj.getPrev() != null ? obj.getPrev() : null;
            this.store.remove(obj.getKey());
            this.removeNode(obj);
            this.size = this.size - 1;
        }
    }

    @Nullable
    public T get(String key) {
        Node<T> node = this.store.get(key);
        if (node != null) {
            node.setVisited(true);
            return node.getValue();
        }
        return null;
    }

    public void put(String key, T value) {
        synchronized (this) {
            if (this.size >= this.capacity) {
                this.evict();
            }
            Node<T> node = new Node<>(key, value);
            this.addToHead(node);
            this.store.put(key, node);
            this.size = this.size + 1;
            node.setVisited(false);
        }
    }

    public void delete(String key) {
        Node<T> node = this.store.get(key);
        if (node != null) {
            this.store.remove(key);
            this.removeNode(node);
            this.size = this.size - 1;
        }
    }

    public void clear() {
        synchronized (this) {
            this.store.clear();
            this.head = null;
            this.tail = null;
            this.hand = null;
            this.size = 0;
        }
    }

    public void showItems() {
        Node<T> current = this.head;
        while (current != null) {
            Node<T> next = current.getNext();
            System.out.println(current.getValue() + "(visited:" + current.isVisited() + ")" + (next != null ? (" -> " + next.getValue()) : "\n"));
            current = next;
        }
    }
}
