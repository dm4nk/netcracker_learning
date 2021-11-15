package model.utilities;

import lombok.NonNull;
import lombok.Setter;
import model.buildings.Building;
import model.buildings.Floor;
import model.buildings.Space;
import model.buildings.SynchronizedFloor;
import model.utilities.factories.BuildingFactory;
import model.utilities.factories.impl.DwellingFactory;

import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Formatter;
import java.util.Scanner;

public abstract class Buildings {
    @NonNull
    @Setter
    private static BuildingFactory buildingFactory = new DwellingFactory();

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
        Floor[] floors = null;
        try (DataInputStream stream = new DataInputStream(in)) {
            int floorsCount = stream.readInt();

            floors = new Floor[floorsCount];

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
                    spaces[j] = createSpace(rooms, square);
                }

                floors[i] = createFloor(spaces);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return createBuilding(floors);
    }

    public static void writeBuilding(@NonNull Building building, @NonNull Writer out) {
        PrintWriter writer = new PrintWriter(out);
            writer.print(building.size() + " ");

            for (Floor f : building.getAllFloors()) {
                writer.print(f.size() + " ");
                for (Space s : f.spaces()) {
                    writer.print(s.getRooms() + " ");
                    writer.print(s.getSpace() + " ");
                }
            }
        writer.flush();
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

        Floor[] floors = new Floor[floorsCount];

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
                spaces[j] = createSpace(rooms, square);
            }

            floors[i] = createFloor(spaces);
        }
        return createBuilding(floors);
    }

    public static Building readBuilding(@NonNull Scanner scanner) {
        int floorsCount = scanner.nextInt();

        Floor[] floors = new Floor[floorsCount];

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
                spaces[j] = createSpace(rooms, square);
            }

            floors[i] = createFloor(spaces);
        }

        return createBuilding(floors);
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

    public static Floor synchronizedFloor(Floor floor) {
        return new SynchronizedFloor(floor);
    }

    public static <T extends Comparable<T>> void sort(@NonNull T[] t) {
        Arrays.sort(t);
    }

    public static <T> void sort(@NonNull T[] t, @NonNull Comparator<T> comparator) {
        Arrays.sort(t, comparator);
    }

    public static Space createSpace(double area) {
        return buildingFactory.createSpace(area);
    }

    public static Space createSpace(int roomsCount, double area) {
        return buildingFactory.createSpace(roomsCount, area);
    }

    public static Floor createFloor(int spacesCount) {
        return buildingFactory.createFloor(spacesCount);
    }

    public static Floor createFloor(Space[] spaces) {
        return buildingFactory.createFloor(spaces);
    }

    public static Building createBuilding(int floorsCount, int[] spacesCounts) {
        return buildingFactory.createBuilding(floorsCount, spacesCounts);
    }

    public static Building createBuilding(Floor[] floors) {
        return buildingFactory.createBuilding(floors);
    }
}
