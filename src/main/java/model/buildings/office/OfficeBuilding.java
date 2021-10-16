package model.buildings.office;

import lombok.EqualsAndHashCode;
import model.buildings.Building;
import model.buildings.Floor;
import model.buildings.Space;
import model.exeptions.FloorIndexOutOfBoundsException;
import model.exeptions.InvalidRoomsCountException;
import model.utilities.MyList;
import model.utilities.SomeBuildingUtilities;

import static model.utilities.IndexChecker.checkIfNumberIsValid;

@EqualsAndHashCode
public class OfficeBuilding implements Building {
    private final MyList<Floor> floors;

    public OfficeBuilding(int floorsCount, int[] flatsCount) {
        if (floorsCount != flatsCount.length) throw new InvalidRoomsCountException();

        floors = new MyList<>();

        for (int f : flatsCount) {
            floors.addToTail(new OfficeFloor(f));
        }
    }

    public OfficeBuilding(Floor[] officeFloors) {

        floors = new MyList<>();

        for (Floor o : officeFloors)
            floors.addToTail(o);
    }

    @Override
    public int size() {
        return floors.size();
    }

    @Override
    public int flatsCount() {
        return SomeBuildingUtilities.flatsCount(floors);
    }

    @Override
    public int sumRoomsCount() {
        return SomeBuildingUtilities.sumRoomsCount(floors);
    }

    @Override

    public int sumSquare() {
        return SomeBuildingUtilities.sumSquare(floors);
    }

    @Override
    public Floor[] getAllFloors() {
        return SomeBuildingUtilities.getAllFloors(floors, size());
    }

    @Override
    public Floor getFloor(int number) {
        checkIfNumberIsValid(number, size() - 1, FloorIndexOutOfBoundsException.class);
        return floors.get(number);
    }

    @Override
    public void setFloor(int number, Floor floor) {
        checkIfNumberIsValid(number, size() - 1, FloorIndexOutOfBoundsException.class);
        floors.set(number, floor);
    }

    @Override
    public Space[] getSquares() {
        return SomeBuildingUtilities.getSquares(floors);
    }

    @Override
    public String toString() {

        StringBuilder floors = new StringBuilder();

        for (Floor f : this.floors) {
            floors.append(f.size()).append("\n");
            for (Space s : f.spaces())
                floors.append(s.getRooms()).append("\n").append(s.getSpace()).append("\n");
        }

        return size() + "\n" + floors.toString();
    }
}
