package model.buildings;

import junit.framework.TestCase;
import org.junit.Assert;

import java.util.stream.IntStream;

public class DwellingTest extends TestCase {

    public void testGetFlat() {
        DwellingFloor floor1 = new DwellingFloor(new Flat[]{new Flat(1, 1)});
        DwellingFloor floor2 = new DwellingFloor(new Flat[]{new Flat(2, 1), new Flat(2, 2)});
        DwellingFloor floor3 = new DwellingFloor(new Flat[]{new Flat(3, 1), new Flat(3, 2), new Flat(3, 3)});

        Dwelling dwelling = new Dwelling(new DwellingFloor[]{floor1, floor2, floor3});

        assertEquals(new Flat(1, 1), dwelling.getFlat(0));
        assertEquals(new Flat(3, 3), dwelling.getFlat(5));
        assertEquals(new Flat(2, 1), dwelling.getFlat(1));
        assertEquals(new Flat(2, 2), dwelling.getFlat(2));
    }

    public void testGetSquares() {
        DwellingFloor floor1 = new DwellingFloor(new Flat[]{new Flat(1, 1)});
        DwellingFloor floor2 = new DwellingFloor(new Flat[]{new Flat(2, 1), new Flat(2, 2)});
        DwellingFloor floor3 = new DwellingFloor(new Flat[]{new Flat(3, 1), new Flat(3, 2), new Flat(3, 3)});

        Dwelling dwelling = new Dwelling(new DwellingFloor[]{floor1, floor2, floor3});

        double[] array = dwelling.getSquares();

        Assert.assertTrue(IntStream.range(0, array.length - 1).allMatch(i -> array[i + 1] <= array[i]));
    }
}