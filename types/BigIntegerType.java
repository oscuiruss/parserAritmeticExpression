package expression.types;

import expression.exceptions.DividedByZero;
import expression.exceptions.ParseException;

import java.math.BigInteger;

public class BigIntegerType implements NumType<BigInteger> {
    @Override
    public BigInteger add(BigInteger first, BigInteger second) {
        return first.add(second);
    }

    @Override
    public BigInteger subtract(BigInteger first, BigInteger second) {
        return first.subtract(second);
    }

    @Override
    public BigInteger divide(BigInteger first, BigInteger second) {
        if (second.compareTo(BigInteger.ZERO) == 0) {
            throw new DividedByZero();
        }
        return first.divide(second);
    }

    @Override
    public BigInteger multiply(BigInteger first, BigInteger second) {
        return first.multiply(second);
    }

    @Override
    public BigInteger unaryMinus(BigInteger num) {
        return num.negate();
    }

    @Override
    public BigInteger parse(String num) {
        try {
            return new BigInteger(num);
        } catch (NumberFormatException e) {
            throw new ParseException("Incorrect number");
        }
    }

}
