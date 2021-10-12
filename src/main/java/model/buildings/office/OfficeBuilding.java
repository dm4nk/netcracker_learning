package model.buildings.office;

import model.exeptions.FloorIndexOutOfBoundsException;
import model.exeptions.InvalidRoomsCountException;
import model.utilities.MyList;

import static model.utilities.IndexChecker.checkIfNumberIsValid;

public class OfficeBuilding {
    private MyList<OfficeFloor> floors;

    public OfficeBuilding(int floorsCount, int[] flatsCount) {
        if (floorsCount != flatsCount.length) throw new InvalidRoomsCountException();

        floors = new MyList<>();

        for (int f : flatsCount) {
            floors.addToTail(new OfficeFloor(f));
        }
    }

    public OfficeBuilding(OfficeFloor[] officeFloors) {

        floors = new MyList<>();

        for(OfficeFloor o : officeFloors)
            floors.addToTail(o);
    }

    public int size() {
        return floors.size();
    }

    public int flatsCount() {
        int sum = 0;

        for (Object floor : floors)
            sum += ((OfficeFloor) floor).size();

        return sum;
    }

    public int sumRoomsCount() {
        int sum = 0;

        for (Object floor : floors)
            sum += ((OfficeFloor) floor).sumRoomCount();

        return sum;
    }

    public int sumSquare() {
        int sum = 0;

        for (Object floor : floors)
            sum += ((OfficeFloor) floor).sumSquare();

        return sum;
    }

    public OfficeFloor[] getAllFloors() {

        OfficeFloor[] floors = new OfficeFloor[size()];

        int i = 0;
        for (Object floor : this.floors)
            floors[i++] = ((OfficeFloor) floor);

        return floors;
    }

    public OfficeFloor getFloor(int number) {
        checkIfNumberIsValid(number, size()-1, FloorIndexOutOfBoundsException.class);
        return floors.get(number);
    }

    public void setFloor(int number, OfficeFloor officeFloor) {
        checkIfNumberIsValid(number, size()-1, FloorIndexOutOfBoundsException.class);
        floors.set(number, officeFloor);
    }

    public Office getOffice(int number) {
        checkIfNumberIsValid(number, sumRoomsCount()-1, FloorIndexOutOfBoundsException.class);
        Entity<OfficeFloor, Integer> floorAndNumber = countFloorAndFlat(number);

        return floorAndNumber.floor.getFlat(floorAndNumber.number);
    }

    public void setOffice(int number, Office office) {
        checkIfNumberIsValid(number, sumRoomsCount()-1, FloorIndexOutOfBoundsException.class);
        Entity<OfficeFloor, Integer> floorAndNumber = countFloorAndFlat(number);

        floorAndNumber.floor.setFlat(floorAndNumber.number, office);
    }

    public void addOffice(int number, Office office) {
        checkIfNumberIsValid(number, sumRoomsCount(), FloorIndexOutOfBoundsException.class);
        Entity<OfficeFloor, Integer> floorAndNumber = countFloorAndFlat(number);

        floorAndNumber.floor.addFlat(floorAndNumber.number, office);
    }

    public void removeOffice(int number) {
        checkIfNumberIsValid(number, sumRoomsCount()-1, FloorIndexOutOfBoundsException.class);
        Entity<OfficeFloor, Integer> floorAndNumber = countFloorAndFlat(number);

        floorAndNumber.floor.removeFlat(floorAndNumber.number);
    }

    private Entity<OfficeFloor, Integer> countFloorAndFlat(int number) {
        int i = 0;
        int j = 0;
        while ((i + getFloor(j++).size()) <= number) {
            i += getFloor(j - 1).size();
        }

        return new Entity<>(getFloor(j - 1), number - i);
    }

    public double[] getSquares() {
        double[] fl = new double[flatsCount()];

        int i = 0;
        for (Object d : floors)
            for (Office f : ((OfficeFloor) d).flats())
                fl[i++] = f.getSpace();

        sort(fl);

        return fl;
    }

    private void sort(double[] fl) {
        boolean isSorted = false;
        double buf;
        while (!isSorted) {
            isSorted = true;
            for (int i = 0; i < fl.length - 1; i++) {
                if (fl[i] < fl[i + 1]) {
                    isSorted = false;

                    buf = fl[i];
                    fl[i] = fl[i + 1];
                    fl[i + 1] = buf;
                }
            }
        }
    }

    private class Entity<_Floor, _Number> {
        _Floor floor;
        _Number number;

        public Entity(_Floor floor, _Number number) {
            this.floor = floor;
            this.number = number;
        }
    }
}
