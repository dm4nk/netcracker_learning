import model.buildings.Building;
import model.buildings.Floor;
import model.buildings.flat.Dwelling;
import model.buildings.flat.DwellingFloor;
import model.buildings.flat.Flat;
import model.buildings.office.Office;
import model.buildings.office.OfficeFloor;

public class Main {
    public static void main(String[] args) {
        DwellingFloor floor1 = new DwellingFloor(new Flat[]{new Flat(1, 1)});
        DwellingFloor floor2 = new DwellingFloor(new Office[]{new Office(2, 1), new Office(2, 2)});
        Floor floor3 = new OfficeFloor(new Office[]{new Office(3, 1), new Office(3, 2), new Office(3, 3)});

        Building building = new Dwelling(new Floor[]{floor1, floor2, floor3});

        System.out.println(building);
    }
}
