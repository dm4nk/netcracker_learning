package model.buildings;

import junit.framework.TestCase;
import model.buildings.flat.Dwelling;
import model.buildings.flat.DwellingFloor;
import model.buildings.office.Office;
import org.junit.Assert;

import java.util.stream.IntStream;

public class DwellingTest extends TestCase {

    public void testGetFlat() {
        DwellingFloor floor1 = new DwellingFloor(new Office[]{new Office(1, 1)});
        DwellingFloor floor2 = new DwellingFloor(new Office[]{new Office(2, 1), new Office(2, 2)});
        DwellingFloor floor3 = new DwellingFloor(new Office[]{new Office(3, 1), new Office(3, 2), new Office(3, 3)});

        Dwelling building = new Dwelling(new DwellingFloor[]{floor1, floor2, floor3});

        assertEquals(new Office(1, 1), building.getSpace(0));
        assertEquals(new Office(3, 3), building.getSpace(5));
        assertEquals(new Office(2, 1), building.getSpace(1));
        assertEquals(new Office(2, 2), building.getSpace(2));
    }

    public void testGetSquares() {
        DwellingFloor floor1 = new DwellingFloor(new Office[]{new Office(1, 1)});
        DwellingFloor floor2 = new DwellingFloor(new Office[]{new Office(2, 1), new Office(2, 2)});
        DwellingFloor floor3 = new DwellingFloor(new Office[]{new Office(3, 1), new Office(3, 2), new Office(3, 3)});

        Dwelling building = new Dwelling(new DwellingFloor[]{floor1, floor2, floor3});

        Space[] array = building.getSquares();

        Assert.assertTrue(IntStream.range(0, array.length - 1).allMatch(i -> array[i + 1].getSpace() <= array[i].getSpace()));
    }
}