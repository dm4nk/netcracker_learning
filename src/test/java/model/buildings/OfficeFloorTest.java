package model.buildings;

import junit.framework.TestCase;
import model.buildings.office.Office;
import model.buildings.office.OfficeFloor;
import model.exeptions.SpaceIndexOutOfBoundsException;

import static org.junit.Assert.assertThrows;

public class OfficeFloorTest extends TestCase {


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
}