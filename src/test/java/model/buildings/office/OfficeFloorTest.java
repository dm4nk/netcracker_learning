package model.buildings.office;

import model.exeptions.SpaceIndexOutOfBoundsException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class OfficeFloorTest {

    @Test
    public void testAddFlat() {
        OfficeFloor officeFloor = new OfficeFloor(new Office[]{new Office(1, 1), new Office(2, 2), new Office(3, 3)});

        officeFloor.addSpace(1, new Office(4, 4));
        assertEquals(4, officeFloor.size());
        assertEquals(new Office(4, 4), officeFloor.getSpace(1));

        officeFloor.addSpace(4, new Office(5, 5));
        assertEquals(5, officeFloor.size());
        assertEquals(new Office(5, 5), officeFloor.getSpace(4));

        assertThrows(SpaceIndexOutOfBoundsException.class, () -> officeFloor.addSpace(6, new Office(6, 6)));
        assertThrows(SpaceIndexOutOfBoundsException.class, () -> officeFloor.addSpace(-1, new Office(6, 6)));
    }

    @Test
    public void testDeleteFlat() {
        OfficeFloor officeFloor = new OfficeFloor(new Office[]{new Office(1, 1), new Office(2, 2), new Office(3, 3)});

        officeFloor.removeSpace(1);
        assertEquals(2, officeFloor.size());
        assertEquals(new Office(1, 1), officeFloor.getSpace(0));
        assertEquals(new Office(3, 3), officeFloor.getSpace(1));

        assertThrows(SpaceIndexOutOfBoundsException.class, () -> officeFloor.removeSpace(6));
        assertThrows(SpaceIndexOutOfBoundsException.class, () -> officeFloor.removeSpace(-1));

        officeFloor.removeSpace(0);
        assertEquals(new Office(3, 3), officeFloor.getSpace(0));

        assertThrows(IllegalStateException.class, () -> officeFloor.removeSpace(0));
    }

    @Test
    public void testTestEquals() {
        OfficeFloor officeFloor1 = new OfficeFloor(new Office[]{new Office(1, 1), new Office(2, 2), new Office(3, 3)});
        OfficeFloor officeFloor2 = new OfficeFloor(new Office[]{new Office(1, 1), new Office(2, 2), new Office(3, 3)});
        OfficeFloor officeFloor3 = new OfficeFloor(new Office[]{new Office(2, 1), new Office(2, 2), new Office(3, 3)});
        OfficeFloor officeFloor4 = new OfficeFloor(new Office[]{new Office(2, 1), new Office(3, 3)});

        assertEquals(officeFloor1, officeFloor2);
        assertNotEquals(officeFloor1, officeFloor3);
        assertNotEquals(officeFloor1, officeFloor4);
    }
}