package expression.operations;

import expression.generic.TripleExpression;
import expression.types.NumType;

public class Multiply<T extends Number> extends MathOperation<T> { ;

    public Multiply(TripleExpression<T> left, TripleExpression<T> right, NumType<T> type) {
        super(left, right, type);
    }

    @Override
    public T calculate(T x, T y) {
        return type.multiply(x, y);
    }
}

