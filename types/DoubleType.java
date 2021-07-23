package expression.types;

import expression.exceptions.ParseException;

public class DoubleType implements NumType<Double> {
    @Override
    public Double add(Double first, Double second) {
        return first + second;
    }

    @Override
    public Double subtract(Double first, Double second) {
        return first - second;
    }

    @Override
    public Double divide(Double first, Double second) {
//        if (second < 1e-10 && second > -1e-10) {
//            throw new DividedByZero();
//        }
        return first / second;
    }

    @Override
    public Double multiply(Double first, Double second) {
        return first * second;
    }

    @Override
    public Double unaryMinus(Double num) {
        return -num;
    }

    @Override
    public Double parse(String num) {
        try {
            return Double.parseDouble(num);
        } catch (NumberFormatException e) {
            throw new ParseException("Incorrect number");
        }
    }

}
