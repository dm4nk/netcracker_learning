package model.utilities.mycollections;

import model.utilities.MyCloneable;

import java.io.Serializable;
import java.util.Iterator;
import java.util.Objects;

public class MyList<T extends MyCloneable> implements Iterable<T>, Serializable, MyCloneable {
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
        if (index == size) addToTail(t);
        else {
            Node tmp = getNodeBefore(index);
            tmp.next = new Node(tmp.next, t);
            ++size;
        }
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
        return new Iterator<>() {
            Node node = head;
            int cur = 0;

            @Override
            public boolean hasNext() {
                return cur != size;
            }

            @Override
            public T next() {
                ++cur;
                return (node = node.next).t;
            }
        };
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MyList)) return false;
        MyList<?> myList = (MyList<?>) o;
        if (size != myList.size) return false;

        Iterator<?> iterator1 = this.iterator(), iterator2 = myList.iterator();

        while (iterator1.hasNext() && iterator2.hasNext())
            if (!iterator1.next().equals(iterator2.next())) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(head, size);
    }

    @Override
    @SuppressWarnings("all")
    public MyList<T> clone() {
        MyList<T> newList = new MyList<>();

        for (T t : this)
            newList.addToTail((T) t.clone());

        return newList;
    }

    private class Node implements Serializable {
        private T t;
        private Node next;

        public Node(Node next, T t) {
            this.next = next;
            this.t = t;
        }
    }
}
