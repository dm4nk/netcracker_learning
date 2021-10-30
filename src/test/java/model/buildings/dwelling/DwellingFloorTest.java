package model.buildings.dwelling;

import model.buildings.office.Office;
import model.exeptions.SpaceIndexOutOfBoundsException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DwellingFloorTest {

    @Test
    public void testAddFlat() {
        DwellingFloor dwellingFloor = new DwellingFloor(new Office[]{new Office(1, 1), new Office(2, 2), new Office(3, 3)});

        dwellingFloor.addSpace(1, new Office(4, 4));
        assertEquals(4, dwellingFloor.size());
        assertEquals(new Office(4, 4), dwellingFloor.getSpace(1));

        dwellingFloor.addSpace(4, new Office(5, 5));
        assertEquals(5, dwellingFloor.size());
        assertEquals(new Office(5, 5), dwellingFloor.getSpace(4));

        assertThrows(SpaceIndexOutOfBoundsException.class, () -> dwellingFloor.addSpace(6, new Office(6, 6)));
        assertThrows(SpaceIndexOutOfBoundsException.class, () -> dwellingFloor.addSpace(-1, new Office(6, 6)));
    }

    @Test
    public void testDeleteFlat() {
        DwellingFloor dwellingFloor = new DwellingFloor(new Office[]{new Office(1, 1), new Office(2, 2), new Office(3, 3)});

        dwellingFloor.removeSpace(1);
        assertEquals(2, dwellingFloor.size());
        assertEquals(new Office(1, 1), dwellingFloor.getSpace(0));
        assertEquals(new Office(3, 3), dwellingFloor.getSpace(1));

        assertThrows(SpaceIndexOutOfBoundsException.class, () -> dwellingFloor.removeSpace(6));
        assertThrows(SpaceIndexOutOfBoundsException.class, () -> dwellingFloor.removeSpace(-1));

        dwellingFloor.removeSpace(0);
        assertEquals(new Office(3, 3), dwellingFloor.getSpace(0));

        assertThrows(IllegalStateException.class, () -> dwellingFloor.removeSpace(0));
    }

    @Test
    public void testTestEquals() {
        DwellingFloor floor1 = new DwellingFloor(new Office[]{new Office(1, 1)});
        DwellingFloor floor2 = new DwellingFloor(new Office[]{new Office(1, 1)});
        DwellingFloor floor3 = new DwellingFloor(new Office[]{new Office(1, 2), new Office(2, 2)});

        assertEquals(floor1, floor2);
        assertNotEquals(floor1, floor3);
    }
}