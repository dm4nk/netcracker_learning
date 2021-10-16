package model.buildings.flat;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import model.buildings.Floor;
import model.buildings.Space;
import model.exeptions.SpaceIndexOutOfBoundsException;
import model.utilities.IterableArray;

import java.util.Iterator;

import static model.utilities.IndexChecker.checkIfNumberIsValid;

@EqualsAndHashCode
@ToString
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

    public Space[] spaces() {
        return spaces;
    }

    public Space getSpace(int number) {
        checkIfNumberIsValid(number, size() - 1, SpaceIndexOutOfBoundsException.class);
        return spaces[number];
    }

    public void setSpace(int number, Space flat) {
        checkIfNumberIsValid(number, size() - 1, SpaceIndexOutOfBoundsException.class);
        spaces[number] = flat;
    }

    public void addSpace(int number, Space flat) {
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
}
