package model.utilities;

import java.util.Iterator;

public class MyList<T> implements Iterable<T> {
    private final Node head = new Node(null, null);
    private int size;

    public MyList() {
        size = 0;
        head.next = head;
    }

    public int size() {
        return size;
    }

    public void addToTail(T t) {
        if (head.next == head) {
            Node node = new Node(null, t);
            head.next = node.next = node;
        } else {
            getNodeBefore(size).next = new Node(head.next, t);
        }
        ++size;
    }

    public void add(int index, T t) {
        Node tmp = getNodeBefore(index);
        tmp.next = new Node(tmp.next, t);
        ++size;
    }

    public void remove(int index) {
        if (size == 0) throw new IllegalStateException();
        Node tmp = getNodeBefore(index);
        tmp.next = tmp.next.next;
        --size;
    }

    public T get(int index) {
        return getNodeBefore(index).next.t;
    }

    public void set(int index, T t) {
        getNodeBefore(index).next.t = t;
    }

    private Node getNodeBefore(int index) {
        Node tmp = head;
        int i = 0;
        while (i++ != index) {
            tmp = tmp.next;
        }
        return tmp;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            Node node = head.next;

            @Override
            public boolean hasNext() {
                return node != head.next;
            }

            @Override
            public T next() {
                Node now = node;
                node = node.next;
                return now.t;
            }
        };
    }

    private class Node {
        private T t;
        private Node next;

        public Node(Node next, T t) {
            this.next = next;
            this.t = t;
        }
    }
}
