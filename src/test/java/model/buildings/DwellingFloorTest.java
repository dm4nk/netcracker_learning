package model.buildings;

import junit.framework.TestCase;

import static org.junit.Assert.assertThrows;

public class DwellingFloorTest extends TestCase {


    public void testAddFlat() {
        DwellingFloor dwellingFloor = new DwellingFloor(new Flat[]{new Flat(1, 1), new Flat(2, 2), new Flat(3, 3)});

        dwellingFloor.addFlat(1, new Flat(4, 4));
        assertEquals(4, dwellingFloor.size());
        assertEquals(new Flat(4, 4), dwellingFloor.getFlat(1));

        dwellingFloor.addFlat(4, new Flat(5, 5));
        assertEquals(5, dwellingFloor.size());
        assertEquals(new Flat(5, 5), dwellingFloor.getFlat(4));

        assertThrows(IndexOutOfBoundsException.class, () -> dwellingFloor.addFlat(6, new Flat(6, 6)));
        assertThrows(IndexOutOfBoundsException.class, () -> dwellingFloor.addFlat(-1, new Flat(6, 6)));
    }

    public void testDeleteFlat() {
        DwellingFloor dwellingFloor = new DwellingFloor(new Flat[]{new Flat(1, 1), new Flat(2, 2), new Flat(3, 3)});

        dwellingFloor.removeFlat(1);
        assertEquals(2, dwellingFloor.size());
        assertEquals(new Flat(1, 1), dwellingFloor.getFlat(0));
        assertEquals(new Flat(3, 3), dwellingFloor.getFlat(1));

        assertThrows(IndexOutOfBoundsException.class, () -> dwellingFloor.removeFlat(6));
        assertThrows(IndexOutOfBoundsException.class, () -> dwellingFloor.removeFlat(-1));

        dwellingFloor.removeFlat(0);
        assertEquals(new Flat(3, 3), dwellingFloor.getFlat(0));

        assertThrows(IllegalStateException.class, () -> dwellingFloor.removeFlat(0));
    }
}