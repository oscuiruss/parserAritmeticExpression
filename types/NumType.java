package expression.types;

public interface NumType<T extends Number> {
    T add(T first, T second);

    T subtract(T first, T second);

    T divide(T first, T second);

    T multiply(T first, T second);

    T unaryMinus(T num);

    T parse(String expr);

}
