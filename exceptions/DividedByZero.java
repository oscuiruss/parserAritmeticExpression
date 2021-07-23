package expression.exceptions;

public class DividedByZero extends RuntimeException {
    public DividedByZero() {
        super("Divide by 0");
    }
}
