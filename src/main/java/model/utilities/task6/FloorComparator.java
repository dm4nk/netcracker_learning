package model.utilities.task6;

import model.buildings.Floor;

import java.util.Comparator;

public class FloorComparator implements Comparator<Floor> {
    @Override
    public int compare(Floor o1, Floor o2) {
        return Double.compare(o2.sumSquare(), o1.sumSquare());
    }
}
