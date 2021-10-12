package model.utilities;

import lombok.ToString;

import java.util.LinkedList;

public class MyList<T> {
    private class Node {
        private T t;
        private Node next;
        public Node(Node next, T t){
            this.next = next;
            this.t = t;
        }
    }
    private final Node head = new Node(null, null);
    private int size;

    public MyList(){
        size = 0;
        head.next = head;
    }

    public int size(){
        return size;
    }

    public void addToTail(T t){
        if(head.next == head){
            Node node = new Node(null, t);
            head.next = node.next = node;
        }
        else {
            getNodeBefore(size).next = new Node(head.next, t);
        }
        ++size;
    }

//    public void addToHead(T t){
//        if(head.next == head){
//            Node node = new Node(null, t);
//            head.next = node.next = node;
//        }
//        else {
//           head.next = getLastNode().next = new Node(head.next, t);
//        }
//        ++size;
//    }

    public void add(int index, T t){
        if(index == size)
            addToTail(t);
        else {
            Node tmp = getNodeBefore(index);
            tmp.next = new Node(tmp.next, t);
            ++size;
        }
    }

    public void remove(int index){
        if(size == 0) throw new IllegalStateException();

        if(index == size)
            removeFromTail();
        else {
            Node tmp = getNodeBefore(index);
            tmp.next = tmp.next.next;
            --size;
        }
    }

   public void removeFromTail() {
       if(size == 0) throw new IllegalStateException();

       Node preLastNode = getNodeBefore(size-1);
       preLastNode.next = head.next;
       --size;
    }

    public T get(int index){
        return getNodeBefore(index).next.t;
    }

    private Node getNodeBefore(int index){
        Node tmp = head;
        int i = 0;
        while(i++ != index) {
            tmp = tmp.next;
        }
        return tmp;
    }
}
