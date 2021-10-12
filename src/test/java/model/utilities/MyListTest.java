package model.utilities;

import junit.framework.TestCase;
import model.buildings.office.Office;

import java.util.Date;

public class MyListTest extends TestCase {

    public void testAddToTail() {
        MyList<Office> list = new MyList();
        Office office1 = new Office();
        Office office2 = new Office(1, 2);
        Office office3 = new Office(3, 4);

        list.addToTail(office1);

        assertEquals(1, list.size());
        assertEquals(office1, list.get(0));

        list.addToTail(office2);

        assertEquals(2, list.size());
        assertEquals(office1, list.get(0));
        assertEquals(office2, list.get(1));

        list.addToTail(office3);

        assertEquals(3, list.size());
        assertEquals(office1, list.get(0));
        assertEquals(office2, list.get(1));
        assertEquals(office3, list.get(2));
    }

    public void testAdd() {
        MyList<Office> list = new MyList();
        Office office1 = new Office();
        Office office2 = new Office(1, 2);
        Office office3 = new Office(3, 4);

        list.addToTail(office2);

        list.add(0, office1);

        assertEquals(2, list.size());
        assertEquals(office1, list.get(0));
        assertEquals(office2, list.get(1));

        list.add(1, office3);

        assertEquals(3, list.size());
        assertEquals(office1, list.get(0));
        assertEquals(office3, list.get(1));
        assertEquals(office2, list.get(2));

    }

    public void testRemove() {
        MyList<Office> list = new MyList();
        Office office1 = new Office(1, 1);
        Office office2 = new Office(2, 2);
        Office office3 = new Office(3, 3);

        list.addToTail(office1);
        list.addToTail(office2);
        list.addToTail(office3);

        System.out.println(list.get(0));
        System.out.println(list.get(1));
        System.out.println(list.get(2));

        list.remove(1);

        System.out.println(list.get(0));
        System.out.println(list.get(1));

        assertEquals(2, list.size());
        assertEquals(office1, list.get(0));
        assertEquals(office3, list.get(1));

        list.remove(0);

        assertEquals(1, list.size());
        assertEquals(office3, list.get(0));

        list.remove(0);

        assertEquals(0, list.size());
    }
}