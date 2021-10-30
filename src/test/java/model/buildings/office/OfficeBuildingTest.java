package model.buildings.office;

import model.buildings.Space;
import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

public class OfficeBuildingTest {

    @Test
    public void testGetFlat() {
        OfficeFloor floor1 = new OfficeFloor(new Office[]{new Office(1, 1)});
        OfficeFloor floor2 = new OfficeFloor(new Office[]{new Office(1, 2), new Office(2, 2)});
        OfficeFloor floor3 = new OfficeFloor(new Office[]{new Office(1, 3), new Office(2, 3), new Office(3, 3)});

        OfficeBuilding officeBuilding = new OfficeBuilding(new OfficeFloor[]{floor1, floor2, floor3});

        assertEquals(new Office(1, 1), officeBuilding.getSpace(0));
        assertEquals(new Office(3, 3), officeBuilding.getSpace(5));
        assertEquals(new Office(1, 2), officeBuilding.getSpace(1));
        assertEquals(new Office(2, 2), officeBuilding.getSpace(2));
    }

    @Test
    public void testGetSquares() {
        OfficeFloor floor1 = new OfficeFloor(new Office[]{new Office(1, 1)});
        OfficeFloor floor2 = new OfficeFloor(new Office[]{new Office(1, 2), new Office(2, 2)});
        OfficeFloor floor3 = new OfficeFloor(new Office[]{new Office(1, 3), new Office(2, 3), new Office(3, 3)});

        OfficeBuilding officeBuilding = new OfficeBuilding(new OfficeFloor[]{floor1, floor2, floor3});

        Space[] array = officeBuilding.getSquares();

        assertTrue(IntStream.range(0, array.length - 1).allMatch(i -> array[i + 1].getSpace() <= array[i].getSpace()));
    }

    @Test
    public void testTestEquals() {
        OfficeFloor floor1 = new OfficeFloor(new Office[]{new Office(1, 1)});
        OfficeFloor floor2 = new OfficeFloor(new Office[]{new Office(1, 2), new Office(2, 2)});
        OfficeFloor floor3 = new OfficeFloor(new Office[]{new Office(1, 3), new Office(2, 3), new Office(3, 3)});
        OfficeFloor floor4 = new OfficeFloor(new Office[]{new Office(1, 3), new Office(3, 3), new Office(3, 3)});

        OfficeBuilding officeBuilding1 = new OfficeBuilding(new OfficeFloor[]{floor1, floor2, floor3});
        OfficeBuilding officeBuilding2 = new OfficeBuilding(new OfficeFloor[]{floor1, floor2, floor3});
        OfficeBuilding officeBuilding3 = new OfficeBuilding(new OfficeFloor[]{floor2, floor1, floor3});
        OfficeBuilding officeBuilding4 = new OfficeBuilding(new OfficeFloor[]{floor1, floor2, floor4});
        OfficeBuilding officeBuilding5 = new OfficeBuilding(new OfficeFloor[]{floor1, floor2});

        assertEquals(officeBuilding1, officeBuilding2);
        assertNotEquals(officeBuilding1, officeBuilding3);
        assertNotEquals(officeBuilding1, officeBuilding4);
        assertNotEquals(officeBuilding1, officeBuilding5);
    }
}