package model.utilities;

import lombok.NonNull;
import model.buildings.Building;
import model.buildings.Floor;
import model.buildings.Space;
import model.exeptions.InexchangeableFloorsException;
import model.exeptions.InexchangeableSpacesException;

public abstract class PlacementExchanger {
    public static boolean canChange(@NonNull Space first, @NonNull Space second) {
        return (first.getSpace() == second.getSpace()) && (first.getRooms() == second.getRooms());
    }

    public static boolean canChange(@NonNull Floor first, @NonNull Floor second) {
        return (first.sumSquare() == second.sumSquare()) && (first.sumRoomCount() == second.sumRoomCount());
    }

    public static void exchangeFloorRooms(@NonNull Floor floor1, int index1, @NonNull Floor floor2, int index2) throws InexchangeableSpacesException {
        IndexChecker.checkIfNumberIsValid(index1, floor1.size() - 1, IndexOutOfBoundsException.class);
        IndexChecker.checkIfNumberIsValid(index2, floor2.size() - 1, IndexOutOfBoundsException.class);
        if (!canChange(floor1.getSpace(index1), floor2.getSpace(index2))) throw new InexchangeableSpacesException();
        swap(floor1, index1, floor2, index2);
    }

    private static void swap(Floor floor1, int index1, Floor floor2, int index2) {
        Space tmp = floor1.getSpace(index1);
        floor1.setSpace(index1, floor2.getSpace(index2));
        floor2.setSpace(index2, tmp);
    }

    public static void exchangeBuildingFloors(@NonNull Building building1, int index1, @NonNull Building building2, int index2) throws InexchangeableFloorsException {
        IndexChecker.checkIfNumberIsValid(index1, building1.size() - 1, IndexOutOfBoundsException.class);
        IndexChecker.checkIfNumberIsValid(index2, building2.size() - 1, IndexOutOfBoundsException.class);
        if (!canChange(building1.getFloor(index1), building2.getFloor(index2)))
            throw new InexchangeableFloorsException();
        swap(building1, index1, building2, index2);
    }

    private static void swap(Building building1, int index1, Building building2, int index2) {
        Floor tmp = building1.getFloor(index1);
        building1.setFloor(index1, building2.getFloor(index2));
        building2.setFloor(index2, tmp);
    }
}
