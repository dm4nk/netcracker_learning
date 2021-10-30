import model.buildings.Building;
import model.buildings.Floor;
import model.buildings.dwelling.Dwelling;
import model.buildings.dwelling.DwellingFloor;
import model.buildings.dwelling.Flat;
import model.buildings.office.Office;
import model.buildings.office.OfficeFloor;
import model.utilities.Buildings;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class Main {
    public static void main(String[] args) throws IOException {
        DwellingFloor floor1 = new DwellingFloor(new Flat[]{new Flat(1, 1)});
        DwellingFloor floor2 = new DwellingFloor(new Office[]{new Office(1, 2), new Office(2, 2)});
        Floor floor3 = new OfficeFloor(new Office[]{new Office(1, 3), new Office(2, 3), new Office(3, 3)});

        Building building = new Dwelling(new Floor[]{floor1, floor2, floor3});

        Building building2;

        File file = new File("src/test/resources/buildingsTestSerializeDeserialize.txt");

//        try(OutputStream outputStream = new FileOutputStream(file)){
//            Buildings.serializeBuilding(building, outputStream);
//        }

        try (InputStream inputStream = new FileInputStream(file)) {
            building2 = Buildings.deserializeBuilding(inputStream);
        }

        System.out.println(building2);
    }
}
