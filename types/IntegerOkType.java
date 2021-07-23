package expression.types;

import expression.exceptions.DividedByZero;
import expression.exceptions.ParseException;
import expression.exceptions.VarOverflow;

public class IntegerOkType implements NumType<Integer> {
    @Override
    public Integer add(Integer first, Integer second) {
        if (first >= 0 && second > Integer.MAX_VALUE - first
                || first < 0 && second < Integer.MIN_VALUE - first) {
            throw new VarOverflow();
        }
        return first + second;
    }

    @Override
    public Integer subtract(Integer first, Integer second) {
        if ((first >= 0 && second < first - Integer.MAX_VALUE)
                || (first < 0 && second > first - Integer.MIN_VALUE)) {
            throw new VarOverflow();
        }
        return first - second;
    }

    @Override
    public Integer divide(Integer first, Integer second) {
        if (second == 0) {
            throw new DividedByZero();
        } else if (first == Integer.MIN_VALUE && second == -1) {
            throw new VarOverflow();
        }
        return first / second;
    }

    @Override
    public Integer multiply(Integer first, Integer second) {
        if (first > 0 && second > 0 && first > Integer.MAX_VALUE / second ||
                first > 0 && second < 0 && second < Integer.MIN_VALUE / first ||
                first < 0 && second > 0 && first < Integer.MIN_VALUE / second ||
                first < 0 && second < 0 && first < Integer.MAX_VALUE / second) {
            throw new VarOverflow();
        }
        return first * second;
    }

    @Override
    public Integer unaryMinus(Integer x) {
        if (x == Integer.MIN_VALUE){
            throw new VarOverflow();
        }
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
