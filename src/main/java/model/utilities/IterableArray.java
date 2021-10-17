package model.utilities;

import lombok.NonNull;

import java.util.Iterator;


public final class IterableArray<T> implements Iterable<T> {
    @NonNull
    private final T[] array;

    public IterableArray(T[] array) {
        this.array = array;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            int cur = 0;

            @Override
            public boolean hasNext() {
                return cur != array.length;
            }

            @Override
            public T next() {
                return array[cur++];
            }
        };
    }
}
