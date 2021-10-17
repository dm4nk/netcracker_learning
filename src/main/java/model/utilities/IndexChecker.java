package model.utilities;

import lombok.NonNull;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * Checks if index is valid and throws exception
 */
public abstract class IndexChecker {
    /**
     * number < 0 || number > border
     */
    public static <T extends IndexOutOfBoundsException> void checkIfNumberIsValid(int number, int border, @NonNull Class<T> c) throws IndexOutOfBoundsException {
        try {
            if (number < 0 || number > border) {
                Constructor<?> constructor = c.getConstructor(String.class);
                throw (IndexOutOfBoundsException) constructor.newInstance("index: " + number + ", bounds: 0, " + border);
            }
        } catch (NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
