package model.buildings.office;

import lombok.NonNull;
import lombok.ToString;
import model.buildings.Floor;
import model.buildings.Space;
import model.exeptions.SpaceIndexOutOfBoundsException;
import model.utilities.MyList;

import java.io.Serializable;
import java.util.Iterator;
import java.util.Objects;

import static model.utilities.IndexChecker.checkIfNumberIsValid;

@ToString
public class OfficeFloor implements Floor, Serializable {
    private static final int SUPER_SECRET_HASH_CODE_VARIABLE = 1337;
    @NonNull
    private final MyList<Space> spaces;

    public OfficeFloor(@NonNull Space[] spaces) {
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

    public void setSpace(int number, @NonNull Space office) {
        checkIfNumberIsValid(number, size() - 1, SpaceIndexOutOfBoundsException.class);
        spaces.set(number, office);
    }

    public void addSpace(int number, @NonNull Space office) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OfficeFloor)) return false;
        OfficeFloor that = (OfficeFloor) o;
        return spaces.equals(that.spaces);
    }

    @Override
    public int hashCode() {
        return Objects.hash(SUPER_SECRET_HASH_CODE_VARIABLE, spaces);
    }
}
