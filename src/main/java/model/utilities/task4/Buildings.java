package model.utilities.task4;

import lombok.NonNull;
import model.buildings.Building;
import model.buildings.Floor;
import model.buildings.Space;
import model.buildings.flat.Dwelling;
import model.buildings.flat.DwellingFloor;
import model.buildings.flat.Flat;

import java.io.*;
import java.util.Formatter;
import java.util.Scanner;

public abstract class Buildings {
    public static void outputBuilding(@NonNull Building building, @NonNull OutputStream out) {
        try (final DataOutputStream stream = new DataOutputStream(out)) {
            stream.writeInt(building.size());

            for (Floor f : building.getAllFloors()) {
                stream.writeInt(f.size());
                for (Space s : f.spaces()) {
                    stream.writeInt(s.getRooms());
                    stream.writeDouble(s.getSpace());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Building inputBuilding(@NonNull InputStream in) {
        DwellingFloor[] floors = null;
        try (DataInputStream stream = new DataInputStream(in)) {
            int floorsCount = stream.readInt();

            floors = new DwellingFloor[floorsCount];

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
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new Dwelling(floors);
    }

    public static void writeBuilding(@NonNull Building building, @NonNull Writer out) {
        try (PrintWriter writer = new PrintWriter(out)) {
            writer.print(building.size() + " ");

            for (Floor f : building.getAllFloors()) {
                writer.print(f.size() + " ");
                for (Space s : f.spaces()) {
                    writer.print(s.getRooms() + " ");
                    writer.print(s.getSpace() + " ");
                }
            }
        }
    }

    public static void writeBuildingFormat(@NonNull Building building, @NonNull Writer out) {
        try (PrintWriter writer = new PrintWriter(out); Formatter formatter = new Formatter()) {
            formatter.format("%d ", building.size());

            for (Floor f : building.getAllFloors()) {
                formatter.format("%d ", f.size());
                for (Space s : f.spaces()) {
                    formatter.format("%d ", s.getRooms());
                    formatter.format("%f ", s.getSpace());
                }
            }

            writer.write(formatter.toString());
        }
    }

    public static Building readBuilding(@NonNull Reader in) throws IOException {
        StreamTokenizer tokenizer = new StreamTokenizer(in);

        tokenizer.nextToken();
        int floorsCount = (int) tokenizer.nval;

        DwellingFloor[] floors = new DwellingFloor[floorsCount];

        int spaceCounter;
        Space[] spaces;
        int rooms;
        double square;

        for (int i = 0; i < floorsCount; ++i) {
            tokenizer.nextToken();
            spaceCounter = (int) tokenizer.nval;

            spaces = new Space[spaceCounter];
            for (int j = 0; j < spaceCounter; ++j) {
                tokenizer.nextToken();
                rooms = (int) tokenizer.nval;
                tokenizer.nextToken();
                square = tokenizer.nval;
                spaces[j] = new Flat(square, rooms);
            }

            floors[i] = new DwellingFloor(spaces);
        }
        return new Dwelling(floors);
    }

    public static Building readBuilding(@NonNull Scanner scanner) {
        int floorsCount = scanner.nextInt();

        DwellingFloor[] floors = new DwellingFloor[floorsCount];

        int spaceCounter;
        Space[] spaces;
        int rooms;
        double square;

        for (int i = 0; i < floorsCount; ++i) {
            spaceCounter = scanner.nextInt();

            spaces = new Space[spaceCounter];
            for (int j = 0; j < spaceCounter; ++j) {
                rooms = scanner.nextInt();
                square = scanner.nextFloat();
                spaces[j] = new Flat(square, rooms);
            }

            floors[i] = new DwellingFloor(spaces);
        }

        return new Dwelling(floors);
    }

    public static void serializeBuilding(@NonNull Building building, @NonNull OutputStream out) {
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(out)) {
            objectOutputStream.writeObject(building);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Building deserializeBuilding(@NonNull InputStream in) {
        Building building = null;
        try (ObjectInputStream objectInputStream = new ObjectInputStream(in)) {
            building = (Building) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return building;
    }
}
