package model.buildings.flat;

import model.buildings.Floor;
import model.buildings.Space;
import model.exeptions.SpaceIndexOutOfBoundsException;

import static model.utilities.IndexChecker.checkIfNumberIsValid;


public class DwellingFloor implements Floor {
    private Space[] spaces;

    public DwellingFloor(Space[] spaces) {
        this.spaces = spaces;
    }

    public DwellingFloor(int flatsCount) {
        spaces = new Flat[flatsCount];

        for (int i = 0; i < flatsCount; ++i) {
            spaces[i] = new Flat();
        }
    }

    public int size() {
        return spaces.length;
    }

    public int sumSquare() {
        int sum = 0;

        for (Space f : spaces) {
            sum += f.getSpace();
        }
        return sum;
    }

    public int sumRoomCount() {
        int sum = 0;

        for (Space f : spaces) {
            sum += f.getRooms();
        }
        return sum;
    }

    public Space[] flats() {
        return spaces;
    }

    public Space getFlat(int number) {
        checkIfNumberIsValid(number, size() - 1, SpaceIndexOutOfBoundsException.class);
        return spaces[number];
    }

    public void setFlat(int number, Space flat) {
        checkIfNumberIsValid(number, size() - 1, SpaceIndexOutOfBoundsException.class);
        spaces[number] = flat;
    }

    public void addFlat(int number, Space flat) {
        checkIfNumberIsValid(number, size(), SpaceIndexOutOfBoundsException.class);
        Space[] newFlats = new Space[size() + 1];

        arrayCopy(spaces, 0, newFlats, 0, number);

        newFlats[number] = flat;

        arrayCopy(spaces, number, newFlats, number + 1, size() - number);

        spaces = newFlats;
    }

    public void removeFlat(int number) {
        if (size() <= 1) throw new IllegalStateException();
        checkIfNumberIsValid(number, size() - 1, SpaceIndexOutOfBoundsException.class);

        Space[] newFlats = new Space[size() - 1];

        arrayCopy(spaces, 0, newFlats, 0, number);
        arrayCopy(spaces, number + 1, newFlats, number, size() - number - 1);

        spaces = newFlats;
    }

    public Space getBestSpace() {
        Space bestSpaceFlat = getFlat(0);
        for (int i = 1; i < size(); ++i) {
            if (bestSpaceFlat.getSpace() < getFlat(i).getSpace())
                bestSpaceFlat = getFlat(i);
        }
        return bestSpaceFlat;
    }

    private void arrayCopy(Space[] source, int sourcePosition, Space[] destination, int destinationPosition, int length) {
        checkIfNumberIsValid(sourcePosition, source.length - length, SpaceIndexOutOfBoundsException.class);
        checkIfNumberIsValid(destinationPosition, destination.length - length, SpaceIndexOutOfBoundsException.class);

        for (int i = 0; i < length; ++i)
            destination[destinationPosition + i] = source[sourcePosition + i];
    }
}
