package model.utilities.task4;

import junit.framework.TestCase;
import model.buildings.Building;
import model.buildings.flat.Dwelling;
import model.buildings.flat.DwellingFloor;
import model.buildings.flat.Flat;
import model.buildings.office.Office;

import java.io.*;
import java.util.Scanner;

public class BuildingsTest extends TestCase {

    public void testInputOutputBuilding() throws IOException {
        DwellingFloor floor1 = new DwellingFloor(new Flat[]{new Flat(1, 1)});
        DwellingFloor floor2 = new DwellingFloor(new Flat[]{new Flat(2, 1), new Flat(2, 2)});
        DwellingFloor floor3 = new DwellingFloor(new Flat[]{new Flat(3, 1), new Flat(3, 2), new Flat(3, 3)});

        Dwelling building1 = new Dwelling(new DwellingFloor[]{floor1, floor2, floor3});
        Building building2;

        File file = new File("src/test/resources/buildingsTestInputOutput.txt");
        try (OutputStream outputStream = new FileOutputStream(file); InputStream inputStream = new FileInputStream(file)) {
            Buildings.outputBuilding(building1, outputStream);
            building2 = Buildings.inputBuilding(inputStream);
        }
        assertEquals(building1, building2);
    }

    public void testReadWriteBuilding() throws IOException {
        DwellingFloor floor1 = new DwellingFloor(new Flat[]{new Flat(1, 1)});
        DwellingFloor floor2 = new DwellingFloor(new Flat[]{new Flat(2, 1), new Flat(2, 2)});
        DwellingFloor floor3 = new DwellingFloor(new Flat[]{new Flat(3, 1), new Flat(3, 2), new Flat(3, 3)});

        Dwelling building1 = new Dwelling(new DwellingFloor[]{floor1, floor2, floor3});
        Building building2;

        File file = new File("src/test/resources/buildingsTestReadWrite.txt");
        try (Writer writer = new FileWriter(file); Reader reader = new FileReader(file)) {
            Buildings.writeBuilding(building1, writer);
            building2 = Buildings.readBuilding(reader);
        }

        assertEquals(building1, building2);
    }

    public void testSerializeDeserializeBuilding() throws IOException {
        DwellingFloor floor1 = new DwellingFloor(new Office[]{new Office(1, 1)});
        DwellingFloor floor2 = new DwellingFloor(new Office[]{new Office(2, 1), new Office(2, 2)});
        DwellingFloor floor3 = new DwellingFloor(new Office[]{new Office(3, 1), new Office(3, 2), new Office(3, 3)});

        Dwelling building1 = new Dwelling(new DwellingFloor[]{floor1, floor2, floor3});
        Building building2;

        File file = new File("src/test/resources/buildingsTestSerializeDeserialize.txt");
        try (OutputStream outputStream = new FileOutputStream(file); InputStream inputStream = new FileInputStream(file)) {
            Buildings.serializeBuilding(building1, outputStream);
            building2 = Buildings.deserializeBuilding(inputStream);
        }

        assertEquals(building1, building2);
    }


    public void testWriteBuildingFormatReadBuilding() throws IOException {
        DwellingFloor floor1 = new DwellingFloor(new Flat[]{new Flat(1, 1)});
        DwellingFloor floor2 = new DwellingFloor(new Flat[]{new Flat(2, 1), new Flat(2, 2)});
        DwellingFloor floor3 = new DwellingFloor(new Flat[]{new Flat(3, 1), new Flat(3, 2), new Flat(3, 3)});


        Dwelling building1 = new Dwelling(new DwellingFloor[]{floor1, floor2, floor3});
        Building building2;

        File file = new File("src/test/resources/buildingsWriteBuildingFormatReadBuilding.txt");
        try (Writer writer = new FileWriter(file); Scanner scanner = new Scanner(file)) {
            Buildings.writeBuildingFormat(building1, writer);
            building2 = Buildings.readBuilding(scanner);
        }

        assertEquals(building1, building2);
    }
}