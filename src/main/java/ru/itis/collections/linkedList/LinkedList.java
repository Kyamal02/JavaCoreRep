package ru.itis.collections.linkedList;

import ru.itis.collections.List;

import java.util.NoSuchElementException;

public class LinkedList<E> implements List<E> {
    private Node<E> head = null;
    private Node<E> tail = null;
    private int size = 0;


    @Override
    public E get(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException();
        }
        Node<E> current = head;
        while (index > 0) {
            current = current.next;
            index--;
        }
        return (E) current.element;
    }

    @Override
    public void add(E element) {
        if (head == null) {
            tail = head = new Node<>(null, null, element);
        } else {
            Node<E> prev = tail;
            prev.next = new Node<>(tail, null, element);
            tail = prev.next;
        }
        size++;
    }

    @Override
    public void add(int index, E element) {
        if (index > size || index < 0) {
            throw new IndexOutOfBoundsException();

        } else if (head == null) {
            head = tail = new Node<>(null, null, element);
            size++;
            return;
        } else if (index == size) {
            add(element);
            return;
        } else if (index == 0) {
            addInHead(element);
            return;
        }
        Node<E> current = head;
        Node<E> previous = null;
        while (index > 0) {
            previous = current;
            current = current.next;
            index--;
        }

        Node<E> newNode = new Node<>(previous, current, element);
        previous.next = newNode;
        current.previous = newNode;
        size++;
    }

    @Override
    public boolean remove(E element) {
        if (head == null) return false;
        Node<E> current = head;
        int index = 0;
        while (current != null) {
            if (element.equals(current.element)) {
                if (index == 0) {
                    removeFirstElement();
                    return true;
                } else if (index == size - 1) {
                    removeLastElement();
                    return true;
                }
                current.previous.next = current.next;
                current.next.previous = current.previous;
                size--;
                return true;
            }
            index++;
            current = current.next;
        }
        return false;
    }

    @Override
    public boolean remove(int index) {
        checkRemoveIndex(index);
        if (removeFirstOrLastElement(index)) {
            return true;
        }
        Node<E> current = head;
        while (index != 0) {
            current = current.next;
            index--;
        }
        current.previous.next = current.next;
        current.next.previous = current.previous;
        size--;
        return true;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        Node<E> currentNode = head;
        Node<E> delNode;
        while (currentNode != null) {
            delNode = currentNode;
            currentNode = currentNode.next;
            delNode.previous = null;
            delNode.next = null;
        }
        head = null;
        tail = null;
        size = 0;
    }

//    private void addInTail(E element) {
//        Node<E> currentTail = tail;
//        currentTail.next = new Node<E>(currentTail, null, element);
//        tail = currentTail.next;
//        size++;
//    }

    private void addInHead(E element) {
        Node<E> currentHead = head;

        currentHead.previous = new Node(null, currentHead, element);
        head = currentHead.previous;

        size++;
    }

    private boolean removeFirstOrLastElement(int index) {
        if (head == tail) {
            head = tail = null;
            size--;
            return true;
        } else if (index == 0) {
            removeFirstElement();
            return true;
        } else if (index == size - 1) {
            removeLastElement();
            return true;
        }

        return false;
    }

    private void removeFirstElement() {

        Node<E> currentHead = head;
        head = currentHead.next;
        head.previous = null;
        size--;
    }

    private void removeLastElement() {
        Node<E> currentTail = tail;
        tail = currentTail.previous;
        tail.next = null;
        size--;
    }

    private void checkRemoveIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        if (size == 0) {
            throw new NoSuchElementException("List is empty");
        }
    }


    private static class Node<E> {
        Node<E> previous;
        Node<E> next;
        E element;

        public Node(Node<E> previous, Node<E> next, E element) {
            this.previous = previous;
            this.next = next;
            this.element = element;
        }

        @Override
        public String toString() {
            return element.toString();
        }
    }
}
