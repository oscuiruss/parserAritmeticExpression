
package expression.generic;

import expression.operations.ToMiniString;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public interface TripleExpression<T> extends ToMiniString {
    T evaluate(T x, T y, T z);
}
