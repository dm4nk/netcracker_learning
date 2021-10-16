package model.buildings;

import junit.framework.TestCase;
import model.buildings.flat.DwellingFloor;
import model.buildings.office.Office;
import model.exeptions.SpaceIndexOutOfBoundsException;

import static org.junit.Assert.assertThrows;

public class DwellingFloorTest extends TestCase {


    public void testAddFlat() {
        DwellingFloor dwellingFloor = new DwellingFloor(new Office[]{new Office(1, 1), new Office(2, 2), new Office(3, 3)});

        dwellingFloor.addFlat(1, new Office(4, 4));
        assertEquals(4, dwellingFloor.size());
        assertEquals(new Office(4, 4), dwellingFloor.getFlat(1));

        dwellingFloor.addFlat(4, new Office(5, 5));
        assertEquals(5, dwellingFloor.size());
        assertEquals(new Office(5, 5), dwellingFloor.getFlat(4));

        assertThrows(SpaceIndexOutOfBoundsException.class, () -> dwellingFloor.addFlat(6, new Office(6, 6)));
        assertThrows(SpaceIndexOutOfBoundsException.class, () -> dwellingFloor.addFlat(-1, new Office(6, 6)));
    }

    public void testDeleteFlat() {
        DwellingFloor dwellingFloor = new DwellingFloor(new Office[]{new Office(1, 1), new Office(2, 2), new Office(3, 3)});

        dwellingFloor.removeFlat(1);
        assertEquals(2, dwellingFloor.size());
        assertEquals(new Office(1, 1), dwellingFloor.getFlat(0));
        assertEquals(new Office(3, 3), dwellingFloor.getFlat(1));

        assertThrows(SpaceIndexOutOfBoundsException.class, () -> dwellingFloor.removeFlat(6));
        assertThrows(SpaceIndexOutOfBoundsException.class, () -> dwellingFloor.removeFlat(-1));

        dwellingFloor.removeFlat(0);
        assertEquals(new Office(3, 3), dwellingFloor.getFlat(0));

        assertThrows(IllegalStateException.class, () -> dwellingFloor.removeFlat(0));
    }
}