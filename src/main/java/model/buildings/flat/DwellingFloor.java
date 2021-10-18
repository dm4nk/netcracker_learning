package model.buildings.flat;

import lombok.NonNull;
import model.buildings.Floor;
import model.buildings.Space;
import model.exeptions.SpaceIndexOutOfBoundsException;
import model.utilities.IterableArray;
import model.utilities.SomeBuildingUtilities;

import java.util.Arrays;
import java.util.Iterator;
import java.util.stream.IntStream;

import static model.utilities.IndexChecker.checkIfNumberIsValid;

public class DwellingFloor implements Floor {
    @NonNull
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

    public Space[] spaces() {
        return spaces;
    }

    public Space getSpace(int number) {
        checkIfNumberIsValid(number, size() - 1, SpaceIndexOutOfBoundsException.class);
        return spaces[number];
    }

    public void setSpace(int number, @NonNull Space flat) {
        checkIfNumberIsValid(number, size() - 1, SpaceIndexOutOfBoundsException.class);
        spaces[number] = flat;
    }

    public void addSpace(int number, @NonNull Space flat) {
        checkIfNumberIsValid(number, size(), SpaceIndexOutOfBoundsException.class);
        Space[] newFlats = new Space[size() + 1];

        arrayCopy(spaces, 0, newFlats, 0, number);

        newFlats[number] = flat;

        arrayCopy(spaces, number, newFlats, number + 1, size() - number);

        spaces = newFlats;
    }

    public void removeSpace(int number) {
        if (size() <= 1) throw new IllegalStateException();
        checkIfNumberIsValid(number, size() - 1, SpaceIndexOutOfBoundsException.class);

        Space[] newFlats = new Space[size() - 1];

        arrayCopy(spaces, 0, newFlats, 0, number);
        arrayCopy(spaces, number + 1, newFlats, number, size() - number - 1);

        spaces = newFlats;
    }

    public Space getBestSpace() {
        Space bestSpaceFlat = getSpace(0);
        for (int i = 1; i < size(); ++i) {
            if (bestSpaceFlat.getSpace() < getSpace(i).getSpace())
                bestSpaceFlat = getSpace(i);
        }
        return bestSpaceFlat;
    }

    private void arrayCopy(Space[] source, int sourcePosition, Space[] destination, int destinationPosition, int length) {
        checkIfNumberIsValid(sourcePosition, source.length - length, SpaceIndexOutOfBoundsException.class);
        checkIfNumberIsValid(destinationPosition, destination.length - length, SpaceIndexOutOfBoundsException.class);

        for (int i = 0; i < length; ++i)
            destination[destinationPosition + i] = source[sourcePosition + i];
    }

    @Override
    public Iterator<Space> iterator() {
        return new IterableArray<>(spaces).iterator();
    }

    @Override
    public String toString() {
        return SomeBuildingUtilities.toString("DwellingFloor", new IterableArray<>(spaces), size());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DwellingFloor)) return false;
        DwellingFloor that = (DwellingFloor) o;
        return spaces.length == that.spaces.length && IntStream.range(0, spaces.length - 1).allMatch(i -> spaces[i].equals(that.spaces[i]));
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(spaces);
    }
}
