package model.buildings.office;

import model.buildings.Floor;
import model.buildings.Space;
import model.exeptions.SpaceIndexOutOfBoundsException;
import model.utilities.MyList;

import static model.utilities.IndexChecker.checkIfNumberIsValid;


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

    public Space[] flats() {
        Space[] offices = new Space[size()];

        int i = 0;
        for (Space f : this.spaces) {
            offices[i++] = f;
        }

        return offices;
    }

    public Space getFlat(int number) {
        checkIfNumberIsValid(number, size() - 1, SpaceIndexOutOfBoundsException.class);
        return spaces.get(number);
    }

    public void setFlat(int number, Space office) {
        checkIfNumberIsValid(number, size() - 1, SpaceIndexOutOfBoundsException.class);
        spaces.set(number, office);
    }

    public void addFlat(int number, Space office) {
        checkIfNumberIsValid(number, size(), SpaceIndexOutOfBoundsException.class);

        spaces.add(number, office);
    }

    public void removeFlat(int number) {
        if (size() <= 1) throw new IllegalStateException();
        checkIfNumberIsValid(number, size() - 1, SpaceIndexOutOfBoundsException.class);

        spaces.remove(number);
    }

    public Space getBestSpace() {
        Space bestSpaceOffice = getFlat(0);
        for (int i = 1; i < size(); ++i) {
            if (bestSpaceOffice.getSpace() < getFlat(i).getSpace())
                bestSpaceOffice = getFlat(i);
        }
        return bestSpaceOffice;
    }
}
