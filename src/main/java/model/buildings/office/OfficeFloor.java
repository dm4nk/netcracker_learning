package model.buildings.office;

import model.exeptions.SpaceIndexOutOfBoundsException;

import static model.utilities.IndexChecker.checkIfNumberIsValid;


public class OfficeFloor {
    private Office[] offices;

    public OfficeFloor(Office[] offices) {
        this.offices = offices;
    }

    public OfficeFloor(int flatsCount) {
        offices = new Office[flatsCount];

        for (int i = 0; i < flatsCount; ++i) {
            offices[i] = new Office();
        }
    }

    public int size() {
        return offices.length;
    }

    public int sumSquare() {
        int sum = 0;

        for (Office f : offices) {
            sum += f.getSpace();
        }
        return sum;
    }

    public int sumRoomCount() {
        int sum = 0;

        for (Office f : offices) {
            sum += f.getRooms();
        }
        return sum;
    }

    public Office[] flats() {
        return offices;
    }

    public Office getFlat(int number) {
        checkIfNumberIsValid(number, size()-1, SpaceIndexOutOfBoundsException.class);
        return offices[number];
    }

    public void setFlat(int number, Office office) {
        checkIfNumberIsValid(number, size()-1, SpaceIndexOutOfBoundsException.class);
        offices[number] = office;
    }

    public void addFlat(int number, Office office) {
        checkIfNumberIsValid(number, size(), SpaceIndexOutOfBoundsException.class);
        Office[] newOffices = new Office[size() + 1];

        arrayCopy(offices, 0, newOffices, 0, number);

        newOffices[number] = office;

        arrayCopy(offices, number, newOffices, number + 1, size() - number);

        offices = newOffices;
    }

    public void removeFlat(int number) {
        if (size() <= 1) throw new IllegalStateException();
        checkIfNumberIsValid(number, size()-1, SpaceIndexOutOfBoundsException.class);

        Office[] newOffices = new Office[size() - 1];

        arrayCopy(offices, 0, newOffices, 0, number);
        arrayCopy(offices, number + 1, newOffices, number, size() - number - 1);

        offices = newOffices;
    }

    public Office getBestSpace() {
        Office bestSpaceOffice = getFlat(0);
        for (int i = 1; i < size(); ++i) {
            if (bestSpaceOffice.getSpace() < getFlat(i).getSpace())
                bestSpaceOffice = getFlat(i);
        }
        return bestSpaceOffice;
    }

    private void arrayCopy(Office[] source, int sourcePosition, Office[] destination, int destinationPosition, int length) {
        checkIfNumberIsValid(sourcePosition, source.length - length, SpaceIndexOutOfBoundsException.class);
        checkIfNumberIsValid(destinationPosition, destination.length - length, SpaceIndexOutOfBoundsException.class);

        for (int i = 0; i < length; ++i)
            destination[destinationPosition + i] = source[sourcePosition + i];
    }
}
