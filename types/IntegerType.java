package expression.types;

import expression.exceptions.DividedByZero;
import expression.exceptions.ParseException;

public class IntegerType implements NumType<Integer> {
    @Override
    public Integer add(Integer first, Integer second) {
        return first + second;
    }

    @Override
    public Integer subtract(Integer first, Integer second) {
        return first - second;
    }

    @Override
    public Integer divide(Integer first, Integer second) {
        if (second == 0) {
            throw new DividedByZero();
        }
        return first / second;
    }

    @Override
    public Integer multiply(Integer first, Integer second) {
        return first * second;
    }

    @Override
    public Integer unaryMinus(Integer x) {
        return -x;
    }

    @Override
    public Integer parse(String num) {
        try {
            return Integer.parseInt(num);
        } catch (NumberFormatException e) {
            throw new ParseException("Incorrect number");
        }
    }

}

