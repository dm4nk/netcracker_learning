package model.utilities.comparators;

import model.buildings.Space;

import java.util.Comparator;

public class SpaceComparator implements Comparator<Space> {
    @Override
    public int compare(Space o1, Space o2) {
        return Integer.compare(o2.getRooms(), o1.getRooms());
    }
}
