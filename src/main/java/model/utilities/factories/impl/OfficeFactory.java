package model.utilities.factories.impl;

import model.buildings.Building;
import model.buildings.Floor;
import model.buildings.Space;
import model.buildings.office.Office;
import model.buildings.office.OfficeBuilding;
import model.buildings.office.OfficeFloor;
import model.utilities.factories.BuildingFactory;

public class OfficeFactory implements BuildingFactory {
    @Override
    public Space createSpace(double area) {
        return new Office(area);
    }

    @Override
    public Space createSpace(int roomsCount, double area) {
        return new Office(roomsCount, area);
    }

    @Override
    public Floor createFloor(int spacesCount) {
        return new OfficeFloor(spacesCount);
    }

    @Override
    public Floor createFloor(Space... spaces) {
        return new OfficeFloor(spaces);
    }

    @Override
    public Building createBuilding(int floorsCount, int... spacesCounts) {
        return new OfficeBuilding(floorsCount, spacesCounts);
    }

    @Override
    public Building createBuilding(Floor... floors) {
        return new OfficeBuilding(floors);
    }
}
