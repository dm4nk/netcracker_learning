package model.utilities;

import model.buildings.Building;
import model.buildings.Floor;
import model.buildings.Space;
import model.buildings.dwelling.Dwelling;
import model.buildings.dwelling.DwellingFloor;
import model.buildings.dwelling.Flat;
import model.buildings.dwelling.hotel.Hotel;
import model.buildings.dwelling.hotel.HotelFloor;
import model.buildings.office.Office;
import model.buildings.office.OfficeBuilding;
import model.buildings.office.OfficeFloor;
import model.utilities.comparators.FloorComparator;
import model.utilities.comparators.SpaceComparator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.util.Comparator;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

public class BuildingsTest {

    DwellingFloor floor1;
    DwellingFloor floor2;
    DwellingFloor floor3;

    Dwelling building1;

    @BeforeEach
    public void initDwelling() {
        floor1 = new DwellingFloor(new Flat[]{new Flat(1, 1)});
        floor2 = new DwellingFloor(new Flat[]{new Flat(1, 2), new Flat(2, 2)});
        floor3 = new DwellingFloor(new Flat[]{new Flat(1, 3), new Flat(2, 3), new Flat(3, 3)});

        building1 = new Dwelling(new DwellingFloor[]{floor1, floor2, floor3});
    }

    @Test
    public void testInputOutputBuilding() throws IOException {
        Building building2;

        File file = new File("src/test/resources/buildingsTestInputOutput.txt");
        try (OutputStream outputStream = new FileOutputStream(file); InputStream inputStream = new FileInputStream(file)) {
            Buildings.outputBuilding(building1, outputStream);
            building2 = Buildings.inputBuilding(inputStream);
        }
        assertEquals(building1, building2);
    }

    @Test
    public void testReadWriteBuilding() throws IOException {
        Building building2;

        File file = new File("src/test/resources/buildingsTestReadWrite.txt");
        try (Writer writer = new FileWriter(file); Reader reader = new FileReader(file)) {
            Buildings.writeBuilding(building1, writer);
            building2 = Buildings.readBuilding(reader);
        }

        assertEquals(building1, building2);
    }

    @Test
    public void testSerializeDeserializeBuilding() throws IOException {
        Floor floor1 = new OfficeFloor(new Office[]{new Office(1, 1)});
        Floor floor2 = new DwellingFloor(new Office[]{new Office(1, 2), new Office(2, 2)});
        Floor floor3 = new DwellingFloor(new Office[]{new Office(1, 3), new Office(2, 3), new Office(3, 3)});

        Building building1 = new OfficeBuilding(new Floor[]{floor1, floor2, floor3});
        Building building2;

        File file = new File("src/test/resources/buildingsTestSerializeDeserialize.txt");
        try (OutputStream outputStream = new FileOutputStream(file); InputStream inputStream = new FileInputStream(file)) {
            Buildings.serializeBuilding(building1, outputStream);
            building2 = Buildings.deserializeBuilding(inputStream);
        }

        assertEquals(building1, building2);
    }

    @Test
    public void testWriteBuildingFormatReadBuilding() throws IOException {
        Building building2;

        File file = new File("src/test/resources/buildingsWriteBuildingFormatReadBuilding.txt");
        try (Writer writer = new FileWriter(file); Scanner scanner = new Scanner(file)) {
            Buildings.writeBuildingFormat(building1, writer);
            building2 = Buildings.readBuilding(scanner);
        }

        assertEquals(building1, building2);
    }

    @Test
    void sort() {
        Floor floor1 = new OfficeFloor(new Office[]{new Office(1, 1)});
        Floor floor3 = new DwellingFloor(new Office[]{new Office(1, 3), new Office(2, 33), new Office(3, 3)});
        Floor floor2 = new DwellingFloor(new Office[]{new Office(1, 4), new Office(2, 2)});

        Building building1 = new OfficeBuilding(new Floor[]{floor1, floor2, floor3});

        Floor[] floors = building1.getAllFloors();

        Buildings.sort(floors);

        assertEquals(1, floors[0].size());
        assertEquals(2, floors[1].size());
        assertEquals(3, floors[2].size());

        Space[] spaces = floor3.spaces();

        Buildings.sort(spaces);

        assertEquals(new Office(1, 3), spaces[0]);
        assertEquals(new Office(3, 3), spaces[1]);
        assertEquals(new Office(2, 33), spaces[2]);
    }

    @Test
    void testSort() {
        Floor floor1 = new OfficeFloor(new Office[]{new Office(1, 1)});
        Floor floor3 = new DwellingFloor(new Office[]{new Office(1, 3), new Office(2, 33), new Office(3, 3)});
        Floor floor2 = new DwellingFloor(new Office[]{new Office(1, 4), new Office(2, 2)});

        Building building1 = new OfficeBuilding(new Floor[]{floor1, floor2, floor3});

        Floor[] floors = building1.getAllFloors();
        Comparator<Floor> floorComparator = new FloorComparator();

        Buildings.sort(floors, floorComparator);

        assertEquals(3, floors[0].size());
        assertEquals(2, floors[1].size());
        assertEquals(1, floors[2].size());

        Space[] spaces = floor3.spaces();
        Comparator<Space> spaceComparator = new SpaceComparator();

        Buildings.sort(spaces, spaceComparator);

        assertEquals(new Office(3, 3), spaces[0]);
        assertEquals(new Office(2, 33), spaces[1]);
        assertEquals(new Office(1, 3), spaces[2]);
    }

    @Test
    void testCreateSpace() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        int roomsCount = 3;
        double area = 19.;

        Space officeSpace = Buildings.createSpace(area, Office.class);
        Space dwellingSpace = Buildings.createSpace(roomsCount, area, Flat.class);

        assertInstanceOf(Office.class, officeSpace);
        assertEquals(officeSpace.getSpace(), area);

        assertInstanceOf(Flat.class, dwellingSpace);
        assertEquals(dwellingSpace.getSpace(), area);
        assertEquals(dwellingSpace.getRooms(), roomsCount);
    }

    @Test
    void testCreateFloor() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        int spaceCount = 12;
        Space[] spaces = new Space[]{new Flat(), new Office()};

        Floor hotelFloor = Buildings.createFloor(spaceCount, HotelFloor.class);
        Floor dwellingFloor = Buildings.createFloor(spaceCount, DwellingFloor.class);
        Floor officeFloor = Buildings.createFloor(spaces, OfficeFloor.class);

        assertInstanceOf(HotelFloor.class, hotelFloor);
        assertEquals(spaceCount, hotelFloor.size());

        assertInstanceOf(DwellingFloor.class, dwellingFloor);
        assertEquals(spaceCount, dwellingFloor.size());

        assertInstanceOf(OfficeFloor.class, officeFloor);
        assertArrayEquals(spaces, officeFloor.spaces());
    }

    @Test
    void testCreateBuilding() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        int floorsCount = 4;
        int[] spacesCount = new int[]{1, 2, 3, 4};
        Floor[] floors = new Floor[]{floor1, floor2};

        Building officeBuilding = Buildings.createBuilding(floorsCount, spacesCount, OfficeBuilding.class);
        Building dwelling = Buildings.createBuilding(floorsCount, spacesCount, Dwelling.class);
        Building hotel = Buildings.createBuilding(floors, Hotel.class);

        assertInstanceOf(OfficeBuilding.class, officeBuilding);
        assertEquals(floorsCount, officeBuilding.size());
        assertEquals(1 + 2 + 3 + 4, officeBuilding.flatsCount());

        assertInstanceOf(Dwelling.class, dwelling);
        assertEquals(floorsCount, dwelling.size());
        assertEquals(1 + 2 + 3 + 4, dwelling.flatsCount());

        assertInstanceOf(Hotel.class, hotel);
        assertArrayEquals(floors, hotel.getAllFloors());
    }
}