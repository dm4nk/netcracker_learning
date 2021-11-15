package model.buildings.office;

import lombok.NonNull;
import model.buildings.Building;
import model.buildings.Floor;
import model.buildings.Space;
import model.exeptions.FloorIndexOutOfBoundsException;
import model.exeptions.InvalidRoomsCountException;
import model.utilities.SomeBuildingUtilities;
import model.utilities.mycollections.MyList;

import java.io.Serializable;
import java.util.Iterator;
import java.util.Objects;

import static model.utilities.IndexChecker.checkIfNumberIsValid;

public class OfficeBuilding implements Building, Serializable {
    private static final int SUPER_SECRET_HASH_CODE_VARIABLE = 1337;
    @NonNull
    private final MyList<Floor> floors;

    public OfficeBuilding(int floorsCount, @NonNull int[] flatsCount) {
        if (floorsCount != flatsCount.length) throw new InvalidRoomsCountException();

        floors = new MyList<>();

        for (int f : flatsCount) {
            floors.addToTail(new OfficeFloor(f));
        }
    }

    public OfficeBuilding(@NonNull Floor[] floors) {

        this.floors = new MyList<>();

        for (Floor o : floors)
            this.floors.addToTail(o);
    }

    private OfficeBuilding(MyList<Floor> floors) {
        this.floors = floors;
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
    public void setFloor(int number, @NonNull Floor floor) {
        checkIfNumberIsValid(number, size() - 1, FloorIndexOutOfBoundsException.class);
        floors.set(number, floor);
    }

    @Override
    public Space[] getSquares() {
        return SomeBuildingUtilities.getSquares(floors);
    }

    @Override
    public Iterator<Floor> iterator() {
        return floors.iterator();
    }

    @Override
    public String toString() {
        return SomeBuildingUtilities.toString("OfficeBuilding (", floors, size());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OfficeBuilding)) return false;
        OfficeBuilding that = (OfficeBuilding) o;
        return floors.equals(that.floors);
    }

    @Override
    public int hashCode() {
        return Objects.hash(SUPER_SECRET_HASH_CODE_VARIABLE, floors);
    }

    @Override
    @SuppressWarnings("all")
    public Object clone() {
        return new OfficeBuilding(floors.clone());
    }
}
