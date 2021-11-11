package model.buildings.dwelling.hotel;

import lombok.NonNull;
import model.buildings.Floor;
import model.buildings.Space;
import model.buildings.dwelling.Dwelling;
import model.exeptions.FloorIndexOutOfBoundsException;
import model.utilities.IterableArray;
import model.utilities.SomeBuildingUtilities;

import java.util.Arrays;
import java.util.Objects;

import static model.utilities.IndexChecker.checkIfNumberIsValid;

public class Hotel extends Dwelling {
    private static final int SUPER_SECRET_HASH_CODE_VARIABLE = 228;
    private static final double[] coef = new double[]{0, 0.25, 0.5, 1, 1.25, 1.5};

    public Hotel(int floorsCount, @NonNull int[] flatsCount) {
        super(floorsCount, flatsCount);
    }

    public Hotel(Floor[] floors) {
        super(floors);
    }

    public int getStars() {
        int maxStars = 0;
        HotelFloor tmp;
        for (Floor f : getAllFloors())
            if (f instanceof HotelFloor) {
                tmp = (HotelFloor) f;
                maxStars = Math.max(tmp.getStars(), maxStars);
            }
        return maxStars;
    }

    @Override
    public Space getBestSpace() {
        checkIfNumberIsValid(0, sumRoomsCount() - 1, FloorIndexOutOfBoundsException.class);

        Space bestSpace = getSpace(0);
        double bestSpaceMultByCoef = bestSpace.getSpace() * coef[0];

        Space tmpBestSpace;
        double tmpBestSpaceMultByCoef;

        for (Floor floor : this)
            if (floor instanceof HotelFloor) {
                tmpBestSpace = floor.getBestSpace();
                tmpBestSpaceMultByCoef = tmpBestSpace.getSpace() * coef[((HotelFloor) floor).getStars()];
                if (tmpBestSpaceMultByCoef > bestSpaceMultByCoef) {
                    bestSpace = tmpBestSpace;
                    bestSpaceMultByCoef = tmpBestSpaceMultByCoef;
                }
            }

        return bestSpace;
    }

    @Override
    public String toString() {
        return SomeBuildingUtilities.toString("Hotel Floor (" + getStars() + ", ", new IterableArray<>(getAllFloors()), size());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Hotel)) return false;
        if (!super.equals(o)) return false;
        Hotel hotel = (Hotel) o;
        return getStars() == hotel.getStars() && Arrays.equals(hotel.getAllFloors(), this.getAllFloors());
    }

    @Override
    public int hashCode() {
        return Objects.hash(SUPER_SECRET_HASH_CODE_VARIABLE, super.hashCode(), getStars());
    }
}
