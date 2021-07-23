package expression.operations;

import expression.generic.TripleExpression;

public class Variable<T> implements TripleExpression<T> {
    private String num;

    public Variable(String num) {
        this.num = num;
    }

    @Override
    public T evaluate(T x, T y, T z) {
        if (num.equals("x")) {
            return x;
        } else if (num.equals("y")) {
            return y;
        } else {
            return z;
        }
    }

}