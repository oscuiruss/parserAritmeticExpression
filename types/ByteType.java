package expression.types;

import expression.exceptions.DividedByZero;
import expression.exceptions.ParseException;

public class ByteType implements NumType<Byte> {
    @Override
    public Byte add(Byte first, Byte second) {
        return (byte) (first + second);
    }

    @Override
    public Byte subtract(Byte first, Byte second) {
        return (byte) (first - second);
    }

    @Override
    public Byte divide(Byte first, Byte second) {
        if (second == 0) {
            throw new DividedByZero();
        }
        return (byte) (first / second);
    }

    @Override
    public Byte multiply(Byte first, Byte second) {
        return (byte) (first * second);
    }

    @Override
    public Byte unaryMinus(Byte num) {
        return (byte) -num;
    }

    @Override
    public Byte parse(String num) {
        try {
            int a = Integer.parseInt(num);
            return (byte) a;
        } catch (NumberFormatException e) {
            throw new ParseException("Incorrect number" + num);
        }
    }
}
