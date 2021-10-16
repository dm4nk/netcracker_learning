package model.utilities.task4;

import junit.framework.TestCase;
import model.buildings.Building;
import model.buildings.flat.Dwelling;
import model.buildings.flat.DwellingFloor;
import model.buildings.office.Office;

import java.io.*;

public class BuildingsTest extends TestCase {

    public void testInputOutputBuilding() throws IOException {
        DwellingFloor floor1 = new DwellingFloor(new Office[]{new Office(1, 1)});
        DwellingFloor floor2 = new DwellingFloor(new Office[]{new Office(2, 1), new Office(2, 2)});
        DwellingFloor floor3 = new DwellingFloor(new Office[]{new Office(3, 1), new Office(3, 2), new Office(3, 3)});

        Dwelling building1 = new Dwelling(new DwellingFloor[]{floor1, floor2, floor3});

        OutputStream outputStream = new FileOutputStream(new File("src/test/resources/buildingsTest.txt"));
        Buildings.outputBuilding(building1, outputStream);

        Building building2 = Buildings.inputBuilding(new FileInputStream(new File("src/test/resources/buildingsTest.txt")));

        assertEquals(building1.toString(), building2.toString());
    }

    public void testWriteBuilding() {
    }

    public void testReadBuilding() {
    }
}