package model.buildings;

import lombok.NonNull;

import java.util.Iterator;

public class SynchronizedFloor implements Floor {

    private final Floor floor;

    public SynchronizedFloor(Floor floor) {
        this.floor = floor;
    }

    @Override
    public synchronized int size() {
        return floor.size();
    }

    @Override
    public synchronized int sumSquare() {
        return floor.sumSquare();
    }

    @Override
    public synchronized int sumRoomCount() {
        return floor.sumRoomCount();
    }

    @Override
    public synchronized Space[] spaces() {
        return floor.spaces();
    }

    @Override
    public synchronized Space getSpace(int number) {
        return floor.getSpace(number);
    }

    @Override
    public synchronized void setSpace(int number, @NonNull Space flat) {
        floor.setSpace(number, flat);
    }

    @Override
    public synchronized void addSpace(int number, @NonNull Space flat) {
        floor.addSpace(number, flat);
    }

    @Override
    public synchronized void removeSpace(int number) {
        floor.removeSpace(number);
    }

    @Override
    public synchronized Space getBestSpace() {
        return floor.getBestSpace();
    }

    @Override
    public Iterator<Space> iterator() {
        return floor.iterator();
    }

    @Override
    @SuppressWarnings("all")
    public synchronized Object clone() {
        return floor.clone();
    }

    @Override
    public synchronized int compareTo(Floor o) {
        return floor.compareTo(o);
    }

    @Override
    public synchronized int hashCode() {
        return floor.hashCode();
    }

    @Override
    @SuppressWarnings("all")
    public synchronized boolean equals(Object obj) {
        return floor.equals(obj);
    }

    @Override
    public synchronized String toString() {
        return floor.toString();
    }
}
