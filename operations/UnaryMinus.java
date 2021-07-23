package expression.operations;

import expression.generic.TripleExpression;
import expression.types.NumType;


public class UnaryMinus<T extends Number> extends UnaryOperation<T> {
    public UnaryMinus(TripleExpression<T> expression, NumType<T> type) {
        super(expression, type);
    }

    @Override
    public T calculate(T x) {
        return type.unaryMinus(x);
    }

}
