package model.utilities;

import model.buildings.office.Office;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MyListTest {
    @Test
    public void testAddToTail() {
        MyList<Office> list = new MyList<>();
        Office office1 = new Office();
        Office office2 = new Office(2, 1);
        Office office3 = new Office(4, 3);

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

    @Test
    public void testAdd() {
        MyList<Office> list = new MyList<>();
        Office office1 = new Office();
        Office office2 = new Office(2, 1);
        Office office3 = new Office(4, 3);

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

    @Test
    public void testRemove() {
        MyList<Office> list = new MyList<>();
        Office office1 = new Office(1, 1);
        Office office2 = new Office(2, 2);
        Office office3 = new Office(3, 3);

        list.addToTail(office1);
        list.addToTail(office2);
        list.addToTail(office3);

        list.remove(1);

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