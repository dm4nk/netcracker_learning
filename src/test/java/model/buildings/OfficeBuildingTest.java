package model.buildings;

import junit.framework.TestCase;
import model.buildings.office.Office;
import model.buildings.office.OfficeBuilding;
import model.buildings.office.OfficeFloor;
import org.junit.Assert;

import java.util.stream.IntStream;

public class OfficeBuildingTest extends TestCase {

    public void testGetFlat() {
        OfficeFloor floor1 = new OfficeFloor(new Office[]{new Office(1, 1)});
        OfficeFloor floor2 = new OfficeFloor(new Office[]{new Office(2, 1), new Office(2, 2)});
        OfficeFloor floor3 = new OfficeFloor(new Office[]{new Office(3, 1), new Office(3, 2), new Office(3, 3)});

        OfficeBuilding officeBuilding = new OfficeBuilding(new OfficeFloor[]{floor1, floor2, floor3});

        assertEquals(new Office(1, 1), officeBuilding.getOffice(0));
        assertEquals(new Office(3, 3), officeBuilding.getOffice(5));
        assertEquals(new Office(2, 1), officeBuilding.getOffice(1));
        assertEquals(new Office(2, 2), officeBuilding.getOffice(2));
    }

    public void testGetSquares() {
        OfficeFloor floor1 = new OfficeFloor(new Office[]{new Office(1, 1)});
        OfficeFloor floor2 = new OfficeFloor(new Office[]{new Office(2, 1), new Office(2, 2)});
        OfficeFloor floor3 = new OfficeFloor(new Office[]{new Office(3, 1), new Office(3, 2), new Office(3, 3)});

        OfficeBuilding officeBuilding = new OfficeBuilding(new OfficeFloor[]{floor1, floor2, floor3});

        double[] array = officeBuilding.getSquares();

        Assert.assertTrue(IntStream.range(0, array.length - 1).allMatch(i -> array[i + 1] <= array[i]));
    }
}