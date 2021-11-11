package model.buildings.dwelling.hotel;

import lombok.NonNull;
import model.buildings.Space;
import model.buildings.dwelling.DwellingFloor;
import model.utilities.IterableArray;
import model.utilities.SomeBuildingUtilities;

import java.util.Arrays;
import java.util.Objects;

public class HotelFloor extends DwellingFloor {
    private static final int SUPER_SECRET_HASH_CODE_VARIABLE = 228;
    private static final int DEFAULT_STARS = 1;
    @NonNull
    private int stars;

    public HotelFloor(@NonNull Space[] spaces) {
        super(spaces);
        this.stars = DEFAULT_STARS;
    }

    public HotelFloor(int flatsCount) {
        super(flatsCount);
        this.stars = DEFAULT_STARS;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        if (stars < 1 || stars > 5) throw new IllegalArgumentException("wrong stars count");
        this.stars = stars;
    }

    @Override
    public String toString() {
        return SomeBuildingUtilities.toString("Hotel Floor (" + stars + ", ", new IterableArray<>(spaces()), size());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof HotelFloor)) return false;
        if (!super.equals(o)) return false;
        HotelFloor spaces = (HotelFloor) o;
        return getStars() == spaces.getStars() && Arrays.equals(spaces.spaces(), this.spaces());
    }

    @Override
    public int hashCode() {
        return Objects.hash(SUPER_SECRET_HASH_CODE_VARIABLE, super.hashCode(), getStars());
    }
}
