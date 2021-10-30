package model.buildings.dwelling;

import model.buildings.Space;
import model.buildings.office.Office;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class FlatTest {
    @Test
    public void testTestHashCode() {
        Space flat1 = new Flat(3, 1);
        Space flat2 = new Flat(3, 1);
        Space office = new Office(3, 1);

        assertEquals(flat1.hashCode(), flat2.hashCode());
        assertNotEquals(flat1.hashCode(), office.hashCode());
    }
}