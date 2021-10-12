package model.exeptions;

public class InvalidSpaceAreaException extends IllegalArgumentException {
    public InvalidSpaceAreaException() {
        super();
    }

    public InvalidSpaceAreaException(String s) {
        super(s);
    }
}
