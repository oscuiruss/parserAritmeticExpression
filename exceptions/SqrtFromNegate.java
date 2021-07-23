package expression.exceptions;

public class SqrtFromNegate extends RuntimeException {
    public SqrtFromNegate() {
        super("Sqrt from <= 0");
    }
}
