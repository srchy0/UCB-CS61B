package deque;

public class LinkedListDeque<T> {
    /** Inner node of deque. */
    public class Node {
        /** Item stored in this node. */
        private T item;
        /** Previous node. */
        private Node pre;
        /** Next node. */
        private Node next;

        /** Construction of Node. */
        public Node(T iitem, Node ppre, Node nnext) {
            item = iitem;
            pre = ppre;
            next = nnext;
        }

        /** Construction of Sentinel Node. */
        public Node(Node ppre, Node nnext) {
            pre = ppre;
            next = nnext;
        }
    }


    /** Size of the deque. */
    private int size;
    /** Sentinel node of deque. */
    private Node sentinel;
    
    /** Construction of Deque. */
    public LinkedListDeque() {
        sentinel = new Node(null, null);
        size = 0;
        sentinel.pre = sentinel;
        sentinel.next = sentinel;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void addFirst(T item) {
        Node newList = new Node(item, sentinel, sentinel.next);
        sentinel.next.pre = newList;
        sentinel.next = newList;
        size++;
    }

    public void addLast(T item) {
        Node newList = new Node(item, sentinel.pre, sentinel);
        sentinel.pre.next = newList;
        sentinel.pre = newList;
        size++;
    }

    public void printDeque() {
        Node ptr = sentinel.next;
        while(ptr != sentinel) {
            System.out.println(ptr.item + " ");
            ptr = ptr.next;
        }
    }

    public T removeFirst() {
        if(size == 0) {
            return null;
        } else {
            T firstNode = sentinel.next.item;
            sentinel.next.next.pre = sentinel;
            sentinel.next = sentinel.next.next;
            size--;
            return firstNode;
        }
    }

    public T removeLast() {
        if(size == 0) {
            return null;
        } else {
            T lastNode = sentinel.pre.item;
            sentinel.pre.pre.next = sentinel;
            sentinel.pre = sentinel.pre.pre;
            size--;
            return lastNode;
        }
    }

    public T get(int index) {
        if(index >= size) {
            return null;
        }
        Node ptr = sentinel;
        while(index >= 0) {
            ptr = ptr.next;
            index--;
        }
        return ptr.item;
    }

    public T getRecursiveHelper(Node begin, int index) {
        if(index == 0) {
            return begin.item;
        } else {
            return getRecursiveHelper(begin.next, index - 1);
        }
    }

    public T getRecursive(int index) {
        if(index >= size) {
            return null;
        }
        return getRecursiveHelper(sentinel.next, index);
    }

}
