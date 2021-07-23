package expression.exceptions;

public class VarOverflow extends RuntimeException {
    public VarOverflow() {
        super("Overflow of the variable");
    }
}
