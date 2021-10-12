package model.buildings;

import junit.framework.TestCase;
import model.buildings.office.Office;
import model.buildings.office.OfficeFloor;
import model.exeptions.SpaceIndexOutOfBoundsException;

import static org.junit.Assert.assertThrows;

public class OfficeFloorTest extends TestCase {


    public void testAddFlat() {
        OfficeFloor officeFloor = new OfficeFloor(new Office[]{new Office(1, 1), new Office(2, 2), new Office(3, 3)});

        officeFloor.addFlat(1, new Office(4, 4));
        assertEquals(4, officeFloor.size());
        assertEquals(new Office(4, 4), officeFloor.getFlat(1));

        officeFloor.addFlat(4, new Office(5, 5));
        assertEquals(5, officeFloor.size());
        assertEquals(new Office(5, 5), officeFloor.getFlat(4));

        assertThrows(SpaceIndexOutOfBoundsException.class, () -> officeFloor.addFlat(6, new Office(6, 6)));
        assertThrows(SpaceIndexOutOfBoundsException.class, () -> officeFloor.addFlat(-1, new Office(6, 6)));
    }

    public void testDeleteFlat() {
        OfficeFloor officeFloor = new OfficeFloor(new Office[]{new Office(1, 1), new Office(2, 2), new Office(3, 3)});

        officeFloor.removeFlat(1);
        assertEquals(2, officeFloor.size());
        assertEquals(new Office(1, 1), officeFloor.getFlat(0));
        assertEquals(new Office(3, 3), officeFloor.getFlat(1));

        assertThrows(SpaceIndexOutOfBoundsException.class, () -> officeFloor.removeFlat(6));
        assertThrows(SpaceIndexOutOfBoundsException.class, () -> officeFloor.removeFlat(-1));

        officeFloor.removeFlat(0);
        assertEquals(new Office(3, 3), officeFloor.getFlat(0));

        assertThrows(IllegalStateException.class, () -> officeFloor.removeFlat(0));
    }
}