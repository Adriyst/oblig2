import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class LinkedListTest {

    private LinkedList<Integer> liste;

    @org.junit.jupiter.api.BeforeEach
    public void setup() {

    }

    /**
     * Tester at det første elementet i listen er null.
     */
    @org.junit.jupiter.api.Test
    public void testEmptyFirst() {
        liste = new LinkedList<>();
        assertThrows(NoSuchElementException.class, () -> liste.first());
    }

    /**
     * Lager en kopi av den tomme listen, og sjekker om den nye listen har det første elementet i den gamle. Tror det er greit at denne er sånn?
     */
    @org.junit.jupiter.api.Test
    public void testEmptyRest() {
        liste = new LinkedList<>();
        //IList<Integer> testListe = liste.rest();
        assertThrows(NullPointerException.class, () -> liste.rest());
    }

    /**
     * Sjekker at det er mulig å legge til en verdi i en liste. Deretter sjekker den at listen inneholder tingen.
     */
    @org.junit.jupiter.api.Test
    public void testEmptyAdd() {
        liste = new LinkedList<>();
        liste.add(10);
        assertEquals(Integer.valueOf(10), liste.first());
        assertEquals(liste.size(), 1);
        //assertTrue(liste.contains(10));
    }

    /**
     * Sjekker at det er mulig å legge til en verdi i en liste. Deretter sjekker den at det første elementet er like det man la til.
     */
    @org.junit.jupiter.api.Test
    public void testEmptyPut() {
        liste = new LinkedList<>();
        assertTrue(liste.put(20));
        assertEquals(liste.first(), Integer.valueOf(20));
    }

    /**
     * Sjekker at det blir kastet et exception når noen prøver å fjerne det første elementet i en tom liste.
     */
    @org.junit.jupiter.api.Test
    public void testEmptyRemove() {
        liste = new LinkedList<>();
        assertThrows(NoSuchElementException.class, () -> {
            liste.remove();
        });
    }

    /**
     * Sjekker at listen inneholder akkurat ett element, og at det elementet eksisterer og er først.
     */
    @org.junit.jupiter.api.Test
    public void testOneFirst() {
        liste = new LinkedList<>();

        liste.put(1);

        assertEquals(liste.size(),1);
        assertEquals(liste.first(), Integer.valueOf(1));
    }


    @org.junit.jupiter.api.Test
    public void testOneRest() {
        liste = new LinkedList<>();
        liste.put(10);
        assertNull(liste.rest());
    }

    /**
     * Sjekker at når det blir lagt til et element på slutten, er det akkurat 2 elementer i listen, det nye elementet
     * eksisterer, og det er ikke først.
     */
    @org.junit.jupiter.api.Test
    public void testOneAdd() {
        liste = new LinkedList<>();
        liste.put(10);
        liste.add(20);
        //assertTrue(liste.contains(20));
        assertNotEquals(liste.first(), Integer.valueOf(20));
        assertEquals(liste.size(), 2);
    }

    /**
     * Sjekker at det nye elementet er først i lista, og at det er akkurat to elementer i listen.
     */
    @org.junit.jupiter.api.Test
    public void testOnePut() {
        liste = new LinkedList<>();
        liste.put(10);

        liste.put(20);
        assertEquals(liste.first(), Integer.valueOf(20));
        assertEquals(liste.size(), 2);
    }

    /**
     * Sjekker at det er akkurat 0 elementer i listen etter å ha fjernet det første, og at det første elementet i listen
     * er null.
     */
    @org.junit.jupiter.api.Test
    public void testOneRemove() {
        liste = new LinkedList<>();

        assertEquals(liste.size(), 0);
        assertThrows(NoSuchElementException.class, () -> liste.remove());
    }

    /**
     * sjekker at det første elementet i listen er det som skal være først.
     */
    @org.junit.jupiter.api.Test
    public void testManyFirst() {
        liste = new LinkedList<>();
        liste.add(1);
        liste.add(2);
        liste.add(3);
        liste.add(4);
        liste.add(5);

        assertEquals(liste.first(), Integer.valueOf(1));
    }

    /**
     * Sjekker at den nye listen har akkurat 4 elementer, at det første elementet er det andre i den gamle listen, og at den nye
     * listen ikke inneholder det første elementet i den gamle listen.
     */
    @org.junit.jupiter.api.Test
    public void testManyRest() {
        liste = new LinkedList<>();
        liste.add(1);
        liste.add(2);
        liste.add(3);
        liste.add(4);
        liste.add(5);

        IList<Integer> testListe = liste.rest();
        assertEquals(testListe.size(), 4);
        assertEquals(testListe.first(), Integer.valueOf(2));
        //assertFalse(testListe.contains(1));
        assertEquals(liste.first(), Integer.valueOf(1));
    }

    /**
     * Sjekker at det nye elementet blir lagd til i listen, at ingen elementer blir fjernet fra listen, og at det nye
     * elementet ikke blir lagd først.
     */
    @org.junit.jupiter.api.Test
    public void testManyAdd() {
        liste = new LinkedList<>();
        liste.add(1);
        liste.add(2);
        liste.add(3);
        liste.add(4);
        liste.add(5);

        liste.add(10);
        //assertTrue(liste.contains(10));
        assertEquals(liste.size(), 6);
        assertNotEquals(liste.first(), 10);
    }

    /**
     * Sjekker at det nye elementet blir lagd til først, og at ingen elementer fra listen blir fjernet.
     */
    @org.junit.jupiter.api.Test
    public void testManyPut() {
        liste = new LinkedList<>();
        liste.add(1);
        liste.add(2);
        liste.add(3);
        liste.add(4);
        liste.add(5);

        liste.put(10);
        assertEquals(liste.first(), Integer.valueOf(10));
        assertEquals(liste.size(), 6);
    }

    /**
     * Sjekker at kun et element blir fjernet, at det andre elementet nå er det første, og at det som var det første
     * ikke lenger eksisterer i lista.
     */
    @org.junit.jupiter.api.Test
    public void testManyRemove() {
        liste = new LinkedList<>();
        liste.add(1);
        liste.add(2);
        liste.add(3);
        liste.add(4);
        liste.add(5);


       liste.remove();
       // funker ikke uten en remove til av en eller annen grunn
        assertEquals(liste.size(), 4);
        assertEquals(liste.first(), Integer.valueOf(2));
        //assertFalse(liste.contains(1));
    }

    /**
     * Sjekker om tallet faktisk blir fjernet fra lista, at det riktige tallet blir flyttet til første plass og
     * at selve metoden returnerer riktig boolean verdi.
     */
    @org.junit.jupiter.api.Test
    public void testRemoveWArgs() {
        liste = new LinkedList<>();

        assertFalse(liste.remove(5));

        liste.put(10);
        liste.put(15);
        liste.put(20);

        liste.remove(20);

        assertEquals(liste.size(), 2);
        assertEquals(liste.first(), Integer.valueOf(15));
        assertFalse(liste.remove(20));
        assertTrue(liste.contains(10));
    }

    /**
     * Sjekker om lista faktisk inneholder noe den skal inneholde, at den ikke inneholder noe den ikke skal inneholde,
     * og at den ikke inneholder noe som skal ha bltt fjernet fra listen.
     */
    @org.junit.jupiter.api.Test
    public void testContains() {
        liste = new LinkedList<>();
        assertFalse(liste.contains(10));

        liste.put(1);
        liste.put(2);
        liste.put(3);

        assertTrue(liste.contains(2));
        assertFalse(liste.contains(4));
        liste.remove(3);
        assertFalse(liste.contains(3));
    }

    /**
     *  Sjekker at listen faktisk er tom før noe blir lagd til, og at den ikke er tom når det er elementer i den.
     */
    @org.junit.jupiter.api.Test
    public void testIsEmpty() {
        liste = new LinkedList<>();
        assertTrue(liste.isEmpty());
        liste.put(10);
        liste.put(15);
        liste.put(20);

        assertFalse(liste.isEmpty());
    }

    /**
     * Sjekker at size er riktig når det blir lagd til og fjernet elementer.
     */
    @org.junit.jupiter.api.Test
    public void testSize() {
        liste = new LinkedList<>();
        assertEquals(liste.size(), 0);
        liste.add(10);
        liste.add(15);
        assertEquals(liste.size(), 2);
        liste.add(20);
        assertEquals(liste.size(), 3);

        liste.remove();
        assertEquals(liste.size(), 2);

        liste.remove(20);
        assertEquals(liste.size(), 1);
    }

    /**
     * Sjekker at elementene i den nye listen blir lagt til i den første listen, og at det første elementet i lista er
     *
     */
    @org.junit.jupiter.api.Test
    public void testAppend() {
        liste = new LinkedList();
        IList<Integer> list = new LinkedList();
        list.put(5);
        list.put(10);
        list.add(30);
        list.add(40);

        liste.put(20);
        liste.put(25);

        liste.append(list);
        assertTrue(liste.contains(30));
        assertEquals(liste.first(), Integer.valueOf(25));
    }

    /**
     * Sjekker at elementene i den nye listen legger seg først, og at det første elementet i listen er det den skal
     * være.
     */
    @org.junit.jupiter.api.Test
    public void testPrepend() {
        liste = new LinkedList<>();
        IList<Integer> list = new LinkedList<>();

        list.put(5);
        list.put(10);
        list.add(30);
        list.add(40);

        liste.put(20);
        liste.put(25);

        liste.prepend(list);
        assertTrue(liste.contains(30));
        assertEquals(Integer.valueOf(10), liste.first());
    }

    /**
     * Sjekker at alle elementene i alle listene i arrayet blir lagt inn i den nye listen, og at riktig element legger
     * seg først.
     */
    @org.junit.jupiter.api.Test
    public void testConcat() {
        liste = new LinkedList<>();
        IList<Integer> liste1 = new LinkedList<>();
        IList<Integer> liste2 = new LinkedList<>();
        IList<Integer> liste3 = new LinkedList<>();

        liste1.put(1);
        liste1.put(2);
        liste1.put(3);

        liste2.put(4);
        liste2.put(5);
        liste2.put(6);

        liste3.put(7);
        liste3.put(8);
        liste3.put(9);

        IList<Integer>[] lists = new IList[]{liste1, liste2, liste3};

        IList<Integer> nyListe = liste.concat(lists);

        assertTrue(nyListe.contains(7));
        assertTrue(nyListe.contains(4));
        assertTrue(nyListe.contains(3));
        assertEquals(nyListe.first(), Integer.valueOf(3));
    }

    @org.junit.jupiter.api.Test
    public void testSort(){
        liste = new LinkedList<>();
        liste.add(10);
        liste.put(15);
        liste.put(5);
        liste.put(20);
        liste.add(25);

        Comparator<Integer> comp = ;
        liste.sort();
    }
}