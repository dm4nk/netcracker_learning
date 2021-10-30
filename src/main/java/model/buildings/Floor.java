package model.buildings;

import lombok.NonNull;
import model.utilities.MyCloneable;

import java.io.Serializable;
import java.util.Iterator;

public interface Floor extends Serializable, Iterable<Space>, MyCloneable, Comparable<Floor> {
    int size();

    default int sumSquare() {
        int sum = 0;
        for (Space f : this)
            sum += f.getSpace();
        return sum;
    }

    default int sumRoomCount() {
        int sum = 0;
        for (Space f : this)
            sum += f.getRooms();
        return sum;
    }

    Space[] spaces();

    Space getSpace(int number);

    void setSpace(int number, @NonNull Space flat);

    void addSpace(int number, @NonNull Space flat);

    void removeSpace(int number);

    default Space getBestSpace() {
        Space bestSpaceFlat = getSpace(0);
        for (Space space : this) {
            if (bestSpaceFlat.getSpace() < space.getSpace())
                bestSpaceFlat = space;
        }
        return bestSpaceFlat;
    }

    Iterator<Space> iterator();

    Object clone();

    @Override
    default int compareTo(Floor o) {
        return Integer.compare(size(), o.size());
    }
}
