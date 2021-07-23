package expression.operations;

import expression.generic.TripleExpression;
import expression.types.NumType;

import java.util.Objects;

public abstract class MathOperation<T extends Number> implements TripleExpression<T> {
    private TripleExpression<T> left;
    private TripleExpression<T> right;
    protected NumType<T> type;

    public MathOperation(TripleExpression<T> left, TripleExpression<T> right, NumType<T> type) {
        this.left = left;
        this.right = right;
        this.type = type;
    }

    abstract public T calculate(T x, T y);

    public T evaluate(T x, T y, T z) {
        return calculate(left.evaluate(x, y, z), right.evaluate(x, y, z));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        MathOperation that = (MathOperation) o;
        return Objects.equals(left, that.left) &&
                Objects.equals(right, that.right);
    }

    @Override
    public int hashCode() {
        return Objects.hash(left, right, type);
    }


}
