package model.buildings.office;

import model.buildings.Space;
import model.buildings.dwelling.Flat;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class OfficeTest {
    @Test
    public void testTestHashCode() {
        Space flat = new Flat(3, 1);
        Space office1 = new Office(3, 1);
        Space office2 = new Office(3, 1);

        assertEquals(office1.hashCode(), office2.hashCode());
        assertNotEquals(flat.hashCode(), office1.hashCode());
    }
}