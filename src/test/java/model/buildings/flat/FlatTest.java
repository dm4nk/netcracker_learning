package model.buildings.flat;

import junit.framework.TestCase;
import model.buildings.Space;
import model.buildings.office.Office;

import static org.junit.Assert.assertNotEquals;

public class FlatTest extends TestCase {

    public void testTestHashCode() {
        Space flat1 = new Flat(1, 3);
        Space flat2 = new Flat(1, 3);
        Space office = new Office(1, 3);

        assertEquals(flat1.hashCode(), flat2.hashCode());
        assertNotEquals(flat1.hashCode(), office.hashCode());
    }
}