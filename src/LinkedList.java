import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Spliterator;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;

public class LinkedList<E> implements IList<E> {

    private class Node {
        private Node next;
        private E data;
       // private IList<E> list;

        public Node(E data) {
            this.data = data;
        }
    }


    private Node head;
    private Node tail;
    private int numberOfEntries;


    public LinkedList(E elem) {
        if (head != null && tail != null) {
            Node forNu = new Node(elem);
            forNu.next = head;
            Node nrTo = head;
            this.head = forNu;
            forNu.next = nrTo;
        } else {
            Node newHead = new Node(elem);
            head = newHead;
            tail = newHead;
        }
        if (elem != null) {
            numberOfEntries++;
        }
    }

    /*public LinkedList(E elem, IList<E> liste) {
        Node forNu = new Node(elem);
        forNu.next = head;
        head = forNu;
        if (head != null && tail != null) {
            Node nrTo = head;
            this.head = forNu;
            forNu.next = nrTo;
        } else {
            Node newHead = new Node(elem);
            head = newHead;
            tail = newHead;
        }
        if (elem != null) {
            numberOfEntries++;
        }
        forNu.list = liste;

    }*/

    public LinkedList(){};

    @Override
    public E first() throws NoSuchElementException {
        if (head != null) {
            return head.data;
        } else {
            throw new NoSuchElementException();
        }
    }

    @Override
    public IList rest() {
        if (head.next == null) {
            return null;
        }
        IList newList = new LinkedList();
        Iterator<E> iter = this.iterator();
        while(iter.hasNext()) {
            newList.add(iter.next());
        }
        newList.remove();
        return newList;
    }

    public E getHead() {
        return this.head.data;
    }

    @Override
    public boolean add(E elem) {
        Node nyNode = new Node(elem);
        if (tail != null) {
            tail.next = nyNode;
            tail = tail.next;
            numberOfEntries++;
        } else if (isEmpty()) {
            tail = nyNode;
            head = nyNode;
            numberOfEntries++;
        }
        // midlertidig, for testing alltid true
        return true;
    }

    public E getTail() {
        return this.tail.data;
    }

    @Override
    public boolean put(E elem) {
        Node nyNode = new Node(elem);
        nyNode.next = head;
        head = nyNode;
        if (tail == null) {
            tail = nyNode;
        }
        numberOfEntries++;
        //midlertidig alltid return true
        return true;
    }

    @Override
    public E remove() throws NoSuchElementException {
        if (!isEmpty()) {
            Node midlertidig = this.head;
            head = head.next;
            numberOfEntries--;
            return midlertidig.data;
        } else {
            throw new NoSuchElementException();
        }
    }

    @Override
    public boolean remove(E elem) {
        boolean ifRemoved = false;
        if(numberOfEntries > 0) {
            Node testNode = new Node(elem);
            Node test2 = head;
            while (testNode.data != test2.data) {
                test2 = test2.next;
                if (test2 == null) {
                    break;
                }
            }
            Node nodeToGet = head;
            for (int i = 1; i <= numberOfEntries; i++) {
                if (test2!=null && test2.data == nodeToGet.next.data) {
                    ifRemoved = true;
                    nodeToGet.next = test2.next;
                    numberOfEntries--;
                    break;
                } else if (test2!=null && test2.data == nodeToGet.data){
                    head = nodeToGet.next;
                    numberOfEntries--;
                    break;
                } else {
                    nodeToGet = nodeToGet.next;
                }
            }
        } else {
        }
        return ifRemoved;
    }

    @Override
    public boolean contains(E elem) {
        boolean isThere = false;
        Node searchNode = new Node(elem);
        Node testNode = head;
        for (int i = 0; i < numberOfEntries; i++) {
            if (searchNode.data == testNode.data) {
                isThere = true;
                break;
            } else {
                testNode = testNode.next;
            }
        }
        return isThere;
    }

    @Override
    public boolean isEmpty() {
        boolean isIt = true;
        if (numberOfEntries != 0) {
            isIt = false;
        }
        return isIt;
    }

    @Override
    public void append(IList<? extends E> list) {
        Iterator<? extends E> iter = list.iterator();
        while(iter.hasNext()) {
            add(iter.next());
        }
    }

    private class IteratorForLinkedList implements Iterator<E> {
        private Node nextNode;

        private IteratorForLinkedList() {
            nextNode = head;
        }

        public boolean hasNext() {
            return nextNode != null;
        }

        public E next() {
            if (hasNext()) {
                Node returnNode = nextNode;
                nextNode = nextNode.next;
                return returnNode.data;
            } else {
                throw new NoSuchElementException("Illegal call to next(); iterator is after end of list.");
            }
        }
    }

    @Override
    public Iterator<E> iterator() {
        return new IteratorForLinkedList();
    }

    public Iterator<E> getIterator() {
        return iterator();
    }



    @Override
    public void prepend(IList<? extends E> list) {
        Iterator<? extends E> iter = list.iterator();
        IList<E> list2 = new LinkedList<>();
        while(iter.hasNext()) {
            list2.put(iter.next());
        }
        Iterator<? extends E> iter2 = list2.iterator();
        while(iter2.hasNext()) {
            put(iter2.next());
        }
    }
    @Override
    public IList<E> concat(IList<? extends E>... lists) {
        IList<E> nyListe = new LinkedList<>();
        for (int i = 0; i < lists.length; i++) {
            Iterator<? extends E> iter = lists[i].iterator();
            while(iter.hasNext()) {
                nyListe.add(iter.next());
            }
        }
        return nyListe;
    }

    private class horseShit implements Comparator<E> {

        @Override
        public int compare(E o1, E o2) {

        }
    }

    @Override
    public void sort(Comparator<? super E> c) {
        E fir = head.data;
        E sec = head.next.data;
        if (c.compare(fir, sec) > 0) {
            System.out.println("hei");
        }
    }


    @Override
    public void filter(Predicate<? super E> filter) {

    }

    @Override
    public int size() {
        return numberOfEntries;
    }

    @Override
    public void clear() {

    }

    @Override
    public Object reduce(Object o, BiFunction f) {
        return null;
    }

    @Override
    public IList map(Function f) {
        return null;
    }
}
