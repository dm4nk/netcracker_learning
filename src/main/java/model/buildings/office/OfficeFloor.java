package model.buildings.office;

import model.buildings.Floor;
import model.buildings.Space;
import model.exeptions.SpaceIndexOutOfBoundsException;
import model.utilities.MyList;

import static model.utilities.IndexChecker.checkIfNumberIsValid;


public class OfficeFloor implements Floor {
    private final MyList<Space> offices;

    public OfficeFloor(Office[] offices) {
        this.offices = new MyList<>();
        for (Office o : offices)
            this.offices.addToTail(o);
    }

    public OfficeFloor(int flatsCount) {
        this.offices = new MyList<>();
        for (int i = 0; i < flatsCount; ++i)
            this.offices.addToTail(new Office());
    }

    public int size() {
        return offices.size();
    }

    public int sumSquare() {
        int sum = 0;

        for (Object f : offices) {
            sum += ((Office) f).getSpace();
        }
        return sum;
    }

    public int sumRoomCount() {
        int sum = 0;

        for (Object f : offices) {
            sum += ((Office) f).getRooms();
        }
        return sum;
    }

    public Space[] flats() {
        Space[] offices = new Office[size()];

        int i = 0;
        for (Object f : this.offices) {
            offices[i++] = ((Office) f);
        }

        return offices;
    }

    public Space getFlat(int number) {
        checkIfNumberIsValid(number, size() - 1, SpaceIndexOutOfBoundsException.class);
        return offices.get(number);
    }

    public void setFlat(int number, Space office) {
        checkIfNumberIsValid(number, size() - 1, SpaceIndexOutOfBoundsException.class);
        offices.set(number, office);
    }

    public void addFlat(int number, Space office) {
        checkIfNumberIsValid(number, size(), SpaceIndexOutOfBoundsException.class);

        offices.add(number, office);
    }

    public void removeFlat(int number) {
        if (size() <= 1) throw new IllegalStateException();
        checkIfNumberIsValid(number, size() - 1, SpaceIndexOutOfBoundsException.class);

        offices.remove(number);
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
