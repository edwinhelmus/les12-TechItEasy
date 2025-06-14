package nl.novi.TechItEasy.exceptions;

public class InvalidTitle extends RuntimeException {

    public InvalidTitle() {
        super();
    }

    public InvalidTitle(String title) {
        super(title);
    }
}
