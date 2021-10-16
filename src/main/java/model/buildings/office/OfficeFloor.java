package model.buildings.office;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import model.buildings.Floor;
import model.buildings.Space;
import model.exeptions.SpaceIndexOutOfBoundsException;
import model.utilities.MyList;

import java.util.Iterator;

import static model.utilities.IndexChecker.checkIfNumberIsValid;

@EqualsAndHashCode
@ToString
public class OfficeFloor implements Floor {
    private final MyList<Space> spaces;

    public OfficeFloor(Space[] spaces) {
        this.spaces = new MyList<>();
        for (Space o : spaces)
            this.spaces.addToTail(o);
    }

    public OfficeFloor(int flatsCount) {
        this.spaces = new MyList<>();
        for (int i = 0; i < flatsCount; ++i)
            this.spaces.addToTail(new Office());
    }

    public int size() {
        return spaces.size();
    }

    public int sumSquare() {
        int sum = 0;

        for (Space f : spaces)
            sum += f.getSpace();

        return sum;
    }

    public int sumRoomCount() {
        int sum = 0;

        for (Space f : spaces)
            sum += f.getRooms();


        return sum;
    }

    public Space[] spaces() {
        Space[] offices = new Space[size()];

        int i = 0;
        for (Space f : this.spaces) {
            offices[i++] = f;
        }

        return offices;
    }

    public Space getSpace(int number) {
        checkIfNumberIsValid(number, size() - 1, SpaceIndexOutOfBoundsException.class);
        return spaces.get(number);
    }

    public void setSpace(int number, Space office) {
        checkIfNumberIsValid(number, size() - 1, SpaceIndexOutOfBoundsException.class);
        spaces.set(number, office);
    }

    public void addSpace(int number, Space office) {
        checkIfNumberIsValid(number, size(), SpaceIndexOutOfBoundsException.class);

        spaces.add(number, office);
    }

    public void removeSpace(int number) {
        if (size() <= 1) throw new IllegalStateException();
        checkIfNumberIsValid(number, size() - 1, SpaceIndexOutOfBoundsException.class);

        spaces.remove(number);
    }

    public Space getBestSpace() {
        Space bestSpaceOffice = getSpace(0);
        for (int i = 1; i < size(); ++i) {
            if (bestSpaceOffice.getSpace() < getSpace(i).getSpace())
                bestSpaceOffice = getSpace(i);
        }
        return bestSpaceOffice;
    }

    @Override
    public Iterator<Space> iterator() {
        return spaces.iterator();
    }
}
