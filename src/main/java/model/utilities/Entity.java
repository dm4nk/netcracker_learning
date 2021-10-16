package model.utilities;

public class Entity<_Floor, _Number> {
    public _Floor floor;
    public _Number number;

    public Entity(_Floor floor, _Number number) {
        this.floor = floor;
        this.number = number;
    }
}
