package model.buildings.office;

import junit.framework.TestCase;
import model.buildings.Space;
import model.buildings.flat.Flat;

import static org.junit.Assert.assertNotEquals;

public class OfficeTest extends TestCase {

    public void testTestHashCode() {
        Space flat = new Flat(1, 3);
        Space office1 = new Office(1, 3);
        Space office2 = new Office(1, 3);

        assertEquals(office1.hashCode(), office2.hashCode());
        assertNotEquals(flat.hashCode(), office1.hashCode());
    }
}