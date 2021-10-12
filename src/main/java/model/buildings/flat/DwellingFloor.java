package model.buildings.flat;

import model.exeptions.SpaceIndexOutOfBoundsException;

import static model.utilities.IndexChecker.checkIfNumberIsValid;


public class DwellingFloor {
    private Flat[] flats;

    public DwellingFloor(Flat[] flats) {
        this.flats = flats;
    }

    public DwellingFloor(int flatsCount) {
        flats = new Flat[flatsCount];

        for (int i = 0; i < flatsCount; ++i) {
            flats[i] = new Flat();
        }
    }

    public int size() {
        return flats.length;
    }

    public int sumSquare() {
        int sum = 0;

        for (Flat f : flats) {
            sum += f.getSpace();
        }
        return sum;
    }

    public int sumRoomCount() {
        int sum = 0;

        for (Flat f : flats) {
            sum += f.getRooms();
        }
        return sum;
    }

    public Flat[] flats() {
        return flats;
    }

    public Flat getFlat(int number) {
        checkIfNumberIsValid(number, size()-1, SpaceIndexOutOfBoundsException.class);
        return flats[number];
    }

    public void setFlat(int number, Flat flat) {
        checkIfNumberIsValid(number, size()-1, SpaceIndexOutOfBoundsException.class);
        flats[number] = flat;
    }

    public void addFlat(int number, Flat flat) {
        checkIfNumberIsValid(number, size(), SpaceIndexOutOfBoundsException.class);
        Flat[] newFlats = new Flat[size() + 1];

        arrayCopy(flats, 0, newFlats, 0, number);

        newFlats[number] = flat;

        arrayCopy(flats, number, newFlats, number + 1, size() - number);

        flats = newFlats;
    }

    public void removeFlat(int number) {
        if (size() <= 1) throw new IllegalStateException();
        checkIfNumberIsValid(number, size()-1, SpaceIndexOutOfBoundsException.class);

        Flat[] newFlats = new Flat[size() - 1];

        arrayCopy(flats, 0, newFlats, 0, number);
        arrayCopy(flats, number + 1, newFlats, number, size() - number - 1);

        flats = newFlats;
    }

    public Flat getBestSpace() {
        Flat bestSpaceFlat = getFlat(0);
        for (int i = 1; i < size(); ++i) {
            if (bestSpaceFlat.getSpace() < getFlat(i).getSpace())
                bestSpaceFlat = getFlat(i);
        }
        return bestSpaceFlat;
    }

    private void arrayCopy(Flat[] source, int sourcePosition, Flat[] destination, int destinationPosition, int length) {
        checkIfNumberIsValid(sourcePosition, source.length - length, SpaceIndexOutOfBoundsException.class);
        checkIfNumberIsValid(destinationPosition, destination.length - length, SpaceIndexOutOfBoundsException.class);

        for (int i = 0; i < length; ++i)
            destination[destinationPosition + i] = source[sourcePosition + i];
    }
}
