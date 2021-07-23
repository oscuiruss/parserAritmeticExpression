package expression.operations;

import expression.generic.TripleExpression;
import expression.types.NumType;

public abstract class UnaryOperation<T extends Number> implements TripleExpression<T> {
    private final TripleExpression<T> expression;
    protected NumType<T> type;
    public UnaryOperation(TripleExpression<T> expression, NumType<T> type) {
        this.expression = expression;
        this.type = type;
    }

    public abstract T calculate(T x);

    @Override
    public T evaluate(T x, T y, T z) {
        return calculate(expression.evaluate(x,y,z));
    }

}
