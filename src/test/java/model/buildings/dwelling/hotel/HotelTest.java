package model.buildings.dwelling.hotel;

import model.buildings.Floor;
import model.buildings.Space;
import model.buildings.dwelling.DwellingFloor;
import model.buildings.dwelling.Flat;
import model.buildings.office.Office;
import model.buildings.office.OfficeFloor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class HotelTest {

    HotelFloor floor1;
    HotelFloor floor2;
    Floor floor3;
    Floor floor4;

    private Hotel hotel;

    @BeforeEach
    public void initHotel() {
        floor1 = new HotelFloor(new Space[]{new Flat(1, 1), new Office(2, 1)});
        floor2 = new HotelFloor(new Space[]{new Office(1, 2), new Office(2, 2), new Flat(2, 3)});
        floor3 = new DwellingFloor(new Space[]{new Flat(1, 3)});
        floor4 = new OfficeFloor(new Space[]{new Office(1, 4)});

        floor1.setStars(3);
        floor2.setStars(4);

        hotel = new Hotel(new Floor[]{floor1, floor2, floor3, floor4});
    }

    @Test
    public void testGetStars() {
        assertEquals(4, hotel.getStars());
        assertThrows(IllegalArgumentException.class, () -> floor1.setStars(0));
        assertThrows(IllegalArgumentException.class, () -> floor1.setStars(6));
    }

    @Test
    public void testGetBestSpace() {
        assertEquals(new Flat(2, 3), hotel.getBestSpace());
    }
}