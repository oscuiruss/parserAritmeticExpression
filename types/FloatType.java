package expression.types;

import expression.exceptions.ParseException;

public class FloatType implements NumType<Float>{
    @Override
    public Float add(Float first, Float second) {
        return first + second;
    }

    @Override
    public Float subtract(Float first, Float second) {
        return first - second;
    }

    @Override
    public Float divide(Float first, Float second) {
        return first / second;
    }

    @Override
    public Float multiply(Float first, Float second) {
        return first * second;
    }

    @Override
    public Float unaryMinus(Float num) {
        return -num;
    }

    public Float parse(String num) {
        try {
            return Float.parseFloat(num);
        } catch (NumberFormatException e) {
            throw new ParseException("Incorrect number");
        }
    }
}
