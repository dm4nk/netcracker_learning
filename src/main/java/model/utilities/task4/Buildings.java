package model.utilities.task4;

import model.buildings.Building;
import model.buildings.Floor;
import model.buildings.Space;
import model.buildings.flat.Dwelling;
import model.buildings.flat.DwellingFloor;
import model.buildings.flat.Flat;

import java.io.*;

public abstract class Buildings {
    public static void outputBuilding(Building building, OutputStream out) throws IOException {
        DataOutputStream stream = new DataOutputStream(out);
        stream.writeInt(building.size());

        for (Floor f : building.getAllFloors()) {
            stream.writeInt(f.size());
            for (Space s : f.spaces()) {
                stream.writeInt(s.getRooms());
                stream.writeDouble(s.getSpace());
            }
        }
        //todo
        stream.flush();
        stream.close();
    }

    public static Building inputBuilding(InputStream in) throws IOException {
        DataInputStream stream = new DataInputStream(in);
        int floorsCount = stream.readInt();

        DwellingFloor[] floors = new DwellingFloor[floorsCount];

        int spaceCounter;
        Space[] spaces;
        int rooms;
        double square;

        for (int i = 0; i < floorsCount; ++i) {
            spaceCounter = stream.readInt();

            spaces = new Space[spaceCounter];
            for (int j = 0; j < spaceCounter; ++j) {
                rooms = stream.readInt();
                square = stream.readDouble();
                spaces[j] = new Flat(square, rooms);
            }

            floors[i] = new DwellingFloor(spaces);
        }

        stream.close();
        return new Dwelling(floors);
    }

//    public static void writeBuilding (Building building, Writer out){
//
//    }
//
//    public static Building readBuilding (Reader in){
//
//    }
}
